package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class SuccessDonationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_success_donation, container, false)

        view.findViewById<Button>(R.id.btnDownloadReceipt).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PaymentReceiptFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            // Navigate back to Donor Dashboard
            parentFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        return view
    }
}