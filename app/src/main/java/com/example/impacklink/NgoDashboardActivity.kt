package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NgoDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_dashboard)

        // Views
        val ivBell = findViewById<ImageView>(R.id.iv_bell)
        val btnViewAll = findViewById<Button>(R.id.btn_view_all)
        val layoutAllProject = findViewById<RelativeLayout>(R.id.layout_all_project_header)
        val layoutImpactAnalysis = findViewById<RelativeLayout>(R.id.layout_impact_analysis_header)

        // Navigation Icons
        val ivHome = findViewById<ImageView>(R.id.ivHome)
        val ivDocument = findViewById<ImageView>(R.id.ivDocument)
        val ivMenuGrid = findViewById<ImageView>(R.id.ivMenuGrid)
        val ivProfileSettings = findViewById<ImageView>(R.id.ivProfileSettings)

        ivBell.setOnClickListener {
            Toast.makeText(this, "You have new notifications!", Toast.LENGTH_SHORT).show()
        }

        btnViewAll.setOnClickListener {
            val intent = Intent(this, NotificationCenterActivity::class.java)
            startActivity(intent)
        }

        layoutAllProject.setOnClickListener {
            val intent = Intent(this, NgoViewProjectActivity::class.java) 
            startActivity(intent)
        }

        layoutImpactAnalysis.setOnClickListener {
            val intent = Intent(this, ProjectAnalysisActivity::class.java)
            startActivity(intent)
        }

        ivHome.setOnClickListener {
            Toast.makeText(this, "You are already on Dashboard", Toast.LENGTH_SHORT).show()
        }
        ivDocument.setOnClickListener {
            val intent = Intent(this, ProjectAnalysisActivity::class.java)
            startActivity(intent)
        }

        ivMenuGrid.setOnClickListener {
            val intent = Intent(this, RoleSelectionActivity::class.java) // ඔයාගේ Role Selection පිටුවේ නම
            startActivity(intent)
        }

        ivProfileSettings.setOnClickListener {
            val intent = Intent(this, NgoProfileActivity::class.java)
            startActivity(intent)
        }
    }
}