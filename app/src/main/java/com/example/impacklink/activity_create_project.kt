package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import model.Project

class CreateProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_project)

        val etTitle = findViewById<EditText>(R.id.etProjectTitle)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val etBudget = findViewById<EditText>(R.id.etBudgetGoal)
        val btnConfirmProject = findViewById<Button>(R.id.btnConfirmProject)

        btnConfirmProject.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val budgetStr = etBudget.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty() && budgetStr.isNotEmpty()) {
                val budget = budgetStr.toDoubleOrNull() ?: 0.0
                val newProject = Project(
                    id = Project.projectList.size + 1,
                    user_id = 1,
                    title = title,
                    description = description,
                    budget = budget,
                    status = "Active"
                )
                Project.projectList.add(newProject)

                Toast.makeText(this, "Successful Create", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, NgoViewProjectActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
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