package com.group7.jhealth.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group7.jhealth.R
import com.group7.jhealth.SIMPLE_DATE_FORMAT_TIME_PATTERN_HR_MIN
import com.group7.jhealth.database.WaterIntake
import com.group7.jhealth.fragments.OnIntakeLongClickListener
import kotlinx.android.synthetic.main.recyler_view_layout_water_intake_history.view.*
import java.text.SimpleDateFormat

/**
 * @param context
 */
class WaterIntakeHistoryRecyclerViewAdapter(var context: Context) : RecyclerView.Adapter<WaterIntakeHistoryRecyclerViewAdapter.ViewHolder>() {

    var intakeHistory = arrayListOf<WaterIntake>()
    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT_TIME_PATTERN_HR_MIN)
    private lateinit var onLongClickCallback: OnIntakeLongClickListener

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent an item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterIntakeHistoryRecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyler_view_layout_water_intake_history, parent, false))
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
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

        /**
         * Called when a view has been clicked and held
         * @param view the view that was clicked and held.
         * @return true if the callback consumed the long click, otherwise false.
         */
        override fun onLongClick(view: View): Boolean {
            onLongClickCallback.onLongClickWaterIntakeRecyclerViewItem(intakeHistory[adapterPosition])
            return true
        }
    }

    /**
     * update dataset with the list of water intake
     * @param intakeHistory list that collects water intakes being input
     */
    fun updateData(intakeHistory: ArrayList<WaterIntake>) {
        this.intakeHistory = intakeHistory
        notifyDataSetChanged()
    }
}