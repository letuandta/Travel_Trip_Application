package com.example.traveltripapplication.database.user;

import android.provider.BaseColumns;

public final class UserContract {
    public UserContract() {}

    protected static final class UserEntry{
        public static final String TABLE_NAME = "user";
        public static final String _ID = BaseColumns._ID;
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
        public static final String AVATAR = "avatar";
        public static final String LAST_LOGIN = "last_login";
        public static final String CREATED_DATE = "created_date";
        public static final String FULL_NAME = "full_name";
        public static final String BIRTHDAY = "birthday";
        public static final String CONTACTS = "contacts_id";
        public static final String STATE = "state_id";
        public static final String IS_SUPER_USER = "is_super_user";
    }
}
