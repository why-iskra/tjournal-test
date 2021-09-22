package ru.unit.tjournaltest.adapter.viewholder

import android.view.View
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import ru.unit.tjournaltest.R

class TimelineVideoViewHolder(itemView: View) : TimelineViewHolder(itemView) {
    var videoView: StyledPlayerView? = null

    init {
        // init views
        videoView = itemView.findViewById(R.id.videoView)

        val player = SimpleExoPlayer.Builder(itemView.context).build()
        videoView?.player = player
        player.repeatMode = SimpleExoPlayer.REPEAT_MODE_ALL
        player.volume = 0f
        videoView?.useController = false
    }
}