package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class CsrAgreementPreviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_csr_agreement_preview, container, false)

        view.findViewById<View>(R.id.btnSubmitAgreement).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrSubmitSuccessFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}
