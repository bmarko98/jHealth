package com.group7.jhealth.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import androidx.core.view.isGone
import androidx.fragment.app.DialogFragment
import com.group7.jhealth.R

/**
 * Class for Setting up Cup Size
 * @property cupSizeNumberPicker of NumberPicker
 * @property customCupSizeEditText of EditText
 * @property listener of WaterTrackerFragmentListener
 * @property customSizeButton of Button
 * @property chosenSize integer initialized to 0
 * @property isCustom boolean initialized to false
 * @property dialogView of View
 * @property cupIconImageView of ImageView
 */
class DrinkCupSizeDialog : DialogFragment() {
    private lateinit var listener: DrinkCupSizeDialogListener
    private lateinit var cupSizeNumberPicker: NumberPicker
    private lateinit var customSizeButton: Button
    private lateinit var customCupSizeEditText: EditText
    private var chosenSize: Int = 0
    private var isCustom = false
    private lateinit var dialogView: View
    private lateinit var cupIconImageView: ImageView

   /**
    * Setting up the parameters for the cup size
    * @param savedInstanceState The last saved instance state of the Fragment,
    * or null if this is a freshly created Fragment.
    * @throws IllegalStateException ---> Activity cannot be null
    * @return Return a new Dialog instance to be displayed by the Fragment.
    */
    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            dialogView = inflater.inflate(R.layout.dialog_drink_cup_size, null)

            this.cupSizeNumberPicker = dialogView.findViewById(R.id.cupSizeNumberPicker)
            this.customSizeButton = dialogView.findViewById(R.id.customSizeButton)
            this.customCupSizeEditText = dialogView.findViewById(R.id.customCupSizeEditText)
            this.cupIconImageView = dialogView.findViewById(R.id.cupIconImageView)

            val pickerValues =
                arrayOf("50", "100", "150", "200", "250", "300", "350", "400", "450", "500", "550", "600", "650", "700", "750", "800", "1000", "1500")

            cupSizeNumberPicker.maxValue = pickerValues.size - 1
            cupSizeNumberPicker.minValue = 0
            cupSizeNumberPicker.displayedValues = pickerValues
            cupSizeNumberPicker.value = pickerValues.indexOf("250")

            cupSizeNumberPicker.setOnValueChangedListener { _, _, newVal ->
                chosenSize = pickerValues[newVal].toInt()

                when (chosenSize) {
                    50, 100, 150 -> cupIconImageView.setImageResource(R.drawable.ic_tea_cup)
                    200, 250, 300 -> cupIconImageView.setImageResource(R.drawable.ic_water_glass)
                    350, 400, 450 -> cupIconImageView.setImageResource(R.drawable.ic_small_water_bottle)
                    500, 550, 600 -> cupIconImageView.setImageResource(R.drawable.ic_water_bottle)
                    else -> cupIconImageView.setImageResource(R.drawable.ic_large_water_bottle)
                }
            }

            customCupSizeEditText.isGone = true

            customSizeButton.setOnClickListener {
                cupSizeNumberPicker.isGone = true
                customSizeButton.isGone = true
                cupIconImageView.isGone = true
                customCupSizeEditText.isGone = false
                isCustom = true
            }

            builder.setMessage(getString(R.string.ml))
                .setPositiveButton(R.string.ok) { _, _ ->
                    if (isCustom)
                        chosenSize = customCupSizeEditText.text.toString().toInt()
                    listener.drinkCupSizeDialogListener(chosenSize)
                }.setNegativeButton(R.string.cancel) { dialog, id ->
                    getDialog()?.cancel()
                }

            builder.setView(dialogView)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    /**
     * Called when a fragment is first attached to its context.
     * @param context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as DrinkCupSizeDialogListener
        } catch (err: ClassCastException) {
            throw ClassCastException(("$context must implement DrinkCupSizeDialogListener"))
        }
    }

    interface DrinkCupSizeDialogListener {
        fun drinkCupSizeDialogListener(chosenSize: Int)
    }
}