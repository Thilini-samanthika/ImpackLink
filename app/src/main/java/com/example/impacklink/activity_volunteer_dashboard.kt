package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class VolunteerDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer_dashboard)

        // --- 1. Top Bar Icons ---
        val imgUserIcon = findViewById<ImageView>(R.id.imgUserIcon)
        val imgBellIcon = findViewById<ImageView>(R.id.imgBellIcon)

        // --- 2. Project Status Buttons ---
        val btnApproved = findViewById<TextView>(R.id.btnApproved)
        val btnPending = findViewById<TextView>(R.id.btnPending)

        // --- 3. Main Action Buttons ---
        val btnTimeTracking = findViewById<Button>(R.id.btnTimeTracking)
        val btnProjectMap = findViewById<Button>(R.id.btnProjectMap)

        // --- 4. Bottom Navigation Bar ---
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)


        // ================= LOGIC & CLICK LISTENERS =================

        // 1. User Icon -> Edit Profile Page
        imgUserIcon.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            startActivity(intent)
        }

        // 2. Bell Icon -> Notification Center
        imgBellIcon.setOnClickListener {
            val intent = Intent(this, NotificationCenterActivity::class.java)
            startActivity(intent)
        }

        // 3. Approved Button -> Display Message
        btnApproved.setOnClickListener {
            Toast.makeText(this, "Approved Successfully", Toast.LENGTH_SHORT).show()
        }

        // 4. Pending Button -> Display Message
        btnPending.setOnClickListener {
            Toast.makeText(this, "Pending", Toast.LENGTH_SHORT).show()
        }

        // 5. Time Tracking Button
        btnTimeTracking.setOnClickListener {
            Toast.makeText(this, "Time Tracking coming soon", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, VolunteerTimeTrackingActivity::class.java)
            // startActivity(intent)
        }

        // 6. Project Map Button
        btnProjectMap.setOnClickListener {
            Toast.makeText(this, "Project Map coming soon", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, VolunteerProjectMapActivity::class.java)
            // startActivity(intent)
        }

        // --- Bottom Navigation Bar Icons Logic ---
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_reports -> {
                    Toast.makeText(this, "Reports coming soon", Toast.LENGTH_SHORT).show()
                    // val intent = Intent(this, ViewAllProjectActivity::class.java)
                    // startActivity(intent)
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
}
