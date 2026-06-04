package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment

class CsrDashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_csr_dashboard, container, false)

        view.findViewById<Button>(R.id.btnCreatePartnershipNav).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CreatePartnershipFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnViewPartnershipNav).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrViewPartnershipFragment())
                .addToBackStack(null)
                .commit()
        }

        // Bottom Navigation
        view.findViewById<ImageButton>(R.id.navHome).setOnClickListener {
            Toast.makeText(context, "You are already on the Dashboard", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<ImageButton>(R.id.navHistory).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrAgreementPreviewFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<ImageButton>(R.id.navSettings).setOnClickListener {
            val intent = Intent(requireContext(), RoleSelectionActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}