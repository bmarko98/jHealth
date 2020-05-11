package com.group7.jhealth

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class BroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val intent1 = Intent(context, MyNewIntentService::class.java)
        context.startService(intent1)
    }
}