package com.group7.jhealth.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.group7.jhealth.R
import com.group7.jhealth.WATER_INTAKE_TARGET_BAR_HEIGHT
import com.group7.jhealth.WaterIntake
import com.group7.jhealth.adapters.WaterIntakeHistoryRecyclerViewAdapter
import com.group7.jhealth.dialogs.DrinkCupSizeDialog
import kotlinx.android.synthetic.main.fragment_water_intake.*
import java.util.*

interface OnIntakeLongClickListener {
    fun onLongClick(intake: WaterIntake)
}

class WaterTrackerFragment : Fragment(), DrinkCupSizeDialog.DrinkCupSizeDialogListener, OnIntakeLongClickListener {

    //private val fragmentManager: FragmentManager = supportFragmentManager
    private lateinit var waterIntakeHistoryRecyclerViewAdapter: WaterIntakeHistoryRecyclerViewAdapter
    private lateinit var intakeHistory: MutableList<WaterIntake>
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_water_intake, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        waterIntakeTargetBar.scaleY = WATER_INTAKE_TARGET_BAR_HEIGHT

        intakeHistory = mutableListOf()
        layoutManager = GridLayoutManager(context, 4)
        waterIntakeHistoryRecyclerView.layoutManager = layoutManager
        waterIntakeHistoryRecyclerViewAdapter = WaterIntakeHistoryRecyclerViewAdapter(context!!)
        waterIntakeHistoryRecyclerView.adapter = waterIntakeHistoryRecyclerViewAdapter

        //TODO: Use interface and implement it in the activity.
        addDrinkingCupButton.setOnClickListener {
            //val newFragment = DrinkCupSizeDialog()
            //newFragment.show(fragmentManager, "")
        }

        addIntakeButton.setOnClickListener {
            intakeHistory.add(WaterIntake(250, Calendar.getInstance().time, R.drawable.custom_cup_icon))
            waterIntakeHistoryRecyclerViewAdapter.updateData(intakeHistory)
        }
    }

/*
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
*/
    override fun drinkCupSizeDialogListener(chosenSize: Int) {
        Log.e("chosen cup size", chosenSize.toString())
    }

    override fun onLongClick(intake: WaterIntake) {
        Log.e("long clicked", intake.intakeAmount.toString() + intake.time)
    }

}
