package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.Firebase

class VerifyActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        auth = Firebase.auth
        val user = auth.currentUser

        user?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Verification email sent!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

        val otp1 = findViewById<EditText>(R.id.otp1)
        val otp2 = findViewById<EditText>(R.id.otp2)
        val otp3 = findViewById<EditText>(R.id.otp3)
        val otp4 = findViewById<EditText>(R.id.otp4)
        val otp5 = findViewById<EditText>(R.id.otp5)
        val otp6 = findViewById<EditText>(R.id.otp6)
        val btnVerify = findViewById<AppCompatButton>(R.id.btnVerify)

        // OTP කොටු අතර Cursor එක මාරු කිරීමේ logic එක
        setupOtpInput(otp1, otp2)
        setupOtpInput(otp2, otp3)
        setupOtpInput(otp3, otp4)
        setupOtpInput(otp4, otp5)
        setupOtpInput(otp5, otp6)
        setupOtpInput(otp6, null)

        btnVerify.setOnClickListener {
            val code = otp1.text.toString() + otp2.text.toString() + otp3.text.toString() +
                    otp4.text.toString() + otp5.text.toString() + otp6.text.toString()

            if (code.length == 6) {
                user?.reload()?.addOnCompleteListener {
                    if (user.isEmailVerified) {
                        Toast.makeText(this, "Verification Successful!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Please verify your email via the link sent.", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter full 6-digit code", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupOtpInput(current: EditText, next: EditText?) {
        current.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) next?.requestFocus()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}