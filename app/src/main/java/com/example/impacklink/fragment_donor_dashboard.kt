package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
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

        view.findViewById<ImageView>(R.id.ivArrow).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DonationListFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnDonateImpact).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DonationImpactFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<ImageView>(R.id.ivProfileIcon).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DonarEditProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        // Bottom Navigation
        view.findViewById<ImageButton>(R.id.navHome).setOnClickListener {
            Toast.makeText(context, "You are already on the Dashboard", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<ImageButton>(R.id.navGrid).setOnClickListener {
            val intent = Intent(requireContext(), RoleSelectionActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<ImageButton>(R.id.navSettings).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DonarEditProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}