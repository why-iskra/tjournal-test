package ru.unit.tjournaltest.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.R
import ru.unit.tjournaltest.SharedPreferencesKeys
import ru.unit.tjournaltest.databinding.FragmentLoginBinding
import ru.unit.tjournaltest.repository.RepositoryApiController
import ru.unit.tjournaltest.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private val model: LoginViewModel by activityViewModels()

    @SuppressLint("ShowToast")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.resultFlow.collect {
                    if (it != null) {
                        if (it == LoginViewModel.LoginError.NON) {
                            activity?.run {
                                getPreferences(Context.MODE_PRIVATE)
                                    .edit()
                                    .putString(SharedPreferencesKeys.xDeviceToken, RepositoryApiController.apiV1.getXDeviceToken())
                                    .apply()
                            }

                            findNavController().navigate(R.id.action_loginFragment_to_accountFragment)
                        } else {
                            Toast.makeText(
                                requireContext(), when (it) {
                                    LoginViewModel.LoginError.INTERNAL -> R.string.login_internal_error
                                    LoginViewModel.LoginError.UNAUTHORIZED -> R.string.login_unauthorized_error
                                    else -> R.string.login_unknown_error
                                }, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        binding.loginButton.setOnClickListener {
            model.login(binding.editTextLogin.text.toString(), binding.editTextPassword.text.toString())
        }

        return binding.root
    }

}