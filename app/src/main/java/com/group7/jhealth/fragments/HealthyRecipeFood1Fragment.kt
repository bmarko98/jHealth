package com.group7.jhealth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.group7.jhealth.R

/**
 * A simple [Fragment] subclass.
 */
class HealthyRecipeFood1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        container?.removeAllViews()
        return inflater.inflate(R.layout.fragment_healthy_recipe_food1, container, false)
    }
}
