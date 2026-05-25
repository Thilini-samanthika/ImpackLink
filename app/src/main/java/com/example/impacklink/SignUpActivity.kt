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

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etMobile: EditText
    private lateinit var etAbout: EditText
    private lateinit var spinnerRole: Spinner
    private lateinit var etAccountHolder: EditText
    private lateinit var etAccountNumber: EditText
    private lateinit var btnSignUp: AppCompatButton
    private lateinit var tvSignIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initializeViews()
        setupSpinner()

        btnSignUp.setOnClickListener {
            registerUser()
        }

        tvSignIn.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainLoginActivity::class.java
                )
            )
            finish()
        }
    }

    private fun initializeViews() {

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmailSignUp)
        etPassword = findViewById(R.id.etPasswordSignUp)
        etMobile = findViewById(R.id.etMobile)
        etAbout = findViewById(R.id.etAbout)
        spinnerRole = findViewById(R.id.spinnerRoleSignUp)
        etAccountHolder = findViewById(R.id.etAccountHolder)
        etAccountNumber = findViewById(R.id.etAccountNumber)
        btnSignUp = findViewById(R.id.btnSignUp)
        tvSignIn = findViewById(R.id.tvSignIn)
    }

    private fun setupSpinner() {

        val roles = arrayOf(
            "NGO",
            "VOLUNTEER",
            "DONOR"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            roles
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinnerRole.adapter = adapter
    }

    private fun registerUser() {

        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val mobile = etMobile.text.toString().trim()
        val about = etAbout.text.toString().trim()
        val role = spinnerRole.selectedItem.toString()
        val holder = etAccountHolder.text.toString().trim()
        val accNo = etAccountNumber.text.toString().trim()

        if (name.isEmpty()) {
            etName.error = "Enter name"
            return
        }

        if (email.isEmpty()) {
            etEmail.error = "Enter email"
            return
        }

        if (password.isEmpty()) {
            etPassword.error = "Enter password"
            return
        }

        btnSignUp.isEnabled = false

        val request = RegisterRequest(
            name,
            email,
            password,
            mobile,
            about,
            role,
            holder,
            accNo
        )

        RetrofitClient.instance
            .register(request)
            .enqueue(object : Callback<AuthResponse> {

                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {

                    btnSignUp.isEnabled = true

                    if (response.isSuccessful &&
                        response.body() != null
                    ) {

                        val authResponse = response.body()

                        if (authResponse?.status == "success") {

                            Toast.makeText(
                                this@SignUpActivity,
                                "Registration Successful",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(
                                this@SignUpActivity,
                                MainLoginActivity::class.java
                            )

                            intent.putExtra(
                                "email",
                                email
                            )

                            intent.putExtra(
                                "password",
                                password
                            )

                            startActivity(intent)
                            finish()

                        } else {

                            Toast.makeText(
                                this@SignUpActivity,
                                authResponse?.message
                                    ?: "Signup failed",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    } else {

                        Toast.makeText(
                            this@SignUpActivity,
                            "Server error",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(
                    call: Call<AuthResponse>,
                    t: Throwable
                ) {

                    btnSignUp.isEnabled = true

                    Toast.makeText(
                        this@SignUpActivity,
                        "Network Error: ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}