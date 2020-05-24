package com.group7.jhealth.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group7.jhealth.KEY_BUNDLE_EXERCISE_IMAGE
import com.group7.jhealth.R
import com.group7.jhealth.dialogs.AddWorkoutDialog
import kotlinx.android.synthetic.main.fragment_workout_detail.*

class WorkoutDetail : Fragment() {
    private lateinit var listener: WorkoutDetailFragmentListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workoutImageView.setImageResource(requireArguments().getInt(KEY_BUNDLE_EXERCISE_IMAGE))

        addWorkoutButton.setOnClickListener {
            listener.onAddWorkoutButtonClicked()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as WorkoutDetailFragmentListener
        } catch (err: ClassCastException) {
            throw ClassCastException(("$context must implement WorkoutDetailFragmentListener"))
        }
    }

    interface WorkoutDetailFragmentListener {
        fun onAddWorkoutButtonClicked()
    }
}