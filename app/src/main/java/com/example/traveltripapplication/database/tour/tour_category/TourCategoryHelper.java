package com.example.traveltripapplication.database.tour.tour_category;

import static com.example.traveltripapplication.database.tour.tour_category.TourCategoryContract.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.database.DatabaseInformation;
import com.example.traveltripapplication.database.tour.category.categoryContract;
import com.example.traveltripapplication.database.tour.tour.TourContracts;

import java.util.List;

public class TourCategoryHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = TourCategoryEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static TourCategoryHelper INSTANCE;

    public TourCategoryHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static TourCategoryHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TourCategoryHelper(context);
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create tour_category", "onCreate: tour_category");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TourCategoryEntry.TABLE_NAME + " ( "
                + TourCategoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TourCategoryEntry.CATEGORY_ID + " INTEGER, "
                + TourCategoryEntry.TOUR_ID + " INTEGER, "
                + "FOREIGN KEY ( "+ TourCategoryEntry.TOUR_ID +" ) " + "REFERENCES " + TourContracts.TourEntry.TABLE_NAME + " ("+ TourContracts.TourEntry._ID +"), "
                + "FOREIGN KEY ("+TourCategoryEntry.CATEGORY_ID+") " + "REFERENCES " + categoryContract.CategoryEntry.TABLE_NAME + " ("+ categoryContract.CategoryEntry._ID +"));"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void InitDataTemplates(){
        List<TourCategoryModel> tourCategoryModels = TourCategoryDataTemplates.getValues();
        tourCategoryModels.forEach(tourCategoryModel -> {
            insert(tourCategoryModel.getTour_id(), tourCategoryModel.getCategory_id());
        });
    }

    public boolean isExistsTourCategory(long tourID, long cateID){
        try(SQLiteDatabase db = getReadableDatabase()) {
            String whereClause = TourCategoryEntry.TOUR_ID + " = ? AND " + TourCategoryEntry.CATEGORY_ID + " = ?";
            String[] whereArgs = new String[]{String.valueOf(tourID), String.valueOf(cateID)};
            Cursor cursor = db.query(TABLE_NAME, null, whereClause, whereArgs, null, null, null);
            if (cursor.moveToFirst()) {
                cursor.close();
                return true;
            }
            cursor.close();
            return false;
        }
    }

    public void insert(long tourID, long cateID){
        if(!isExistsTourCategory(tourID, cateID)) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TourCategoryEntry.TOUR_ID, tourID);
            values.put(TourCategoryEntry.CATEGORY_ID, cateID);
            long id = db.insert(TABLE_NAME, null, values);
            db.close();
        }
    }
}
