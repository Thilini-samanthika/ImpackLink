package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)

        // UI Elements
        val etResetEmail = findViewById<EditText>(R.id.etResetEmail)
        val btnSendReset = findViewById<AppCompatButton>(R.id.btnSendReset)
        val tvBackToSignIn = findViewById<TextView>(R.id.tvBackToSignIn)

        btnSendReset.setOnClickListener {
            val email = etResetEmail.text.toString().trim()

            if (email.isNotEmpty()) {
                Toast.makeText(this, "Verification step initiated", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ResetPasswordActivity::class.java)
                startActivity(intent)
            } else {
                etResetEmail.error = "Please enter your email"
                Toast.makeText(this, "Email is required!", Toast.LENGTH_SHORT).show()
            }
        }

        tvBackToSignIn.setOnClickListener {
            val intent = Intent(this, MainLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val mainView = findViewById<android.view.View>(R.id.main)
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}