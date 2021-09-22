package ru.unit.tjournaltest.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.unit.tjournaltest.api.Headers
import ru.unit.tjournaltest.other.SharedPreferencesHelper

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            val request = request()
                .newBuilder()
                .addHeader(Headers.USER_AGENT_KEY, Headers.USER_AGENT_VALUE)
                .addHeader(Headers.X_DEVICE_TOKEN_KEY, SharedPreferencesHelper.instance.xDeviceToken ?: "")
                .build()

            proceed(request)
        }
    }
}
