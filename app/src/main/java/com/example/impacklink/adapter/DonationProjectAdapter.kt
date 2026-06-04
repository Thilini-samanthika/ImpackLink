package com.example.impacklink.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.impacklink.R
import model.Project

class DonationProjectAdapter(
    private val projects: List<Project>,
    private val onUpdateClick: (Project) -> Unit,
    private val onDeleteClick: (Project) -> Unit
) : RecyclerView.Adapter<DonationProjectAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvProjectTitle)
        val tvAmount: TextView = view.findViewById(R.id.tvDonationAmount)
        val btnUpdate: Button = view.findViewById(R.id.btnUpdate)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_donation_project, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]
        holder.tvTitle.text = project.title
        
        // Placeholder for donation amount - ideally this should come from a donation model
        // For now, we display a mock value or 0.00 if no data
        val mockAmount = if (position == 0) "Rs 100,000.00" else "Rs 200,000.00"
        holder.tvAmount.text = mockAmount

        holder.btnUpdate.setOnClickListener {
            onUpdateClick(project)
        }
        
        holder.btnDelete.setOnClickListener {
            onDeleteClick(project)
        }
    }

    override fun getItemCount() = projects.size
}
