package com.fitmotivator.eagleeye.fitmotivator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Hafiz on 2/9/2015.
 */
public class DBAdapter{




    // For logging:
    private static final String TAG = "DBAdapter";

    // DB Fields
    public static final String KEY_ROWID = "_id";
    public static final int COL_ROWID = 0;

    //Table columns
    public static final String KEY_ID = "day_id";
    public static final String KEY_DAY = "day";
    public static final String KEY_WEEK = "day_type";

    // TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
    public static final int COL_DAY = 1;
    public static final int COL_DAY_TYPE = 2;

    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_DAY, KEY_WEEK};


    //Database Version
    public static final int DATABASE_VERSION = 1;
    //DB name
    public static final String DATABASE_NAME = "schedule";
    //TABLE name
    public static final String TABLE_NAME = "mainTable";

    private static final String DATABASE_CREATE_SQL = "CREATE TABLE " + TABLE_NAME + "(" +
            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DAY + " TEXT, " +
            KEY_WEEK + " TEXT " + ");";

    //Context of application using database
    private final Context context;

    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;


    /////////////////////////////////////////////////////////////////////
    //	Public methods:
    /////////////////////////////////////////////////////////////////////

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    //Open the database connection
    public DBAdapter open(){
       db = myDBHelper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }

    //Adding values to table

    public long insertRow(String day, String type){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DAY, day);
        initialValues.put(KEY_WEEK, type);

        //Insert it into the database
        return db.insert(TABLE_NAME, null, initialValues);
    }

    //Return all data in the database
    public Cursor getAllRows(){
        String where = null;
        Cursor c = db.query(true, TABLE_NAME, ALL_KEYS, where, null, null, null, null, null);

        if (c != null){
            c.moveToFirst();
        }

        return c;
    }

    public void deleteAll(){
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
        if(c.moveToFirst()){
            do {
                deleteRow(c.getLong((int) rowId));
            }while (c.moveToNext());
        }
        c.close();
    }

    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        return db.delete(TABLE_NAME, where, null) != 0;
    }

    /////////////////////////////////////////////////////////////////////
    //	Private Helper Classes:
    /////////////////////////////////////////////////////////////////////

    /**
     * Private class which handles database creation and upgrading.
     * Used to handle low-level database access.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

            // Recreate new database:
            onCreate(_db);
        }
    }
}
