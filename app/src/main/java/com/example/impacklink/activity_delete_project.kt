package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.impacklink.api.AuthResponse
import com.example.impacklink.api.RetrofitClient
import model.Application
import model.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_project)

        val projectId = intent.getIntExtra("PROJECT_ID", -1)

        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        btnDelete.setOnClickListener {
            if (projectId != -1) {
                deleteProject(projectId)
            } else {
                // If no ID passed, proceed to success for UI flow
                val intent = Intent(this, DeleteSuccessActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        btnCancel.setOnClickListener {
            // Navigate back to NGO Dashboard page as requested
            val intent = Intent(this, NgoDashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun deleteProject(id: Int) {
        val request = mapOf("id" to id)
        RetrofitClient.instance.deleteProject(request).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    // Update Logic Link: Remove project and its applications
                    Project.projectList.removeAll { it.id == id }
                    Application.applicationList.removeAll { it.projectId == id }

                    val intent = Intent(this@DeleteProjectActivity, DeleteSuccessActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@DeleteProjectActivity, "Delete Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(this@DeleteProjectActivity, "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
