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
        private const val PREFERENCES_NAME = "tj"

        const val XDEVICETOKEN_KEY = "xdevicetoken"

        const val AVATAR_URL_KEY = "avatar_url"
        const val NAME_KEY = "name"
        const val KARMA_KEY = "karma"
    }

    fun get(): SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    fun edit(): SharedPreferences.Editor = get().edit()
}