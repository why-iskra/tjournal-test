package ru.unit.tjournaltest.adapter.viewholder

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import ru.unit.tjournaltest.databinding.RecyclerTimelineItemBinding
import ru.unit.tjournaltest.databinding.RecyclerTimelineYoutubeItemBinding

class TimelineYoutubeViewHolder(
    val bindingYoutube: RecyclerTimelineYoutubeItemBinding
) : TimelineViewHolder(RecyclerTimelineItemBinding.bind(bindingYoutube.root)) {
    private var youTubePlayer: YouTubePlayer? = null
    private var currentVideoId: String? = null

    init {
        bindingYoutube.youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@TimelineYoutubeViewHolder.youTubePlayer = youTubePlayer
                this@TimelineYoutubeViewHolder.youTubePlayer?.cueVideo(currentVideoId ?: "", 0f)
            }
        })
    }

    fun cueVideo(videoId: String) {
        currentVideoId = videoId
        youTubePlayer?.cueVideo(videoId, 0f)
    }
}