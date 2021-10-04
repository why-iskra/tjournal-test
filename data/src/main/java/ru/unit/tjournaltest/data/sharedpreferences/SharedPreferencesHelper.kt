package ru.unit.tjournaltest.data.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val PREFERENCES_NAME = "main"

        const val XDEVICETOKEN_KEY = "xdevicetoken"

        const val USER_ME_KEY = "user_me"
    }

    fun get(): SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    fun edit(): SharedPreferences.Editor = get().edit()
}