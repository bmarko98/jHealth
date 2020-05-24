package com.group7.jhealth.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group7.jhealth.R
import com.group7.jhealth.SIMPLE_DATE_FORMAT_TIME_PATTERN_DAY_MNT
import com.group7.jhealth.database.WeightProgress
import kotlinx.android.synthetic.main.recyler_view_layout_weight_history.view.*
import java.text.SimpleDateFormat

class WeightHistoryRecyclerViewAdapter() :
    RecyclerView.Adapter<WeightHistoryRecyclerViewAdapter.ViewHolder>() {

    var intakeHistory = arrayListOf<WeightProgress>()
    private val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT_TIME_PATTERN_DAY_MNT)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeightHistoryRecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyler_view_layout_weight_history, parent, false))
    }

    override fun getItemCount(): Int {
        return intakeHistory.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(intakeHistory[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(weightProgress: WeightProgress) {
            itemView.dateTextView.text = dateFormat.format(weightProgress.time)
            itemView.weightTextView.text = weightProgress.weightAmount.toString()
        }
    }

    fun updateData(weightProgress: ArrayList<WeightProgress>) {
        this.intakeHistory = weightProgress
        notifyDataSetChanged()
    }
}
