package com.example.traveltripapplication.data.database.user.contacts;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.data.database.DatabaseInformation;
import com.example.traveltripapplication.model.ContactsModel;

public class ContactsHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = ContactsContract.ContactsEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static ContactsHelper INSTANCE;

    public ContactsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static ContactsHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ContactsHelper(context);
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            Log.d("create contacts", "onCreate: contacts");
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + ContactsContract.ContactsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ContactsContract.ContactsEntry.PHONE_NUMBER + " TEXT, "
                    + ContactsContract.ContactsEntry.EMAIL + " TEXT, "
                    + ContactsContract.ContactsEntry.CURRENT_ADDRESS + " TEXT, "
                    + ContactsContract.ContactsEntry.MORE_INFORMATION + " TEXT );");
            db.execSQL("INSERT INTO " + TABLE_NAME + " ( "
                    + ContactsContract.ContactsEntry.PHONE_NUMBER + ", "
                    + ContactsContract.ContactsEntry.EMAIL + ", "
                    + ContactsContract.ContactsEntry.CURRENT_ADDRESS + ", "
                    + ContactsContract.ContactsEntry.MORE_INFORMATION + " ) "
                    + "VALUES ('0364971541', "
                    + "'letuandat20201@gmail.com', "
                    + "'Le Van Chi, Linh Trung, Thu Duc, TP.HCM', "
                    + "'Admin app, dev'"
                    + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public long insert(ContactsModel contactsModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContactsContract.ContactsEntry.PHONE_NUMBER, contactsModel.getPhone_number());
        values.put(ContactsContract.ContactsEntry.CURRENT_ADDRESS, contactsModel.getAddress());
        values.put(ContactsContract.ContactsEntry.MORE_INFORMATION, contactsModel.getMore());
        long id = db.insert(TABLE_NAME, ContactsContract.ContactsEntry._ID, values);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public ContactsModel getContactsById(long id) {
        SQLiteDatabase db = getReadableDatabase();
        ContactsModel contactsModel = new ContactsModel();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE _id = ?;" , new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            contactsModel.setAddress(cursor.getString(cursor.getColumnIndex(ContactsContract.ContactsEntry.CURRENT_ADDRESS)));
            contactsModel.setMore(cursor.getString(cursor.getColumnIndex(ContactsContract.ContactsEntry.MORE_INFORMATION)));
            contactsModel.setPhone_number(cursor.getString(cursor.getColumnIndex(ContactsContract.ContactsEntry.PHONE_NUMBER)));
        }
        cursor.close();
        return contactsModel;
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME +" WHERE _id = ?;", new String[]{String.valueOf(id)});
        db.close();
    }

    public int update(ContactsModel contactsModel, long contactId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContactsContract.ContactsEntry.PHONE_NUMBER, contactsModel.getPhone_number());
        values.put(ContactsContract.ContactsEntry.CURRENT_ADDRESS, contactsModel.getAddress());
        values.put(ContactsContract.ContactsEntry.MORE_INFORMATION, contactsModel.getMore());
        int id = db.update(TABLE_NAME, values, ContactsContract.ContactsEntry._ID + " = ? ", new String[]{String.valueOf(contactId)}  );
        db.close();
        return id;
    }
}
