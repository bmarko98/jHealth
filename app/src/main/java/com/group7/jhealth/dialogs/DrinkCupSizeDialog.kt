package com.group7.jhealth.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import androidx.core.view.isGone
import androidx.fragment.app.DialogFragment
import com.group7.jhealth.R

class DrinkCupSizeDialog() : DialogFragment() {
    private lateinit var listener: DrinkCupSizeDialogListener
    private lateinit var cupSizeNumberPicker: NumberPicker
    private lateinit var customSizeButton: Button
    private lateinit var customCupSizeEditText: EditText
    private var chosenSize: Int = 0
    private var isCustom = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView: View = inflater.inflate(R.layout.dialog_drink_cup_size, null)

            this.cupSizeNumberPicker = dialogView.findViewById(R.id.cupSizeNumberPicker)
            this.customSizeButton = dialogView.findViewById(R.id.customSizeButton)
            this.customCupSizeEditText = dialogView.findViewById(R.id.customCupSizeEditText)

            val pickerValues = arrayOf("100", "150", "200", "250", "300", "350", "400", "450", "500", "550", "600", "650", "700", "750", "800")

            cupSizeNumberPicker.maxValue = pickerValues.size - 1
            cupSizeNumberPicker.minValue = 0
            cupSizeNumberPicker.displayedValues = pickerValues

            cupSizeNumberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
                chosenSize = pickerValues[newVal].toInt()
            }

            customCupSizeEditText.isGone = true

            customSizeButton.setOnClickListener {
                cupSizeNumberPicker.isGone = true
                customSizeButton.isGone = true
                customCupSizeEditText.isGone = false
                isCustom = true
            }

            builder.setMessage(getString(R.string.ml))
                .setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialog, id ->
                    if (isCustom)
                        chosenSize = customCupSizeEditText.text.toString().toInt()

                    listener.drinkCupSizeDialogListener(chosenSize)
                }).setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, id ->
                    getDialog()?.cancel()
                })

            builder.setView(dialogView)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as DrinkCupSizeDialogListener
        } catch (err: ClassCastException) {
            throw ClassCastException((context.toString() + " must implement DrinkCupSizeDialogListener"))
        }
    }

    interface DrinkCupSizeDialogListener {
        fun drinkCupSizeDialogListener(chosenSize: Int)
    }
}