package ru.unit.tjournaltest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ru.unit.tjournaltest.R
import ru.unit.tjournaltest.data.SharedPreferencesHelper
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navigationController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController

        navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.timelineFragment -> navigationController.navigate(R.id.timelineFragment)
                R.id.accountFragment -> {
                    if (sharedPreferencesHelper.xDeviceToken.isNullOrEmpty()) {
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