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
import kotlinx.android.synthetic.main.fragment_shoulder.*

class ShoulderFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shoulder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        backPressButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.back_presses)
            navController.navigate(R.id.action_nav_shoulder_frag_to_nav_workout_detail_frag, bundle)
        }
        seatedFrontPressButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.seated_front_presses)
            navController.navigate(R.id.action_nav_shoulder_frag_to_nav_workout_detail_frag, bundle)
            }
        seatedDumbbellPressButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.dumbbell_presses_shoulder)
            navController.navigate(R.id.action_nav_shoulder_frag_to_nav_workout_detail_frag, bundle)
        }
        uprightRowButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.upright_rows)
            navController.navigate(R.id.action_nav_shoulder_frag_to_nav_workout_detail_frag, bundle)
        }
        lateralRaiseButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.lateral_raises)
            navController.navigate(R.id.action_nav_shoulder_frag_to_nav_workout_detail_frag, bundle)
        }
    }
}
