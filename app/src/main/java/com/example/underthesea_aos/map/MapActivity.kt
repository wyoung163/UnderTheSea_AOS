package com.example.underthesea_aos.map

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Transformations.map
import com.example.underthesea_aos.R
import com.example.underthesea_aos.databinding.ActivityMainBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
*/
class MapActivity :AppCompatActivity(), OnMapReadyCallback{
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: PlaceHelper
    lateinit var  database: SQLiteDatabase

    override fun onCreate(saveInstanceState: Bundle?){
        super.onCreate(saveInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_map)

        dbHelper = PlaceHelper(this, "place.db", null, 1);
        database = dbHelper.readableDatabase

        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val marker = LatLng(80.241615, 128.695587)
        mMap.addMarker(MarkerOptions().position(marker).title("marker title"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
    }
}