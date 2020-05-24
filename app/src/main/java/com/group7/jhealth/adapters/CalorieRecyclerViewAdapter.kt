package com.group7.jhealth.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group7.jhealth.R
import com.group7.jhealth.SIMPLE_DATE_FORMAT_TIME_PATTERN_HR_MIN
import com.group7.jhealth.database.CalorieIntake
import kotlinx.android.synthetic.main.recycler_view_calorie.view.*
import java.text.SimpleDateFormat

class CalorieRecyclerViewAdapter() :
    RecyclerView.Adapter<CalorieRecyclerViewAdapter.ViewHolder>() {

    var intakeHistory = arrayListOf<CalorieIntake>()
    private val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT_TIME_PATTERN_HR_MIN)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalorieRecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_calorie, parent, false))
    }

    override fun getItemCount(): Int {
        return intakeHistory.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(intakeHistory[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(calorieIntake: CalorieIntake) {
            itemView.dateTextView.text = dateFormat.format(calorieIntake.time)
            itemView.foodNameTextView.text = calorieIntake.foodName
            itemView.calorieTextView.text = calorieIntake.calorie.toString()
        }
    }

    fun updateData(calorieIntake: ArrayList<CalorieIntake>) {
        this.intakeHistory = calorieIntake
        notifyDataSetChanged()
    }
}
