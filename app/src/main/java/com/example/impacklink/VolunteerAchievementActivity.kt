package com.example.impacklink

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VolunteerAchievementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_achievement)

        val etTrophy = findViewById<EditText>(R.id.etTrophyName)
        val etEvent = findViewById<EditText>(R.id.etEventName)
        val etPlace = findViewById<EditText>(R.id.etPlace)

        findViewById<Button>(R.id.btnAddTrophy).setOnClickListener {
            Toast.makeText(this, "Added Trophy: ${etTrophy.text}", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnAddEvent).setOnClickListener {
            Toast.makeText(this, "Added Event: ${etEvent.text}", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnAddPlace).setOnClickListener {
            Toast.makeText(this, "Added Location: ${etPlace.text}", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
