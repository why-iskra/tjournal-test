package ru.unit.tjournaltest.adapter.viewholder

import android.view.View
import android.widget.ImageView
import ru.unit.tjournaltest.R

class TimelineImageViewHolder(itemView: View) : TimelineViewHolder(itemView) {
    var imageView: ImageView? = null

    init {
        // init views
        imageView = itemView.findViewById(R.id.imageView)
    }
}