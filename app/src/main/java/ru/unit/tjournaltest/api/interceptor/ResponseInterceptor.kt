package ru.unit.tjournaltest.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.unit.tjournaltest.other.SharedPreferencesHelper

class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            val response = proceed(request())
            if (response.headers().names().contains("x-device-token")) {
                SharedPreferencesHelper.instance.xDeviceToken = response.header("x-device-token")
            }

            response
        }
    }
}
