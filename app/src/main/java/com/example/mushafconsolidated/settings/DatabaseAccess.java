package com.example.mushafconsolidated.settings;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.mushafconsolidated.Entities.BookMarks;
import com.example.mushafconsolidated.Entities.BuckWater;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class for access quran databases
 */

public class DatabaseAccess {
    public static final String
            MAIN_DATABASE = Environment.getExternalStorageDirectory().getAbsolutePath() + AppConstants.Paths.MAIN_DATABASE_PATH;
     //     Environment.getExternalStorageDirectory().getAbsolutePath() + "/Mushafapplication"+ "/QuranDatabase.db
     public static final String
             MAIN_DATABASEVERB = Environment.getExternalStorageDirectory().getAbsolutePath() + AppConstants.Path.VERB_DATABASE_PATH;

    /**
     * Empty constructor for database class
     */
    public DatabaseAccess() {
    }

    /**
     * Function to open connection with database
     *
     * @param path database path in mobile
     * @return database object to start queries
     */
    public SQLiteDatabase openDB(String path) {

        Log.d("DATABASE", path);

        SQLiteDatabase db;
        try {
     db = SQLiteDatabase.openDatabase(path, null, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return db;

    }

    /**
     * Function to close connection with database
     *
     * @param db database object you would to close
     */
    public void closeDB(SQLiteDatabase db) {
        if (db != null)
            db.close();
    }

    public ArrayList<BuckWater> getBuckwater() {
        SQLiteDatabase db=  openDB(MAIN_DATABASEVERB);
        ArrayList<BuckWater> buckWaters = new ArrayList<BuckWater>();
        String sql = "select * from buckwater";

        db.beginTransaction();



        StringBuilder qu=new StringBuilder();

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {


            buckWaters.add(new BuckWater(cursor.getInt(0),//v.chapter
                    cursor.getString(1),//v.verseno
                    cursor.getString(2),//t.authoname
                    cursor.getString(3),cursor.getString(4)));//transation

            cursor.moveToNext();
        }
        cursor.close();
        db.endTransaction();
        return buckWaters;
    }













    public long bookmark(BookMarks translation) {

        try {
            SQLiteDatabase db = openDB(MAIN_DATABASE);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateandTime = sdf.format(new Date());
            ContentValues contentValues = new ContentValues();


            contentValues.clear();
            contentValues.put("chapterno", translation.getChapterno());
            contentValues.put("header", translation.getHeader());
            contentValues.put("verseno", translation.getVerseno());
            contentValues.put("surahname", translation.getSurahname());
            contentValues.put("datetime",currentDateandTime);
            final long datetime = db.insertWithOnConflict("bookmark", null, contentValues,
                    SQLiteDatabase.CONFLICT_REPLACE);


            //  String sql = "insert into `bookmarks` (`page` , `bookmarkTime`) values (" + page + " , '" + currentDateandTime + "')";
            // db.execSQL(sql);
         //   boolean addRow =  db.insert("bookmarks",null,row)>0;
            closeDB(db);
            return datetime;
        } catch (Exception e) {
            return 0;
        }
    }

    public  List<BookMarks> getBookmarks() {
        SQLiteDatabase db=  openDB(MAIN_DATABASE);
       List<BookMarks> translationbypar = new ArrayList<BookMarks>();
        String sql = "select * from bookmark";

        db.beginTransaction();
    //    Cursor cursor = db.rawQuery(sql, null);
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {


            translationbypar.add(new BookMarks(cursor.getInt(0),//v.chapter
                    cursor.getString(1),//v.verseno
                    cursor.getString(2),//v.verseno
                    cursor.getString(3),//t.authoname
                    cursor.getString(4),
                    cursor.getString(5)));//transation

            cursor.moveToNext();
        }
        cursor.close();
        db.endTransaction();
        return translationbypar;
    }


}
