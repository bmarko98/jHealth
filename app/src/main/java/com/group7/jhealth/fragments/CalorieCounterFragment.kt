package com.group7.jhealth.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.group7.jhealth.KEY_BUNDLE_CALORIE_HISTORY

import com.group7.jhealth.R
import com.group7.jhealth.adapters.CalorieRecyclerViewAdapter
import com.group7.jhealth.adapters.WeightHistoryRecyclerViewAdapter
import com.group7.jhealth.database.CalorieIntake
import com.group7.jhealth.database.WeightProgress
import kotlinx.android.synthetic.main.fragment_calorie_counter.*

/**
 * A simple [Fragment] subclass.
 */
class CalorieCounterFragment : Fragment() {
    private lateinit var calorieRecyclerViewAdapter: CalorieRecyclerViewAdapter
    private var calorieIntakeHistory: ArrayList<CalorieIntake>? = arrayListOf()

    private lateinit var listener: CalorieCounterFragmentListener
    private lateinit var layoutManager: GridLayoutManager

    /**
     * Called to have the fragment instantiate its user interface view.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calorie_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            this.calorieIntakeHistory = requireArguments().getParcelableArrayList(KEY_BUNDLE_CALORIE_HISTORY)
        } catch (err: Exception) {
            err.printStackTrace()
        }

        layoutManager = GridLayoutManager(context, 1)
        calorieRecyclerView.layoutManager = layoutManager
        calorieRecyclerViewAdapter = CalorieRecyclerViewAdapter()
        calorieRecyclerView.adapter = calorieRecyclerViewAdapter
        calorieIntakeHistory?.let { calorieRecyclerViewAdapter.updateData(it) }

        newfood_button.setOnClickListener {
            listener.onAddCalorieButtonClicked()
            try {
                this.calorieIntakeHistory = requireArguments().getParcelableArrayList(KEY_BUNDLE_CALORIE_HISTORY)
                calorieIntakeHistory?.let { it1 -> calorieRecyclerViewAdapter.updateData(it1) }
            } catch (err: Exception) {
                err.printStackTrace()
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as CalorieCounterFragmentListener
        } catch (err: ClassCastException) {
            throw ClassCastException(("$context must implement CalorieCounterFragmentListener"))
        }
    }

    interface CalorieCounterFragmentListener {
        fun onAddCalorieButtonClicked()
    }
}
