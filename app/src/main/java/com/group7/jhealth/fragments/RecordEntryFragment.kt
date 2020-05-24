package com.group7.jhealth.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.group7.jhealth.KEY_BUNDLE_WEIGHT_HISTORY
import com.group7.jhealth.R
import com.group7.jhealth.adapters.WeightHistoryRecyclerViewAdapter
import com.group7.jhealth.database.WeightProgress
import kotlinx.android.synthetic.main.fragment_record_entry.*
import java.lang.Exception
import kotlin.collections.ArrayList

/**
 * Class For Recording an Entry
 */
class RecordEntryFragment : Fragment() {

    private lateinit var weightHistoryRecyclerViewAdapter: WeightHistoryRecyclerViewAdapter
    private var weightHistory: ArrayList<WeightProgress>? = null
    private lateinit var listener: RecordEntryFragmentListener
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_record_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            this.weightHistory = requireArguments().getParcelableArrayList(KEY_BUNDLE_WEIGHT_HISTORY)
        } catch (err: Exception) {
            err.printStackTrace()
        }

        layoutManager = GridLayoutManager(context, 1)
        weightHistoryRecyclerView.layoutManager = layoutManager
        weightHistoryRecyclerViewAdapter = WeightHistoryRecyclerViewAdapter()
        weightHistoryRecyclerView.adapter = weightHistoryRecyclerViewAdapter
        weightHistory?.let { weightHistoryRecyclerViewAdapter.updateData(it) }

        submitRecordButton.setOnClickListener {
            listener.addWeightProgressToDatabase(weightEditText.text.toString().toInt())
            this.weightHistory = requireArguments().getParcelableArrayList(KEY_BUNDLE_WEIGHT_HISTORY)
            weightHistory?.let { it1 -> weightHistoryRecyclerViewAdapter.updateData(it1) }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as RecordEntryFragmentListener
        } catch (err: ClassCastException) {
            throw ClassCastException(("$context must implement RecordEntryFragmentListener"))
        }
    }

    interface RecordEntryFragmentListener {
        fun addWeightProgressToDatabase(weight: Int)
    }
}