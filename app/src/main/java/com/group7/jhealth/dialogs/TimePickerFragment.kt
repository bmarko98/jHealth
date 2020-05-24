package com.group7.jhealth.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

/**
 * Class for Initializing Time and Setting to the one chosen by User
 */
class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    /**
    * @param savedInstanceState The last saved instance state of the Fragment
    * @return Create a new instance of TimePickerDialog and return it
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    /**
     * Called when user is done setting a new time and the dialog has closed
     * @param view the view associated with this listener
     * @param hourOfDay the hour that was set
     * @param minute the minute that was set
     */
    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Do something with the time chosen by the user
    }
}

