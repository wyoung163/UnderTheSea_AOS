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
import kotlin.math.ln

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

        dbHelper = PlaceHelper(this, "place.db", null, 2);
        database = dbHelper.writableDatabase
        dbHelper.insertPlace()

        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val select = "select * from Place"
        database = dbHelper.readableDatabase
        //데이터를 받아 줍니다.
        val cursor = database.rawQuery(select,null)

        /*
        while(cursor.moveToNext()){
            val place_id = cursor.getLong(cursor.getColumnName())
            val latitude = cursor.getString(cursor.getColumnIndex("latitude"))
            val longitude = cursor.getString(cursor.getColumnIndex("longitude"))
            val name = cursor.getString(cursor.getColumnIndex("name"))

            list.add(Place(place_id,latitude,longitude,name))
        }
        cursor.close()

         */
        //val marker = LatLng(37.5666805, 126.9784147) //서울시청 좌표
        var marker: LatLng? = null
        while (cursor.moveToNext())
        {
            marker = LatLng(cursor.getString(1).toDouble(), cursor.getString(2).toDouble())
            //마커
            mMap.addMarker(MarkerOptions().position(marker).title(cursor.getString(3)))
        }

        //MarkerOptions() marker를 만드는 역할
        //position() marker의 위치
        //title 클릭 했을 때 타이틀을 만들어준다
        //카메라의 위치를 마커가 바라보는 곳으로 위치 시켜준다
        val cameraOption = CameraPosition.Builder().target(LatLng(35.9666805, 126.9784147)).zoom(7f).build()
        val camera = CameraUpdateFactory.newCameraPosition(cameraOption)
        mMap.moveCamera(camera)
    }
}