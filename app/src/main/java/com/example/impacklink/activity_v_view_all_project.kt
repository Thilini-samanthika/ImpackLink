package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class VolunteerViewAllProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vview_all_project)


        val btnApplyProject1 = findViewById<Button>(R.id.btnApplyProject1)
        val btnApplyProject2 = findViewById<Button>(R.id.btnApplyProject2)
        val btnPendingProject3 = findViewById<Button>(R.id.btnPendingProject3)


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)


        btnApplyProject1.setOnClickListener {
            Toast.makeText(this, "Apply Success Full", Toast.LENGTH_SHORT).show()
        }

        btnApplyProject2.setOnClickListener {
            Toast.makeText(this, "Apply Success Full", Toast.LENGTH_SHORT).show()
        }


        btnPendingProject3.setOnClickListener {
            Toast.makeText(this, "Pending", Toast.LENGTH_SHORT).show()
        }


        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.menu_home -> {
                    val intent = Intent(this, VolunteerDashboardActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.menu_reports -> {
                    Toast.makeText(this, "View All Project ", Toast.LENGTH_SHORT).show()
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
}
