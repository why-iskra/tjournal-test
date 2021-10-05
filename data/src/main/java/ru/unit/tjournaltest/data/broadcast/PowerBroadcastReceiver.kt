package ru.unit.tjournaltest.data.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import ru.unit.tjournaltest.data.R
import javax.inject.Inject

class PowerBroadcastReceiver @Inject constructor() : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent ?: return
        context ?: return

        if (intent.action == Intent.ACTION_POWER_CONNECTED) {
            Toast.makeText(context, R.string.power_connected_action_message, Toast.LENGTH_SHORT).show()
        } else if (intent.action == Intent.ACTION_POWER_DISCONNECTED) {
            Toast.makeText(context, R.string.power_disconnected_action_message, Toast.LENGTH_SHORT).show()
        }
    }
}