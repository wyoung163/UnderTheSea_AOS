package com.example.underthesea_aos.map

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Transformations.map
import com.example.underthesea_aos.R
import com.example.underthesea_aos.databinding.ActivityMainBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
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

class MapActivity :AppCompatActivity(),OnMapReadyCallback{
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: PlaceHelper
    lateinit var  database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
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
        val marker = LatLng(37.5666805, 126.9784147) //서울시청 좌표
        //마커
        mMap.addMarker(MarkerOptions().position(marker).title("marker in seoul"))
        //MarkerOptions() marker를 만드는 역할
        //position() marker의 위치
        //title 클릭 했을 때 타이틀을 만들어준다
        //카메라의 위치를 마커가 바라보는 곳으로 위치 시켜준다
        val cameraOption = CameraPosition.Builder().target(marker).zoom(15f).build()
        val camera = CameraUpdateFactory.newCameraPosition(cameraOption)
        mMap.moveCamera(camera)
    }
}

