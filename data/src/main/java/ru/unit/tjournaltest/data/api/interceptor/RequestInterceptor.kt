package ru.unit.tjournaltest.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.unit.tjournaltest.data.api.Headers
import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesAuth
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesAuth
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            val request = request()
                .newBuilder()
                .addHeader(Headers.USER_AGENT_KEY, Headers.USER_AGENT_VALUE)
                .addHeader(Headers.X_DEVICE_TOKEN_KEY, sharedPreferencesHelper.xDeviceToken ?: "")
                .build()

            proceed(request)
        }
    }
}
