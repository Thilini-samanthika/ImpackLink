package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment

class VolEditProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_donar_edit_profile, container, false)

        val spinner = view.findViewById<Spinner>(R.id.spinnerCountry)
        val countries = arrayOf("Sri Lanka", "United States", "United Kingdom", "Australia")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, countries)
        spinner.adapter = adapter

        view.findViewById<Button>(R.id.btnSaveProfile).setOnClickListener {
            Toast.makeText(context, "Profile Updated!", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return view
    }
}