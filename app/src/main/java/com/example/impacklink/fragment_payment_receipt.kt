package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class PaymentReceiptFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_payment_receipt, container, false)

        view.findViewById<Button>(R.id.btnDownloadReceipt).setOnClickListener {
            Toast.makeText(context, "Downloading PDF Receipt...", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}