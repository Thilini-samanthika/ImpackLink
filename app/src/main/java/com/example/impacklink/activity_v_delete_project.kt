package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class VolunteerDeleteProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vdelete_project)

        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        btnDelete.setOnClickListener {
            // Volunteer Specific Success Activity (or reuse NGO one for now)
            val intent = Intent(this, ActivityVSuccess::class.java)
            startActivity(intent)
            finish()
        }

        btnCancel.setOnClickListener {
            // Navigate back to Volunteer View Project
            val intent = Intent(this, VolunteerViewProjectActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
