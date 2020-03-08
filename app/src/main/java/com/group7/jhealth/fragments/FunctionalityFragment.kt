package com.group7.jhealth.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group7.jhealth.R
import com.group7.jhealth.activities.DietMontoringActivity
import kotlinx.android.synthetic.main.fragment_functionality.*

class FunctionalityFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_functionality, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dietMonitoring.setOnClickListener{
            startActivity(Intent(context,DietMontoringActivity::class.java))
    }
    }
}