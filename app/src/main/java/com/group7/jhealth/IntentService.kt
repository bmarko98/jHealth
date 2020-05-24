package com.group7.jhealth

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyNewIntentService : IntentService("IntentService") {

    private lateinit var preferences: SharedPreferences

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        createNotificationChannel()
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
        builder.setContentTitle(getString(R.string.notification_title))
        preferences = this.getSharedPreferences(SHARED_PREF_FILE, AppCompatActivity.MODE_PRIVATE)

        if (preferences.getBoolean(KEY_PREF_IS_USR_TAKING_MED, false)) {
            Log.e("med notif. true", preferences.getBoolean(KEY_PREF_IS_USR_TAKING_MED, false).toString())
            builder.setContentText(getString(R.string.notification_message_med))
        }
        else {
            Log.e("med notif. false", preferences.getBoolean(KEY_PREF_IS_USR_TAKING_MED, false).toString())
            builder.setContentText(getString(R.string.notification_message))
        }

        builder.setSmallIcon(R.drawable.ic_workout_plan)
        val notifyIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)
        val notificationCompat: Notification = builder.build()
        val managerCompat = NotificationManagerCompat.from(this)
        managerCompat.notify(NOTIFICATION_ID, notificationCompat)
    }
}