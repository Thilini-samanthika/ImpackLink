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
import com.google.firebase.auth.FirebaseAuth

class MainLoginActivity : AppCompatActivity() {

    // Firebase Auth ප්‍රකාශයට පත් කිරීම
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_login)

        // Firebase Auth Initialize කිරීම
        auth = FirebaseAuth.getInstance()

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignIn = findViewById<AppCompatButton>(R.id.btnSignIn)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvCreateAccount = findViewById<TextView>(R.id.tvCreateAccount)

        // 1. Sign In Button එකේ වැඩකටයුතු
        btnSignIn.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Firebase හරහා ලොගින් වීම පරීක්ෂා කිරීම
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // ලොගින් වීම සාර්ථකයි නම් RoleSelectionActivity වෙත යාම
                            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, RoleSelectionActivity::class.java)
                            startActivity(intent)
                            finish() // Login පිටුව වසා දැමීම
                        } else {
                            // තොරතුරු වැරදි නම් හෝ වෙනත් දෝෂයක් ඇත්නම්
                            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter Email and Password", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. Forgot Password ක්ලික් කළ විට ForgotPasswordActivity වෙත යාම
        tvForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // 3. Create Account ක්ලික් කළ විට SignUpActivity වෙත යාම
        tvCreateAccount.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // System bars padding (Interface එක ලස්සනට තබා ගැනීමට)
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