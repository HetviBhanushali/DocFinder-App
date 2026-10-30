package com.example.docfinderv2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doctor(
    val id: Int,
    val name: String,
    val specialization: String,
    val location: String,
    val rating: Float,
    val clinicAddress: String,
    val consultationFee: String,
    val timings: String,
    val phoneNumber: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable
