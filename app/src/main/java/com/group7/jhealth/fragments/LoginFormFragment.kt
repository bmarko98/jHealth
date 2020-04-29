package com.group7.jhealth.fragments

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.group7.jhealth.*
import com.group7.jhealth.database.UserInfo
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_login_form.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Class for setting up the Login Form
 * @property dateFormat initialized to a SimpleDataFormat
 * @property realm default instance of Realm
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
    private val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT_TIME_PATTERN)

    private var realm = Realm.getDefaultInstance()
    private var name = ""
    private var age = 0
    private var gender = "" //empty string means other or, the user skipped the form
    private var weight = 0
    private var wakeUpTime: Date = dateFormat.parse(DEFAULT_WAKE_UP_TIME)
    private var sleepTime: Date = dateFormat.parse(DEFAULT_SLEEP_UP_TIME)

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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has returned, but before any saved state has been restored in to the view.
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()

        genderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.femaleRadioButton -> gender = KEY_GENDER_FEMALE
                R.id.maleRadioButton -> gender = KEY_GENDER_MALE
            }
        }
        oKButton.setOnClickListener {
            updateDatabase()
            navController.navigate(R.id.action_global_navigate_to_home_fragment)
        }
        skipButton.setOnClickListener {
            navController.navigate(R.id.action_global_navigate_to_home_fragment)
        }
        wakeUpTimeTextView.setOnClickListener {
            getTimeFromUser(wakeUpTimeTextView)
        }
        sleepTimeTextView.setOnClickListener {
            getTimeFromUser(sleepTimeTextView)
        }
        displayUserInfo()
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
        name = nameEditText.text.toString()
        if (ageEditText.text.toString().isNotBlank())
            age = Integer.parseInt(ageEditText.text.toString())
        if (weightEditText.text.toString().isNotBlank())
            weight = Integer.parseInt(weightEditText.text.toString())
        if (wakeUpTimeTextView.text.toString().isNotBlank())
            wakeUpTime = dateFormat.parse(wakeUpTimeTextView.text.toString())
        if (sleepTimeTextView.text.toString().isNotBlank())
            sleepTime = dateFormat.parse(sleepTimeTextView.text.toString())

        realm.beginTransaction()

        val user: UserInfo? = if (realm.where<UserInfo>().findFirst() != null) {
            realm.where<UserInfo>().findFirst()
        } else {
            realm.createObject<UserInfo>((realm.where<UserInfo>().findAll().size) + 1)
            realm.where<UserInfo>().findFirst()
        }
        user?.name = name
        user?.age = age
        user?.gender = gender
        user?.weight = weight
        user?.wakeUpTime = wakeUpTime
        user?.sleepTime = sleepTime
        realm.commitTransaction()
    }

    /**
     * if Database is non-empty
     * display User's input values for the given parameters
     */
    private fun displayUserInfo() {
        if (realm.where<UserInfo>().findFirst() != null) {
            val user: UserInfo? = realm.where<UserInfo>().findFirst()

            nameEditText.setText(user?.name)
            ageEditText.setText(user?.age.toString())
            when (user?.gender) {
                KEY_GENDER_FEMALE -> femaleRadioButton.isChecked = true
                KEY_GENDER_MALE -> maleRadioButton.isChecked = true
            }
            weightEditText.setText(user?.weight.toString())
            wakeUpTimeTextView.text = dateFormat.format(user?.wakeUpTime)
            sleepTimeTextView.text = dateFormat.format(user?.sleepTime)
        }
    }

    /**
     * set up time properties selected by user
     * @param textView of TextView stores user's input
     */
    private fun getTimeFromUser(textView: TextView) {
        val calendar = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            textView.text = dateFormat.format(calendar.time)
        }
        TimePickerDialog(context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }
}