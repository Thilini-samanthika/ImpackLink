package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.impacklink.adapter.VolunteerApplicationAdapter
import com.example.impacklink.adapter.VolunteerProjectAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import model.Application
import model.Project

class VolunteerViewProjectActivity : AppCompatActivity() {

    private lateinit var rvAvailableProjects: RecyclerView
    private lateinit var rvMyApplications: RecyclerView
    private var volunteerName: String = "Volunteer"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vview_project)

        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        volunteerName = sharedPref.getString("userName", "Volunteer") ?: "Volunteer"

        rvAvailableProjects = findViewById(R.id.rvAvailableProjects)
        rvMyApplications = findViewById(R.id.rvMyApplications)
        val btnViewAll = findViewById<Button>(R.id.btnViewAll)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        rvAvailableProjects.layoutManager = LinearLayoutManager(this)
        rvMyApplications.layoutManager = LinearLayoutManager(this)

        refreshUI()

        btnViewAll.setOnClickListener {
            val intent = Intent(this, VolunteerViewAllProjectActivity::class.java)
            startActivity(intent)
        }

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

    private fun refreshUI() {
        // Source of Truth logic link
        rvAvailableProjects.adapter = VolunteerProjectAdapter(Project.projectList, volunteerName) {
            updateApplicationsList()
        }
        updateApplicationsList()
    }

    private fun updateApplicationsList() {
        val myApps = Application.applicationList.filter { it.volunteerName == volunteerName }
        rvMyApplications.adapter = VolunteerApplicationAdapter(myApps)
    }

    override fun onResume() {
        super.onResume()
        refreshUI()
    }
}
