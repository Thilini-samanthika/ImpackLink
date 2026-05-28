package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class CsrEditPartnershipFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_csr_edit_partnership, container, false)

        view.findViewById<Button>(R.id.btnSaveChangePartnership).setOnClickListener {
            Toast.makeText(context, "Changes Saved Successfully", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}