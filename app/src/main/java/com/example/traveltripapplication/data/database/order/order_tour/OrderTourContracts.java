package com.example.traveltripapplication.data.database.order.order_tour;

import android.provider.BaseColumns;

public class OrderTourContracts {
    public OrderTourContracts() {
    }

    public final static class OrderEntry{
        public static final String TABLE_NAME = "order_tour";
        public static final String _ID = BaseColumns._ID;
        public static final String USER_ID = "user_id";
        public static final String TOUR_ID = "tour_id";
        public static final String ORDER_DATE = "order_date";
        public static final String ORDER_STATE_ID = "order_state_id";
        public static final String CUSTOMER_NAME = "customer_name";
        public static final String ADDRESS = "address";
        public static final String PHONE = "phone";
    }
}
