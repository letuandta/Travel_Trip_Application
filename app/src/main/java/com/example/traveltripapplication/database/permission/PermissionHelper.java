package com.example.traveltripapplication.database.permission;

import static com.example.traveltripapplication.database.permission.PermissionContract.PermissionEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.traveltripapplication.database.DatabaseInformation;

public class PermissionHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    public static final int VERSION = DatabaseInformation.VERSION;

    public static final String TABLE_NAME = PermissionEntry.TABLE_NAME;

    private static PermissionHelper INSTANCE;

    public PermissionHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static PermissionHelper getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new PermissionHelper(context);
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create peemission", "onCreate: permission");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + PermissionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PermissionEntry.PERMISSION__NAME + " TEXT NOT NULL);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + PermissionEntry.PERMISSION__NAME+" ) VALUES ('admin');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + PermissionEntry.PERMISSION__NAME+" ) VALUES ('customer');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + PermissionEntry.PERMISSION__NAME+" ) VALUES ('tour manager');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + PermissionEntry.PERMISSION__NAME+" ) VALUES ('order manager');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public long insert(String permission_name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PermissionEntry.PERMISSION__NAME, permission_name);
        long id = db.insert(TABLE_NAME, PermissionEntry._ID, values);
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
