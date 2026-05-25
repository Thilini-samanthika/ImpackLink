package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import com.example.impacklink.api.AuthResponse
import com.example.impacklink.api.LoginRequest
import com.example.impacklink.api.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignIn = findViewById<AppCompatButton>(R.id.btnSignIn)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvCreateAccount = findViewById<TextView>(R.id.tvCreateAccount)

        val database = AppDatabase.getDatabase(this)

        btnSignIn.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val request = LoginRequest(email, password)
                
                RetrofitClient.instance.login(request).enqueue(object : Callback<AuthResponse> {
                    override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                        val authResponse = response.body()
                        if (response.isSuccessful && authResponse != null) {
                            if (authResponse.status == "success") {
                                val user = authResponse.user
                                val role = user?.role ?: "NGO"

                                // Trigger and Save Notification
                                lifecycleScope.launch {
                                    val notification = NotificationEntity(
                                        title = "Login Successful",
                                        description = "You have successfully logged into your profile.",
                                        role = role,
                                        statusText = "Success",
                                        iconType = "TICK"
                                    )
                                    database.notificationDao().insert(notification)
                                    
                                    Toast.makeText(this@MainLoginActivity, "Welcome ${user?.name ?: "User"}!", Toast.LENGTH_SHORT).show()
                                    
                                    val intent = Intent(this@MainLoginActivity, RoleSelectionActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                            } else {
                                Toast.makeText(this@MainLoginActivity, "Login Failed: ${authResponse.message}", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            val errorMsg = response.errorBody()?.string() ?: "Unknown Server Error"
                            Toast.makeText(this@MainLoginActivity, "Server Error: $errorMsg", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                        Toast.makeText(this@MainLoginActivity, "Network Failure: ${t.localizedMessage}. Check your server and internet connection.", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this, "Please enter Email and Password", Toast.LENGTH_SHORT).show()
            }
        }

        tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        tvCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}