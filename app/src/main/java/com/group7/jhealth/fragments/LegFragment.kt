package com.group7.jhealth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.group7.jhealth.KEY_BUNDLE_EXERCISE_IMAGE
import com.group7.jhealth.R
import kotlinx.android.synthetic.main.fragment_leg.*

class LegFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_leg, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        squatButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.squats)
            navController.navigate(R.id.action_nav_leg_frag_to_nav_workout_detail_frag, bundle)
        }
        angledLegPressButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.angled_leg_presses)
            navController.navigate(R.id.action_nav_leg_frag_to_nav_workout_detail_frag, bundle)
        }
        legExtensionButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.leg_extensions)
            navController.navigate(R.id.action_nav_leg_frag_to_nav_workout_detail_frag, bundle)
        }
        dumbbellLungeButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.dumbbell_lunges)
            navController.navigate(R.id.action_nav_leg_frag_to_nav_workout_detail_frag, bundle)
        }
        powerSuatButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.power_squats)
            navController.navigate(R.id.action_nav_leg_frag_to_nav_workout_detail_frag, bundle)
        }
    }
}