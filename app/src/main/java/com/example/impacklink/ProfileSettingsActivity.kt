package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_settings)
        val layoutProfile = findViewById<LinearLayout>(R.id.layoutProfile)
        val layoutNotifications = findViewById<LinearLayout>(R.id.layoutNotifications)
        val layoutLogout = findViewById<LinearLayout>(R.id.layoutLogout)

        layoutProfile.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            startActivity(intent)
        }
        layoutNotifications.setOnClickListener {
            val intent = Intent(this, NotificationCenterActivity::class.java)
            startActivity(intent)
        }

        layoutLogout.setOnClickListener {
            Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, splashpage::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}