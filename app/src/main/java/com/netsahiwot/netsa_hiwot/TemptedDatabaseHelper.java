package com.solodroid.ecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Sammie on 8/16/2016.
 */

public class TemptedDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "NetsaDB";
    public static final String TBL_NAME = "Tempted";
    public static final String ID = "Id";
    public static final String VERSE = "Verse";
    public static final String LOC_AUTH = "Loc_Auth";

    public TemptedDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper onCreate...");
        db.execSQL("CREATE TABLE " + TBL_NAME + " (" + ID + " INT, " + VERSE
                + " VARCHAR, " + LOC_AUTH + " VARCHAR)");
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper onCreate...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper onUpgrade()...");
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper onUpgrade()...");
    }

    public boolean addQuote(String verse, String source) {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper addQuote()...");
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor abc = db.rawQuery("SELECT No from " + TBL_NAME + " where No = " + num, null);
        //if (!abc.moveToFirst()) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Quote", verse);
        contentValues.put("Loc_Auth", source);
        db.insertOrThrow(TBL_NAME, "", contentValues);
        Log.d("Hi Sammie!!! ---", "Finished DBOH addQuote()...");
        return true;
        /*} else {
            Log.d("Hi Sammie!!!",
                    "Finished TemptedDatabaseHelper addQuote() cause number is already blocked...");
            return false;
        }*/
    }

    public Cursor getAllQuote() {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper getAllQuote()...");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TBL_NAME, null);
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper getAllQuote()...");
        return res;
    }

    public int numberOfRows() {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper numberOfRows()...");
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TBL_NAME);
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper numberOfRows()...");
        return numRows;
    }

    public boolean rmvVerse(int pos) {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper rmvVerse()...");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor s = db.rawQuery("SELECT * FROM "+ TBL_NAME, null);
        s.moveToPosition(pos);
        int toBR = s.getInt(s.getColumnIndex(ID));
        db.delete(TBL_NAME, ID + " = '" + toBR + "'", null);
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper rmvVerse()...");
        return true;
    }

    public String lookFor(String _id) {
        Log.d("Hi Sammie!!!", "I just got into lookFor() method");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + VERSE + " FROM " + TBL_NAME + " WHERE " + ID + " = " + Integer.parseInt(_id), null);
        if (c.moveToFirst()) {
            return c.getString(c.getColumnIndex(VERSE));
        } else
            return null;
    }

    public ArrayList<String[]> getAllVerses() {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper <ArrayList> getAllVerses()...");
        ArrayList<String[]> allVersesAL = new <String[]>ArrayList();
        Log.d("Hi Sammie!!! ---", "Formed an arraylist object...");
        Cursor allVerse = getAllQuote();
        Log.d("Hi Sammie!!! ---", "Method getAllQuote() has brought a Data...");
        allVerse.moveToFirst();
        Log.d("Hi Sammie!!! ---", "Moved to the first of the arraylist...");
        String[] s = new String[2];
        while (!allVerse.isAfterLast()) {
            s[0] = allVerse.getString(allVerse.getColumnIndex(VERSE));
            s[1] = allVerse.getString(allVerse.getColumnIndex(LOC_AUTH));

            if (allVerse.getString(allVerse.getColumnIndex(VERSE)) != null)
            {
                allVersesAL.add(s);
            }
            allVerse.moveToNext();
        }
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper <ArrayList> getAllVerses()...");
        return allVersesAL;

    }

}