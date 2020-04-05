package com.group7.jhealth.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.group7.jhealth.R
import com.group7.jhealth.WATER_INTAKE_TARGET_BAR_HEIGHT
import com.group7.jhealth.database.WaterIntake
import com.group7.jhealth.adapters.WaterIntakeHistoryRecyclerViewAdapter
import com.group7.jhealth.database.UserInfo
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_water_intake.*
import java.util.*

interface OnIntakeLongClickListener {
    fun onLongClickWaterIntakeRecyclerViewItem(intake: WaterIntake)
}

class WaterTrackerFragment : Fragment() {

    private lateinit var waterIntakeHistoryRecyclerViewAdapter: WaterIntakeHistoryRecyclerViewAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var listener: WaterTrackerFragmentListener
    private lateinit var intakeHistory: MutableList<WaterIntake>
    private lateinit var realm: Realm

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_water_intake, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        waterIntakeTargetBar.scaleY = WATER_INTAKE_TARGET_BAR_HEIGHT
        realm = Realm.getDefaultInstance()

        layoutManager = GridLayoutManager(context, 4)
        waterIntakeHistoryRecyclerView.layoutManager = layoutManager
        waterIntakeHistoryRecyclerViewAdapter = WaterIntakeHistoryRecyclerViewAdapter(context!!)
        waterIntakeHistoryRecyclerView.adapter = waterIntakeHistoryRecyclerViewAdapter
        intakeHistory = realm.where<WaterIntake>().findAll()
        waterIntakeHistoryRecyclerViewAdapter.updateData(intakeHistory)

        addDrinkingCupButton.setOnClickListener {
            listener.onAddDrinkingCupButtonClicked()
        }

        addIntakeButton.setOnClickListener {
            addWaterIntakeToDatabase()
            waterIntakeHistoryRecyclerViewAdapter.updateData(intakeHistory)
        }
    }

    private fun addWaterIntakeToDatabase() {
        //TODO: This has to be moved to MainActivity with an interface. Handle database there. Find a way to pass WaterIntake list to this fragment to update the recycler view.
        realm.beginTransaction()
        val waterIntake: WaterIntake = realm.createObject<WaterIntake>((realm.where<WaterIntake>().findAll().size) + 1)
        waterIntake.intakeAmount = realm.where<UserInfo>().findFirst()?.drinkCupSize!!
        waterIntake.time = Calendar.getInstance().time
        waterIntake.iconId = getCupIcon(waterIntake.intakeAmount)
        realm.commitTransaction()
    }

    private fun getCupIcon(intakeAmount: Int): Int {
        return when (intakeAmount) {
            50, 100, 150 -> R.drawable.tea_cup_icon
            200, 250, 300 -> R.drawable.water_glass_icon
            350, 400, 450 -> R.drawable.small_water_bottle_icon
            500, 550, 600 -> R.drawable.water_bottle_icon
            650, 700, 750, 800, 1000, 1500 -> R.drawable.large_water_bottle_icon
            else -> R.drawable.custom_cup_icon
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as WaterTrackerFragmentListener
        } catch (err: ClassCastException) {
            throw ClassCastException(("$context must implement WaterTrackerFragmentListener"))
        }
    }

    interface WaterTrackerFragmentListener {
        fun onAddDrinkingCupButtonClicked()
    }
}
