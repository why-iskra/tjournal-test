package ru.unit.tjournaltest.adapter.viewholder

import android.view.View
import android.widget.TextView
import ru.unit.tjournaltest.R

open class TimelineTextViewHolder(itemView: View) : TimelineViewHolder(itemView) {
    var textView: TextView? = null

    init {
        // init views
        textView = itemView.findViewById(R.id.textView)
    }
}