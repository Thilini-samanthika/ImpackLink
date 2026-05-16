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

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reset_password)

        val etNew = findViewById<EditText>(R.id.etNewPassword)
        val etConfirm = findViewById<EditText>(R.id.etConfirmPassword)
        val btnConfirm = findViewById<AppCompatButton>(R.id.btnConfirm)
        val tvSignIn = findViewById<TextView>(R.id.tvSignInReset)

        btnConfirm.setOnClickListener {
            val pass1 = etNew.text.toString()
            val pass2 = etConfirm.text.toString()

            if (pass1.isNotEmpty() && pass1 == pass2) {
                val intent = Intent(this, VerifyActivity::class.java)
                startActivity(intent)
            } else if (pass1 != pass2) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }


        tvSignIn.setOnClickListener {
            startActivity(Intent(this, MainLoginActivity::class.java))
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