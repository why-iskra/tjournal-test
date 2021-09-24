package ru.unit.tjournaltest.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.R
import ru.unit.tjournaltest.databinding.FragmentAccountBinding
import ru.unit.tjournaltest.other.RoundCornersTransform
import ru.unit.tjournaltest.viewmodel.AccountViewModel

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account) {

    private val model: AccountViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (model.isAuthorized()) {
            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<TextView>(R.id.textViewTitle)?.text = getString(R.string.account)

        val binding = FragmentAccountBinding.bind(view)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.userMeFlow.collect {
                    if (it != null) {
                        binding.swipeRefreshLayout.isRefreshing = false

                        if (it.success) {
                            it.result?.let { it2 ->
                                binding.textViewName.text = it2.name
                                binding.textViewKarma.text = it2.karma.toString()
                                Picasso
                                    .get()
                                    .load(it2.avatarUrl)
                                    .transform(RoundCornersTransform(16f))
                                    .into(binding.imageViewAvatar)
                            }
                        }
                    }
                }
                model.stateFlow.collect {
                    if (it == AccountViewModel.State.FAIL) {
                        Toast.makeText(requireContext(), getString(R.string.fail), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            model.refresh()
        }

        model.loadUserMe()
    }

}