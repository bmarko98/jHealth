package com.group7.jhealth.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.group7.jhealth.R
import com.group7.jhealth.SHARED_PREF_FILE

class PreferencesFragment : PreferenceFragmentCompat()
{
    private lateinit var preferences: SharedPreferences
    lateinit var preferencesEditor: SharedPreferences.Editor

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        preferences = requireContext().getSharedPreferences(SHARED_PREF_FILE, AppCompatActivity.MODE_PRIVATE)
    }
}