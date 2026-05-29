package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RoleSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selection2)

        val btnNgo = findViewById<Button>(R.id.btnNgo)
        val btnVolunteer = findViewById<Button>(R.id.btnVolunteer)
        val btnDonor = findViewById<Button>(R.id.btnDonor)
        val btnCompany = findViewById<Button>(R.id.btnCompany)
        val ivProfile = findViewById<ImageView>(R.id.ivProfile)
        val ivHome = findViewById<ImageView>(R.id.ivHome)

        btnNgo.setOnClickListener {
            navigateToDashboard("NGO")
        }

        btnVolunteer.setOnClickListener {
            navigateToDashboard("VOLUNTEER")
        }

        btnDonor.setOnClickListener {
            navigateToDashboard("DONOR")
        }

        btnCompany.setOnClickListener {
            navigateToDashboard("COMPANY")
        }

        ivProfile.setOnClickListener {
            val intent = Intent(this, ProfileSettingsActivity::class.java)
            startActivity(intent)
        }

        ivHome.setOnClickListener {
            // Already on role selection/home
        }
    }

    private fun navigateToDashboard(role: String) {
        val intent = when (role.uppercase()) {
            "NGO" -> Intent(this, NgoDashboardActivity::class.java)
            "VOLUNTEER" -> Intent(this, VolunteerDashboardActivity::class.java)
            "DONOR" -> Intent(this, DonorDashboardActivity::class.java)
            "COMPANY" -> Intent(this, CsrDashboardActivity::class.java)
            else -> null
        }
        intent?.let {
            startActivity(it)
            // finish() // Optional: whether to finish RoleSelection or not
        }
    }
}