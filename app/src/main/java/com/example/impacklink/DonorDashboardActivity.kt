package com.example.impacklink

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class DonorDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_donor_dashboard)

        findViewById<CardView>(R.id.cardDonationAction).setOnClickListener {
            Toast.makeText(this, "Opening Donation Panel", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnDonateImpact).setOnClickListener {
            Toast.makeText(this, "Loading Metrics Dashboard...", Toast.LENGTH_SHORT).show()
        }
    }
}
