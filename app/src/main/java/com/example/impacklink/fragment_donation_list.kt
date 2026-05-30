package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment

class DonationListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_donation_list, container, false)

        // Setup Spinners for Project Names
        val projectNames = arrayOf("Project A - NGO X", "Project B - NGO Y", "Project C - NGO Z")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, projectNames)
        
        val spinner1 = view.findViewById<Spinner>(R.id.spinnerProject1)
        val spinner2 = view.findViewById<Spinner>(R.id.spinnerProject2)
        
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        // Card 1 Buttons
        view.findViewById<Button>(R.id.btnUpdate1).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UpdateDonationFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnDelete1).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DonationDeleteFragment())
                .addToBackStack(null)
                .commit()
        }

        // Card 2 Buttons
        view.findViewById<Button>(R.id.btnUpdate2).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UpdateDonationFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnDelete2).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DonationDeleteFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}