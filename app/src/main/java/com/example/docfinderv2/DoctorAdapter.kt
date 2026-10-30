package com.example.docfinderv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorAdapter(
    private val doctors: List<Doctor>,
    private val onClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    inner class DoctorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto: ImageView = view.findViewById(R.id.ivDoctorPhoto)
        val tvName: TextView = view.findViewById(R.id.tvDoctorName)
        val tvSpecialization: TextView = view.findViewById(R.id.tvDoctorSpecialization)
        val tvLocation: TextView = view.findViewById(R.id.tvDoctorLocation)
        val tvRating: TextView = view.findViewById(R.id.tvDoctorRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.tvName.text = doctor.name
        holder.tvSpecialization.text = doctor.specialization
        holder.tvLocation.text = doctor.location
        holder.tvRating.text = doctor.rating.toString()
        holder.itemView.setOnClickListener { onClick(doctor) }
    }

    override fun getItemCount(): Int = doctors.size
}
