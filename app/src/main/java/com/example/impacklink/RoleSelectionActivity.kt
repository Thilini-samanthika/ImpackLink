package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RoleSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selection)

        val btnNgo = findViewById<Button>(R.id.btnNgo)
        val btnVolunteer = findViewById<Button>(R.id.btnVolunteer)
        val btnDonor = findViewById<Button>(R.id.btnDonor)
        val btnCompany = findViewById<Button>(R.id.btnCompany)
        val ivProfile = findViewById<ImageView>(R.id.ivProfile)

        btnNgo.setOnClickListener {
            val intent = Intent(this, NgoDashboardActivity::class.java)
            startActivity(intent)
        }

        btnVolunteer.setOnClickListener {
            // val intent = Intent(this, VolunteerDashboardActivity::class.java)
            // startActivity(intent)
        }

        btnDonor.setOnClickListener {
            // val intent = Intent(this, DonorDashboardActivity::class.java)
            // startActivity(intent)
        }

        btnCompany.setOnClickListener {
            // val intent = Intent(this, CompanyDashboardActivity::class.java)
            // startActivity(intent)
        }

        ivProfile.setOnClickListener {
            val intent = Intent(this, ProfileSettingsActivity::class.java)
            startActivity(intent)
        }
    }
}