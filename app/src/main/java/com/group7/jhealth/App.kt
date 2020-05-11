package com.group7.jhealth

import android.app.Application
import android.app.IntentService
import android.content.Intent


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startService(Intent(this, IntentService::class.java))
    }
}