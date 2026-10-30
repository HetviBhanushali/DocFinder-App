package com.example.docfinderv2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoritesActivity : AppCompatActivity() {

    private lateinit var rvFavorites: RecyclerView
    private lateinit var emptyState: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        rvFavorites = findViewById(R.id.rvFavorites)
        rvFavorites.layoutManager = LinearLayoutManager(this)
        emptyState = findViewById(R.id.emptyState)

        setupBottomNav()
    }

    override fun onResume() {
        super.onResume()
        // Refresh every time the screen becomes visible, so newly saved
        // or removed favorites always show the latest state.
        loadFavorites()
    }

    private fun loadFavorites() {
        val favorites = FavoritesManager.getFavorites(this)

        if (favorites.isEmpty()) {
            rvFavorites.visibility = View.GONE
            emptyState.visibility = View.VISIBLE
            return
        }

        rvFavorites.visibility = View.VISIBLE
        emptyState.visibility = View.GONE

        rvFavorites.adapter = DoctorAdapter(favorites) { doctor ->
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra(DoctorDetailsActivity.EXTRA_DOCTOR, doctor)
            startActivity(intent)
        }
    }

    private fun setupBottomNav() {
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_favorites
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_search -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_favorites -> true
                R.id.nav_about -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
