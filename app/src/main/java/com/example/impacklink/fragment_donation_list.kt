package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class DonationListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_donation_list, container, false)

        view.findViewById<Button>(R.id.btnUpdate1).setOnClickListener {
            Toast.makeText(context, "Updating item...", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnDelete1).setOnClickListener {
            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}