package com.example.impacklink

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class VolunteerTimeTrackingActivity : AppCompatActivity() {

    private var totalHours = 85
    private var isAm = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_time_tracking)

        val etHour = findViewById<EditText>(R.id.etHour)
        val etMinute = findViewById<EditText>(R.id.etMinute)
        val tvTotalHours = findViewById<TextView>(R.id.tvTotalHours)
        val btnTrackTime = findViewById<Button>(R.id.btnTrackTime)
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val tvAm = findViewById<TextView>(R.id.tvAm)
        val tvPm = findViewById<TextView>(R.id.tvPm)

        val pinkColor = ContextCompat.getColor(this, R.color.pink_selected)

        btnBack.setOnClickListener {
            finish()
        }

        tvAm.setOnClickListener {
            isAm = true
            tvAm.setTextColor(pinkColor)
            tvPm.setTextColor(Color.BLACK)
        }

        tvPm.setOnClickListener {
            isAm = false
            tvPm.setTextColor(pinkColor)
            tvAm.setTextColor(Color.BLACK)
        }

        btnTrackTime.setOnClickListener {
            val hoursInput = etHour.text.toString().toIntOrNull() ?: 0
            if (hoursInput > 0) {
                totalHours += hoursInput
                // Using string resource with placeholder
                tvTotalHours.text = getString(R.string.total_tracked_hours_prefix, totalHours)
                Toast.makeText(this, "Time tracked successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a valid duration", Toast.LENGTH_SHORT).show()
            }
        }

        // Initialize display
        tvTotalHours.text = getString(R.string.total_tracked_hours_prefix, totalHours)
    }
}
