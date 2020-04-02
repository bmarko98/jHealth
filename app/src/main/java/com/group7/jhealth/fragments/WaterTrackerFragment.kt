package com.group7.jhealth.fragments

import android.content.Context
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

class WaterTrackerFragment : Fragment() {

    private lateinit var waterIntakeHistoryRecyclerViewAdapter: WaterIntakeHistoryRecyclerViewAdapter
    private lateinit var intakeHistory: MutableList<WaterIntake>
    private lateinit var layoutManager: GridLayoutManager
    //private lateinit var listener: DialogListener

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

            //listener.dialogListener()
        }

        addIntakeButton.setOnClickListener {
            intakeHistory.add(WaterIntake(250, Calendar.getInstance().time, R.drawable.custom_cup_icon))
            waterIntakeHistoryRecyclerViewAdapter.updateData(intakeHistory)
        }
    }
/*
    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as DialogListener
        } catch (err: ClassCastException) {
            throw ClassCastException((context.toString() + " must implement DrinkCupSizeDialogListener"))
        }
    }

    interface DialogListener {
        fun dialogListener()
    }*/
}
