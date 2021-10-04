package ru.unit.tjournaltest.data.sharedpreferences

import com.google.gson.Gson
import ru.unit.tjournaltest.data.json.annotation.GsonSimple
import ru.unit.tjournaltest.domain.user.pojo.UserPOJO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesUser @Inject constructor(
    private val preferences: SharedPreferencesHelper,
    @GsonSimple private val gson: Gson
) {
    var userMe: UserPOJO?
        get() {
            val json = preferences.get().getString(SharedPreferencesHelper.USER_ME_KEY, "")
            if (json.isNullOrEmpty()) {
                return null
            } else {
                return gson.fromJson(json, UserPOJO::class.java)
            }
        }
        set(value) = preferences.edit().putString(SharedPreferencesHelper.USER_ME_KEY, gson.toJson(value)).apply()

    fun clear() {
        preferences.edit()
            .remove(SharedPreferencesHelper.USER_ME_KEY)
            .apply()
    }
}