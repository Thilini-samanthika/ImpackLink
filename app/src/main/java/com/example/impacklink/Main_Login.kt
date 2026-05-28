package com.example.impacklink

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.impacklink.api.AuthResponse
import com.example.impacklink.api.LoginRequest
import com.example.impacklink.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainLoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignIn: AppCompatButton
    private lateinit var tvForgotPassword: TextView
    private lateinit var tvCreateAccount: TextView

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)

        initializeViews()

        database = AppDatabase.getDatabase(this)

        // Signup walin awapu values auto fill karanawa
        etEmail.setText(
            intent.getStringExtra("email") ?: ""
        )

        etPassword.setText(
            intent.getStringExtra("password") ?: ""
        )

        btnSignIn.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty()) {
                etEmail.error = "Enter Email"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Enter Password"
                return@setOnClickListener
            }

            loginUser(email, password)
        }

        tvForgotPassword.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ForgotPasswordActivity::class.java
                )
            )
        }

        tvCreateAccount.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SignUpActivity::class.java
                )
            )
        }
    }

    private fun initializeViews() {

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnSignIn = findViewById(R.id.btnSignIn)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        tvCreateAccount = findViewById(R.id.tvCreateAccount)

    }

    private fun loginUser(
        email: String,
        password: String
    ) {

        btnSignIn.isEnabled = false

        val request = LoginRequest(
            email,
            password
        )

        RetrofitClient.instance.login(request)
            .enqueue(object : Callback<AuthResponse> {

                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {

                    btnSignIn.isEnabled = true

                    if (response.isSuccessful) {

                        val authResponse = response.body()

                        if (authResponse?.status == "success") {

                            val user = authResponse.user
                            val role = user?.role ?: "NGO"

                            lifecycleScope.launch {

                                try {
                                    // Save notification
                                    val notification = NotificationEntity(
                                        title = "Login Successful",
                                        description = "Successfully logged in",
                                        role = role,
                                        statusText = "Success",
                                        iconType = "TICK"
                                    )
                                    database.notificationDao().insert(notification)

                                    // Save user profile
                                    if (user != null) {
                                        val profile = UserProfile(
                                            name = user.name,
                                            email = user.email,
                                            mobile = user.mobile ?: "",
                                            about = user.about ?: "",
                                            role = role,
                                            accountHolderName = user.account_holder ?: "",
                                            accountNumber = user.account_number ?: ""
                                        )
                                        database.userProfileDao().insertOrUpdateProfile(profile)
                                    }

                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    // We still proceed to navigate even if local DB save fails
                                }

                                Toast.makeText(
                                    this@MainLoginActivity,
                                    "Welcome ${user?.name ?: "User"} (ID: ${user?.id})",
                                    Toast.LENGTH_SHORT
                                ).show()

                                // ✅ SAVE SESSION to SharedPreferences
                                val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
                                sharedPref.edit()
                                    .putInt("userId", user?.id ?: 0)
                                    .putBoolean("isLoggedIn", true)
                                    .putString("userRole", role)
                                    .apply()

                                println("DEBUG: Saved User ID: ${user?.id}")

                                // Navigate to Role Selection Activity
                                val intent = Intent(this@MainLoginActivity, RoleSelectionActivity::class.java)
                                intent.putExtra("role", role)
                                startActivity(intent)
                                finish()
                            }

                        } else {

                            Toast.makeText(
                                this@MainLoginActivity,
                                authResponse?.message
                                    ?: "Login Failed",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    } else {

                        val errorMessage =
                            response.errorBody()?.string()
                                ?: "Unknown server error"

                        Toast.makeText(
                            this@MainLoginActivity,
                            errorMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(
                    call: Call<AuthResponse>,
                    t: Throwable
                ) {

                    btnSignIn.isEnabled = true

                    Toast.makeText(
                        this@MainLoginActivity,
                        "Network Error : ${t.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}