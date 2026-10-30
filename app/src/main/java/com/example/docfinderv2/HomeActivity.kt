package com.example.docfinderv2

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    companion object {
        /**
         * Shared sample data source so other screens (e.g. the nearby map) show the same doctors.
         * Coordinates are real Mumbai-area locations. Replace with real clinic data when available.
         */
        fun sampleDoctorsPublic(): List<Doctor> = listOf(
            Doctor(
                id = 1,
                name = "Dr. Priya Shah",
                specialization = "Cardiologist",
                location = "Bandra, Mumbai",
                rating = 4.8f,
                clinicAddress = "Hill Road, Bandra West",
                consultationFee = "₹800",
                timings = "9 AM - 6 PM",
                phoneNumber = "+91 9876510001",
                latitude = 19.0596,
                longitude = 72.8295
            ),

            Doctor(
                id = 2,
                name = "Dr. Amit Khan",
                specialization = "Dentist",
                location = "Andheri West",
                rating = 4.9f,
                clinicAddress = "Lokhandwala Complex",
                consultationFee = "₹700",
                timings = "10 AM - 7 PM",
                phoneNumber = "+91 9876510002",
                latitude = 19.1366,
                longitude = 72.8276
            ),

            Doctor(
                id = 3,
                name = "Dr. Neha Verma",
                specialization = "Pediatrician",
                location = "Powai",
                rating = 4.7f,
                clinicAddress = "Hiranandani Gardens",
                consultationFee = "₹650",
                timings = "10 AM - 5 PM",
                phoneNumber = "+91 9876510003",
                latitude = 19.1176,
                longitude = 72.9060
            ),

            Doctor(
                id = 4,
                name = "Dr. Rohan Mehta",
                specialization = "Eye Specialist",
                location = "Malad West",
                rating = 4.6f,
                clinicAddress = "Link Road",
                consultationFee = "₹600",
                timings = "9 AM - 5 PM",
                phoneNumber = "+91 9876510004",
                latitude = 19.1864,
                longitude = 72.8485
            ),

            Doctor(
                id = 5,
                name = "Dr. Kavita Rao",
                specialization = "Orthopedic",
                location = "Thane West",
                rating = 4.9f,
                clinicAddress = "Ghodbunder Road",
                consultationFee = "₹900",
                timings = "11 AM - 8 PM",
                phoneNumber = "+91 9876510005",
                latitude = 19.2183,
                longitude = 72.9781
            ),

            Doctor(
                id = 6,
                name = "Dr. Sameer Joshi",
                specialization = "Neurologist",
                location = "Mulund West",
                rating = 4.8f,
                clinicAddress = "LBS Marg",
                consultationFee = "₹1200",
                timings = "10 AM - 6 PM",
                phoneNumber = "+91 9876510006",
                latitude = 19.1726,
                longitude = 72.9561
            ),

            Doctor(
                id = 7,
                name = "Dr. Anjali Desai",
                specialization = "Dermatologist",
                location = "Ghatkopar",
                rating = 4.7f,
                clinicAddress = "MG Road",
                consultationFee = "₹700",
                timings = "10 AM - 7 PM",
                phoneNumber = "+91 9876510007",
                latitude = 19.0856,
                longitude = 72.9081
            ),

            Doctor(
                id = 8,
                name = "Dr. Vikram Patel",
                specialization = "Psychiatrist",
                location = "Dadar",
                rating = 4.8f,
                clinicAddress = "Ranade Road",
                consultationFee = "₹1000",
                timings = "9 AM - 5 PM",
                phoneNumber = "+91 9876510008",
                latitude = 19.0178,
                longitude = 72.8478
            ),

            Doctor(
                id = 9,
                name = "Dr. Sneha Kulkarni",
                specialization = "Gynecologist",
                location = "Chembur",
                rating = 4.9f,
                clinicAddress = "Diamond Garden",
                consultationFee = "₹900",
                timings = "10 AM - 6 PM",
                phoneNumber = "+91 9876510009",
                latitude = 19.0522,
                longitude = 72.9005
            ),

            Doctor(
                id = 10,
                name = "Dr. Raj Malhotra",
                specialization = "ENT Specialist",
                location = "Borivali West",
                rating = 4.6f,
                clinicAddress = "SV Road",
                consultationFee = "₹650",
                timings = "9 AM - 4 PM",
                phoneNumber = "+91 9876510010",
                latitude = 19.2307,
                longitude = 72.8567
            ),

            Doctor(
                id = 11,
                name = "Dr. Meera Iyer",
                specialization = "General Physician",
                location = "Kurla",
                rating = 4.5f,
                clinicAddress = "Nehru Nagar",
                consultationFee = "₹500",
                timings = "9 AM - 5 PM",
                phoneNumber = "+91 9876510011",
                latitude = 19.0728,
                longitude = 72.8826
            ),

            Doctor(
                id = 12,
                name = "Dr. Karan Nair",
                specialization = "Urologist",
                location = "Santacruz",
                rating = 4.8f,
                clinicAddress = "Linking Road",
                consultationFee = "₹1100",
                timings = "11 AM - 7 PM",
                phoneNumber = "+91 9876510012",
                latitude = 19.0817,
                longitude = 72.8415
            ),

            Doctor(
                id = 13,
                name = "Dr. Pooja Sharma",
                specialization = "Oncologist",
                location = "Vashi",
                rating = 4.9f,
                clinicAddress = "Sector 17",
                consultationFee = "₹1500",
                timings = "10 AM - 5 PM",
                phoneNumber = "+91 9876510013",
                latitude = 19.0771,
                longitude = 72.9987
            ),

            Doctor(
                id = 14,
                name = "Dr. Arjun Singh",
                specialization = "Pulmonologist",
                location = "Nerul",
                rating = 4.7f,
                clinicAddress = "Palm Beach Road",
                consultationFee = "₹950",
                timings = "9 AM - 6 PM",
                phoneNumber = "+91 9876510014",
                latitude = 19.0330,
                longitude = 73.0297
            ),

            Doctor(
                id = 15,
                name = "Dr. Nisha Kapoor",
                specialization = "Endocrinologist",
                location = "Kandivali",
                rating = 4.8f,
                clinicAddress = "Mahavir Nagar",
                consultationFee = "₹1000",
                timings = "10 AM - 6 PM",
                phoneNumber = "+91 9876510015",
                latitude = 19.2050,
                longitude = 72.8420
            ),

            Doctor(
                id = 16,
                name = "Dr. Rahul Gupta",
                specialization = "Cardiologist",
                location = "Colaba",
                rating = 4.9f,
                clinicAddress = "Colaba Causeway",
                consultationFee = "₹1200",
                timings = "10 AM - 7 PM",
                phoneNumber = "+91 9876510016",
                latitude = 18.9067,
                longitude = 72.8147
            ),

            Doctor(
                id = 17,
                name = "Dr. Ritu Chawla",
                specialization = "Dentist",
                location = "Jogeshwari",
                rating = 4.7f,
                clinicAddress = "SV Road",
                consultationFee = "₹550",
                timings = "9 AM - 5 PM",
                phoneNumber = "+91 9876510017",
                latitude = 19.1342,
                longitude = 72.8488
            ),

            Doctor(
                id = 18,
                name = "Dr. Mohit Agarwal",
                specialization = "Dermatologist",
                location = "Marine Lines",
                rating = 4.8f,
                clinicAddress = "Princess Street",
                consultationFee = "₹800",
                timings = "10 AM - 6 PM",
                phoneNumber = "+91 9876510018",
                latitude = 18.9442,
                longitude = 72.8237
            ),

            Doctor(
                id = 19,
                name = "Dr. Isha Fernandes",
                specialization = "Psychiatrist",
                location = "Churchgate",
                rating = 4.6f,
                clinicAddress = "Veer Nariman Road",
                consultationFee = "₹1000",
                timings = "11 AM - 7 PM",
                phoneNumber = "+91 9876510019",
                latitude = 18.9322,
                longitude = 72.8264
            ),

            Doctor(
                id = 20,
                name = "Dr. Vivek Bansal",
                specialization = "Orthopedic",
                location = "Seawoods",
                rating = 4.7f,
                clinicAddress = "Sector 46",
                consultationFee = "₹900",
                timings = "9 AM - 5 PM",
                phoneNumber = "+91 9876510020",
                latitude = 19.0144,
                longitude = 73.0176
            ),

            Doctor(
                id = 21,
                name = "Dr. Ayesha Sheikh",
                specialization = "Nephrologist",
                location = "Sion",
                rating = 4.8f,
                clinicAddress = "Sion Circle",
                consultationFee = "₹1200",
                timings = "10 AM - 6 PM",
                phoneNumber = "+91 9876510021",
                latitude = 19.0427,
                longitude = 72.8615
            ),

            Doctor(
                id = 22,
                name = "Dr. Harsh Vora",
                specialization = "Gastroenterologist",
                location = "Matunga",
                rating = 4.7f,
                clinicAddress = "Kings Circle",
                consultationFee = "₹950",
                timings = "9 AM - 5 PM",
                phoneNumber = "+91 9876510022",
                latitude = 19.0269,
                longitude = 72.8553
            ),

            Doctor(
                id = 23,
                name = "Dr. Shruti Patil",
                specialization = "Rheumatologist",
                location = "Khar",
                rating = 4.8f,
                clinicAddress = "16th Road",
                consultationFee = "₹1000",
                timings = "10 AM - 7 PM",
                phoneNumber = "+91 9876510023",
                latitude = 19.0722,
                longitude = 72.8365
            ),

            Doctor(
                id = 24,
                name = "Dr. Yash Bhatt",
                specialization = "Hepatologist",
                location = "Lower Parel",
                rating = 4.9f,
                clinicAddress = "Phoenix Mills",
                consultationFee = "₹1300",
                timings = "11 AM - 6 PM",
                phoneNumber = "+91 9876510024",
                latitude = 18.9987,
                longitude = 72.8258
            ),

            Doctor(
                id = 25,
                name = "Dr. Tanvi Kulkarni",
                specialization = "Plastic Surgeon",
                location = "Juhu",
                rating = 4.9f,
                clinicAddress = "Juhu Tara Road",
                consultationFee = "₹2000",
                timings = "11 AM - 8 PM",
                phoneNumber = "+91 9876510025",
                latitude = 19.1075,
                longitude = 72.8263
            ),

            Doctor(
                id = 26,
                name = "Dr. Akash Menon",
                specialization = "Neurosurgeon",
                location = "Fort",
                rating = 4.9f,
                clinicAddress = "DN Road",
                consultationFee = "₹1800",
                timings = "10 AM - 5 PM",
                phoneNumber = "+91 9876510026",
                latitude = 18.9340,
                longitude = 72.8355
            ),

            Doctor(
                id = 27,
                name = "Dr. Sonia Das",
                specialization = "Allergist",
                location = "Mira Road",
                rating = 4.7f,
                clinicAddress = "Shanti Park",
                consultationFee = "₹700",
                timings = "9 AM - 4 PM",
                phoneNumber = "+91 9876510027",
                latitude = 19.2952,
                longitude = 72.8544
            ),

            Doctor(
                id = 28,
                name = "Dr. Chirag Shah",
                specialization = "Immunologist",
                location = "Bhandup",
                rating = 4.8f,
                clinicAddress = "LBS Road",
                consultationFee = "₹1100",
                timings = "10 AM - 6 PM",
                phoneNumber = "+91 9876510028",
                latitude = 19.1452,
                longitude = 72.9376
            ),

            Doctor(
                id = 29,
                name = "Dr. Rakesh Sinha",
                specialization = "Diabetologist",
                location = "Airoli",
                rating = 4.8f,
                clinicAddress = "Sector 3",
                consultationFee = "₹850",
                timings = "9 AM - 5 PM",
                phoneNumber = "+91 9876510029",
                latitude = 19.1551,
                longitude = 72.9996
            ),

            Doctor(
                id = 30,
                name = "Dr. Priti Naik",
                specialization = "Physiotherapist",
                location = "Panvel",
                rating = 4.7f,
                clinicAddress = "Old Panvel",
                consultationFee = "₹600",
                timings = "9 AM - 6 PM",
                phoneNumber = "+91 9876510030",
                latitude = 18.9894,
                longitude = 73.1175
            ),
            Doctor(
                id = 31,
                name = "Dr. Aniket Joshi",
                specialization = "Cardiologist",
                location = "Worli, Mumbai",
                rating = 4.8f,
                clinicAddress = "Annie Besant Road, Worli",
                consultationFee = "₹950",
                timings = "9 AM - 6 PM",
                phoneNumber = "+91 9876510031",
                latitude = 19.0176,
                longitude = 72.8174
            ),

            Doctor(
                id = 32,
                name = "Dr. Pooja Mehta",
                specialization = "Dentist",
                location = "Vile Parle West, Mumbai",
                rating = 4.7f,
                clinicAddress = "SV Road, Vile Parle West",
                consultationFee = "₹600",
                timings = "10 AM - 7 PM",
                phoneNumber = "+91 9876510032",
                latitude = 19.1005,
                longitude = 72.8410
            ),

            Doctor(
                id = 33,
                name = "Dr. Rakesh Nair",
                specialization = "Pediatrician",
                location = "Bhandup West, Mumbai",
                rating = 4.8f,
                clinicAddress = "LBS Marg, Bhandup West",
                consultationFee = "₹700",
                timings = "9 AM - 5 PM",
                phoneNumber = "+91 9876510033",
                latitude = 19.1458,
                longitude = 72.9357
            ),

            Doctor(
                id = 34,
                name = "Dr. Shruti Shah",
                specialization = "Eye Specialist",
                location = "Mira Road, Mumbai",
                rating = 4.9f,
                clinicAddress = "Shanti Park, Mira Road",
                consultationFee = "₹750",
                timings = "10 AM - 6 PM",
                phoneNumber = "+91 9876510034",
                latitude = 19.2952,
                longitude = 72.8544
            ),

            Doctor(
                id = 35,
                name = "Dr. Deepak Kulkarni",
                specialization = "Orthopedic",
                location = "Kalyan West",
                rating = 4.7f,
                clinicAddress = "Murbad Road, Kalyan",
                consultationFee = "₹850",
                timings = "10 AM - 6 PM",
                phoneNumber = "+91 9876510035",
                latitude = 19.2437,
                longitude = 73.1355
            ),

            Doctor(
                id = 36,
                name = "Dr. Asha Fernandes",
                specialization = "Dermatologist",
                location = "Mahim, Mumbai",
                rating = 4.8f,
                clinicAddress = "Cadell Road, Mahim",
                consultationFee = "₹800",
                timings = "11 AM - 7 PM",
                phoneNumber = "+91 9876510036",
                latitude = 19.0354,
                longitude = 72.8402
            ),

            Doctor(
                id = 37,
                name = "Dr. Vivek Soni",
                specialization = "General Physician",
                location = "Byculla, Mumbai",
                rating = 4.6f,
                clinicAddress = "Dr. Ambedkar Road, Byculla",
                consultationFee = "₹500",
                timings = "9 AM - 5 PM",
                phoneNumber = "+91 9876510037",
                latitude = 18.9765,
                longitude = 72.8331
            ),

            Doctor(
                id = 38,
                name = "Dr. Nikita Patil",
                specialization = "Gynecologist",
                location = "Goregaon East, Mumbai",
                rating = 4.9f,
                clinicAddress = "Film City Road, Goregaon East",
                consultationFee = "₹900",
                timings = "10 AM - 6 PM",
                phoneNumber = "+91 9876510038",
                latitude = 19.1663,
                longitude = 72.8605
            ),

            Doctor(
                id = 39,
                name = "Dr. Harsh Mehra",
                specialization = "Neurologist",
                location = "Navi Mumbai (Belapur)",
                rating = 4.8f,
                clinicAddress = "CBD Belapur, Sector 15",
                consultationFee = "₹1300",
                timings = "11 AM - 7 PM",
                phoneNumber = "+91 9876510039",
                latitude = 19.0247,
                longitude = 73.0398
            ),

            Doctor(
                id = 40,
                name = "Dr. Sana Kapoor",
                specialization = "ENT Specialist",
                location = "Charni Road, Mumbai",
                rating = 4.7f,
                clinicAddress = "Girgaon Chowpatty Road",
                consultationFee = "₹700",
                timings = "9 AM - 5 PM",
                phoneNumber = "+91 9876510040",
                latitude = 18.9543,
                longitude = 72.8126
            )
        )
    }

    private lateinit var allDoctors: List<Doctor>
    private lateinit var rvDoctors: RecyclerView

    // Current filter state — combined whenever either one changes
    private var currentQuery: String = ""
    private var currentCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        allDoctors = sampleDoctorsPublic()

        setupCategories()
        setupDoctorList()
        setupSearch()
        setupBottomNav()
    }

    private fun setupCategories() {
        val rvCategories: RecyclerView = findViewById(R.id.rvCategories)
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val categories = listOf(
            Category("General Physician", R.drawable.ic_stethoscope),
            Category("Dentist", R.drawable.ic_tooth),
            Category("Cardiologist", R.drawable.ic_heart),
            Category("Pediatrician", R.drawable.ic_smiley),
            Category("Eye Specialist", R.drawable.ic_eye),
            Category("Dermatologist", R.drawable.ic_skin),
            Category("Orthopedic", R.drawable.ic_bone),
            Category("Neurologist", R.drawable.ic_psychology),
            Category("Gynecologist", R.drawable.ic_female),
            Category("ENT Specialist", R.drawable.ic_ear),
            Category("Psychiatrist", R.drawable.ic_brain),
            Category("Urologist", R.drawable.medicos),
            Category("Oncologist", R.drawable.outline_pulse_alert_24),
            Category("Pulmonologist", R.drawable.outline_air_24),
            Category("Gastroenterologist", R.drawable.outline_pill_24),
            Category("Nephrologist", R.drawable.outline_local_hospital_24),
            Category("Endocrinologist", R.drawable.ic_hormone),
            Category("Nutritionist", R.drawable.outline_allergies_24),
            Category("Rheumatologist", R.drawable.outline_healing_24),
            Category("General Surgeon", R.drawable.outline_emergency_24)
        )

        rvCategories.adapter = CategoryAdapter(categories) { category ->
            currentCategory = category?.name
            applyFilters()
        }
    }

    private fun setupDoctorList() {
        rvDoctors = findViewById(R.id.rvDoctors)
        rvDoctors.layoutManager = LinearLayoutManager(this)
        rvDoctors.adapter = DoctorAdapter(allDoctors) { doctor -> openDoctorDetails(doctor) }
    }

    private fun setupSearch() {
        val etSearch: EditText = findViewById(R.id.etSearch)
        etSearch.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentQuery = s?.toString()?.trim()?.lowercase().orEmpty()
                applyFilters()
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })
    }

    private fun applyFilters() {
        val filtered = allDoctors.filter { doctor ->
            val matchesQuery = currentQuery.isEmpty() ||
                    doctor.name.lowercase().contains(currentQuery) ||
                    doctor.specialization.lowercase().contains(currentQuery)

            val matchesCategory = currentCategory == null ||
                    doctor.specialization.equals(currentCategory, ignoreCase = true)

            matchesQuery && matchesCategory
        }

        rvDoctors.adapter = DoctorAdapter(filtered) { doctor -> openDoctorDetails(doctor) }
    }

    private fun openDoctorDetails(doctor: Doctor) {
        val intent = Intent(this, DoctorDetailsActivity::class.java)
        intent.putExtra(DoctorDetailsActivity.EXTRA_DOCTOR, doctor)
        startActivity(intent)
    }

    private fun setupBottomNav() {
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_search -> {
                    startActivity(Intent(this, NearbyMapActivity::class.java))
                    true
                }
                R.id.nav_favorites -> {
                    startActivity(Intent(this, FavoritesActivity::class.java))
                    true
                }
                R.id.nav_about -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}