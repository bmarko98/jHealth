package com.group7.jhealth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.group7.jhealth.R
import kotlinx.android.synthetic.main.fragment_workout_plan.*

/**
 * Class for Setting up the Workout Planning option
 */
class WorkoutPlanFragment : Fragment() {
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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        ABSButton.setOnClickListener {
            navController.navigate(R.id.action_nav_workout_plan_to_nav_abs_frag)
        }

        backButton.setOnClickListener {
            navController.navigate(R.id.action_nav_workout_plan_to_nav_back_frag)
        }

        bicepsButton.setOnClickListener {
            navController.navigate(R.id.action_nav_workout_plan_to_nav_biceps_frag)
        }

        chestButton.setOnClickListener {
            navController.navigate(R.id.action_nav_workout_plan_to_nav_chest_frag)
        }

        forearmsButton.setOnClickListener {
            navController.navigate(R.id.action_nav_workout_plan_to_nav_forearm_frag)
        }

        legsButton.setOnClickListener {
            navController.navigate(R.id.action_nav_workout_plan_to_nav_leg_frag)
        }

        shouldersButton.setOnClickListener {
            navController.navigate(R.id.action_nav_workout_plan_to_nav_shoulder_frag)
        }

        triceps.setOnClickListener {
            navController.navigate(R.id.action_nav_workout_plan_to_nav_triceps_frag)
        }
    }
}