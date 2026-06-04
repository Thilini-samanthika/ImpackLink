package com.example.impacklink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CsrDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_csr_dashboard)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CsrDashboardFragment())
                .commit()
        }
    }
}
