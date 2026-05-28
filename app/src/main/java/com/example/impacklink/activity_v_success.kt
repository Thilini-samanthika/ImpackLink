package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ActivityVSuccess : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_page)


        val btnBack = findViewById<ImageView>(R.id.btnBack)


        btnBack.setOnClickListener {


            val intent = Intent(this, UpdateProjectActivity::class.java)
            startActivity(intent)


            finish()
        }
    }
}


