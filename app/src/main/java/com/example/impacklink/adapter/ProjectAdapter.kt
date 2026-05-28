package com.example.impacklink.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.impacklink.R
import com.example.impacklink.UpdateProjectActivity
import model.Project

class ProjectAdapter(private val projects: List<Project>) :
    RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvProjectTitle)
        val tvCategory: TextView = view.findViewById(R.id.tvProjectCategory)
        val tvStatus: TextView = view.findViewById(R.id.tvProjectStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project_view, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projects[position]
        holder.tvTitle.text = project.title
        holder.tvCategory.text = "Category"
        holder.tvStatus.text = project.status

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateProjectActivity::class.java)
            intent.putExtra("PROJECT_DATA", project)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = projects.size
}