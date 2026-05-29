package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.impacklink.adapter.VolunteerApplicationAdapter
import model.Application

class VolunteerDashboardActivity : AppCompatActivity() {

    private lateinit var rvAppliedProjectsDashboard: RecyclerView
    private var volunteerName: String = "Volunteer"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer_dashboard)

        // Load volunteer name from session
        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        volunteerName = sharedPref.getString("userName", "Volunteer") ?: "Volunteer"
        
        findViewById<TextView>(R.id.txtUserName).text = getString(R.string.welcome_user, volunteerName)

        // --- 1. Top Bar Icons ---
        val imgUserIcon = findViewById<ImageView>(R.id.imgUserIcon)
        val imgBellIcon = findViewById<ImageView>(R.id.imgBellIcon)

        // --- 2. Applied Projects List (View Project Section) ---
        rvAppliedProjectsDashboard = findViewById(R.id.rvAppliedProjectsDashboard)
        rvAppliedProjectsDashboard.layoutManager = LinearLayoutManager(this)
        updateAppliedProjects()

        // --- 3. Main Action Buttons ---
        val btnTimeTracking = findViewById<Button>(R.id.btnTimeTracking)
        val btnProjectMap = findViewById<Button>(R.id.btnProjectMap)
        val ivViewProjectArrow = findViewById<ImageView>(R.id.ivViewProjectArrow)
        val ivTrophy = findViewById<ImageView>(R.id.ivTrophy)

        // --- 4. Bottom Navigation Bar ---
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)


        // ================= LOGIC & CLICK LISTENERS =================

        imgUserIcon.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            startActivity(intent)
        }

        imgBellIcon.setOnClickListener {
            val intent = Intent(this, NotificationCenterActivity::class.java)
            startActivity(intent)
        }

        btnTimeTracking.setOnClickListener {
            val intent = Intent(this, VolunteerTimeTrackingActivity::class.java)
            startActivity(intent)
        }

        btnProjectMap.setOnClickListener {
            val intent = Intent(this, VolunteerProjectMapActivity::class.java)
            startActivity(intent)
        }

        ivViewProjectArrow.setOnClickListener {
            val intent = Intent(this, VolunteerViewProjectActivity::class.java)
            startActivity(intent)
        }

        ivTrophy.setOnClickListener {
            val intent = Intent(this, VolunteerAchievementActivity::class.java)
            startActivity(intent)
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_reports -> {
                    val intent = Intent(this, NotificationCenterActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_apps -> {
                    val intent = Intent(this, RoleSelectionActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_profile -> {
                    val intent = Intent(this, ProfileSettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_home -> {
                    Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateAppliedProjects()
    }

    private fun updateAppliedProjects() {
        val myApps = Application.applicationList.filter { it.volunteerName == volunteerName }
        rvAppliedProjectsDashboard.adapter = VolunteerApplicationAdapter(myApps)
    }
}
