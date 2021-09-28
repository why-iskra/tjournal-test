package ru.unit.tjournaltest.adapter.viewholder

import ru.unit.tjournaltest.databinding.RecyclerTimelineItemBinding
import ru.unit.tjournaltest.databinding.RecyclerTimelineTextItemBinding

class TimelineTextViewHolder(
    val bindingText: RecyclerTimelineTextItemBinding
) : TimelineViewHolder(RecyclerTimelineItemBinding.bind(bindingText.root))