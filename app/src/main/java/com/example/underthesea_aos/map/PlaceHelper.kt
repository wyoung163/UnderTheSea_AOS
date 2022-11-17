package com.example.underthesea_aos.map

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PlaceHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version){
    override fun onCreate(db: SQLiteDatabase) {
        var sql: String = "CREATE TABLE if not exists Place(" +
                "place_id integer primary key autoincrement," +
                "name text not null," +
                "location text not null,"+
                "content text," +
                "type integer not null," +
                "site_url blob);"

        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql: String = "DROP TABLE if exists Place"
        db.execSQL(sql)
        onCreate(db)
    }
}
