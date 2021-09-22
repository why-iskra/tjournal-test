package ru.unit.tjournaltest.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.unit.tjournaltest.R
import ru.unit.tjournaltest.adapter.TimelineAdapter
import ru.unit.tjournaltest.databinding.FragmentTimelineBinding
import ru.unit.tjournaltest.viewmodel.TimelineViewModel
import java.time.LocalDateTime

@AndroidEntryPoint
class TimelineFragment : Fragment(R.layout.fragment_timeline) {

    private val model: TimelineViewModel by activityViewModels()

    private lateinit var adapter: TimelineAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<TextView>(R.id.textViewTitle)?.text = getString(R.string.video_and_gifs)

        val binding = FragmentTimelineBinding.bind(view)

        // setup recyclerView
        binding.recyclerViewTimeLine.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewTimeLine.overScrollMode = View.OVER_SCROLL_NEVER
        if (!this::adapter.isInitialized) {
            adapter = TimelineAdapter(binding.recyclerViewTimeLine, LocalDateTime.now())
        }
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW

        binding.recyclerViewTimeLine.adapter = adapter

        // setup swipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener {
            model.reset()
            adapter.refresh()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            model.timelineItemsFlow.collectLatest { adapter.submitData(it) }
        }
    }
}


