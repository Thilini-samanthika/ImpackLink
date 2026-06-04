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

class MakeDonationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_make_donation, container, false)

        val spinner = view.findViewById<Spinner>(R.id.spinnerProjects)
        val projectNames = Project.projectList.map { it.title }.toTypedArray()
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, projectNames)

        view.findViewById<Button>(R.id.btnPayNow).setOnClickListener {
            Toast.makeText(context, "Processing payment step...", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return view
    }
}