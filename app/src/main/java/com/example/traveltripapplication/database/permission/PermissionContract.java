package com.example.traveltripapplication.database.permission;

import android.provider.BaseColumns;

public final class PermissionContract {
    public PermissionContract() {}

    public static class PermissionEntry implements BaseColumns {
        public static final String TABLE_NAME = "permission";
        public static final String _ID = BaseColumns._ID;
        public static final String PERMISSION__NAME = "permission_name";
    }
}
