package com.group7.jhealth.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.group7.jhealth.R
import com.group7.jhealth.SHARED_PREF_FILE

/**
 * @constructor empty constructor
 * @property preferences of SharedPreferences
 * @property preferencesEditor of SharedPreferences.Editor
 */
class PreferencesFragment : PreferenceFragmentCompat()
{
    private lateinit var preferences: SharedPreferences
    lateinit var preferencesEditor: SharedPreferences.Editor

    /**
     * Called during {@link #onCreate(Bundle)} to supply the preferences for this fragment.
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state.
     * @param rootKey            If non-null, this preference fragment should be rooted at the
     *                           {@link PreferenceScreen} with this key.
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        preferences = requireContext().getSharedPreferences(SHARED_PREF_FILE, AppCompatActivity.MODE_PRIVATE)
    }
}