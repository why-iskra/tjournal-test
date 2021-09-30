package ru.unit.tjournaltest.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.R
import ru.unit.tjournaltest.data.json.annotation.GsonBeautiful
import ru.unit.tjournaltest.data.notification.MessageNotification
import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesAuth
import ru.unit.tjournaltest.data.socket.SocketState
import ru.unit.tjournaltest.viewmodel.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var authPreferences: SharedPreferencesAuth

    @Inject
    lateinit var messageNotification: MessageNotification

    @Inject
    @GsonBeautiful
    lateinit var gson: Gson

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navigationController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController

        navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.timelineFragment -> navigationController.navigate(R.id.timelineFragment)
                R.id.accountFragment -> {
                    if (authPreferences.xDeviceToken.isNullOrEmpty()) {
                        navigationController.navigate(R.id.loginFragment)
                    } else {
                        navigationController.navigate(R.id.accountFragment)
                    }
                }
            }

            true
        }

        lifecycleScope.launch {
            model.socketEventFlow.collectLatest {
                val result = model.getMessage(it)

                if (!(result.first.isEmpty() && result.second.isEmpty())) {
                    messageNotification.createNotification(result.first, result.second)
                }
            }
        }

        lifecycleScope.launch {
            model.socketStateFlow.collectLatest {
                if (it == SocketState.CONNECTION_ERROR || it == SocketState.EVENT_ERROR) {
                    toast(R.string.something_went_wrong)
                }
            }
        }
    }

    private fun toast(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}