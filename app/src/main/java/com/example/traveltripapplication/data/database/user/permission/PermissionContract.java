package com.example.traveltripapplication.data.database.user.permission;

import android.provider.BaseColumns;

public final class PermissionContract {
    public PermissionContract() {}

    protected static class PermissionEntry implements BaseColumns {
        public static final String TABLE_NAME = "permission";
        public static final String _ID = BaseColumns._ID;
        public static final String PERMISSION__NAME = "permission_name";
    }
}
