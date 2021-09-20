package ru.unit.tjournaltest.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.unit.tjournaltest.R
import ru.unit.tjournaltest.SharedPreferencesKeys
import ru.unit.tjournaltest.repository.RepositoryApiController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = getPreferences(Context.MODE_PRIVATE).getString(SharedPreferencesKeys.xDeviceToken, "")

        if (!token.isNullOrEmpty()) {
            RepositoryApiController.apiV1.setXDeviceToken(token)
        }

        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navigationController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController

        navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.timelineFragment -> navigationController.navigate(R.id.timelineFragment)
                R.id.accountFragment -> {
                    if (RepositoryApiController.apiV1.getXDeviceToken().isNullOrEmpty()) {
                        navigationController.navigate(R.id.loginFragment)
                    } else {
                        navigationController.navigate(R.id.accountFragment)
                    }
                }
            }

            true
        }

    }

}