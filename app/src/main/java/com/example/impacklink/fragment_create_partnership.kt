package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment

class CreatePartnershipFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_partnership, container, false)

        val spinnerNgo = view.findViewById<Spinner>(R.id.spinnerNgo)
        val ngoList = arrayOf("Eco Green NGO", "Save Kids Foundation", "Hope Charity")
        spinnerNgo.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, ngoList)

        val spinnerCsr = view.findViewById<Spinner>(R.id.spinnerCsrType)
        val csrTypes = arrayOf("Environmental", "Education", "Healthcare", "Community")
        spinnerCsr.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, csrTypes)

        val spinnerBudget = view.findViewById<Spinner>(R.id.spinnerBudgetRange)
        val budgetRanges = arrayOf("Rs. 10k - 50k", "Rs. 50k - 200k", "Rs. 200k+")
        spinnerBudget.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, budgetRanges)

        view.findViewById<Button>(R.id.btnSubmitRequest).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrSubmitSuccessFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<View>(R.id.btnEditPartnershipNav).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrEditPartnershipFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        view.findViewById<View>(R.id.btnHome).setOnClickListener {
            parentFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        view.findViewById<View>(R.id.btnReports).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrAgreementPreviewFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<View>(R.id.btnMenu).setOnClickListener {
            val intent = Intent(requireContext(), RoleSelectionActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
