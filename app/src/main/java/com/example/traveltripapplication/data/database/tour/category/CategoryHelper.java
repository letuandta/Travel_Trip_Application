package com.example.traveltripapplication.data.database.tour.category;

import static com.example.traveltripapplication.data.database.tour.category.categoryContract.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.data.database.DatabaseInformation;
import com.example.traveltripapplication.data.database.user.user.UserContract;
import com.example.traveltripapplication.model.CategoryModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = CategoryEntry.TABLE_NAME;
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
                                + CategoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                + CategoryEntry.CATEGORY_CODE + " TEXT, "
                                + CategoryEntry.CATEGORY_NAME + " TEXT);"
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
                values.put(CategoryEntry.CATEGORY_CODE, categoryModel.getCateCode());
                values.put(CategoryEntry.CATEGORY_NAME, categoryModel.getCateName());
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
        values.put(CategoryEntry.CATEGORY_CODE, categoryModel.getCateCode());
        values.put(CategoryEntry.CATEGORY_NAME, categoryModel.getCateName());
        long id = db.insert(TABLE_NAME, CategoryEntry._ID, values);
        db.close();
        return id;
    }
    @SuppressLint("Range")
    public ArrayList<CategoryModel> getListCate() {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            categoryModels.add(new CategoryModel(
                    cursor.getLong(cursor.getColumnIndex(CategoryEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(CategoryEntry.CATEGORY_CODE)),
                    cursor.getString(cursor.getColumnIndex(CategoryEntry.CATEGORY_NAME))
            ));
        }
        cursor.getCount();
        Log.d("listCate", "getListCate: "+ cursor.getCount());
        cursor.close();
        return categoryModels;
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME +" WHERE _id = ?;", new String[]{String.valueOf(id)});
        db.close();
    }

    public int update(CategoryModel categoryModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CategoryEntry.CATEGORY_CODE, categoryModel.getCateCode());
        values.put(CategoryEntry.CATEGORY_NAME, categoryModel.getCateName());
        int id = db.update(TABLE_NAME, values, CategoryEntry._ID + " = ?", new String[]{String.valueOf(categoryModel.getCateID())});
        db.close();
        return id;
    }


}
