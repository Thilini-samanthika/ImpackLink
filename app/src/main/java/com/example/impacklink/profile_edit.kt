package com.example.impacklink // ඔයාගේ ඇප් එකේ package නම මෙතන තියෙන්න ඕනේ

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ProfileEditActivity : AppCompatActivity() {

    private val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

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

        val rolesArray = arrayOf("NGO", "Volunteer", "Donor", "Company / CSR")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, rolesArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRole.adapter = adapter



        lifecycleScope.launch {
            val savedProfile = database.userProfileDao().getUserProfile()
            if (savedProfile != null) {
                etName.setText(savedProfile.name)
                etEmail.setText(savedProfile.email)
                etMobile.setText(savedProfile.mobile)
                etAbout.setText(savedProfile.about)

                val spinnerPosition = adapter.getPosition(savedProfile.role)
                spinnerRole.setSelection(spinnerPosition)

                etHolderName.setText(savedProfile.accountHolderName)
                etAccountNumber.setText(savedProfile.accountNumber)
            }
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val mobile = etMobile.text.toString().trim()
            val about = etAbout.text.toString().trim()
            val selectedRole = spinnerRole.selectedItem.toString()
            val holderName = etHolderName.text.toString().trim()
            val accountNumber = etAccountNumber.text.toString().trim()

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill in your Name and Email", Toast.LENGTH_SHORT).show()
            } else {

                lifecycleScope.launch {
                    val userProfile = UserProfile(
                        id = 1,
                        name = name,
                        email = email,
                        mobile = mobile,
                        about = about,
                        role = selectedRole,
                        accountHolderName = holderName,
                        accountNumber = accountNumber
                    )

                    database.userProfileDao().insertOrUpdateProfile(userProfile)

                    Toast.makeText(this@ProfileEditActivity, "Profile Saved to Database!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        ivHome.setOnClickListener {
            // val intent = Intent(this, MainActivity::class.java)
            // startActivity(intent)
        }

        ivMenuGrid.setOnClickListener {
        }

        ivProfileSettings.setOnClickListener {
            finish()
        }
    }
}