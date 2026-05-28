package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class CsrViewPartnershipFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_csr_view_partnership, container, false)

        view.findViewById<Button>(R.id.btnViewItem).setOnClickListener {
            Toast.makeText(context, "Opening Agreement details", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return view
    }
}