package com.example.traveltripapplication.data.database.tour.category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.data.database.DatabaseInformation;
import com.example.traveltripapplication.model.CategoryModel;

import java.util.Arrays;
import java.util.List;

public class CategoryHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = categoryContract.CategoryEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static CategoryHelper INSTANCE;

    public CategoryHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static CategoryHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryHelper(context);
        }
        return INSTANCE;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create category", "onCreate: category");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                                + categoryContract.CategoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                + categoryContract.CategoryEntry.CATEGORY_CODE + " TEXT, "
                                + categoryContract.CategoryEntry.CATEGORY_NAME + " TEXT);"
                );
    }

    public void initDataTemplates(){
        Cursor cursor = getCategoryById(1);
        SQLiteDatabase db = getWritableDatabase();
        if(cursor.getCount() == 0){
            List<CategoryModel> categoryModels = Arrays.asList(
                    new CategoryModel(1, "C001", "Trong nước"),
                    new CategoryModel(2, "C002", "Quốc tế"),
                    new CategoryModel(3, "C003", "Tour tham quan"),
                    new CategoryModel(4, "C004", "Tour nghĩ dưỡng"),
                    new CategoryModel(5, "C005", "Tour lịch sử")
            );
            ContentValues values = new ContentValues();
            categoryModels.forEach(categoryModel -> {
                values.put(categoryContract.CategoryEntry.CATEGORY_CODE, categoryModel.getCateCode());
                values.put(categoryContract.CategoryEntry.CATEGORY_NAME, categoryModel.getCateName());
                long id = db.insert(TABLE_NAME, null, values);
                values.clear();
            });
        }
        cursor.close();
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public Cursor getCategoryById(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE _id = ?;" , new String[]{String.valueOf(id)});
        return cursor;
    }

    public long insert(CategoryModel categoryModel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(categoryContract.CategoryEntry.CATEGORY_CODE, categoryModel.getCateCode());
        values.put(categoryContract.CategoryEntry.CATEGORY_NAME, categoryModel.getCateName());
        long id = db.insert(TABLE_NAME, categoryContract.CategoryEntry._ID, values);
        db.close();
        return id;
    }
}
