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

        if (getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE).getBoolean(KEY_PREF_IS_FIRST_LAUNCH, true)) {
            preferencesEditor = preferences.edit()
            preferencesEditor.putBoolean(KEY_PREF_IS_FIRST_LAUNCH, false)
            preferencesEditor.apply()
            fragmentManager.beginTransaction().add(R.id.fragmentHolder, PersonalInfoFragment()).addToBackStack("").commit()
        } else {
            fragmentManager.beginTransaction().add(R.id.fragmentHolder, FunctionalityFragment()).addToBackStack("").commit()
        }
    }
}