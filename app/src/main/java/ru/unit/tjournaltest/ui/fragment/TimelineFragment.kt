package ru.unit.tjournaltest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import ru.unit.tjournaltest.adapter.TimelineAdapter
import ru.unit.tjournaltest.databinding.FragmentTimelineBinding
import ru.unit.tjournaltest.viewmodel.TimelineViewModel
import java.time.LocalDateTime


class TimelineFragment : Fragment() {

    private val model: TimelineViewModel by activityViewModels()

    private lateinit var adapter: TimelineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTimelineBinding.inflate(inflater, container, false)

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            model.timelineItemsFlow.collectLatest { adapter.submitData(it) }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}


