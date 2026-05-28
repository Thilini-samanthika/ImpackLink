package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.impacklink.adapter.ProjectAdapter
import com.example.impacklink.api.RetrofitClient
import model.ProjectResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NgoViewProjectActivity : AppCompatActivity() {

    private lateinit var rvProjects: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ngo_view_project_activity)

        val ivBack = findViewById<ImageView>(R.id.ivBack)
        val btnAddNewProject = findViewById<AppCompatButton>(R.id.btnAddNewProject)
        val btnUpdateProject = findViewById<AppCompatButton>(R.id.btnUpdateProject)
        rvProjects = findViewById(R.id.rvProjects)

        rvProjects.layoutManager = LinearLayoutManager(this)

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

    override fun onResume() {
        super.onResume()
        fetchProjects()
    }

    private fun fetchProjects() {
        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        val userId = sharedPref.getInt("userId", 0)

        RetrofitClient.instance.getProjects(userId).enqueue(object : Callback<ProjectResponse> {
            override fun onResponse(call: Call<ProjectResponse>, response: Response<ProjectResponse>) {
                if (response.isSuccessful) {
                    val projects = response.body()?.projects ?: emptyList()
                    rvProjects.adapter = ProjectAdapter(projects)
                } else {
                    Toast.makeText(this@NgoViewProjectActivity, "Failed to load projects", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProjectResponse>, t: Throwable) {
                Toast.makeText(this@NgoViewProjectActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
