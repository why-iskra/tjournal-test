package ru.unit.tjournaltest.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.R
import ru.unit.tjournaltest.data.socket.SocketState
import ru.unit.tjournaltest.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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
                    if (model.isAuthorized()) {
                        navigationController.navigate(R.id.accountFragment)
                    } else {
                        navigationController.navigate(R.id.loginFragment)
                    }
                }
            }

            true
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