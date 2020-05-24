package com.group7.jhealth.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.group7.jhealth.R

class AddWorkoutDialog : DialogFragment() {
    private lateinit var listener: AddWorkoutDialogListener
    private lateinit var repsEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var dialogView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            dialogView = inflater.inflate(R.layout.dialog_add_workout, null)

            this.repsEditText = dialogView.findViewById(R.id.repsEditText)
            this.weightEditText = dialogView.findViewById(R.id.weightEditText)

            builder.setMessage("")
                .setPositiveButton(R.string.ok) { _, _ ->
                    listener.addWorkoutDialogListener(repsEditText.text.toString().toInt(), weightEditText.text.toString().toInt())
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
            listener = context as AddWorkoutDialogListener
        } catch (err: ClassCastException) {
            throw ClassCastException(("$context must implement AddWorkoutDialogListener"))
        }
    }

    interface AddWorkoutDialogListener {
        fun addWorkoutDialogListener(reps: Int, weight: Int)
    }
}