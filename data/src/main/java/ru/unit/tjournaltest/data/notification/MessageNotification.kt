package ru.unit.tjournaltest.data.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.unit.tjournaltest.data.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageNotification @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val MESSAGE_CHANNEL_ID = "tj_message_channel"
        private const val MESSAGE_NOTIFICATION_ID = 101
    }

    init {
        createNotificationChannel()
    }

    fun createNotification(title: String, message: String) {
        val notification = NotificationCompat.Builder(context, MESSAGE_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_round_email_24)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(MESSAGE_NOTIFICATION_ID, notification)
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.message_channel_name)
            val descriptionText = context.getString(R.string.message_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(MESSAGE_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}