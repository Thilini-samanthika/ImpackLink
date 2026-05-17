package com.example.impacklink // ඔයාගේ package නම දාන්න

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProjectAnalysisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_analysis)

        val ivHome = findViewById<ImageView>(R.id.ivHome)
        val ivMenuGrid = findViewById<ImageView>(R.id.ivMenuGrid)
        ivHome.setOnClickListener {
            val intent = Intent(this, NgoDashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        ivMenuGrid.setOnClickListener {
            val intent = Intent(this, RoleSelectionActivity::class.java)
            startActivity(intent)
        }
    }
}