package com.group7.jhealth.activities

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.group7.jhealth.R
import com.group7.jhealth.*
import kotlinx.android.synthetic.main.activity_water_intake.*

class WaterTrackerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.WaterIntakeTheme)
        setContentView(R.layout.activity_water_intake)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(applicationContext, R.color.darkBlue)))
        supportActionBar?.setTitle(R.string.water_tracker)

        waterIntakeTargetBar.scaleY = WATER_INTAKE_TARGET_BAR_HEIGHT
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.water_tracker_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.drinkLog -> Log.e("menu", "drink log")
            R.id.drinkReport -> Log.e("menu", "drink report")
            R.id.waterTrackerSettings -> Log.e("menu", "settings")
        }
        return super.onOptionsItemSelected(item)
    }
}
