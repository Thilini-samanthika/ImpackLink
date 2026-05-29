package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.impacklink.api.AuthResponse
import com.example.impacklink.api.RetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import model.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_project)

        val etProjectTitle = findViewById<EditText>(R.id.etProjectTitle)
        val etCategory = findViewById<EditText>(R.id.etCategory)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val etRequiredVolunteers = findViewById<EditText>(R.id.etRequiredVolunteers)
        val etBudgetGoal = findViewById<EditText>(R.id.etBudgetGoal)
        val btnConfirmProject = findViewById<Button>(R.id.btnConfirmProject)

        btnConfirmProject.setOnClickListener {
            val title = etProjectTitle.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val budgetStr = etBudgetGoal.text.toString().trim()
            val volunteersStr = etRequiredVolunteers.text.toString().trim()

            if (title.isEmpty()) {
                Toast.makeText(this, "Please enter project title", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val budget = budgetStr.toDoubleOrNull() ?: 0.0
            val volunteersCount = volunteersStr.toIntOrNull() ?: 0
            val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
            val userId = sharedPref.getInt("userId", 0)
            
            println("DEBUG: Sending Project with User ID: $userId")

            if (userId == 0) {
                Toast.makeText(this, "Session expired. Please login again.", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainLoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                return@setOnClickListener
            }

            val project = Project(
                id = 0,
                user_id = userId,
                title = title,
                description = description,
                budget = budget,
                status = "Active",
                required_volunteers = volunteersCount
            )

            RetrofitClient.instance.createProject(project).enqueue(object : Callback<AuthResponse> {
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    if (response.isSuccessful && response.body()?.status == "success") {
                        // Add to local logic link
                        Project.projectList.add(project.copy(id = Project.projectList.size + 1))

                        Toast.makeText(this@CreateProjectActivity, "Project Created Successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@CreateProjectActivity, NgoViewProjectActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val msg = response.body()?.message ?: response.errorBody()?.string() ?: "Unknown error"
                        Toast.makeText(this@CreateProjectActivity, "Failed: $msg", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Toast.makeText(this@CreateProjectActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
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
