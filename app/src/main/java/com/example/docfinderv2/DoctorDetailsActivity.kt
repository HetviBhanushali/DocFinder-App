package com.example.docfinderv2

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class DoctorDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DOCTOR = "extra_doctor"
    }

    private lateinit var doctor: Doctor
    private lateinit var btnSaveFavorites: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        doctor = getDoctorFromIntent() ?: run {
            finish()
            return
        }

        bindHeader()
        bindInfoRows()
        bindActions()

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }
    }

    private fun getDoctorFromIntent(): Doctor? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DOCTOR, Doctor::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DOCTOR)
        }
    }

    private fun bindHeader() {
        findViewById<TextView>(R.id.tvDoctorName).text = doctor.name
        findViewById<TextView>(R.id.tvDoctorSpecialization).text = doctor.specialization
    }

    private fun bindInfoRows() {
        setInfoRow(
            rowId = R.id.rowAddress,
            iconRes = android.R.drawable.ic_dialog_map,
            title = "Clinic Address",
            subtitle = doctor.clinicAddress
        )
        setInfoRow(
            rowId = R.id.rowFee,
            iconRes = android.R.drawable.ic_menu_sort_by_size,
            title = "Consultation Fee",
            subtitle = doctor.consultationFee
        )
        setInfoRow(
            rowId = R.id.rowTimings,
            iconRes = android.R.drawable.ic_menu_recent_history,
            title = "Timings",
            subtitle = doctor.timings
        )
        setInfoRow(
            rowId = R.id.rowPhone,
            iconRes = android.R.drawable.ic_menu_call,
            title = "Phone Number",
            subtitle = doctor.phoneNumber
        )
    }

    private fun setInfoRow(rowId: Int, iconRes: Int, title: String, subtitle: String) {
        val row = findViewById<android.view.View>(rowId)
        row.findViewById<ImageView>(R.id.ivRowIcon).setImageResource(iconRes)
        row.findViewById<TextView>(R.id.tvRowTitle).text = title
        row.findViewById<TextView>(R.id.tvRowSubtitle).text = subtitle
    }

    private fun bindActions() {
        findViewById<MaterialButton>(R.id.btnCallDoctor).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${doctor.phoneNumber}"))
            startActivity(intent)
        }

        btnSaveFavorites = findViewById(R.id.btnSaveFavorites)
        updateFavoriteButtonLabel()

        btnSaveFavorites.setOnClickListener {
            if (FavoritesManager.isFavorite(this, doctor.id)) {
                FavoritesManager.removeDoctor(this, doctor.id)
                Toast.makeText(this, "${doctor.name} removed from favorites", Toast.LENGTH_SHORT).show()
            } else {
                FavoritesManager.saveDoctor(this, doctor)
                Toast.makeText(this, "${doctor.name} saved to favorites", Toast.LENGTH_SHORT).show()
            }
            updateFavoriteButtonLabel()
        }
    }

    private fun updateFavoriteButtonLabel() {
        btnSaveFavorites.text = if (FavoritesManager.isFavorite(this, doctor.id)) {
            "Remove from Favorites"
        } else {
            "Save to Favorites"
        }
    }
}
