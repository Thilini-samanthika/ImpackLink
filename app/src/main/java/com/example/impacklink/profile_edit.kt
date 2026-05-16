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

    // 0. කලින් අපි හදපු AppDatabase එක මෙතනදී initialize කරගන්නවා
    private val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        // 1. XML එකේ තියෙන Input Fields සහ Views හඳුනාගැනීම
        val etName = findViewById<EditText>(R.id.et_name)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etMobile = findViewById<EditText>(R.id.et_mobile)
        val etAbout = findViewById<EditText>(R.id.et_about)
        val spinnerRole = findViewById<Spinner>(R.id.spinner_role)
        val etHolderName = findViewById<EditText>(R.id.et_holder_name)
        val etAccountNumber = findViewById<EditText>(R.id.et_account_number)
        val btnSave = findViewById<Button>(R.id.btn_save)

        // Bottom Navigation Icons
        val ivHome = findViewById<ImageView>(R.id.ivHome)
        val ivMenuGrid = findViewById<ImageView>(R.id.ivMenuGrid)
        val ivProfileSettings = findViewById<ImageView>(R.id.ivProfileSettings)


        // 2. Spinner (Your Role) එකට Roles ඇතුළත් කිරීම
        val rolesArray = arrayOf("NGO", "Volunteer", "Donor", "Company / CSR")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, rolesArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRole.adapter = adapter


        // 3. පිටුව ඕපන් වෙද්දීම කලින් සේව් කරපු දත්ත Database එකේ තියෙනවාදැයි බලා ඒවා EditText වලට පිරවීම
        lifecycleScope.launch {
            val savedProfile = database.userProfileDao().getUserProfile()
            if (savedProfile != null) {
                etName.setText(savedProfile.name)
                etEmail.setText(savedProfile.email)
                etMobile.setText(savedProfile.mobile)
                etAbout.setText(savedProfile.about)

                // Spinner එකේ තිබුණු Role එක ආපහු සිලෙක්ට් කිරීම
                val spinnerPosition = adapter.getPosition(savedProfile.role)
                spinnerRole.setSelection(spinnerPosition)

                etHolderName.setText(savedProfile.accountHolderName)
                etAccountNumber.setText(savedProfile.accountNumber)
            }
        }


        // 4. Save Button එක ක්ලික් කළ විට Room Database එකට සේව් වීම
        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val mobile = etMobile.text.toString().trim()
            val about = etAbout.text.toString().trim()
            val selectedRole = spinnerRole.selectedItem.toString()
            val holderName = etHolderName.text.toString().trim()
            val accountNumber = etAccountNumber.text.toString().trim()

            // Validation: නම සහ ඊමේල් හිස්දැයි බැලීම
            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill in your Name and Email", Toast.LENGTH_SHORT).show()
            } else {
                // Background Thread එකක් (Coroutines) ඇතුළේ දත්ත ටික Database එකට යවනවා
                lifecycleScope.launch {
                    val userProfile = UserProfile(
                        id = 1, // හැමවිටම එකම profile එකක් update වීමට id එක 1 ලෙස තබයි
                        name = name,
                        email = email,
                        mobile = mobile,
                        about = about,
                        role = selectedRole,
                        accountHolderName = holderName,
                        accountNumber = accountNumber
                    )

                    // Database එකට ඇතුළත් කිරීම (REPLACE strategy එක නිසා කලින් එක update වේ)
                    database.userProfileDao().insertOrUpdateProfile(userProfile)

                    Toast.makeText(this@ProfileEditActivity, "Profile Saved to Database!", Toast.LENGTH_SHORT).show()
                    finish() // දත්ත සේව් වුණාට පස්සේ ආපහු කලින් Settings පිටුවට යනවා
                }
            }
        }


        // 5. Bottom Navigation Icons වලට Click Listeners
        ivHome.setOnClickListener {
            // val intent = Intent(this, MainActivity::class.java)
            // startActivity(intent)
        }

        ivMenuGrid.setOnClickListener {
            // මැද අයිකනයට අදාළ පිටුවට Intent එකක් මෙතනින් දාන්න
        }

        ivProfileSettings.setOnClickListener {
            // දැනටමත් ඉන්නේ Profile සම්බන්ධ කොටසක නිසා, Settings එකට යන්න finish() කරන්න පුළුවන්
            finish()
        }
    }
}