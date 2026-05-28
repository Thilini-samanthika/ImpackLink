package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class CreateProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_project)

        val btnConfirmProject = findViewById<Button>(R.id.btnConfirmProject)
        btnConfirmProject.setOnClickListener {


            Toast.makeText(this, "Successful Create", Toast.LENGTH_SHORT).show()


            val intent = Intent(this, NgoViewProjectsActivity::class.java)
            startActivity(intent)


            finish()
        }


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {

                    val intent = Intent(this, NgoDashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.menu_reports -> {

                    val intent = Intent(this, ProjectAnalysisActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_apps -> {

                    val intent = Intent(this, RoleSelectionActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}