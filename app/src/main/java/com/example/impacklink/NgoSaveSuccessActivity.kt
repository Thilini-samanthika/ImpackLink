package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class NgoSaveProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_save_success)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnDone = findViewById<Button>(R.id.btnDone)

        btnBack.setOnClickListener {
            val intent = Intent(this, NgoViewProjectActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnDone.setOnClickListener {
            val intent = Intent(this, NgoViewProjectActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}