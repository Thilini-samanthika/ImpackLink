package com.example.impacklink

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class NgoProfileEditActivity : AppCompatActivity() {

    private val database by lazy { AppDatabase.getDatabase(this) }

    private lateinit var etNgoName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMobileNo: EditText
    private lateinit var etLocation: EditText
    private lateinit var etWebsite: EditText
    private lateinit var etNgoId: EditText
    private lateinit var etPositionName: EditText
    private lateinit var etAbout: EditText
    private lateinit var btnSaveProfile: Button
    private lateinit var btnAllProjects: Button
    private lateinit var ivUserAvatar: ImageView

    private var selectedImageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                selectedImageUri = uri
                ivUserAvatar.setImageURI(uri)
                // Persist permission to access URI across reboots if needed, 
                // but for simple local URIs setImageURI often works for the session.
                contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_profile_edit)

        initializeViews()
        loadProfileData()

        ivUserAvatar.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
                addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            pickImageLauncher.launch(intent)
        }

        btnSaveProfile.setOnClickListener {
            saveProfileData()
        }

        btnAllProjects.setOnClickListener {
            val intent = Intent(this, NgoProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigation.selectedItemId = R.id.menu_profile

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, NgoDashboardActivity::class.java))
                    finish()
                    true
                }
                R.id.menu_reports -> {
                    startActivity(Intent(this, ProjectAnalysisActivity::class.java))
                    true
                }
                R.id.menu_apps -> {
                    startActivity(Intent(this, RoleSelectionActivity::class.java))
                    true
                }
                R.id.menu_profile -> {
                    startActivity(Intent(this, NgoProfileActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun initializeViews() {
        etNgoName = findViewById(R.id.etNgoName)
        etEmail = findViewById(R.id.etEmail)
        etMobileNo = findViewById(R.id.etMobileNo)
        etLocation = findViewById(R.id.etLocation)
        etWebsite = findViewById(R.id.etWebsite)
        etNgoId = findViewById(R.id.etNgoId)
        etPositionName = findViewById(R.id.etPositionName)
        etAbout = findViewById(R.id.etAbout)
        btnSaveProfile = findViewById(R.id.btnSaveProfile)
        btnAllProjects = findViewById(R.id.btnAllProjects)
        ivUserAvatar = findViewById(R.id.ivUserAvatar)
    }

    private fun loadProfileData() {
        lifecycleScope.launch {
            val profile = database.userProfileDao().getUserProfile()
            profile?.let {
                etNgoName.setText(it.name)
                etEmail.setText(it.email)
                etMobileNo.setText(it.mobile)
                etLocation.setText(it.location)
                etWebsite.setText(it.website)
                etNgoId.setText(it.ngoId)
                etPositionName.setText(it.positionName)
                etAbout.setText(it.about)
                it.profileImageUri?.let { uriString ->
                    selectedImageUri = Uri.parse(uriString)
                    ivUserAvatar.setImageURI(selectedImageUri)
                }
            }
        }
    }

    private fun saveProfileData() {
        val name = etNgoName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val mobile = etMobileNo.text.toString().trim()
        val location = etLocation.text.toString().trim()
        val website = etWebsite.text.toString().trim()
        val ngoId = etNgoId.text.toString().trim()
        val position = etPositionName.text.toString().trim()
        val about = etAbout.text.toString().trim()

        if (name.isEmpty()) {
            etNgoName.error = "Required"
            return
        }

        lifecycleScope.launch {
            val currentProfile = database.userProfileDao().getUserProfile()
            val newProfile = UserProfile(
                id = currentProfile?.id ?: 1,
                name = name,
                email = email,
                mobile = mobile,
                about = about,
                role = currentProfile?.role ?: "NGO",
                accountHolderName = currentProfile?.accountHolderName ?: "",
                accountNumber = currentProfile?.accountNumber ?: "",
                location = location,
                website = website,
                ngoId = ngoId,
                positionName = position,
                profileImageUri = selectedImageUri?.toString() ?: currentProfile?.profileImageUri
            )
            database.userProfileDao().insertOrUpdateProfile(newProfile)
            Toast.makeText(this@NgoProfileEditActivity, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
            
            val intent = Intent(this@NgoProfileEditActivity, NgoProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}