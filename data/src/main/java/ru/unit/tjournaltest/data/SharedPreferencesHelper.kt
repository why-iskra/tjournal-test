package ru.unit.tjournaltest.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val PREFERENCES_NAME = "tj.main"

        private const val XDEVICETOKEN_KEY = "xdevicetoken"
    }

    private fun getPreferences() = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    private fun getEditPreferences() = getPreferences().edit()

    var xDeviceToken
        get() = getPreferences().getString(XDEVICETOKEN_KEY, "")
        set(value) = getEditPreferences().putString(XDEVICETOKEN_KEY, value).apply()
}