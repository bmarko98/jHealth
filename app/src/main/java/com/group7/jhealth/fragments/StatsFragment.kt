package com.group7.jhealth.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.group7.jhealth.*
import com.group7.jhealth.database.CalorieIntake
import com.group7.jhealth.database.SleepData
import com.group7.jhealth.database.WaterIntake
import com.group7.jhealth.database.WeightProgress
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.fragment_stats.*


class StatsFragment : Fragment() {

    private var weightHistory: ArrayList<WeightProgress>? = null
    private var calorieHistory: ArrayList<CalorieIntake>? = null
    private var intakeHistory: ArrayList<WaterIntake>? = null
    private lateinit var sleepData: SleepData
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
            this.sleepData = (requireArguments().getParcelable(KEY_BUNDLE_SLEEP_DATA) as SleepData?)!!
        } catch (err: Exception) {
            err.printStackTrace()
        }

        updateWaterTrackerGraph()
        updateCalorieIntakeGraph()
        updateWeightProgressGraph()
        updateSleepTrackingChart()

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

    private fun updateSleepTrackingChart()
    {
        var pieEntries = arrayListOf<PieEntry>()

        pieEntries.add(PieEntry(sleepData.happySleepCtr.toFloat(), "Happy"))
        pieEntries.add(PieEntry(sleepData.mehSleepCtr.toFloat(), "Meh"))
        pieEntries.add(PieEntry(sleepData.sadSleepButton.toFloat(), "Sad"))

        var dataSet : PieDataSet = PieDataSet(pieEntries, "sleep tracker data")
        dataSet.setColors(listOf(Color.BLUE, Color.RED, Color.YELLOW))
        var pieData:PieData = PieData(dataSet)

        sleepMonitoringPieChart.data = pieData
        sleepMonitoringPieChart.invalidate()
    }
}