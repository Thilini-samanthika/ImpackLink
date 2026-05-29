package com.example.impacklink.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.impacklink.R
import model.Application

class VolunteerApplicationAdapter(private val applications: List<Application>) :
    RecyclerView.Adapter<VolunteerApplicationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_app_project_title)
        val tvStatus: TextView = view.findViewById(R.id.tv_app_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_volunteer_application, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val application = applications[position]
        holder.tvTitle.text = application.projectTitle
        holder.tvStatus.text = application.status
        
        if (application.status == "Approved") {
            holder.tvStatus.setBackgroundColor(0xFF00A676.toInt())
        } else {
            holder.tvStatus.setBackgroundColor(0xFF333333.toInt())
        }
    }

    override fun getItemCount() = applications.size
}
