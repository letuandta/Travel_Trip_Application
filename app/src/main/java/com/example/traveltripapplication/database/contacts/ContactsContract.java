package com.example.traveltripapplication.database.contacts;

import android.provider.BaseColumns;

public final class ContactsContract {
    public ContactsContract() {}

    public static final class ContactsEntry{
        public static final String TABLE_NAME = "contacts";
        public static final String _ID = BaseColumns._ID;
        public static final String PHONE_NUMBER = "phone_number";
        public static final String EMAIL = "email";
        public static final String CURRENT_ADDRESS = "current_address";
        public static final String MORE_INFORMATION = "more_information";
    }
}
