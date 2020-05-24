package com.group7.jhealth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.group7.jhealth.KEY_BUNDLE_WEIGHT_HISTORY
import com.group7.jhealth.R
import com.group7.jhealth.database.WeightProgress
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_diet_monitoring.*
import java.lang.Exception

/**
 * Class for Setting up the Diet Monitoring option
 */
class DietMonitoringFragment : Fragment() {

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

    private var weightHistory: ArrayList<WeightProgress>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diet_monitoring, container, false)
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has returned, but before any saved state has been restored in to the view.
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            this.weightHistory = requireArguments().getParcelableArrayList(KEY_BUNDLE_WEIGHT_HISTORY)
        } catch (err: Exception) {
            err.printStackTrace()
        }

        weightProgressButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_WEIGHT_HISTORY to weightHistory)
            findNavController().navigate(R.id.action_nav_diet_monitoring_to_nav_record_entry, bundle)
        }
        calorieCounterButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_diet_monitoring_to_nav_calorie_counter)
        }
        healthyRecipesButton.setOnClickListener {
            findNavController().navigate((R.id.action_nav_diet_monitoring_to_nav_healthy_recipe))
        }

    }
}
