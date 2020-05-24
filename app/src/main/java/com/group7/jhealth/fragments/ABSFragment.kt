package com.group7.jhealth.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.group7.jhealth.KEY_BUNDLE_EXERCISE_IMAGE
import com.group7.jhealth.R
import kotlinx.android.synthetic.main.fragment_abs.*
import kotlinx.android.synthetic.main.fragment_chest.*

class ABSFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_abs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        inclineBenchSitupButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.incline_bench_sit_ups)
            navController.navigate(R.id.action_nav_abs_frag_to_nav_workout_detail_frag, bundle)
        }
        hangingLegRaises.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.hanging_leg_raises)
            navController.navigate(R.id.action_nav_abs_frag_to_nav_workout_detail_frag, bundle)
        }
        crunchesButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.crunches)
            navController.navigate(R.id.action_nav_abs_frag_to_nav_workout_detail_frag, bundle)
        }
        legRaisesButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.leg_raises)
            navController.navigate(R.id.action_nav_abs_frag_to_nav_workout_detail_frag, bundle)
        }
        supermanButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.superman)
            navController.navigate(R.id.action_nav_abs_frag_to_nav_workout_detail_frag, bundle)
        }
    }
}