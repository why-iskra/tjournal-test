package ru.unit.tjournaltest.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.unit.tjournaltest.data.api.Headers
import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesAuth
import javax.inject.Inject

class ResponseInterceptor @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesAuth
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            val response = proceed(request())
            if (response.headers().names().contains(Headers.X_DEVICE_TOKEN_KEY)) {
                sharedPreferencesHelper.xDeviceToken = response.header(Headers.X_DEVICE_TOKEN_KEY)
            }

            response
        }
    }
}
