package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class CardDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_card_details, container, false)

        val etCardName = view.findViewById<EditText>(R.id.etCardName)
        val etCardNumber = view.findViewById<EditText>(R.id.etCardNumber)
        val etCardExpiry = view.findViewById<EditText>(R.id.etCardExpiry)
        val etCardCvv = view.findViewById<EditText>(R.id.etCardCvv)

        view.findViewById<Button>(R.id.btnConfirmCard).setOnClickListener {
            val name = etCardName.text.toString()
            val number = etCardNumber.text.toString()
            val expiry = etCardExpiry.text.toString()
            val cvv = etCardCvv.text.toString()

            if (name.isEmpty() || number.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
                Toast.makeText(context, "Please enter valid card details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Mock database update success
            Toast.makeText(context, "Donation Updated Successfully!", Toast.LENGTH_SHORT).show()
            
            // Navigate to Success page
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SuccessDonationFragment())
                .commit()
        }

        view.findViewById<View>(R.id.ivHeaderIcon).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return view
    }
}