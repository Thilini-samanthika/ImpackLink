package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AchievementFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_achievement, container, false)

        val etTrophy = view.findViewById<EditText>(R.id.etTrophyName)
        val etEvent = view.findViewById<EditText>(R.id.etEventName)
        val etPlace = view.findViewById<EditText>(R.id.etPlace)

        view.findViewById<Button>(R.id.btnAddTrophy).setOnClickListener {
            Toast.makeText(context, "Added Trophy: ${etTrophy.text}", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnAddEvent).setOnClickListener {
            Toast.makeText(context, "Added Event: ${etEvent.text}", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnAddPlace).setOnClickListener {
            Toast.makeText(context, "Added Location: ${etPlace.text}", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}