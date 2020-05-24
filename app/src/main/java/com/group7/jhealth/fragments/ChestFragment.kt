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
import kotlinx.android.synthetic.main.fragment_chest.*

class ChestFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        benchPressButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.bench_presses)
            navController.navigate(R.id.action_nav_chest_frag_to_nav_workout_detail_frag, bundle)
        }
        inclinePressButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.incline_presses)
            navController.navigate(R.id.action_nav_chest_frag_to_nav_workout_detail_frag, bundle)
        }
        dumbbellPressButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.dumbbell_presses)
            navController.navigate(R.id.action_nav_chest_frag_to_nav_workout_detail_frag, bundle)
        }
        dumbbellFlyButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.dumbbell_flys)
            navController.navigate(R.id.action_nav_chest_frag_to_nav_workout_detail_frag, bundle)
        }
        dumbbellPulloverButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.dumbbell_pullovers)
            navController.navigate(R.id.action_nav_chest_frag_to_nav_workout_detail_frag, bundle)
        }
    }
}