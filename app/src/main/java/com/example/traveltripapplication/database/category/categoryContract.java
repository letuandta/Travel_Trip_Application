package com.example.traveltripapplication.database.category;

import android.provider.BaseColumns;

public class categoryContract {
    public categoryContract() {
    }

    public static final class CategoryEntry{
        public static final String TABLE_NAME = "category";
        public static final String _ID = BaseColumns._ID;
        public static final String CATEGORY_CODE = "category_code";
        public static final String CATEGORY_NAME = "category_name";
    }
}
