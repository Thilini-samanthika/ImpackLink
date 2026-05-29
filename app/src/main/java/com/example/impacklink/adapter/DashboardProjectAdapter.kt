package com.example.impacklink.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.impacklink.DeleteProjectActivity
import com.example.impacklink.R
import model.Application

class DashboardProjectAdapter(private val applications: MutableList<Application>) :
    RecyclerView.Adapter<DashboardProjectAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_project_name)
        val btnDelete: Button = view.findViewById(R.id.btn_delete)
        val btnApprove: Button = view.findViewById(R.id.btn_approve)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dashboard_project, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val application = applications[position]
        // Display Volunteer Name and Project Title as requested
        val displayText = "${application.volunteerName} (${application.projectTitle})"
        holder.tvName.text = displayText

        // Link logic: Clicking Approve updates the status visible to volunteers
        holder.btnApprove.setOnClickListener {
            application.status = "Approved"
            Toast.makeText(holder.itemView.context, "Approved ${application.volunteerName}", Toast.LENGTH_SHORT).show()
            notifyItemChanged(position)
        }

        // Link logic: Clicking Delete navigates to NGO Delete Project page
        holder.btnDelete.setOnClickListener {
            val intent = Intent(holder.itemView.context, DeleteProjectActivity::class.java)
            intent.putExtra("PROJECT_ID", application.projectId)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = applications.size
}
