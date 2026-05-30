package com.example.impacklink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DonorDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_dashboard_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DonorDashboardFragment())
                .commit()
        }
    }
}
