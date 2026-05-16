package com.example.impacklink // ඔයාගේ ඇප් එකේ package නම

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_settings)

        // XML එකේ තියෙන Views හඳුනාගැනීම
        val layoutProfile = findViewById<LinearLayout>(R.id.layoutProfile)
        val layoutNotifications = findViewById<LinearLayout>(R.id.layoutNotifications)
        val layoutLogout = findViewById<LinearLayout>(R.id.layoutLogout)

        // 1. Profile බටන් එක ක්ලික් කළ විට ProfileEditActivity එකට යාම
        layoutProfile.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            startActivity(intent)
        }

        // 2. Notifications බටන් එක ක්ලික් කළ විට NotificationCenterActivity එකට යාම
        layoutNotifications.setOnClickListener {
            val intent = Intent(this, NotificationCenterActivity::class.java)
            startActivity(intent)
        }

        // 3. Log Out බටන් එක ක්ලික් කළ විට SplashActivity එකට යාම
        layoutLogout.setOnClickListener {
            Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, SplashActivity::class.java)
            // Back stack එක clear කරනවා, User ට ආපහු settings වලට එන්න බැරි වෙන්න
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}