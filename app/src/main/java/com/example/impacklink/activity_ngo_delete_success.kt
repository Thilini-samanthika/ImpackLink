package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class NgoDeleteSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_delete_success)

        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener {
            // Ngo Dashboard එකට හෝ Project View එකට redirect කරන්න පුළුවන්
            val intent = Intent(this, NgoViewProjectActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}