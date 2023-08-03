package com.example.traveltripapplication.database.state;

import android.provider.BaseColumns;

public final class StateContract {
    public StateContract() {}

    public static final class StateRetry implements BaseColumns {
        public static final String TABLE_NAME = "state";
        public static final String _ID = BaseColumns._ID;
        public static final String STATE_NAME = "state_name";
    }
}
