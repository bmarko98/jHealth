package com.group7.jhealth.activities

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.group7.jhealth.R
import com.group7.jhealth.*
import com.group7.jhealth.dialogs.DrinkCupSizeDialog
import kotlinx.android.synthetic.main.activity_water_intake.*

class WaterTrackerActivity : AppCompatActivity(), DrinkCupSizeDialog.DrinkCupSizeDialogListener {

    private val fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.WaterIntakeTheme)
        setContentView(R.layout.activity_water_intake)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(applicationContext, R.color.darkBlue)))
        supportActionBar?.setTitle(R.string.water_tracker)

        waterIntakeTargetBar.scaleY = WATER_INTAKE_TARGET_BAR_HEIGHT

        addDrinkingCupButton.setOnClickListener {
            val newFragment = DrinkCupSizeDialog()
            newFragment.show(fragmentManager, "")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.water_tracker_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.drinkLog -> Log.e("menu", "drink log") //water intake history
            R.id.drinkReport -> Log.e("menu", "drink report")   //graphs and averages and stuff like that
            R.id.waterTrackerSettings -> Log.e("menu", "settings")  //edit personal info regarding water reminder, notification and alarm settings
        }
        return super.onOptionsItemSelected(item)
    }

    override fun drinkCupSizeDialogListener(chosenSize: Int) {
        Log.e("chosen cup size", chosenSize.toString())
    }
}
