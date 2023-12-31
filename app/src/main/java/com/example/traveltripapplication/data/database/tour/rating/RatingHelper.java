package com.example.traveltripapplication.data.database.tour.rating;

import static com.example.traveltripapplication.data.database.tour.rating.RatingContracts.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.data.database.DatabaseInformation;
import com.example.traveltripapplication.data.database.tour.tour.TourContracts;
import com.example.traveltripapplication.data.database.user.user.UserContract;
import com.example.traveltripapplication.model.RatingModel;
import com.example.traveltripapplication.model.UserModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RatingHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = RatingEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static RatingHelper INSTANCE;

    public RatingHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static RatingHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RatingHelper(context);
        }
        return INSTANCE;
    }

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("created rating", "onCreate: rating");
        db.execSQL("create table if not exists " + TABLE_NAME + " ( "
                + RatingEntry._ID + " integer primary key autoincrement, "
                + RatingEntry.TOUR_ID + " integer, "
                + RatingEntry.USER_ID + " integer, "
                + RatingEntry.SCORES + " real, "
                + RatingEntry.MESSAGE + " text, "
                + RatingEntry.CREATED_DATE + " text, "
                + "foreign key ("+ RatingEntry.TOUR_ID +") references " + TourContracts.TourEntry.TABLE_NAME + " ("+ TourContracts.TourEntry._ID +"), "
                + "foreign key ("+ RatingEntry.USER_ID +") references " + UserContract.UserEntry.TABLE_NAME + " ("+ UserContract.UserEntry._ID +"));"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void initDataTemplates(){
        List<RatingModel> ratingModels = RatingDataTemplates.getValues();
        ratingModels.forEach(this::insert);
    }

    @SuppressLint("Range")
    public ArrayList<RatingModel> getRatingByTourId(long tourId, int currentPage){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<RatingModel> ratingModels = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT r.*, u.full_name, u.avatar\n" +
                "FROM rating r\n" +
                "INNER JOIN user u\n" +
                "ON r.user_id = u._id\n" +
                "WHERE r.tour_id = ?\n" +
                "ORDER BY r._id DESC\n" +
                "LIMIT ?,?", new String[]{String.valueOf(tourId), String.valueOf(((currentPage - 1) * 2)), String.valueOf(2)});
        while (cursor.moveToNext()){
            RatingModel ratingModel = new RatingModel();
            ratingModel.set_id(cursor.getLong(cursor.getColumnIndex(RatingEntry._ID)));
            ratingModel.setTourId(cursor.getLong(cursor.getColumnIndex(RatingEntry.TOUR_ID)));
            ratingModel.setScores(cursor.getDouble(cursor.getColumnIndex(RatingEntry.SCORES)));
            ratingModel.setMessage(cursor.getString(cursor.getColumnIndex(RatingEntry.MESSAGE)));
            ratingModel.setCreatedDate(cursor.getString(cursor.getColumnIndex(RatingEntry.CREATED_DATE)));
            UserModel userModel = new UserModel();
            userModel.set_ID(cursor.getLong(cursor.getColumnIndex(RatingEntry.USER_ID)));
            userModel.setFull_name(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.FULL_NAME)));
            userModel.setAvatar(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.AVATAR)));
            ratingModel.setUserModel(userModel);
            ratingModels.add(ratingModel);
        }
        cursor.close();
        return ratingModels;
    }

    @SuppressLint("Range")
    public int getCommentCountByTourId(long tourId){
        SQLiteDatabase db = getReadableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery("SELECT count(r.tour_id) as _count\n" +
                "FROM rating r\n" +
                "WHERE r.tour_id = ?", new String[]{String.valueOf(tourId)});
        if(cursor.moveToFirst()){
            count = cursor.getInt(cursor.getColumnIndex("_count"));
        }
        cursor.close();
        return count;
    }

    @SuppressLint("Range")
    public double getAverageRatingByTourId(long tourId){
        SQLiteDatabase db = getReadableDatabase();
        double avgRating = 0.0;
        String[] columns = new String[]{"avg("+RatingEntry.SCORES+") as avg_rating"};
        String whereClause = RatingEntry.TOUR_ID + " = ? ";
        String[] whereArgs = new String[]{String.valueOf(tourId)};
        Cursor cursor = db.query(TABLE_NAME, columns, whereClause, whereArgs, null, null,null);
        if(cursor.moveToFirst()){
            avgRating = cursor.getDouble(cursor.getColumnIndex("avg_rating"));
        }
        cursor.close();
        return avgRating;
    }

    public long insert(RatingModel rating){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RatingEntry.TOUR_ID, rating.getTourId());
        values.put(RatingEntry.USER_ID, rating.getUserId());
        values.put(RatingEntry.SCORES, rating.getScores());
        values.put(RatingEntry.MESSAGE, rating.getMessage());
        values.put(RatingEntry.CREATED_DATE, String.valueOf(dtf.format(now)));
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }
}
