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
import model.Project

class DashboardProjectAdapter(private val projects: List<Project>) :
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
        val project = projects[position]
        holder.tvName.text = project.title

        holder.btnApprove.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Project Approved Successfully", Toast.LENGTH_SHORT).show()
        }

        holder.btnDelete.setOnClickListener {
            val intent = Intent(holder.itemView.context, DeleteProjectActivity::class.java)
            intent.putExtra("PROJECT_ID", project.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = projects.size
}