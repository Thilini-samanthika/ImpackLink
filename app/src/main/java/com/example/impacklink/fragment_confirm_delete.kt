package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ConfirmDeleteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_confirm_delete, container, false)

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}