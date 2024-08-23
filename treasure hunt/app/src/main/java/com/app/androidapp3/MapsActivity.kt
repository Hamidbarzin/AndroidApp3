package com.app.androidapp3

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MapsActivity : AppCompatActivity() {

    private val locations = listOf(
        "City Hall",
        "Tim Hortons",
        "Coffee Shop",
        "Chearch",
        "Library",
        "Bookstore",
        "Park",
        "Gym",
        "Shopping Mall",
        "Cinema",
        "Restaurant",
        "Hotel",
        "Music Store",
        "Art Gallery",
        "Theater",
        "Supermarket",
        "Florist",
        "Pharmacy",
        "Bank",
        "Train Station"
    )

    private var currentLocationIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // location
        showNextLocation()

        // next button
        findViewById<Button>(R.id.next_button).setOnClickListener {
            showNextLocation()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showNextLocation() {
        val locationTextView = findViewById<TextView>(R.id.placeholder_text)

        if (currentLocationIndex < locations.size) {
            locationTextView.text = "Next location: ${locations[currentLocationIndex]}"
            currentLocationIndex++
        } else {
            // see location
            locationTextView.text = "Congratulations! You have visited all locations. You are now eligible for the free vacation draw!"
            findViewById<Button>(R.id.next_button).isEnabled = false
        }
    }
}