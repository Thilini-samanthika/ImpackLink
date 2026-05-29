package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.impacklink.api.AuthResponse
import com.example.impacklink.api.RetrofitClient
import model.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateProjectActivity : AppCompatActivity() {

    private var currentProject: Project? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_project)

        currentProject = intent.getSerializableExtra("PROJECT_DATA") as? Project

        val etTitle = findViewById<EditText>(R.id.etUpdateProjectTitle)
        val etCategory = findViewById<EditText>(R.id.etUpdateCategory)
        val etDescription = findViewById<EditText>(R.id.etUpdateDescription)
        val etVolunteers = findViewById<EditText>(R.id.etUpdateRequiredVolunteers)
        val etBudget = findViewById<EditText>(R.id.etUpdateBudgetGoal)
        val spinnerStatus = findViewById<Spinner>(R.id.spinnerStatus)
        val btnSaveProject = findViewById<Button>(R.id.btnSaveProject)

        val statusOptions = arrayOf("Active", "Completed")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statusOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStatus.adapter = adapter

        // Populate fields if data was passed
        currentProject?.let { project ->
            etTitle.setText(project.title)
            etDescription.setText(project.description)
            etBudget.setText(project.budget.toString())
            val statusIndex = statusOptions.indexOf(project.status)
            if (statusIndex >= 0) spinnerStatus.setSelection(statusIndex)
        }

        btnSaveProject.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val budgetStr = etBudget.text.toString().trim()
            val status = spinnerStatus.selectedItem.toString()

            if (title.isEmpty()) {
                Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val budget = budgetStr.toDoubleOrNull() ?: 0.0

            val updatedProject = Project(
                id = currentProject?.id ?: 0,
                user_id = currentProject?.user_id ?: 0,
                title = title,
                description = description,
                budget = budget,
                status = status
            )

            RetrofitClient.instance.updateProject(updatedProject).enqueue(object : Callback<AuthResponse> {
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    if (response.isSuccessful && response.body()?.status == "success") {
                        // Navigate to Save Success page as requested
                        val intent = Intent(this@UpdateProjectActivity, NgoSaveProjectActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@UpdateProjectActivity, "Update Failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Toast.makeText(this@UpdateProjectActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
