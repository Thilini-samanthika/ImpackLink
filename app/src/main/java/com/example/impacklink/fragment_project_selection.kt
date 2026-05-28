package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment

class ProjectSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_project_selection, container, false)

        view.findViewById<ImageButton>(R.id.btnShare).setOnClickListener {
            Toast.makeText(context, "Sharing project maps...", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<ImageButton>(R.id.btnChat).setOnClickListener {
            Toast.makeText(context, "Opening project chat...", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<ImageButton>(R.id.btnDownload).setOnClickListener {
            Toast.makeText(context, "Downloading map offline...", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}