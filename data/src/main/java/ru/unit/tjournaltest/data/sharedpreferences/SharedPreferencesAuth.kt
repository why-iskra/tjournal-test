package ru.unit.tjournaltest.data.sharedpreferences

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesAuth @Inject constructor(
    private val preferences: SharedPreferencesHelper
) {
    var xDeviceToken
        get() = preferences.get().getString(SharedPreferencesHelper.XDEVICETOKEN_KEY, "")
        set(value) = preferences.edit().putString(SharedPreferencesHelper.XDEVICETOKEN_KEY, value).apply()
}