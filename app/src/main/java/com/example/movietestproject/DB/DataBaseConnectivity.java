package com.example.movietestproject.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnectivity extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movies.db";
    public static final String TABLE_NAME = "favourite_movie_info";
    public static final String COL1 = "movieID";
    public static final String COL2 = "movieTitle";
    public static final String COL3 = "movieOverview";
    public static final String COL4 = "movieOriginalTitle";
    public static final String COL5 = "movieRating";
    public static final String COL6 = "movieReleaseDate";
    public static final String COL7 = "movieImageURL";

    private static DataBaseConnectivity dbInstance;
    private Context context;

    private DataBaseConnectivity(Context context)
    {
        super(context, DATABASE_NAME, null,1);
        this.context=context;
    }

    public static DataBaseConnectivity getDBInstance(Context context)
    {
        if(dbInstance == null)
        {
            dbInstance = new DataBaseConnectivity(context.getApplicationContext());
        }
        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (movieID INTEGER PRIMARY KEY AUTOINCREMENT, " + "movieTitle TEXT, movieOverview TEXT, movieOriginalTitle TEXT, movieRating NUMERIC, movieReleaseDate TEXT, movieImageURL TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    public boolean addData(String title, String overview, String originalTitle, Double rating, String releaseDate, String imageURL)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL2, title);
        values.put(COL3, overview);
        values.put(COL4, originalTitle);
        values.put(COL5, rating);
        values.put(COL6, releaseDate);
        values.put(COL7, imageURL);


        long result = db.insert(TABLE_NAME, null, values);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getContents()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL2, null);
        return data;
    }
}
