package com.group7.jhealth.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group7.jhealth.R
import com.group7.jhealth.SIMPLE_DATE_FORMAT_TIME_PATTERN
import com.group7.jhealth.database.WaterIntake
import com.group7.jhealth.fragments.OnIntakeLongClickListener
import kotlinx.android.synthetic.main.recyler_view_layout_water_intake_history.view.*
import java.text.SimpleDateFormat

class WaterIntakeHistoryRecyclerViewAdapter(var context: Context) : RecyclerView.Adapter<WaterIntakeHistoryRecyclerViewAdapter.ViewHolder>() {

    var intakeHistory = listOf<WaterIntake>()
    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT_TIME_PATTERN)
    private lateinit var onLongClickCallback: OnIntakeLongClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterIntakeHistoryRecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyler_view_layout_water_intake_history, parent, false))
    }

    override fun getItemCount(): Int {
        return intakeHistory.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(intakeHistory[position])

        try {
            onLongClickCallback = context as OnIntakeLongClickListener
        } catch (e: Exception) {
            throw Exception("OnIntakeLongClickListener is not implemented")
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnLongClickListener {
        init {
            view.setOnLongClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bindView(waterIntake: WaterIntake) {
            itemView.recyclerCupIconImageView.setImageResource(waterIntake.iconId)
            itemView.cupSizeTextView.text = waterIntake.intakeAmount.toString() + " ml"
            itemView.timeTextView.text = dateFormat.format(waterIntake.time)
        }

        override fun onLongClick(view: View): Boolean {
            onLongClickCallback.onLongClickWaterIntakeRecyclerViewItem(intakeHistory[adapterPosition])
            return true
        }
    }

    fun updateData(intakeHistory: List<WaterIntake>) {
        this.intakeHistory = intakeHistory
        notifyDataSetChanged()
    }
}