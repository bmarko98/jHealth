package com.group7.jhealth.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group7.jhealth.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var listener: HomeFragmentListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dietMonitoringTextView.setOnClickListener {
            listener.onClickListener(R.id.dietMonitoringTextView)
        }

        sleepMonitoringTextView.setOnClickListener {
            listener.onClickListener(R.id.sleepMonitoringTextView)
        }

        waterTrackerTextView.setOnClickListener {
            listener.onClickListener(R.id.waterTrackerTextView)
        }

        workoutPlanTextView.setOnClickListener {
            listener.onClickListener(R.id.workoutPlanTextView)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as HomeFragmentListener
        } catch (err: ClassCastException) {
            throw ClassCastException(("$context must implement HomeFragmentListener"))
        }
    }

    interface HomeFragmentListener {
        fun onClickListener(clickedItemId: Int)
    }
}
