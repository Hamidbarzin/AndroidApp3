package com.app.androidapp3

import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapsActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

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

        // تنظیمات Osmdroid
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        setContentView(R.layout.activity_maps)

        // مقداردهی اولیه MapView
        mapView = findViewById(R.id.map)
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)
        mapView.setBuiltInZoomControls(true)
        mapView.setMultiTouchControls(true)

        val mapController = mapView.controller
        mapController.setZoom(15.0)
        val startPoint = GeoPoint(35.6892, 51.3890) // مثال: تهران
        mapController.setCenter(startPoint)

        // افزودن نشانگر اولیه
        addMarker(startPoint, "Start Point")

        // تنظیم کلیک برای دکمه
        findViewById<Button>(R.id.next_button).setOnClickListener {
            showNextLocation()
        }

        showNextLocation()
    }

    private fun showNextLocation() {
        val locationTextView = findViewById<TextView>(R.id.placeholder_text)

        if (currentLocationIndex < locations.size) {
            val locationName = locations[currentLocationIndex]
            // باید مختصات دقیق هر مکان را داشته باشید
            // به عنوان مثال:
            val geoPoint = when (locationName) {
                "City Hall" -> GeoPoint(35.6892, 51.3890) // تهران
                "Tim Hortons" -> GeoPoint(34.0, 151.0) // نمونه
                // ادامه بر اساس مکان‌ها
                else -> GeoPoint(35.6892, 51.3890) // مکان پیش‌فرض
            }

            locationTextView.text = "Next location: $locationName"
            addMarker(geoPoint, locationName)
            mapView.controller.animateTo(geoPoint)
            currentLocationIndex++
        } else {
            locationTextView.text = "Congratulations! You have visited all locations. You are now eligible for the free vacation draw!"
            findViewById<Button>(R.id.next_button).isEnabled = false
        }
    }

    private fun addMarker(point: GeoPoint, title: String) {
        val marker = Marker(mapView)
        marker.position = point
        marker.title = title
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(marker)
        mapView.invalidate()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}
