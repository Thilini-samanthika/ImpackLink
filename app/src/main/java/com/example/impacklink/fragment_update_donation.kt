package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class UpdateDonationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_donation, container, false)
        val etAmount = view.findViewById<EditText>(R.id.etChangeAmount)

        view.findViewById<Button>(R.id.btnSaveChange).setOnClickListener {
            Toast.makeText(context, "Saved change amount: ${etAmount.text}", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.btnCancelUpdate).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return view
    }
}