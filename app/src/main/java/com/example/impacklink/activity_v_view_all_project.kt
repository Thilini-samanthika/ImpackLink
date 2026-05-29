package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.impacklink.adapter.VolunteerProjectAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import model.Project

class VolunteerViewAllProjectActivity : AppCompatActivity() {

    private lateinit var rvAllProjectsDiscovery: RecyclerView
    private var volunteerName: String = "Volunteer"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vview_all_project)

        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        volunteerName = sharedPref.getString("userName", "Volunteer") ?: "Volunteer"

        rvAllProjectsDiscovery = findViewById(R.id.rvAllProjectsDiscovery)
        rvAllProjectsDiscovery.layoutManager = LinearLayoutManager(this)
        
        fetchProjects()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    val intent = Intent(this, VolunteerDashboardActivity::class.java)
                    startActivity(intent)
                    true
                }
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
                else -> false
            }
        }
    }

    private fun fetchProjects() {
        // Use the Logic Link - shared Source of Truth
        rvAllProjectsDiscovery.adapter = VolunteerProjectAdapter(Project.projectList, volunteerName) {
            // Callback when an application is made
        }
    }
}
