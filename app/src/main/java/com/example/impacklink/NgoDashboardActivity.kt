package com.example.impacklink // ඔයාගේ ඇප් එකේ package නම මෙතනට දාන්න

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NgoDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_dashboard)

        // 1. XML එකේ තියෙන Views හඳුනාගැනීම
        val ivBell = findViewById<ImageView>(R.id.iv_bell)
        val btnViewAll = findViewById<Button>(R.id.btn_view_all)

        // Bottom Navigation Bar එකේ තියෙන Icons
        val ivHome = findViewById<ImageView>(R.id.ivHome)
        val ivDocument = findViewById<ImageView>(R.id.ivDocument) // Layout එකේ අලුතින් තියෙන ලේඛන අයිකනය
        val ivMenuGrid = findViewById<ImageView>(R.id.ivMenuGrid)
        val ivProfileSettings = findViewById<ImageView>(R.id.ivProfileSettings)


        // 2. Notification Bell එක ක්ලික් කළ විට (Figma එකේ කහ පාට බෙල් එක)
        ivBell.setOnClickListener {
            Toast.makeText(this, "You have new notifications!", Toast.LENGTH_SHORT).show()
        }


        // 3. "View All" බටන් එක ක්ලික් කළ විට Notification Center එකට යෑම
        btnViewAll.setOnClickListener {
            // Notification Center පිටුව විවෘත කිරීමට Intent එකක් පාවිච්චි කරයි
            val intent = Intent(this, NotificationCenterActivity::class.java)
            startActivity(intent)
        }


        // 4. Bottom Navigation Icons වලට Click Listeners දැමීම
        ivHome.setOnClickListener {
            // දැනටමත් ඉන්නේ Dashboard (Home) එකේ නිසා පොඩි මැසේජ් එකක් දාන්න පුළුවන්
            Toast.makeText(this, "You are already on Dashboard", Toast.LENGTH_SHORT).show()
        }

        ivDocument.setOnClickListener {
            // Projects සම්බන්ධ වෙනත් පිටුවක් තියෙනවා නම් ඒකට මෙතනින් Intent එකක් දාන්න
        }

        ivMenuGrid.setOnClickListener {
            // මැද Grid අයිකනයට අදාළ පිටුවට Intent එකක් මෙතනින් දාන්න
        }

        // ⚙️ යටම දකුණු පැත්තේ තියෙන Settings අයිකනය ක්ලික් කළ විට Profile Edit එකට යෑම
        ivProfileSettings.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            startActivity(intent)
        }
    }
}