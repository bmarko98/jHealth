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
import kotlinx.android.synthetic.main.fragment_biceps.*

class BicepsFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_biceps, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val navController = findNavController()

            curlsButton.setOnClickListener {
                val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.curls)
                navController.navigate(R.id.action_nav_biceps_frag_to_nav_workout_detail_frag, bundle)
            }
            barbellCurlsButton.setOnClickListener {
                val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.barbell_curls)
                navController.navigate(R.id.action_nav_biceps_frag_to_nav_workout_detail_frag, bundle)
            }
            preacherCurlsButton.setOnClickListener {
                val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.preacher_curls)
                navController.navigate(R.id.action_nav_biceps_frag_to_nav_workout_detail_frag, bundle)
            }
            pushDownsButton.setOnClickListener {
                val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.push_downs)
                navController.navigate(R.id.action_nav_biceps_frag_to_nav_workout_detail_frag, bundle)
            }
            tricepsExtensionButton.setOnClickListener {
                val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.triceps_extension)
                navController.navigate(R.id.action_nav_biceps_frag_to_nav_workout_detail_frag, bundle)
            }
        }
}