package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class DonorDashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_donor_dashboard, container, false)

        view.findViewById<CardView>(R.id.cardDonationAction).setOnClickListener {
            // Action to launch donation layout
            Toast.makeText(context, "Opening Donation Panel", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnDonateImpact).setOnClickListener {
            Toast.makeText(context, "Loading Metrics Dashboard...", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}