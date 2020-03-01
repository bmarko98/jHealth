package com.group7.jhealth.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.group7.jhealth.KEY_PREF_IS_FIRST_LAUNCH
import com.group7.jhealth.R
import com.group7.jhealth.SHARED_PREF_FILE
import com.group7.jhealth.fragments.FunctionalityFragment
import com.group7.jhealth.fragments.PersonalInfoFragment


class MainActivity : AppCompatActivity() {
    private val fragmentManager: FragmentManager = supportFragmentManager
    private lateinit var preferences: SharedPreferences
    lateinit var preferencesEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = this.getSharedPreferences(SHARED_PREF_FILE, AppCompatActivity.MODE_PRIVATE)
        //shared preferences is used to store isFirstLaunch boolean value.
        //https://developer.android.com/reference/android/content/SharedPreferences

        if (getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE).getBoolean(KEY_PREF_IS_FIRST_LAUNCH, true)) {
            //if the user launches this app for the first time, PersonalInfoFragment will be launched where user can enter his/her personal data
            //those data will be stored in the database and user can update it from the settings. To be implemented later.
            preferencesEditor = preferences.edit()
            preferencesEditor.putBoolean(KEY_PREF_IS_FIRST_LAUNCH, false)
            preferencesEditor.apply()
            fragmentManager.beginTransaction().add(R.id.fragmentHolder, PersonalInfoFragment()).addToBackStack("").commit()
            //https://developer.android.com/guide/components/fragments
        } else {
            //if it is not the first time, FunctionalityFragment will be launched where user can choose a functionality
            fragmentManager.beginTransaction().add(R.id.fragmentHolder, FunctionalityFragment()).addToBackStack("").commit()
        }
    }
}