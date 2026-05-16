package com.example.impacklink

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class NotificationModel(
    val category: String,
    val iconRes: Int,
    val title: String,
    val subtitle: String,
    val buttonText: String
)

class NotificationAdapter(private val list: List<NotificationModel>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategory: TextView = view.findViewById(R.id.tv_category)
        val ivIcon: ImageView = view.findViewById(R.id.iv_icon)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvSubtitle: TextView = view.findViewById(R.id.tv_subtitle)
        val btnAction: Button = view.findViewById(R.id.btn_action)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvCategory.text = item.category
        holder.ivIcon.setImageResource(item.iconRes)
        holder.tvTitle.text = item.title
        holder.tvSubtitle.text = item.subtitle
        holder.btnAction.text = item.buttonText
    }

    override fun getItemCount(): Int = list.size
}