package com.group7.jhealth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.group7.jhealth.*
import kotlinx.android.synthetic.main.fragment_login_form.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class LoginFormFragment : Fragment() {

    private val registrationViewModel by activityViewModels<RegistrationViewModel>()
    private val dateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT_TIME_PATTERN)

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
            val wakeUpTime = formatter.parse(wakeUpTimeEditText.text.toString())
            val sleepTime = formatter.parse(sleepTimeEditText.text.toString())
            registrationViewModel.collectProfileData(name, age, gender, weight, wakeUpTime, sleepTime)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            registrationViewModel.userCancelledRegistration()
            navController.popBackStack(R.id.nav_login_form, false)
        }

        skipButton.setOnClickListener {
            registrationViewModel.userCancelledRegistration()
            navController.popBackStack(R.id.nav_login_form, false)
        }
    }
}