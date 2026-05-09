package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class activity_user_agreement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_agreement)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAgree = findViewById<Button>(R.id.btnAgree)
        val checkDonation = findViewById<CheckBox>(R.id.checkDonation)

        btnAgree.isEnabled = false
        btnAgree.alpha = 0.5f

        checkDonation.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                btnAgree.isEnabled = true
                btnAgree.alpha = 1.0f
            } else {
                btnAgree.isEnabled = false
                btnAgree.alpha = 0.5f
            }
        }

        btnAgree.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}