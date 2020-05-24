package com.group7.jhealth.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.group7.jhealth.R

class AddCalorieDialog : DialogFragment() {
    private lateinit var listener: AddCalorieDialogListener
    private lateinit var foodNameEditText: EditText
    private lateinit var calorieEditText: EditText
    private lateinit var dialogView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            dialogView = inflater.inflate(R.layout.dialog_add_calorie, null)

            this.foodNameEditText = dialogView.findViewById(R.id.foodNameEditText)
            this.calorieEditText = dialogView.findViewById(R.id.calorieEditText)

            builder.setMessage("")
                .setPositiveButton(R.string.ok) { _, _ ->
                    listener.addCalorieDialogListener(foodNameEditText.text.toString(), calorieEditText.text.toString().toInt())
                }.setNegativeButton(R.string.cancel) { _, _ ->
                    dialog?.cancel()
                }

            builder.setView(dialogView)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as AddCalorieDialogListener
        } catch (err: ClassCastException) {
            throw ClassCastException(("$context must implement AddCalorieDialogListener"))
        }
    }

    interface AddCalorieDialogListener {
        fun addCalorieDialogListener(foodName: String, calorie: Int)
    }
}