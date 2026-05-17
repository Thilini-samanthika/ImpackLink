package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class NgoProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ngo_profile)

        val btnEditProfile = findViewById<Button>(R.id.btnEditProfile)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        btnEditProfile.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            startActivity(intent)
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, NgoDashboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_apps -> {
                    val intent = Intent(this, RoleSelectionActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_profile -> {
                    // Already here
                    true
                }
                else -> false
            }
        }
    }
}