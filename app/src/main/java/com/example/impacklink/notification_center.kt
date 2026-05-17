package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class NotificationCenterActivity : AppCompatActivity() {

    private lateinit var ngoContainer: LinearLayout
    private lateinit var volunteerContainer: LinearLayout
    private lateinit var donorContainer: LinearLayout
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_center)

        ngoContainer = findViewById(R.id.container_ngo_notifications)
        volunteerContainer = findViewById(R.id.container_volunteer_notifications)
        donorContainer = findViewById(R.id.container_donor_notifications)
        database = AppDatabase.getDatabase(this)

        loadNotificationsFromDb()

        findViewById<ImageView>(R.id.ivHome).setOnClickListener {
            finish()
        }
    }

    private fun loadNotificationsFromDb() {
        lifecycleScope.launch {
            val notifications = database.notificationDao().getAllNotifications()
            renderNotifications(notifications)
        }
    }

    private fun renderNotifications(notifications: List<NotificationEntity>) {
        ngoContainer.removeAllViews()
        volunteerContainer.removeAllViews()
        donorContainer.removeAllViews()

        val inflater = LayoutInflater.from(this)

        for (notification in notifications) {
            val view = inflater.inflate(R.layout.item_notification_card, null)

            val tvTitle = view.findViewById<TextView>(R.id.tv_noti_title)
            val tvDesc = view.findViewById<TextView>(R.id.tv_noti_description)
            val btnAction = view.findViewById<Button>(R.id.btn_noti_action)
            val ivIcon = view.findViewById<ImageView>(R.id.iv_noti_icon)

            tvTitle.text = notification.title
            tvDesc.text = notification.description
            btnAction.text = notification.statusText

            when (notification.iconType) {
                "TICK" -> {
                    ivIcon.setImageResource(R.drawable.ic_check)
                    btnAction.setBackgroundColor(android.graphics.Color.parseColor("#4CAF50"))
                    btnAction.setTextColor(android.graphics.Color.WHITE)
                }
                "WARNING" -> {
                    ivIcon.setImageResource(R.drawable.ic_lock)
                    btnAction.setBackgroundColor(android.graphics.Color.parseColor("#D9534F"))
                    btnAction.setTextColor(android.graphics.Color.WHITE)
                }
                else -> {
                    ivIcon.setImageResource(R.drawable.ic_message)
                }
            }

            btnAction.setOnClickListener {
                Toast.makeText(this, "${notification.title} clicked!", Toast.LENGTH_SHORT).show()
            }

            when (notification.role.uppercase()) {
                "NGO" -> ngoContainer.addView(view)
                "VOLUNTEER" -> volunteerContainer.addView(view)
                "DONOR" -> donorContainer.addView(view)
            }
        }
    }
}