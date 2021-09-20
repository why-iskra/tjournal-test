package ru.unit.tjournaltest.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class XDeviceTokenCatcherInterceptor : Interceptor {

    var XDeviceToken: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            val request = request().newBuilder().apply {
                if (XDeviceToken != null) {
                    addHeader("x-device-token", XDeviceToken)
                }
            }.build()

            val response = proceed(request)
            if (response.headers().names().contains("x-device-token")) {
                XDeviceToken = response.header("x-device-token")
            }

            response
        }
    }
}
