package com.example.docfinderv2

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject

/**
 * Simple persistence layer for favorite doctors.
 * Stores each favorite doctor as a JSON string inside a SharedPreferences StringSet.
 * No external libraries required (uses org.json, built into Android).
 */
object FavoritesManager {

    private const val PREFS_NAME = "docfinder_prefs"
    private const val KEY_FAVORITES = "favorite_doctors"

    fun saveDoctor(context: Context, doctor: Doctor) {
        val prefs = prefs(context)
        val current = getFavoritesJsonSet(prefs).toMutableSet()
        current.removeAll { JSONObject(it).getInt("id") == doctor.id }
        current.add(doctorToJson(doctor))
        prefs.edit().putStringSet(KEY_FAVORITES, current).apply()
    }

    fun removeDoctor(context: Context, doctorId: Int) {
        val prefs = prefs(context)
        val current = getFavoritesJsonSet(prefs).toMutableSet()
        current.removeAll { JSONObject(it).getInt("id") == doctorId }
        prefs.edit().putStringSet(KEY_FAVORITES, current).apply()
    }

    fun isFavorite(context: Context, doctorId: Int): Boolean {
        return getFavorites(context).any { it.id == doctorId }
    }

    fun getFavorites(context: Context): List<Doctor> {
        return getFavoritesJsonSet(prefs(context)).map { jsonToDoctor(it) }
    }

    private fun prefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private fun getFavoritesJsonSet(prefs: SharedPreferences): Set<String> =
        prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()

    private fun doctorToJson(doctor: Doctor): String {
        val json = JSONObject()
        json.put("id", doctor.id)
        json.put("name", doctor.name)
        json.put("specialization", doctor.specialization)
        json.put("location", doctor.location)
        json.put("rating", doctor.rating.toDouble())
        json.put("clinicAddress", doctor.clinicAddress)
        json.put("consultationFee", doctor.consultationFee)
        json.put("timings", doctor.timings)
        json.put("phoneNumber", doctor.phoneNumber)
        json.put("latitude", doctor.latitude)
        json.put("longitude", doctor.longitude)
        return json.toString()
    }

    private fun jsonToDoctor(jsonStr: String): Doctor {
        val json = JSONObject(jsonStr)
        return Doctor(
            id = json.getInt("id"),
            name = json.getString("name"),
            specialization = json.getString("specialization"),
            location = json.getString("location"),
            rating = json.getDouble("rating").toFloat(),
            clinicAddress = json.getString("clinicAddress"),
            consultationFee = json.getString("consultationFee"),
            timings = json.getString("timings"),
            phoneNumber = json.getString("phoneNumber"),
            latitude = json.optDouble("latitude", 19.0760),
            longitude = json.optDouble("longitude", 72.8777)
        )
    }
}
