package com.example.underthesea_aos.map

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class PlaceHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version){
    lateinit var lats: ArrayList<String>
    lateinit var lngs: ArrayList<String>
    lateinit var names: Array<String>

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.disableWriteAheadLogging()
    }

    override fun onCreate(db: SQLiteDatabase) {
        var sql: String = "CREATE TABLE if not exists Place(" +
                "place_id integer primary key autoincrement," +
                "latitude text not null, " +
                "longitude text not null, " +
                "name text not null)"
                //"type integer not null," +
                //"site_url blob);"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql: String = "DROP TABLE if exists Place"
        db.execSQL(sql)
        onCreate(db)
    }

    fun insertPlace(){
        //place 마커 데이터
        lats = arrayListOf("37.7941946", "37.5284304", "37.5660148", "37.5103503", "37.5206865", "37.5677554", "35.231024",
            "34.7585289", "36.8944539", "33.5434231", "33.5582327")
        lngs = arrayListOf("127.1644031", "126.9330781", "126.8765181", "126.9960378", "127.0122724", "126.8856819", "128.684556",
            "127.9759777", "126.2066731", "126.6697752", "126.759787")
        names = arrayOf("고모리호수공원", "여의도한강공원", "난지한강공원", "반포한강공원", "잠원한강공원", "하늘공원", "용지호수공원",
            "한려해상국립공원", "태안해안국립공원", "함덕해수욕장", "김녕해수욕장")

        val db = this.writableDatabase

        for(i in 0..lats.size-1) {
            val values = ContentValues()
            values.put("latitude", lats[i])
            values.put("longitude", lngs[i])
            values.put("name", names[i])
            db.insert("Place", null, values)
//            db.execSQL(
//                "insert into Place(latitude,longitude,name) values(" +
//                        lats[i] + "," +
//                        lngs[i] + "," +
//                        "'names[i]'" + ")"
//            )
        }
        db.close()
    }
}
