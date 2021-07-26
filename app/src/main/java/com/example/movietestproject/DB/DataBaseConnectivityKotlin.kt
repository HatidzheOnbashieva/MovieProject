package com.example.movietestproject.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseConnectivityKotlin(context: Context): SQLiteOpenHelper() {
    val DATABASE_NAME = "movies.db"
    val TABLE_NAME = "favourite_movie_info"
    val COL1 = "movieID"
    val COL2 = "movieTitle"
    val COL3 = "movieOverview"
    val COL4 = "movieOriginalTitle"
    val COL5 = "movieRating"
    val COL6 = "movieReleaseDate"
    val COL7 = "movieImageURL"

    var dbInstance: DataBaseConnectivityKotlin? = null
    var context: Context? = null

    init {
        this.context = context
    }

    fun getDBInstance(context: Context): DataBaseConnectivityKotlin? {
        if (dbInstance == null) {
            dbInstance = DataBaseConnectivityKotlin(context.applicationContext)
        }
        return dbInstance
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + DataBaseConnectivity.TABLE_NAME + " (movieID INTEGER PRIMARY KEY AUTOINCREMENT, " + "movieTitle TEXT, movieOverview TEXT, movieOriginalTitle TEXT, movieRating NUMERIC, movieReleaseDate TEXT, movieImageURL TEXT)"
        db!!.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + DataBaseConnectivity.TABLE_NAME)
    }

    fun addData(
        title: String?,
        overview: String?,
        originalTitle: String?,
        rating: Double?,
        releaseDate: String?,
        imageURL: String?
    ): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(DataBaseConnectivity.COL2, title)
        values.put(DataBaseConnectivity.COL3, overview)
        values.put(DataBaseConnectivity.COL4, originalTitle)
        values.put(DataBaseConnectivity.COL5, rating)
        values.put(DataBaseConnectivity.COL6, releaseDate)
        values.put(DataBaseConnectivity.COL7, imageURL)
        val result = db.insert(DataBaseConnectivity.TABLE_NAME, null, values)
        return if (result == -1L) {
            false
        } else {
            true
        }
    }

    fun getContents(): Cursor? {
        val db = this.writableDatabase
        return db.rawQuery(
            "SELECT * FROM " + DataBaseConnectivity.TABLE_NAME + " ORDER BY " + DataBaseConnectivity.COL2,
            null
        )
    }
}