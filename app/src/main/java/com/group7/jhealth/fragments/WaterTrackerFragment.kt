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
import com.group7.jhealth.adapters.WaterIntakeHistoryRecyclerViewAdapter
import com.group7.jhealth.database.WaterIntake
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_water_intake.*

interface OnIntakeLongClickListener {
    fun onLongClickWaterIntakeRecyclerViewItem(intake: WaterIntake)
}

/**
 * Class for Setting up the Water Tracking option
 * @property waterIntakeHistoryRecyclerViewAdapter
 * @property layoutManager of GridLayout Manager
 * @property listener of WaterTrackerFragmentListener
 * @property intakeHistory initialized to null
 * @property realm instance of Realm
 */
class WaterTrackerFragment : Fragment() {

    private lateinit var waterIntakeHistoryRecyclerViewAdapter: WaterIntakeHistoryRecyclerViewAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var listener: WaterTrackerFragmentListener
    private var intakeHistory: MutableList<WaterIntake>? = null
    private lateinit var realm: Realm

    /**
     * Called to have the fragment instantiate its user interface view.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_water_intake, container, false)
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has returned, but before any saved state has been restored in to the view.
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
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

    /**
     * updates the Intake History list with latest intake
     *  @param intakeHistory list that collects selected water Intake amount
     */
    fun updateIntakeHistory(intakeHistory: MutableList<WaterIntake>) {
        this.intakeHistory = intakeHistory
    }

    /**
     * Called when a fragment is first attached to its context.
     * @param context
     */
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
