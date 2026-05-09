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

class MainLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignIn = findViewById<AppCompatButton>(R.id.btnSignIn)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword) // XML එකේ මේ ID එක පරීක්ෂා කරන්න
        val tvCreateAccount = findViewById<TextView>(R.id.tvCreateAccount)

        btnSignIn.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val intent = Intent(this, RoleSelectionActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter Email and Password", Toast.LENGTH_SHORT).show()
            }
        }
        tvForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        tvCreateAccount.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // System bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}