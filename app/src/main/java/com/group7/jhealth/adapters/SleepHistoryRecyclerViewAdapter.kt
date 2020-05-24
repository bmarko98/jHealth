package com.group7.jhealth.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group7.jhealth.R
import com.group7.jhealth.SIMPLE_DATE_FORMAT_TIME_PATTERN_DAY_MNT
import com.group7.jhealth.database.SleepTracker
import kotlinx.android.synthetic.main.recycler_view_layout_sleep_history.view.*
import java.text.SimpleDateFormat

class SleepHistoryRecyclerViewAdapter():
    RecyclerView.Adapter<SleepHistoryRecyclerViewAdapter.ViewHolder>() {
        var sleepHistory = arrayListOf<SleepTracker>()
         private val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT_TIME_PATTERN_DAY_MNT)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepHistoryRecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_layout_sleep_history, parent, false))
    }

    override fun getItemCount(): Int {
        return sleepHistory.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(sleepHistory[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(sleepTracker: SleepTracker) {
          //  itemView.dateTextView.text = dateFormat.format(SleepTracker.time)
            itemView.sleepQualityTextView.text = sleepTracker.sleepQuality.toString()
        }
    }

    fun updateData(weightProgress: ArrayList<SleepTracker>) {
        this.sleepHistory = weightProgress
        notifyDataSetChanged()
    }
}