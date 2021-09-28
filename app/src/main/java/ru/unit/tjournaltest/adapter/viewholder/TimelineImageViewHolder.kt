package ru.unit.tjournaltest.adapter.viewholder

import ru.unit.tjournaltest.databinding.RecyclerTimelineImageItemBinding
import ru.unit.tjournaltest.databinding.RecyclerTimelineItemBinding

class TimelineImageViewHolder(
    val bindingImage: RecyclerTimelineImageItemBinding
) : TimelineViewHolder(RecyclerTimelineItemBinding.bind(bindingImage.root))