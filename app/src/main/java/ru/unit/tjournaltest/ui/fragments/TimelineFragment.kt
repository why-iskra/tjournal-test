package ru.unit.tjournaltest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.unit.tjournaltest.adapters.TimelineAdapter
import ru.unit.tjournaltest.api.dto.TimelineItemDTO
import ru.unit.tjournaltest.databinding.FragmentTimelineBinding
import ru.unit.tjournaltest.datasource.TimelineDataSource
import ru.unit.tjournaltest.viewmodels.TimelineFragmentViewModel
import java.time.LocalDateTime
import java.util.concurrent.Executors


class TimelineFragment : Fragment() {

    private val model: TimelineFragmentViewModel by activityViewModels()

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(12)
        .build()

    private lateinit var pagedList: PagedList<TimelineItemDTO>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTimelineBinding.inflate(inflater, container, false)

        pagedList = PagedList.Builder(TimelineDataSource(), pagedListConfig)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(ContextCompat.getMainExecutor(context))
            .build()

        // setup recyclerView
        binding.recyclerViewTimeLine.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewTimeLine.overScrollMode = View.OVER_SCROLL_NEVER
        val adapter = TimelineAdapter(binding.recyclerViewTimeLine, LocalDateTime.now())
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                binding.swipeRefreshLayout.isRefreshing = false
                super.onItemRangeInserted(positionStart, itemCount)
            }
        })
        adapter.submitList(pagedList)
        binding.recyclerViewTimeLine.adapter = adapter

        // setup swipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener {
            model.reset()
            pagedList.detach()

            pagedList = PagedList.Builder(TimelineDataSource(), pagedListConfig)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setNotifyExecutor(ContextCompat.getMainExecutor(context))
                .build()
            adapter.submitList(pagedList)
        }

        // init refresh
        binding.swipeRefreshLayout.isRefreshing = true

        return binding.root
    }
}


