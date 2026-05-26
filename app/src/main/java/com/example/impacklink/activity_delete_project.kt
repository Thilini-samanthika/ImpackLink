package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DeleteProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_project)

        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        btnDelete.setOnClickListener {

            val intent = Intent(this, NgoDeleteSuccessActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnCancel.setOnClickListener {

            val intent = Intent(this, NgoViewProjectActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
