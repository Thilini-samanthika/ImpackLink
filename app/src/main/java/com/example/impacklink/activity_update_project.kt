package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpdateProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_project)


        val spinnerStatus = findViewById<Spinner>(R.id.spinnerStatus)


        val statusOptions = arrayOf("Active / Completed", "Active", "Completed")


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statusOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStatus.adapter = adapter



        val btnSaveProject = findViewById<Button>(R.id.btnSaveProject)
        btnSaveProject.setOnClickListener {


            Toast.makeText(this, "Successful Update", Toast.LENGTH_SHORT).show()


            val intent = Intent(this, NgoViewProjectsActivity::class.java)
            startActivity(intent)


            finish()
        }
    }
}