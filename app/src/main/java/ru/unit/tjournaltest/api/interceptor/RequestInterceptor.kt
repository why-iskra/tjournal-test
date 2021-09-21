package ru.unit.tjournaltest.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.unit.tjournaltest.other.SharedPreferencesHelper

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            val request = request()
                .newBuilder()
                .addHeader("user-agent", "Android V1")
                .addHeader("x-device-token", SharedPreferencesHelper.instance.xDeviceToken ?: "")
                .build()

            proceed(request)
        }
    }
}
