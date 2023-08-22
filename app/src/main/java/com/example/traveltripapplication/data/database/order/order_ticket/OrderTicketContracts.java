package com.example.traveltripapplication.data.database.order.order_ticket;

import android.provider.BaseColumns;

public class OrderTicketContracts {
    public OrderTicketContracts() {
    }

    public final static class OrderTicketEntry{
        public static final String TABLE_NAME = "order_ticket";
        public static final String _ID = BaseColumns._ID;
        public static final String ORDER_ID = "order_id";
        public static final String TOUR_TICKET_ID = "tour_ticket_id";
    }
}
