package ru.unit.tjournaltest.other

import android.content.Context

class SharedPreferencesHelper(
    private val context: Context
) {
    companion object {
        lateinit var instance: SharedPreferencesHelper // FIXME: static leak
        fun initialize(context: Context) {
            instance = SharedPreferencesHelper(context)
        }

        private const val PREFERENCES_NAME = "tj.main"

        private const val XDEVICETOKEN_KEY = "xdevicetoken"
    }

    private fun getPreferences() = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    private fun getEditPreferences() = getPreferences().edit()

    var xDeviceToken
        get() = getPreferences().getString(XDEVICETOKEN_KEY, "")
        set(value) = getEditPreferences().putString(XDEVICETOKEN_KEY, value).apply()
}