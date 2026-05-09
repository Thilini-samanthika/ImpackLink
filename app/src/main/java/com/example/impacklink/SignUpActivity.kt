package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        val btnSignUp = findViewById<AppCompatButton>(R.id.btnSignUp)
        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)

        btnSignUp.setOnClickListener {
            Toast.makeText(this, "Account Created! Please Login.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvSignIn.setOnClickListener {
            val intent = Intent(this, MainLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}