package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class VolunteerEditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_v_edit_profile)

        val spinnerSkill = findViewById<Spinner>(R.id.spinnerSkill)
        val spinnerAvailability = findViewById<Spinner>(R.id.spinnerAvailability)

        val skills = arrayOf("design", "development", "marketing", "management")
        val adapterSkill = ArrayAdapter(this, android.R.layout.simple_spinner_item, skills)
        adapterSkill.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSkill.adapter = adapterSkill

        val availabilities = arrayOf("weekdays", "weekends", "both")
        val adapterAvailability = ArrayAdapter(this, android.R.layout.simple_spinner_item, availabilities)
        adapterAvailability.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAvailability.adapter = adapterAvailability

        val btnSaveProfile = findViewById<Button>(R.id.btnSaveProfile)
        val btnDeleteProfile = findViewById<Button>(R.id.btnDeleteProfile)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        btnSaveProfile.setOnClickListener {
            Toast.makeText(this, "Save Successful", Toast.LENGTH_SHORT).show()
        }

        btnDeleteProfile.setOnClickListener {
            Toast.makeText(this, "Delete profile functionality coming soon", Toast.LENGTH_SHORT).show()
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    val intent = Intent(this, VolunteerDashboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_reports -> {
                    val intent = Intent(this, VolunteerViewAllProjectActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_apps -> {
                    val intent = Intent(this, RoleSelectionActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
