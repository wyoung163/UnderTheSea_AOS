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
    lateinit var lats: ArrayList<String>
    lateinit var lngs: ArrayList<String>
    lateinit var names: ArrayList<String>
    /*
    <장소 database>
    lateinit var dbHelper: PlaceHelper
    lateinit var  database: SQLiteDatabase
     */

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_map)

        /*
        <장소 database>
        dbHelper = PlaceHelper(this, "place.db", null, 1);
        database = dbHelper.readableDatabase
         */

        //place 마커 데이터
        lats = arrayListOf("37.7941946", "37.5284304", "37.5660148", "37.5103503", "37.5206865", "37.5677554", "35.231024",
            "34.7585289", "36.8944539", "33.5434231", "33.5582327")
        lngs = arrayListOf("127.1644031", "126.9330781", "126.8765181", "126.9960378", "127.0122724", "126.8856819", "128.684556",
            "127.9759777", "126.2066731", "126.6697752", "126.759787")
        names = arrayListOf("고모리호수공원", "여의도한강공원", "난지한강공원", "반포한강공원", "잠원한강공원", "하늘공원", "용지호수공원",
            "한려해상국립공원", "태안해안국립공원", "함덕해수욕장", "김녕해수욕장")

        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //val marker = LatLng(37.5666805, 126.9784147) //서울시청 좌표
        var marker: LatLng? = null
        for(i in 0..lats.size-1) {
            marker = LatLng(lats[i].toDouble(), lngs[i].toDouble())
            //마커
            mMap.addMarker(MarkerOptions().position(marker).title(names[i]))
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