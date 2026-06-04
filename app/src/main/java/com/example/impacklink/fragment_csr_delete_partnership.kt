package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class CsrDeletePartnershipFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_csr_delete_partnership, container, false)

        view.findViewById<Button>(R.id.btnConfirmDeleteCsr).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrDeleteSuccessFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnCancelDeleteCsr).setOnClickListener {
            // Navigate back to CSR Dashboard page
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrDashboardFragment())
                .commit()
        }
        return view
    }
}