package com.group7.jhealth.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.group7.jhealth.R
import kotlinx.android.synthetic.main.fragment_healthy_recipes.*

/**
 * A simple [Fragment] subclass.
 */
class HealthyRecipesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_healthy_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        food1Button.setOnClickListener {
            findNavController().navigate(R.id.action_nav_healthy_recipe_to_nav_healthy_recipe1)
        }
        food2Button.setOnClickListener {
            findNavController().navigate(R.id.action_nav_healthy_recipe_to_nav_healthy_recipe2)
        }
    }
}
