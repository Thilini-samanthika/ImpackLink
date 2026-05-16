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

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        // Firebase Auth Initialize කිරීම
        auth = FirebaseAuth.getInstance()

        // XML එකේ ඇති IDs නිවැරදිව ලබා ගැනීම
        val etEmail = findViewById<EditText>(R.id.etEmailSignUp) // xml එකේ ඇති ID එක බලන්න
        val etPassword = findViewById<EditText>(R.id.etPasswordSignUp)
        val btnSignUp = findViewById<AppCompatButton>(R.id.btnSignUp)
        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)

        // 1. Sign Up Button එක ක්ලික් කළ විට
        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Firebase හරහා ගිණුම සෑදීම
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // ගිණුම සෑදීම සාර්ථකයි, දැන් Verification Email එක යවමු
                            val user = auth.currentUser
                            user?.sendEmailVerification()?.addOnCompleteListener { emailTask ->
                                if (emailTask.isSuccessful) {
                                    Toast.makeText(this, "Account Created! Verification email sent.", Toast.LENGTH_LONG).show()

                                    // VerifyActivity වෙත යොමු කිරීම
                                    val intent = Intent(this, VerifyActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        } else {
                            // Error එකක් ආවොත් (උදා: ඊමේල් එක කලින් පාවිච්චි කර ඇත්නම්)
                            Toast.makeText(this, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. දැනටමත් ගිණුමක් ඇත්නම් Sign In ක්ලික් කළ විට Login පිටුවට යාම
        tvSignIn.setOnClickListener {
            val intent = Intent(this, MainLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // System bars padding
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