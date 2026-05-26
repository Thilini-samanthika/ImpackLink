package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class NgoViewProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.NgoViewProjectActivity)

        val ivBack = findViewById<ImageView>(R.id.ivBack)
        val btnAddNewProject = findViewById<AppCompatButton>(R.id.btnAddNewProject)
        val btnUpdateProject = findViewById<AppCompatButton>(R.id.btnUpdateProject)

        ivBack.setOnClickListener {
            onBackPressed()
        }

        btnAddNewProject.setOnClickListener {
            val intent = Intent(this, CreateProjectActivity::class.java)
            startActivity(intent)
        }

        btnUpdateProject.setOnClickListener {
            val intent = Intent(this, UpdateProjectActivity::class.java)
            startActivity(intent)
        }
    }
}