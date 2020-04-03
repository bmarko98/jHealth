package com.group7.jhealth.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.group7.jhealth.*
import kotlinx.android.synthetic.main.fragment_login_form.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class LoginFormFragment : Fragment() {

    private val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT_TIME_PATTERN)
    private lateinit var listener: LoginFormFragmentListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()

        oKButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = Integer.parseInt(ageEditText.text.toString())
            var gender = ""
            genderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.femaleRadioButton -> gender = KEY_GENDER_FEMALE
                    R.id.maleRadioButton -> gender = KEY_GENDER_MALE
                }
            }
            val weight = Integer.parseInt(weightEditText.text.toString())
            val formatter: DateFormat = dateFormat
            val wakeUpTime = formatter.parse(wakeUpTimeEditText.text.toString())!!
            val sleepTime = formatter.parse(sleepTimeEditText.text.toString())!!

            listener.onSubmitLoginForm(name, age, gender, weight, wakeUpTime, sleepTime)
            navController.navigate(R.id.action_global_navigate_to_home_fragment)
        }

        skipButton.setOnClickListener {
            navController.navigate(R.id.action_global_navigate_to_home_fragment)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as LoginFormFragmentListener
        } catch (err: ClassCastException) {
            throw ClassCastException(("$context must implement LoginFormFragmentListener"))
        }
    }

    interface LoginFormFragmentListener {
        fun onSubmitLoginForm(name: String, age: Int, gender: String, weight: Int, wakeUpTime: Date, sleepTime: Date)
    }
}