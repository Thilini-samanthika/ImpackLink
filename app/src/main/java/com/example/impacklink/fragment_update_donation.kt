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
import model.Project

class UpdateDonationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_donation, container, false)

        val spinner = view.findViewById<Spinner>(R.id.spinnerProjectsUpdate)
        val projectNames = Project.projectList.map { it.title }.toTypedArray()
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, projectNames)

        view.findViewById<Button>(R.id.btnPayNowUpdate).setOnClickListener {
            // Navigate to CardDetailsFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CardDetailsFragment())
                .addToBackStack(null)
                .commit()
        }

        setupNavigation(view)

        return view
    }

    private fun setupNavigation(view: View) {
        view.findViewById<View>(R.id.navHome).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        view.findViewById<View>(R.id.navSettings).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DonarEditProfileFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}