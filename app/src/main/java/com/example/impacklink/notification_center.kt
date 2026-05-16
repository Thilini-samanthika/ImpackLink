package com.example.impacklink

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationCenterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_center)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_notifications)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Figma එකේ තියෙන විස්තර ටික List එකක් ලෙස ඇතුළත් කිරීම
        val notificationList = listOf(
            NotificationModel("ngo notification", R.drawable.ic_message, "education for all", "hope foundation", "apply"),
            NotificationModel("volunteer notification", R.drawable.ic_check, "community clean up", "green earth NGO", "apply"),
            ... // මෙතනට තවත් ඔයාට කැමති notifications දාන්න පුළුවන්
        )

        val adapter = NotificationAdapter(notificationList)
        recyclerView.adapter = adapter

        // Bottom Navigation Back Click
        findViewById<ImageView>(R.id.ivProfileSettings).setOnClickListener {
            finish()
        }
    }
}