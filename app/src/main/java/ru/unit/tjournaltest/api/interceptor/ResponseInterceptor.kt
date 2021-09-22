package ru.unit.tjournaltest.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.unit.tjournaltest.api.Headers
import ru.unit.tjournaltest.other.SharedPreferencesHelper
import javax.inject.Inject

class ResponseInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

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
