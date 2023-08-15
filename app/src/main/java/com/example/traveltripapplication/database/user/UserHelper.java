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
                + UserEntry.FULL_NAME + " TEXT, "
                + UserEntry.BIRTHDAY + " TEXT, "
                + UserEntry.IS_ENOUGH + " INTEGER, "
                + "FOREIGN KEY ("+UserEntry.CONTACTS+") REFERENCES "+ContactsContract.ContactsEntry.TABLE_NAME+" ("+ ContactsContract.ContactsEntry._ID +"), "
                + "FOREIGN KEY ("+UserEntry.STATE+") REFERENCES "+ StateContract.StateRetry.TABLE_NAME+" ("+ StateContract.StateRetry._ID +") "
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
                + UserEntry.FULL_NAME + ", "
                + UserEntry.IS_ENOUGH + ", "
                + UserEntry.BIRTHDAY + " ) "
                + "VALUES ('admin', " //username
                + "'admin123456', " //password
                + "'admin.traveltrip@gmail.com', " //email
                + "1, " //contacts_id
                + "'https://i.pinimg.com/564x/40/98/2a/40982a8167f0a53dedce3731178f2ef5.jpg', " //avatar link
                + "1, " //state_id
                + "1, " // is super user
                + " '" + String.valueOf(dtf.format(now)) + "', " //last login
                + " '" + String.valueOf(dtf.format(now)) + "', " // created date
                + "'admin', " // full name
                + "1, " //is_enough
                + "'" + String.valueOf(dtf.format(now)) + "'" //birthday
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }


    public Boolean isUsernameExists(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

    public Boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

    public long createAccount(String full_name, String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserEntry.FULL_NAME, full_name);
        values.put(UserEntry.USERNAME, username);
        values.put(UserEntry.PASSWORD, password);
        values.put(UserEntry.AVATAR, "https://i.pinimg.com/564x/40/98/2a/40982a8167f0a53dedce3731178f2ef5.jpg");
        values.put(UserEntry.IS_ENOUGH, 0);
        values.put(UserEntry.STATE, 1);
        values.put(UserEntry.IS_SUPER_USER, 0);
        values.put(UserEntry.CREATED_DATE, dtf.format(now));
        long id;
        if (!isUsernameExists(username)){
            id = db.insert(TABLE_NAME, UserEntry._ID, values);
        }
        else {
            id=-1;
        }
        return id;
    }

    public int updateUser(UserModel user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserEntry.AVATAR, user.getAvatar());
        values.put(UserEntry.FULL_NAME, user.getFull_name());
        values.put(UserEntry.EMAIL, user.getEmail());
        values.put(UserEntry.CONTACTS, user.getContacts_id());
        values.put(UserEntry.BIRTHDAY, user.getBirthday());
        values.put(UserEntry.LAST_LOGIN, dtf.format(now));
        values.put(UserEntry.IS_ENOUGH, 1);
        int id = db.update(TABLE_NAME, values, UserEntry._ID + " = ?", new String[]{String.valueOf(user.get_ID())});
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
            user.setFull_name(result.getString(result.getColumnIndex(UserEntry.FULL_NAME)));
        }
        else {
           user.set_ID(-1);
        }
        result.close();
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
            user.setFull_name(result.getString(result.getColumnIndex(UserEntry.FULL_NAME)));
        }
        else {
            user.set_ID(-1);
        }
        return user;
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME +" WHERE _id = ?;", new String[]{String.valueOf(id)});
        db.close();
    }
}
