package com.example.impacklink // ඔයාගේ ඇප් එකේ පැකේජ් නම මෙතන තියෙන්න ඕනේ

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RoleSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selection)

        // 1. XML එකේ තියෙන බොත්තම් (Buttons) සොයා සම්බන්ධ කරගැනීම
        val btnNgo = findViewById<Button>(R.id.btnNgo)
        val btnVolunteer = findViewById<Button>(R.id.btnVolunteer)
        val btnDonor = findViewById<Button>(R.id.btnDonor)
        val btnCompany = findViewById<Button>(R.id.btnCompany)

        // 2. යටම තියෙන Profile Icon එක සම්බන්ධ කරගැනීම
        val ivProfile = findViewById<ImageView>(R.id.ivProfile)


        // ================= බොත්තම් වල ක්ලික් ඉවෙන්ට්ස් (Click Listeners) =================

        // NGO බටන් එක ක්ලික් කළ විට NgoDashboardActivity එකට යාම
        btnNgo.setOnClickListener {
            val intent = Intent(this, NgoDashboardActivity::class.java)
            startActivity(intent)
        }

        // Volunteer බටන් එක ක්ලික් කළ විට VolunteerDashboardActivity එකට යාම
        btnVolunteer.setOnClickListener {
            val intent = Intent(this, VolunteerDashboardActivity::class.java)
            startActivity(intent)
        }

        // Donor බටන් එක ක්ලික් කළ විට DonorDashboardActivity එකට යාම
        btnDonor.setOnClickListener {
            val intent = Intent(this, DonorDashboardActivity::class.java)
            startActivity(intent)
        }

        // Company බටන් එක ක්ලික් කළ විට CompanyDashboardActivity එකට යාම
        btnCompany.setOnClickListener {
            val intent = Intent(this, CompanyDashboardActivity::class.java)
            startActivity(intent)
        }

        // Profile Icon එක ක්ලික් කළ විට ProfileSettingsActivity එකට යාම
        ivProfile.setOnClickListener {
            val intent = Intent(this, ProfileSettingsActivity::class.java)
            startActivity(intent)
        }
    }
}