package ru.unit.tjournaltest.adapter.viewholder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.unit.tjournaltest.R

open class TimelineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var context: Context? = null

    var imageViewIcon: ImageView? = null
    var textViewTheme: TextView? = null
    var textViewAuthor: TextView? = null
    var textViewTime: TextView? = null
    var textViewTitle: TextView? = null
    var textViewComments: TextView? = null
    var textViewRating: TextView? = null

    init {
        // init views
        context = itemView.context

        imageViewIcon = itemView.findViewById(R.id.imageViewIcon)
        textViewTheme = itemView.findViewById(R.id.textViewTheme)
        textViewAuthor = itemView.findViewById(R.id.textViewAuthor)
        textViewTime = itemView.findViewById(R.id.textViewTime)
        textViewTitle = itemView.findViewById(R.id.textViewTitle)
        textViewComments = itemView.findViewById(R.id.textViewComments)
        textViewRating = itemView.findViewById(R.id.textViewRating)
    }
}
