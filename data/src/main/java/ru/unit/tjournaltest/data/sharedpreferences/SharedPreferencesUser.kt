package ru.unit.tjournaltest.data.sharedpreferences

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesUser @Inject constructor(
    private val preferences: SharedPreferencesHelper
) {
    var avatarUrl
        get() = preferences.get().getString(SharedPreferencesHelper.AVATAR_URL_KEY, "")
        set(value) = preferences.edit().putString(SharedPreferencesHelper.AVATAR_URL_KEY, value).apply()

    var name
        get() = preferences.get().getString(SharedPreferencesHelper.NAME_KEY, "")
        set(value) = preferences.edit().putString(SharedPreferencesHelper.NAME_KEY, value).apply()

    var karma
        get() = preferences.get().getInt(SharedPreferencesHelper.KARMA_KEY, 0)
        set(value) = preferences.edit().putInt(SharedPreferencesHelper.KARMA_KEY, value).apply()

    fun clear() {
        preferences.edit()
            .remove(SharedPreferencesHelper.AVATAR_URL_KEY)
            .remove(SharedPreferencesHelper.NAME_KEY)
            .remove(SharedPreferencesHelper.KARMA_KEY)
            .apply()
    }
}