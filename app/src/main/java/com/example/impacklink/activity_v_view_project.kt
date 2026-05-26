package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class VolunteerViewProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vview_project)


        val btnApply = findViewById<Button>(R.id.btnApply)
        val btnViewAll = findViewById<Button>(R.id.btnViewAll)


        val statusApproved1 = findViewById<TextView>(R.id.statusApproved1)
        val statusApproved2 = findViewById<TextView>(R.id.statusApproved2)
        val statusPending1 = findViewById<TextView>(R.id.statusPending1)
        val statusPending2 = findViewById<TextView>(R.id.statusPending2)


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)



        btnApply.setOnClickListener {
            Toast.makeText(this, "Apply Success Full", Toast.LENGTH_SHORT).show()
        }


        btnViewAll.setOnClickListener {
            Toast.makeText(this, "View All Projects coming soon", Toast.LENGTH_SHORT).show()
        }


        statusApproved1.setOnClickListener {
            Toast.makeText(this, "Approved Success Full", Toast.LENGTH_SHORT).show()
        }

        statusApproved2.setOnClickListener {
            Toast.makeText(this, "Approved Success Full", Toast.LENGTH_SHORT).show()
        }


        statusPending1.setOnClickListener {
            Toast.makeText(this, "Pending", Toast.LENGTH_SHORT).show()
        }

        statusPending2.setOnClickListener {
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
}
