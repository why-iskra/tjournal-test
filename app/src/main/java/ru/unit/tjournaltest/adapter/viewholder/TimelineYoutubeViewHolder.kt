package ru.unit.tjournaltest.adapter.viewholder

import android.view.View
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import ru.unit.tjournaltest.R

class TimelineYoutubeViewHolder(itemView: View) : TimelineTextViewHolder(itemView) {
    var youtubeView: YouTubePlayerView? = null

    private var youTubePlayer: YouTubePlayer? = null
    private var currentVideoId: String? = null

    init {
        // init views
        youtubeView = itemView.findViewById(R.id.youtubeView)

        // init players
        youtubeView?.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
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