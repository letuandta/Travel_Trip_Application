package com.example.traveltripapplication.database.user.contacts;

import static com.example.traveltripapplication.database.user.contacts.ContactsContract.ContactsEntry;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.database.DatabaseInformation;
import com.example.traveltripapplication.model.ContactsModel;

public class ContactsHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = ContactsEntry.TABLE_NAME;
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
                    + ContactsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ContactsEntry.PHONE_NUMBER + " TEXT, "
                    + ContactsEntry.EMAIL + " TEXT, "
                    + ContactsEntry.CURRENT_ADDRESS + " TEXT, "
                    + ContactsEntry.MORE_INFORMATION + " TEXT );");
            db.execSQL("INSERT INTO " + TABLE_NAME + " ( "
                    + ContactsEntry.PHONE_NUMBER + ", "
                    + ContactsEntry.EMAIL + ", "
                    + ContactsEntry.CURRENT_ADDRESS + ", "
                    + ContactsEntry.MORE_INFORMATION + " ) "
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
        values.put(ContactsEntry.PHONE_NUMBER, contactsModel.getPhone_number());
        values.put(ContactsEntry.CURRENT_ADDRESS, contactsModel.getAddress());
        values.put(ContactsEntry.MORE_INFORMATION, contactsModel.getMore());
        long id = db.insert(TABLE_NAME, ContactsEntry._ID, values);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public ContactsModel getContactsById(long id) {
        SQLiteDatabase db = getReadableDatabase();
        ContactsModel contactsModel = new ContactsModel();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE _id = ?;" , new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            contactsModel.setAddress(cursor.getString(cursor.getColumnIndex(ContactsEntry.CURRENT_ADDRESS)));
            contactsModel.setMore(cursor.getString(cursor.getColumnIndex(ContactsEntry.MORE_INFORMATION)));
            contactsModel.setPhone_number(cursor.getString(cursor.getColumnIndex(ContactsEntry.PHONE_NUMBER)));
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
        values.put(ContactsEntry.PHONE_NUMBER, contactsModel.getPhone_number());
        values.put(ContactsEntry.CURRENT_ADDRESS, contactsModel.getAddress());
        values.put(ContactsEntry.MORE_INFORMATION, contactsModel.getMore());
        int id = db.update(TABLE_NAME, values,ContactsEntry._ID + " = ? ", new String[]{String.valueOf(contactId)}  );
        db.close();
        return id;
    }
}
