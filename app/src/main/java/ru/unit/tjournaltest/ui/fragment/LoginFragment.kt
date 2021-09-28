package ru.unit.tjournaltest.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.R
import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesAuth
import ru.unit.tjournaltest.databinding.FragmentLoginBinding
import ru.unit.tjournaltest.viewmodel.LoginViewModel
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var authPreferences: SharedPreferencesAuth

    private val model: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<TextView>(R.id.textViewTitle)?.text = getString(R.string.auth)

        val binding = FragmentLoginBinding.bind(view)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.stateFlow.collect {
                    if (it == LoginViewModel.State.LOADING) {
                        binding.progressBar.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.GONE
                        when (it) {
                            LoginViewModel.State.LOADED -> loginComplete()
                            LoginViewModel.State.ERROR_UNAUTHORIZED -> toast(R.string.login_unauthorized_error)
                            LoginViewModel.State.ERROR_UNKNOWN -> toast(R.string.login_unknown_error)
                            LoginViewModel.State.ERROR_INTERNAL -> toast(R.string.login_internal_error)
                        }
                    }
                }
            }
        }

        binding.loginButton.setOnClickListener {
            model.login(binding.editTextLogin.text.toString(), binding.editTextPassword.text.toString())
        }
    }

    private fun loginComplete() {
        if (model.isAuthorized()) {
            findNavController().navigate(R.id.action_loginFragment_to_accountFragment)
        }
    }

    private fun toast(@StringRes message: Int) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}