package ru.unit.tjournaltest.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class UserAgentInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            proceed(
                request()
                    .newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", "Android V1")
                    .build()
            )
        }
    }
}