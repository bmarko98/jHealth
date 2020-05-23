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
import kotlinx.android.synthetic.main.fragment_back.*

class BackFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_back, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        chinUpsButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.chin_ups)
            navController.navigate(R.id.action_nav_back_frag_to_nav_workout_detail_frag, bundle)
        }
        deadliftsButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.deadlifts)
            navController.navigate(R.id.action_nav_back_frag_to_nav_workout_detail_frag, bundle)
        }

        latPullDownsButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.lat_pull_down)
            navController.navigate(R.id.action_nav_back_frag_to_nav_workout_detail_frag, bundle)
        }
        seatedRowsButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.seated_rows)
            navController.navigate(R.id.action_nav_back_frag_to_nav_workout_detail_frag, bundle)
        }
        oneArmDumbbelRowsButton.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_EXERCISE_IMAGE to R.drawable.dumbell_rows)
            navController.navigate(R.id.action_nav_back_frag_to_nav_workout_detail_frag, bundle)
        }
    }


}