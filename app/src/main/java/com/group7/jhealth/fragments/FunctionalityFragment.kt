package com.group7.jhealth.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group7.jhealth.R
import com.group7.jhealth.activities.DietMonitoring
import com.group7.jhealth.activities.SleepMonitoring
import com.group7.jhealth.activities.WaterTrackerActivity
import com.group7.jhealth.activities.WorkoutPlan
import kotlinx.android.synthetic.main.fragment_functionality.*

class FunctionalityFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_functionality, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutButton.setOnClickListener {
            startActivity(Intent(context, WorkoutPlan::class.java))
        }

        dietMonitoring.setOnClickListener {
            startActivity(Intent(context, DietMonitoring::class.java))
        }

        waterTrackerButton.setOnClickListener {
            startActivity(Intent(context, WaterTrackerActivity::class.java))
        }
        sleepTrackerButton.setOnClickListener {
            startActivity(Intent(context, SleepMonitoring::class.java))
        }
    }
}