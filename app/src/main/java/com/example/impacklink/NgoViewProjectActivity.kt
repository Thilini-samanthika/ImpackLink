package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.impacklink.adapter.ProjectAdapter
import model.Project

class NgoViewProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ngo_view_project_activity)

        val ivBack = findViewById<ImageView>(R.id.ivBack)
        val btnAddNewProject = findViewById<AppCompatButton>(R.id.btnAddNewProject)
        val btnUpdateProject = findViewById<AppCompatButton>(R.id.btnUpdateProject)

        // Initialize RecyclerView
        val rvProjects = findViewById<RecyclerView>(R.id.rvProjects)
        rvProjects.layoutManager = LinearLayoutManager(this)
        rvProjects.adapter = ProjectAdapter(Project.projectList)

        ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
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
        // Refresh the list when returning to this activity
        findViewById<RecyclerView>(R.id.rvProjects).adapter?.notifyDataSetChanged()
    }
}