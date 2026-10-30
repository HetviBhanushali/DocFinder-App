package com.example.docfinderv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Category(val name: String, val iconRes: Int)

class CategoryAdapter(
    private val categories: List<Category>,
    private val onClick: (Category?) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    // Index of the currently selected category, or -1 if none selected (showing all doctors)
    private var selectedPosition: Int = -1

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: View = view
        val ivIcon: ImageView = view.findViewById(R.id.ivCategoryIcon)
        val tvName: TextView = view.findViewById(R.id.tvCategoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.tvName.text = category.name
        holder.ivIcon.setImageResource(category.iconRes)

        val isSelected = position == selectedPosition
        holder.root.setBackgroundResource(
            if (isSelected) R.drawable.bg_category_item_selected else R.drawable.bg_category_item
        )
        val textAndIconColor = if (isSelected) {
            android.graphics.Color.WHITE
        } else {
            holder.root.context.getColor(R.color.teal_dark)
        }
        holder.tvName.setTextColor(textAndIconColor)
        holder.ivIcon.setColorFilter(textAndIconColor)

        holder.root.setOnClickListener {
            // Tapping the already-selected category clears the filter (shows all doctors)
            selectedPosition = if (selectedPosition == position) -1 else position
            notifyDataSetChanged()
            onClick(if (selectedPosition == -1) null else category)
        }
    }

    override fun getItemCount(): Int = categories.size
}
