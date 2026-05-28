package com.example.impacklink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class NgoProfileActivity : AppCompatActivity() {

    private val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ngo_profile)

        val imgHeaderIcon = findViewById<ImageView>(R.id.imgHeaderIcon)
        imgHeaderIcon.setOnClickListener {
            finish()
        }

        val btnEditProfile = findViewById<Button>(R.id.btnEditProfile)
        btnEditProfile.setOnClickListener {
            val intent = Intent(this, NgoProfileEditActivity::class.java)
            startActivity(intent)
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigation.selectedItemId = R.id.menu_profile

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, NgoDashboardActivity::class.java))
                    finish()
                    true
                }
                R.id.menu_reports -> {
                    startActivity(Intent(this, ProjectAnalysisActivity::class.java))
                    true
                }
                R.id.menu_apps -> {
                    startActivity(Intent(this, RoleSelectionActivity::class.java))
                    true
                }
                R.id.menu_profile -> true
                else -> false
            }
        }

        loadProfileData()
    }

    override fun onResume() {
        super.onResume()
        loadProfileData()
    }

    private fun loadProfileData() {
        lifecycleScope.launch {
            val profile = database.userProfileDao().getUserProfile()
            profile?.let {
                findViewById<TextView>(R.id.tvNgoName).text = it.name
                findViewById<TextView>(R.id.tvNgoCategory).text = it.about
                findViewById<TextView>(R.id.tvNgoIdValue).text = it.ngoId
                findViewById<TextView>(R.id.tvLocationValue).text = it.location
                findViewById<TextView>(R.id.tvEmailValue).text = it.email
                findViewById<TextView>(R.id.tvPhoneValue).text = it.mobile
                findViewById<TextView>(R.id.tvWebValue).text = it.website
                findViewById<TextView>(R.id.tvManagerName).text = it.accountHolderName
                findViewById<TextView>(R.id.tvPositionValue).text = it.positionName
                
                it.profileImageUri?.let { uriString ->
                    findViewById<ImageView>(R.id.imgProfileLogo).setImageURI(Uri.parse(uriString))
                }
            }
        }
    }
}