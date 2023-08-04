package com.example.traveltripapplication.database.user;

import static com.example.traveltripapplication.database.user.UserContract.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.database.DatabaseInformation;
import com.example.traveltripapplication.database.contacts.ContactsContract;
import com.example.traveltripapplication.database.contacts.ContactsHelper;
import com.example.traveltripapplication.database.state.StateContract;
import com.example.traveltripapplication.model.UserModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = UserEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static UserHelper INSTANCE;

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static UserHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserHelper(context);
        }
        return INSTANCE;
    }

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create user", "onCreate: user");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserEntry.USERNAME + " TEXT, "
                + UserEntry.PASSWORD + " TEXT, "
                + UserEntry.EMAIL + " TEXT, "
                + UserEntry.CONTACTS + " INTEGER, "
                + UserEntry.AVATAR + " TEXT, "
                + UserEntry.STATE + " INTEGER, "
                + UserEntry.IS_SUPER_USER + " INTEGER, "
                + UserEntry.LAST_LOGIN + " TEXT, "
                + UserEntry.CREATED_DATE + " TEXT, "
                + UserEntry.FIRST_NAME + " TEXT, "
                + UserEntry.LAST_NAME + " TEXT, "
                + UserEntry.BIRTHDAY + " TEXT, "
                + "FOREIGN KEY ("+UserEntry.CONTACTS+") REFERENCES "+ContactsContract.ContactsEntry.TABLE_NAME+" ("+ ContactsContract.ContactsEntry._ID +"), "
                + "FOREIGN KEY ("+UserEntry.CONTACTS+") REFERENCES "+ StateContract.StateRetry.TABLE_NAME+" ("+ StateContract.StateRetry._ID +") "
                + ");");
        db.execSQL("INSERT INTO " + TABLE_NAME + " ( "
                + UserEntry.USERNAME + ", "
                + UserEntry.PASSWORD + ", "
                + UserEntry.EMAIL + ", "
                + UserEntry.CONTACTS + ", "
                + UserEntry.AVATAR + ", "
                + UserEntry.STATE + ", "
                + UserEntry.IS_SUPER_USER + ", "
                + UserEntry.LAST_LOGIN + ", "
                + UserEntry.CREATED_DATE + ", "
                + UserEntry.FIRST_NAME + ", "
                + UserEntry.LAST_NAME + ", "
                + UserEntry.BIRTHDAY + " ) "
                + "VALUES ('admin', "
                + "'admin123456', "
                + "'admin.traveltrip@gmail.com', "
                + "1, "
                + "'https://i.pinimg.com/564x/40/98/2a/40982a8167f0a53dedce3731178f2ef5.jpg', "
                + "1, "
                + "1, "
                + " '" + String.valueOf(dtf.format(now)) + "', "
                + " '" + String.valueOf(dtf.format(now)) + "', "
                + "'admin', "
                + "'traveltrip', "
                + "'" + String.valueOf(dtf.format(now)) + "'"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public long createAccount(String username, String email, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserEntry.USERNAME, username);
        values.put(UserEntry.PASSWORD, password);
        values.put(UserEntry.EMAIL, email);
        long id = db.insert(TABLE_NAME, UserEntry._ID, values);
        return id;
    }

    public int updateUser(UserModel user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserEntry.USERNAME, user.getUsername());
        values.put(UserEntry.PASSWORD, user.getPassword());
        values.put(UserEntry.AVATAR, user.getAvatar());
        values.put(UserEntry.EMAIL, user.getEmail());
        values.put(UserEntry.LAST_NAME, user.getLast_name());
        values.put(UserEntry.FIRST_NAME, user.getFirst_name());
        values.put(UserEntry.CONTACTS, user.getContacts_id());
        values.put(UserEntry.STATE, user.getState());
        values.put(UserEntry.IS_SUPER_USER, user.getIs_super_user());
        values.put(UserEntry.BIRTHDAY, user.getBirthday());
        values.put(UserEntry.CREATED_DATE, dtf.format(now));
        values.put(UserEntry.LAST_LOGIN, dtf.format(now));
        int id = db.update(TABLE_NAME, values, null, null);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public UserModel getUserByUsernameAndPassword(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        UserModel user = new UserModel();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE state_id = 1 AND username = ? AND password = ?;" ,
                new String[]{username, password});
        if(result.moveToFirst()) {
            user.set_ID(result.getLong(result.getColumnIndex(UserEntry._ID)));
            user.setAvatar(result.getString(result.getColumnIndex(UserEntry.AVATAR)));
            user.setBirthday(result.getString(result.getColumnIndex(UserEntry.BIRTHDAY)));
            user.setIs_super_user(result.getLong(result.getColumnIndex(UserEntry.IS_SUPER_USER)));
            user.setContacts_id(result.getLong(result.getColumnIndex(UserEntry.CONTACTS)));
            user.setCreated_date(result.getString(result.getColumnIndex(UserEntry.CREATED_DATE)));
            user.setLats_name(result.getString(result.getColumnIndex(UserEntry.LAST_NAME)));
            user.setFirst_name(result.getString(result.getColumnIndex(UserEntry.FIRST_NAME)));
        }
        else {
           user.set_ID(-1);
        }
        return user;
    }

    @SuppressLint("Range")
    public UserModel getUserByEmailAndPassword(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        UserModel user = new UserModel();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE state_id = 1 AND username = ? AND password = ?;" ,
                new String[]{email, password});
        if(result.moveToFirst()) {
            user.set_ID(result.getLong(result.getColumnIndex(UserEntry._ID)));
            user.setAvatar(result.getString(result.getColumnIndex(UserEntry.AVATAR)));
            user.setBirthday(result.getString(result.getColumnIndex(UserEntry.BIRTHDAY)));
            user.setIs_super_user(result.getLong(result.getColumnIndex(UserEntry.IS_SUPER_USER)));
            user.setContacts_id(result.getLong(result.getColumnIndex(UserEntry.CONTACTS)));
            user.setCreated_date(result.getString(result.getColumnIndex(UserEntry.CREATED_DATE)));
            user.setLats_name(result.getString(result.getColumnIndex(UserEntry.LAST_NAME)));
            user.setFirst_name(result.getString(result.getColumnIndex(UserEntry.FIRST_NAME)));
        }
        else {
            user.set_ID(-1);
        }
        return user;
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME +" WHERE id = ?;", new String[]{String.valueOf(id)});
        db.close();
    }
}
