package ru.unit.tjournaltest.data.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import ru.unit.tjournaltest.data.AppMessageBroadcastActions
import javax.inject.Inject

class AppMessageBroadcastReceiver @Inject constructor() : BroadcastReceiver() {
    companion object {
        const val FIELD_MESSAGE = "message"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra(FIELD_MESSAGE) ?: return

        if (intent.action == AppMessageBroadcastActions.ACTION_RECEIVE_APP_NOTIFICATION) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}