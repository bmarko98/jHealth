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
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_water_intake.*

interface OnIntakeLongClickListener {
    fun onLongClickWaterIntakeRecyclerViewItem(intake: WaterIntake)
}

class WaterTrackerFragment : Fragment() {

    private lateinit var waterIntakeHistoryRecyclerViewAdapter: WaterIntakeHistoryRecyclerViewAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var listener: WaterTrackerFragmentListener
    private var intakeHistory: MutableList<WaterIntake>? = null
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
        waterIntakeHistoryRecyclerViewAdapter = WaterIntakeHistoryRecyclerViewAdapter(requireContext())
        waterIntakeHistoryRecyclerView.adapter = waterIntakeHistoryRecyclerViewAdapter
        intakeHistory?.let { waterIntakeHistoryRecyclerViewAdapter.updateData(it) }

        addDrinkingCupButton.setOnClickListener {
            listener.onAddDrinkingCupButtonClicked()
        }

        addIntakeButton.setOnClickListener {
            listener.addWaterIntakeToDatabase()
            intakeHistory?.let { it1 -> waterIntakeHistoryRecyclerViewAdapter.updateData(it1) }
        }
    }

    fun updateIntakeHistory(intakeHistory: MutableList<WaterIntake>) {
        this.intakeHistory = intakeHistory
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
        fun addWaterIntakeToDatabase()
    }
}
