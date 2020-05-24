package com.group7.jhealth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group7.jhealth.*
import com.group7.jhealth.database.CalorieIntake
import com.group7.jhealth.database.WaterIntake
import com.group7.jhealth.database.WeightProgress
import com.group7.jhealth.database.WorkoutInfo
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.fragment_stats.*
import java.lang.Exception

class StatsFragment : Fragment() {

    private var weightHistory: ArrayList<WeightProgress>? = null
    private var calorieHistory: ArrayList<CalorieIntake>? = null
    private var intakeHistory: ArrayList<WaterIntake>? = null
    private var workoutInfo: Double = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            this.intakeHistory = requireArguments().getParcelableArrayList(KEY_BUNDLE_INTAKE_HISTORY)
            this.calorieHistory = requireArguments().getParcelableArrayList(KEY_BUNDLE_CALORIE_HISTORY)
            this.weightHistory = requireArguments().getParcelableArrayList(KEY_BUNDLE_WEIGHT_HISTORY)
            this.workoutInfo = requireArguments().getDouble(KEY_BUNDLE_AVERAGE_WEIGHT_LIFTED)

        } catch (err: Exception) {
            err.printStackTrace()
        }

        updateWaterTrackerGraph()
        updateCalorieIntakeGraph()
        updateWeightProgressGraph()

        averageWeightTextView.text = workoutInfo.toString()
    }

    private fun updateWaterTrackerGraph() {
        val someArray: ArrayList<DataPoint> = arrayListOf()

        for (i in intakeHistory!!.indices) {
            someArray.add(DataPoint(i.toDouble(), intakeHistory!![i].intakeAmount.toDouble()))
        }
        val series = LineGraphSeries(someArray.toTypedArray())
        waterTrackerGraph.addSeries(series)
    }

    private fun updateCalorieIntakeGraph() {
        val someArray: ArrayList<DataPoint> = arrayListOf()

        for (i in calorieHistory!!.indices) {
            someArray.add(DataPoint(i.toDouble(), calorieHistory!![i].calorie.toDouble()))
        }
        val series = LineGraphSeries(someArray.toTypedArray())
        calorieIntakeGraph.addSeries(series)
    }

    private fun updateWeightProgressGraph() {
        val someArray: ArrayList<DataPoint> = arrayListOf()

        for (i in weightHistory!!.indices) {
            someArray.add(DataPoint(i.toDouble(), weightHistory!![i].weightAmount.toDouble()))
        }
        val series = LineGraphSeries(someArray.toTypedArray())
        weightProgressGraph.addSeries(series)
    }
}