package com.example.traveltripapplication.database.position;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.database.DatabaseInformation;
import com.example.traveltripapplication.database.position.PositionContract.PositionEntry;

public class PositionHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = PositionEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static PositionHelper INSTANCE;

    public PositionHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static PositionHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PositionHelper(context);
        }
        return INSTANCE;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create position", "onCreate: position");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + PositionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PositionEntry.POSITION_NAME + " TEXT NOT NULL);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + PositionEntry.POSITION_NAME+" ) VALUES ('owner');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + PositionEntry.POSITION_NAME+" ) VALUES ('customer');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + PositionEntry.POSITION_NAME+" ) VALUES ('staff');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public long insert(String position_name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("position_name", position_name);
        long id = db.insert(TABLE_NAME, PositionEntry._ID, values);
        db.close();
        return id;
    }

    public Cursor getAll() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME +" WHERE _id = ?;", new String[]{String.valueOf(id)});
        db.close();
    }
}
