package com.netsahiwot.netsa_hiwot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sammie on 8/16/2016.
 */

public class TemptedDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "NetsaDB.db";
    public static final String TBL_NAME = "Tempted";
    public static final String ID = "_id";
    public static final String VERSE = "Verse";
    public static final String LOC_AUTH = "Loc_Auth";
    private static final int DATABASE_VERSION = 1;
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context myContext;

    public TemptedDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    public void CopyDataBaseFromAsset() throws IOException {

        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = getDatabasePath();

        // if the path doesn't exist first, create it
        File f = new File(myContext.getApplicationInfo().dataDir
                + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    private static String getDatabasePath() {
        return myContext.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }

    public void openDataBase() throws SQLException {
        File dbFile = myContext.getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Log.d(getClass().getName(), "Copying success from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TemptedDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        try {
            CopyDataBaseFromAsset();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.w(TemptedDatabaseHelper.class.getName(), "Data base is upgraded  ");

    }

    public boolean addQuote(String verse, String source) {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper addQuote()...");
        ContentValues contentValues = new ContentValues();
        contentValues.put(VERSE, verse);
        contentValues.put(LOC_AUTH, source);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertOrThrow(TBL_NAME, "", contentValues);
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper addQuote()...");
        return true;
    }

    public boolean updateQuote(int id, String quote, String loc) {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper UpdateQuote()...");
        ContentValues contentValues = new ContentValues();
        contentValues.put("Verse", quote);
        contentValues.put("Loc_Auth", loc);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TBL_NAME, contentValues, ID + " = '" + id + "'", null);
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper UpdateQuote()...");
        return true;
    }

    public Cursor getAllQuote() {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper getAllQuote()...");
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor res = dbr.rawQuery("SELECT * FROM " + TBL_NAME, null);
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper getAllQuote()...");
        return res;
    }

    public int numberOfRows() {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper numberOfRows()...");
        SQLiteDatabase dbr = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(dbr, TBL_NAME);
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper numberOfRows()...");
        return numRows;
    }

    public int rmvVerse(int pos) {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper rmvVerse()...");
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TBL_NAME, ID + " = '" + pos + "'", null);
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper rmvVerse()...");
        return result;
    }

    public String lookFor(String _id) {
        Log.d("Hi Sammie!!!", "I just got into lookFor() method");
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor c = dbr.rawQuery("SELECT " + VERSE + " FROM " + TBL_NAME + " WHERE " + ID + " = " + Integer.parseInt(_id), null);
        if (c.moveToFirst()) {
            return c.getString(c.getColumnIndex(VERSE));
        } else
            return null;
    }

    public ArrayList<ArrayList<String>> getAllVerses() {
        Log.d("Hi Sammie!!! ---", "Inside TemptedDatabaseHelper <ArrayList> getAllVerses()...");
        ArrayList<ArrayList<String>> allVersesAL = new ArrayList<ArrayList<String>>();
        ArrayList<String> subAL;
        Log.d("Hi Sammie!!! ---", "Formed an arraylist object...");
        Cursor allVerse = getAllQuote();
        Log.d("Hi Sammie!!! ---", "Method getAllQuote() has brought a Data...");
        allVerse.moveToFirst();
        Log.d("Hi Sammie!!! ---", "Moved to the first of the cursor...");
        do {
            subAL = new ArrayList<>();
            subAL.clear();
            subAL.add(allVerse.getString(allVerse.getColumnIndex(VERSE)));
            subAL.add(allVerse.getString(allVerse.getColumnIndex(LOC_AUTH)));
            Log.d("Content of subAL", subAL + "");
            allVersesAL.add(subAL);
        } while (allVerse.moveToNext());
        Log.d("Hi Sammie!!! ---", "Finished TemptedDatabaseHelper <ArrayList> getAllVerses()...");
        // Only here for testing purpose
/*        for (List<String> li : allVersesAL) {
            try {
                Log.d(getClass().getMethod("getAllVerses", null).getName(), "Inside the for loop...");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            for (String s : li) {
                Log.d("getAllVerses", "Inside the second for loop...");
                Log.d("Testing arraylist...", s + "  ::  ");
            }
        }
        */
        return allVersesAL;
    }
}