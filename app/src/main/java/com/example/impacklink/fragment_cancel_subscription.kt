package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class CancelSubscriptionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cancel_subscription, container, false)

        view.findViewById<Button>(R.id.btnCancelRecurring).setOnClickListener {
            Toast.makeText(context, "Subscription Canceled", Toast.LENGTH_LONG).show()
        }
        view.findViewById<Button>(R.id.btnGoBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return view
    }
}