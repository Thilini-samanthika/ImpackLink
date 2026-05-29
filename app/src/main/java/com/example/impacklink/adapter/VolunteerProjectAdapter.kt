package com.example.impacklink.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.impacklink.R
import model.Application
import model.Project

class VolunteerProjectAdapter(
    private val projects: List<Project>,
    private val volunteerName: String,
    private val onApplySuccess: () -> Unit
) : RecyclerView.Adapter<VolunteerProjectAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvProjectTitle)
        val tvCategory: TextView = view.findViewById(R.id.tvProjectCategory)
        val tvStatus: TextView = view.findViewById(R.id.tvProjectStatus)
        val btnApply: Button = view.findViewById(R.id.btnApplyProject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]
        holder.tvTitle.text = project.title
        val categoryText = "Category: ${project.description}"
        holder.tvCategory.text = categoryText
        
        // Check if already applied to determine status display
        val existingApp = Application.applicationList.find { it.projectId == project.id && it.volunteerName == volunteerName }
        
        if (existingApp != null) {
            holder.tvStatus.text = "Status: ${existingApp.status}"
            if (existingApp.status == "Approved") {
                holder.tvStatus.setTextColor(0xFF00A676.toInt()) // Green
            } else {
                holder.tvStatus.setTextColor(0xFF333333.toInt()) // Grey for Pending
            }
            holder.btnApply.isEnabled = false
            holder.btnApply.text = "Applied"
        } else {
            holder.tvStatus.text = "Status: Available"
            holder.tvStatus.setTextColor(0xFF007BFF.toInt()) // Blue
            holder.btnApply.isEnabled = true
            holder.btnApply.text = "apply"
        }

        holder.btnApply.setOnClickListener {
            Application.applicationList.add(
                Application(project.id, project.title, volunteerName, "Pending")
            )
            Toast.makeText(holder.itemView.context, "Applied to ${project.title}", Toast.LENGTH_SHORT).show()
            notifyItemChanged(position)
            onApplySuccess()
        }
    }

    override fun getItemCount() = projects.size
}
