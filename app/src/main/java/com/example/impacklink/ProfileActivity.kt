package com.example.impacklink

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ProfileEditActivity : AppCompatActivity() {

    private val database by lazy {
        AppDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        // 🟢 Views
        val etName = findViewById<EditText>(R.id.et_name)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etMobile = findViewById<EditText>(R.id.et_mobile)
        val etAbout = findViewById<EditText>(R.id.et_about)
        val spinnerRole = findViewById<Spinner>(R.id.spinner_role)
        val etHolderName = findViewById<EditText>(R.id.et_holder_name)
        val etAccountNumber = findViewById<EditText>(R.id.et_account_number)
        val btnSave = findViewById<Button>(R.id.btn_save)

        val ivHome = findViewById<ImageView>(R.id.ivHome)
        val ivMenuGrid = findViewById<ImageView>(R.id.ivMenuGrid)
        val ivProfileSettings = findViewById<ImageView>(R.id.ivProfileSettings)

        // 🟢 Spinner setup
        val rolesArray = arrayOf("NGO", "Volunteer", "Donor", "Company / CSR")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            rolesArray
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRole.adapter = adapter

        // 🟢 LOAD DATA FROM ROOM
        lifecycleScope.launch {
            val savedProfile = database.userProfileDao().getUserProfile()

            savedProfile?.let {

                etName.setText(it.name)
                etEmail.setText(it.email)
                etMobile.setText(it.mobile)
                etAbout.setText(it.about)

                val pos = adapter.getPosition(it.role)
                if (pos >= 0) spinnerRole.setSelection(pos)

                etHolderName.setText(it.accountHolderName)
                etAccountNumber.setText(it.accountNumber)
            }
        }

        // 🟢 SAVE BUTTON
        btnSave.setOnClickListener {

            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val mobile = etMobile.text.toString().trim()
            val about = etAbout.text.toString().trim()
            val role = spinnerRole.selectedItem.toString()
            val holderName = etHolderName.text.toString().trim()
            val accountNumber = etAccountNumber.text.toString().trim()

            // Validation
            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(
                    this,
                    "Name and Email required",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
                val userId = sharedPref.getInt("userId", 1)

                val userProfile = UserProfile(
                    id = userId,
                    name = name,
                    email = email,
                    mobile = mobile,
                    about = about,
                    role = role,
                    accountHolderName = holderName,
                    accountNumber = accountNumber
                )

                database.userProfileDao().insertOrUpdateProfile(userProfile)

                Toast.makeText(
                    this@ProfileEditActivity,
                    "Profile Saved Successfully!",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }

        // 🟢 NAV BUTTONS
        ivHome.setOnClickListener {
            // startActivity(Intent(this, MainActivity::class.java))
        }

        ivMenuGrid.setOnClickListener {
            // menu action
        }

        ivProfileSettings.setOnClickListener {
            finish()
        }
    }
}