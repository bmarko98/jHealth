package com.group7.jhealth.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group7.jhealth.R
import com.group7.jhealth.SIMPLE_DATE_FORMAT_TIME_PATTERN
import com.group7.jhealth.WaterIntake
import kotlinx.android.synthetic.main.recyler_view_layout_water_intake_history.view.*
import java.text.SimpleDateFormat

class WaterIntakeHistoryRecyclerView : RecyclerView.Adapter<WaterIntakeHistoryRecyclerView.ViewHolder>() {

    var intakeHistory = listOf<WaterIntake>()
    private val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT_TIME_PATTERN)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterIntakeHistoryRecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyler_view_layout_water_intake_history, parent, false))
    }

    override fun getItemCount(): Int {
        return intakeHistory.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(intakeHistory[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(waterIntake: WaterIntake) {
            itemView.recyclerCupIconImageView.setImageResource(waterIntake.iconId)
            itemView.cupSizeTextView.text = waterIntake.intakeAmount.toString()
            itemView.timeTextView.text = dateFormat.format(waterIntake.time)
        }
    }

    fun updateData(intakeHistory: List<WaterIntake>) {
        this.intakeHistory = intakeHistory
        notifyDataSetChanged()
    }
}