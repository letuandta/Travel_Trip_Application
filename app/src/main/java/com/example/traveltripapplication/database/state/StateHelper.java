package com.example.traveltripapplication.database.state;

import static com.example.traveltripapplication.database.state.StateContract.StateRetry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.database.DatabaseInformation;

public class StateHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = StateRetry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static StateHelper INSTANCE;

    public StateHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static StateHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new StateHelper(context);
        }
        return INSTANCE;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create state", "onCreate: state");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + StateRetry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StateRetry.STATE_NAME + " TEXT NOT NULL);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + StateRetry.STATE_NAME+" ) VALUES ('actived');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + StateRetry.STATE_NAME+" ) VALUES ('stop');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
