package ru.unit.tjournaltest.adapter.viewholder

import com.google.android.exoplayer2.SimpleExoPlayer
import ru.unit.tjournaltest.databinding.RecyclerTimelineItemBinding
import ru.unit.tjournaltest.databinding.RecyclerTimelineVideoItemBinding

class TimelineVideoViewHolder(
    val bindingVideo: RecyclerTimelineVideoItemBinding
) : TimelineViewHolder(RecyclerTimelineItemBinding.bind(bindingVideo.root)) {
    init {
        val player = SimpleExoPlayer.Builder(itemView.context).build()
        bindingVideo.videoView.player = player
        player.repeatMode = SimpleExoPlayer.REPEAT_MODE_ALL
        player.volume = 0f
        bindingVideo.videoView.useController = false
    }
}