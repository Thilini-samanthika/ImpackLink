package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class TimeTrackingFragment : Fragment() {

    private var totalHours = 85

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_time_tracking, container, false)

        val etHour = view.findViewById<EditText>(R.id.etHour)
        val etMinute = view.findViewById<EditText>(R.id.etMinute)
        val tvTotalHours = view.findViewById<TextView>(R.id.tvTotalHours)
        val btnTrackTime = view.findViewById<Button>(R.id.btnTrackTime)

        btnTrackTime.setOnClickListener {
            val hoursInput = etHour.text.toString().toIntOrNull() ?: 0
            if (hoursInput > 0) {
                totalHours += hoursInput
                tvTotalHours.text = getString(R.string.total_tracked_hours_prefix, totalHours)
                Toast.makeText(context, "Time tracked successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please enter a valid duration", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}