package com.group7.jhealth.activities

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.group7.jhealth.R

class WaterTrackerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_intake)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(applicationContext,
            R.color.darkBlue)))

        supportActionBar?.setTitle(R.string.water_tracker)
    }
}
