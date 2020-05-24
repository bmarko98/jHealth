package com.group7.jhealth.fragments

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.group7.jhealth.*
import com.group7.jhealth.database.UserInfo
import kotlinx.android.synthetic.main.fragment_login_form.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Class for setting up the Login Form
 * @property dateFormat initialized to a SimpleDataFormat
 * @property name initialized to empty string
 * @property age initialized to 0
 * @property gender initialized to empty string
 * @property weight initialized to 0
 * @property wakeUpTime initialized to the default wake up time
 * @property sleepTime initialized to the default sleep time
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LoginFormFragment : Fragment() {

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT_TIME_PATTERN_HR_MIN)
    private var name = ""
    private var age = 0
    private var gender = "" //empty string means other or, the user skipped the form
    private var weight = 0
    private var wakeUpTime: Date = dateFormat.parse(DEFAULT_WAKE_UP_TIME)
    private var sleepTime: Date = dateFormat.parse(DEFAULT_SLEEP_UP_TIME)
    private var workoutDuration = 0
    private var isTakingMed = false
    private lateinit var listener: LoginFormFragmentListener

    /**
     * Called to have the fragment instantiate its user interface view.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has returned, but before any saved state has been restored in to the view.
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listener.updateUserInfoUI()
        displayUserInfo()

        nameEditText.doOnTextChanged { text, _, _, _ ->
            if (text!!.isNotBlank()) {
                name = text.toString()
                updateDatabase()
            }
        }

        ageEditText.doOnTextChanged { text, _, _, _ ->
            if (text!!.isNotBlank()) {
                age = Integer.parseInt(text.toString())
                updateDatabase()
            }
        }

        weightEditText.doOnTextChanged { text, _, _, _ ->
            if (text!!.isNotBlank()) {
                weight = Integer.parseInt(text.toString())
                updateDatabase()
            }
        }

        genderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.femaleRadioButton -> gender = KEY_GENDER_FEMALE
                R.id.maleRadioButton -> gender = KEY_GENDER_MALE
            }
            updateDatabase()
        }

        wakeUpTimeTextView.setOnClickListener {
            getTimeFromUser(wakeUpTimeTextView)
        }

        sleepTimeTextView.setOnClickListener {
            getTimeFromUser(sleepTimeTextView)
        }

        workoutDurationSeekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                workoutDuration = seekBar.progress
                workoutDurationTextView.text = getString(R.string.workout_duration_minutes, workoutDuration)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                updateDatabase()
            }
        })

        medToggleButton.setOnCheckedChangeListener { _, isChecked ->
            isTakingMed = isChecked
            updateDatabase()
        }
    }

    /**
     * updates Realm with user's input
     * name
     * age
     * weight
     * wakeupTime
     * sleepTime
     */
    private fun updateDatabase() {
        listener.updateUserInfoDatabase(name, age, gender, weight, wakeUpTime, sleepTime, workoutDuration, isTakingMed)
    }

    /**
     * display User's input values for the given parameters
     */
    private fun displayUserInfo() {
        val user: UserInfo?
        try {
            user = requireArguments().getParcelable(KEY_BUNDLE_USER_INFO) as UserInfo?
        } catch (err: Exception) {
            err.printStackTrace()
            return
        }
        this.name = user!!.name
        this.age = user.age
        this.gender = user.gender
        this.weight = user.weight
        this.weight = user.weight
        this.wakeUpTime = user.wakeUpTime!!
        this.sleepTime = user.sleepTime!!
        this.workoutDuration = user.workoutDuration
        this.isTakingMed = user.isTakingMed

        nameEditText.setText(name)
        ageEditText.setText(age.toString())
        when (gender) {
            KEY_GENDER_FEMALE -> femaleRadioButton.isChecked = true
            KEY_GENDER_MALE -> maleRadioButton.isChecked = true
        }
        weightEditText.setText(weight.toString())
        wakeUpTimeTextView.text = dateFormat.format(wakeUpTime)
        sleepTimeTextView.text = dateFormat.format(sleepTime)
        workoutDurationSeekBar.progress = workoutDuration
        workoutDurationTextView.text = getString(R.string.workout_duration_minutes, workoutDuration)

        medToggleButton.isChecked = isTakingMed
    }

    /**
     * set up time properties selected by user
     * @param textView of TextView stores user's input
     */
    private fun getTimeFromUser(textView: TextView) {
        val calendar = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            textView.text = dateFormat.format(calendar.time)

            when (textView.id) {
                R.id.wakeUpTimeTextView -> wakeUpTime = dateFormat.parse(textView.text.toString())
                R.id.sleepTimeTextView -> sleepTime = dateFormat.parse(textView.text.toString())
            }
            updateDatabase()
        }
        TimePickerDialog(
            context,
            timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    /**
     * called once the fragment is associated with its activity.
     * @param context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as LoginFormFragmentListener
        } catch (err: ClassCastException) {
            throw ClassCastException(("$context must implement LoginFormFragmentListener"))
        }
    }

    /**
     * implemented in MainActivity
     */
    interface LoginFormFragmentListener {
        fun updateUserInfoDatabase(
            name: String,
            age: Int,
            gender: String,
            weight: Int,
            wakeUpTime: Date,
            sleepTime: Date,
            workoutDuration: Int,
            isTakingMed: Boolean
        )

        fun updateUserInfoUI()
    }
}