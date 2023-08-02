package com.example.traveltripapplication.database.position;

import android.provider.BaseColumns;

public final class PositionContract {
    public PositionContract() {}

    public static class PositionEntry implements BaseColumns {
        public static final String TABLE_NAME = "position";
        public static final String _ID = BaseColumns._ID;
        public static final String POSITION_NAME = "position_name";
    }
}
