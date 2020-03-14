package com.group7.jhealth.activities

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.group7.jhealth.R
import com.group7.jhealth.*
import com.group7.jhealth.adapters.WaterIntakeHistoryRecyclerViewAdapter
import com.group7.jhealth.dialogs.DrinkCupSizeDialog
import kotlinx.android.synthetic.main.activity_water_intake.*
import java.util.*

interface OnIntakeLongClickListener {
    fun onLongClick(intake: WaterIntake)
}

class WaterTrackerActivity : AppCompatActivity(), DrinkCupSizeDialog.DrinkCupSizeDialogListener, OnIntakeLongClickListener {

    private val fragmentManager: FragmentManager = supportFragmentManager
    private lateinit var waterIntakeHistoryRecyclerViewAdapter: WaterIntakeHistoryRecyclerViewAdapter
    private lateinit var intakeHistory: MutableList<WaterIntake>
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.WaterIntakeTheme)
        setContentView(R.layout.activity_water_intake)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(applicationContext, R.color.darkBlue)))
        supportActionBar?.setTitle(R.string.water_tracker)

        waterIntakeTargetBar.scaleY = WATER_INTAKE_TARGET_BAR_HEIGHT

        intakeHistory = mutableListOf()
        layoutManager = GridLayoutManager(this, 4)
        waterIntakeHistoryRecyclerView.layoutManager = layoutManager
        waterIntakeHistoryRecyclerViewAdapter = WaterIntakeHistoryRecyclerViewAdapter(this)
        waterIntakeHistoryRecyclerView.adapter = waterIntakeHistoryRecyclerViewAdapter

        addDrinkingCupButton.setOnClickListener {
            val newFragment = DrinkCupSizeDialog()
            newFragment.show(fragmentManager, "")
        }

        addIntakeButton.setOnClickListener {
            intakeHistory.add(WaterIntake(250, Calendar.getInstance().getTime(), R.drawable.custom_cup_icon))
            waterIntakeHistoryRecyclerViewAdapter.updateData(intakeHistory)
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

    override fun onLongClick(intake: WaterIntake) {
        Log.e("long clicked", intake.intakeAmount.toString() + intake.time)
        //Display edit dialog. Maybe give user option to remove an item. IDK
    }
}
