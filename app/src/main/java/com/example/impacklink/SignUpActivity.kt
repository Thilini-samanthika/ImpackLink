package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.impacklink.api.AuthResponse
import com.example.impacklink.api.RegisterRequest
import com.example.impacklink.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmailSignUp)
        val etPassword = findViewById<EditText>(R.id.etPasswordSignUp)
        val etMobile = findViewById<EditText>(R.id.etMobile)
        val etAbout = findViewById<EditText>(R.id.etAbout)
        val spinnerRole = findViewById<Spinner>(R.id.spinnerRoleSignUp)
        val etAccountHolder = findViewById<EditText>(R.id.etAccountHolder)
        val etAccountNumber = findViewById<EditText>(R.id.etAccountNumber)
        val btnSignUp = findViewById<AppCompatButton>(R.id.btnSignUp)
        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)

        // Set up Spinner
        val roles = arrayOf("NGO", "VOLUNTEER", "DONOR")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRole.adapter = adapter

        btnSignUp.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val mobile = etMobile.text.toString().trim()
            val about = etAbout.text.toString().trim()
            val role = spinnerRole.selectedItem.toString()
            val holder = etAccountHolder.text.toString().trim()
            val accNo = etAccountNumber.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val request = RegisterRequest(name, email, password, mobile, about, role, holder, accNo)
                
                RetrofitClient.instance.register(request).enqueue(object : Callback<AuthResponse> {
                    override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                        val authResponse = response.body()
                        if (response.isSuccessful && authResponse != null) {
                            if (authResponse.status == "success") {
                                Toast.makeText(this@SignUpActivity, "Registration Successful!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@SignUpActivity, MainLoginActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this@SignUpActivity, "Signup Failed: ${authResponse.message}", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            val errorMsg = response.errorBody()?.string() ?: "Unknown Server Error"
                            Toast.makeText(this@SignUpActivity, "Server Error: $errorMsg", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, "Network Failure: ${t.localizedMessage}. Check your server and internet connection.", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this, "Name, Email, and Password are required", Toast.LENGTH_SHORT).show()
            }
        }

        tvSignIn.setOnClickListener {
            startActivity(Intent(this, MainLoginActivity::class.java))
            finish()
        }
    }
}