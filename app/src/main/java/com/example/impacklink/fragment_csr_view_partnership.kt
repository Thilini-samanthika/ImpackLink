package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class CsrViewPartnershipFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_csr_view_partnership, container, false)

        // Simulate availability logic
        view.findViewById<View>(R.id.btnPending1).visibility = View.GONE
        view.findViewById<View>(R.id.btnApply2).visibility = View.GONE
        view.findViewById<View>(R.id.btnPending3).visibility = View.GONE

        view.findViewById<Button>(R.id.btnApply1).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrAgreementPreviewFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnApply2).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrAgreementPreviewFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnApply3).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrAgreementPreviewFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnDelete1).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrDeletePartnershipFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnDelete2).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrDeletePartnershipFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnDelete3).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrDeletePartnershipFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return view
    }
}