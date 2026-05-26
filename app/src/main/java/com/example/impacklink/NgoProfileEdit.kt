package com.example.impacklink // ඔයාගේ නිවැරදිම පැකේජ් නාමය

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ngo_edit_profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit) // ඔයාගේ XML layout එකේ නම

        // 1. VIEW ALL PROJECT බොත්තම ක්‍රියාකරවීම (මෙතන ID එක මම නිවැරදි කළා මචං)
        val btnViewAllProject = findViewById<Button>(R.id.btnViewAllProject)
        btnViewAllProject.setOnClickListener {
            // view all project ක්ලික් කළ විට -> Ngo View Project Page එකට යාම
            val intent = Intent(this, NgoViewProjectActivity::class.java)
            startActivity(intent)
        }

        // 2. Bottom Navigation Bar එක සම්බන්ධ කර ගැනීම සහ ක්‍රියාකාරීත්වය
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // දැනට ඉන්නේ Profile Edit එකේ නිසා යට බාර් එකේ Profile Settings Icon එක select වී පෙන්වීමට
        bottomNavigation.selectedItemId = R.id.menu_profile

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    // Home ක්ලික් කළ විට -> NGO Dashboard Page එකට යාම
                    val intent = Intent(this, NgoDashboardActivity::class.java)
                    startActivity(intent)
                    finish() // දැනට තියෙන Edit Profile පිටුව Stack එකෙන් අයින් කිරීමට
                    true
                }
                R.id.menu_reports -> {
                    // Report ක්ලික් කළ විට -> Project Analytics (ProjectAnalysisActivity) එකට යාම
                    val intent = Intent(this, ProjectAnalysisActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_apps -> {
                    // Apps ක්ලික් කළ විට -> Role Selection Page එකට යාම
                    val intent = Intent(this, RoleSelectionActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_profile -> {
                    // දැනටමත් ඉන්නේ මේ පිටුවට සම්බන්ධ Profile/Settings කොටසේ නිසා (නැවත වෙනස් නොවේ)
                    true
                }
                else -> false
            }
        }
    }
}