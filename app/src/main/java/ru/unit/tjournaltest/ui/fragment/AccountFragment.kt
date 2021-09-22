package ru.unit.tjournaltest.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.R
import ru.unit.tjournaltest.databinding.FragmentAccountBinding
import ru.unit.tjournaltest.other.DifferentUtils
import ru.unit.tjournaltest.other.RoundCornersTransform
import ru.unit.tjournaltest.other.SharedPreferencesHelper
import ru.unit.tjournaltest.viewmodel.AccountViewModel

class AccountFragment : Fragment(R.layout.fragment_account) {

    private val model: AccountViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (SharedPreferencesHelper.instance.xDeviceToken.isNullOrEmpty()) {
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
                                    .with(context)
                                    .load(DifferentUtils.apiGenImageUrl(it2.avatar.data.uuid))
                                    .transform(RoundCornersTransform(16f))
                                    .into(binding.imageViewAvatar)
                            }
                        }
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