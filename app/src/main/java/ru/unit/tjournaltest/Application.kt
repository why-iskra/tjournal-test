package ru.unit.tjournaltest

import android.app.Application
import ru.unit.tjournaltest.other.SharedPreferencesHelper
import ru.unit.tjournaltest.repository.RepositoryApiController

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        initInstance()
    }

    private fun initInstance() {
        RepositoryApiController.initialize()
        SharedPreferencesHelper.initialize(applicationContext)
    }
}