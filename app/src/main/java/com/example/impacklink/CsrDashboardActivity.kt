package com.example.impacklink

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CsrDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_csr_dashboard)

        findViewById<Button>(R.id.btnCreatePartnershipNav).setOnClickListener {
            Toast.makeText(this, "Create Partnership", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnViewPartnershipNav).setOnClickListener {
            Toast.makeText(this, "Edit Partnership", Toast.LENGTH_SHORT).show()
        }
    }
}
