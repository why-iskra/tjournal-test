package ru.unit.tjournaltest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.MediaItem
import com.squareup.picasso.Picasso
import ru.unit.tjournaltest.R
import ru.unit.tjournaltest.adapter.viewholder.*
import ru.unit.tjournaltest.data.api.DifferentUtils
import ru.unit.tjournaltest.domain.timeline.pojo.TimelineItemPOJO
import ru.unit.tjournaltest.domain.timeline.pojo.TimelineTypeImagePOJO
import ru.unit.tjournaltest.domain.timeline.pojo.TimelineTypeTextPOJO
import ru.unit.tjournaltest.domain.timeline.pojo.TimelineTypeVideoPOJO
import ru.unit.tjournaltest.other.RoundCornersTransform
import ru.unit.tjournaltest.other.dateCountdown
import ru.unit.tjournaltest.other.humanNumber
import java.time.LocalDateTime
import java.time.ZoneOffset

class TimelinePreloadAdapter(
    recyclerView: RecyclerView,
    private val items: List<TimelineItemPOJO>,
    private var currentDate: LocalDateTime,
) : RecyclerView.Adapter<TimelineViewHolder>() {

    companion object {
        private const val VIEW_TYPE_EMPTY_ITEM = 0
        private const val VIEW_TYPE_TEXT_ITEM = 1
        private const val VIEW_TYPE_IMAGE_ITEM = 2
        private const val VIEW_TYPE_VIDEO_ITEM = 3
        private const val VIEW_TYPE_YOUTUBE_ITEM = 4
    }

    init {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = linearLayoutManager.itemCount
                val firstCompletelyVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition()

                // play only completely visible player
                for (i in 0 until totalItemCount) {
                    val viewHolder =
                        recyclerView.findViewHolderForAdapterPosition(i) as? TimelineViewHolder
                    if (viewHolder is TimelineVideoViewHolder) {
                        viewHolder.videoView?.player?.playWhenReady =
                            firstCompletelyVisibleItem == i
                    }
                }
            }
        })
    }

    override fun onViewRecycled(holder: TimelineViewHolder) {
        if (holder is TimelineVideoViewHolder) {
            holder.videoView?.player?.stop() // stop player before recycling
        }

        super.onViewRecycled(holder)
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]

        return when (item.cover?.type) {
            "text" -> VIEW_TYPE_TEXT_ITEM
            "image", "media" -> {
                if (item.cover?.image?.type == "gif") {
                    VIEW_TYPE_VIDEO_ITEM
                } else {
                    VIEW_TYPE_IMAGE_ITEM
                }
            }
            "video" -> {
                if ((item.cover?.video?.externalService?.name ?: "") == "youtube") {
                    VIEW_TYPE_YOUTUBE_ITEM
                } else {
                    VIEW_TYPE_VIDEO_ITEM
                }
            }
            else -> VIEW_TYPE_EMPTY_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        // if plug then use recycler_load_indicator_item, else recycler_timeline_item
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                when (viewType) {
                    VIEW_TYPE_TEXT_ITEM -> R.layout.recycler_timeline_text_item
                    VIEW_TYPE_IMAGE_ITEM -> R.layout.recycler_timeline_image_item
                    VIEW_TYPE_VIDEO_ITEM -> R.layout.recycler_timeline_video_item
                    VIEW_TYPE_YOUTUBE_ITEM -> R.layout.recycler_timeline_youtube_item
                    else -> R.layout.recycler_timeline_item
                },
                parent,
                false
            )
        return when (viewType) {
            VIEW_TYPE_TEXT_ITEM -> TimelineTextViewHolder(itemView)
            VIEW_TYPE_IMAGE_ITEM -> TimelineImageViewHolder(itemView)
            VIEW_TYPE_VIDEO_ITEM -> TimelineVideoViewHolder(itemView)
            VIEW_TYPE_YOUTUBE_ITEM -> TimelineYoutubeViewHolder(itemView)
            else -> TimelineViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        val item = items[position]

        // setup views
        holder.textViewAuthor?.text = item.authorName
        holder.textViewTheme?.text = item.subsiteName
        holder.textViewComments?.text = humanNumber(item.comments)
        holder.textViewRating?.text = humanNumber(item.rating)
        holder.textViewTime?.text =
            holder.context?.let { dateCountdown(it, LocalDateTime.ofEpochSecond(item.date, 0, ZoneOffset.UTC), currentDate) }

        holder.textViewTitle?.visibility =
            if (item.title.isNullOrEmpty()) View.GONE else View.VISIBLE
        holder.textViewTitle?.text = item.title

        // setup cover
        when (holder) {
            is TimelineImageViewHolder -> setupImageCover(holder, item.cover?.image)
            is TimelineVideoViewHolder -> setupVideoImageCover(holder, item.cover?.image)
            is TimelineYoutubeViewHolder -> setupVideoYouTubeCover(holder, item.cover?.video)
            is TimelineTextViewHolder -> setupTextCover(holder, item.cover?.text)
        }

        // draw subsite icon
        item.avatar?.let {
            Picasso
                .get()
                .load(DifferentUtils.apiGenImageRectUrl(it.uuid, 64))
                .transform(RoundCornersTransform(16f))
                .into(holder.imageViewIcon)
        }
    }

    private fun setupTextCover(holder: TimelineTextViewHolder, cover: TimelineTypeTextPOJO?) {
        if (cover == null) return
        holder.textView?.text = cover.text
    }

    private fun setupImageCover(holder: TimelineImageViewHolder, cover: TimelineTypeImagePOJO?) {
        if (cover == null) return

        // draw picture
        Picasso
            .get()
            .load(DifferentUtils.apiGenImageUrl(cover.uuid))
            .into(holder.imageView)
    }

    private fun setupVideoImageCover(
        holder: TimelineVideoViewHolder,
        cover: TimelineTypeImagePOJO?
    ) {
        if (cover == null) return

        // setup player
        val mediaItem = MediaItem.fromUri(DifferentUtils.apiGenImageGifMP4Url(cover.uuid))
        val player = holder.videoView?.player
        player?.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    private fun setupVideoYouTubeCover(
        holder: TimelineYoutubeViewHolder,
        cover: TimelineTypeVideoPOJO?
    ) {
        if (cover == null) return

        if (cover.title.isNullOrEmpty()) {
            holder.textView?.visibility = View.GONE
        } else {
            holder.textView?.visibility = View.VISIBLE
            holder.textView?.text = cover.title
        }

        // setup youtube player
        holder.cueVideo(cover.externalService.id)
    }

    override fun getItemCount() = items.size
}
