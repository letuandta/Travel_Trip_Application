package com.example.traveltripapplication.database.tour_ticket;

import android.provider.BaseColumns;

import com.example.traveltripapplication.model.TourTicketModel;

import java.util.Arrays;
import java.util.List;

public class TourTicketContracts {
    public TourTicketContracts() {
    }

    public static final class TourTicketEntry{
        public static final String TABLE_NAME = "tour_ticket";
        public static final String _ID = BaseColumns._ID;
        public static final String TOUR_ID = "tour_id";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String TOUR_DATE = "tour_date";
        public static final String REMAINING = "remaining";
        public static final String AMOUNT = "amount";
        public static final String ACTIVE = "active";
        public static final String PRICE = "price";
    }

    public static final class TourTicketDataTemplates{
        public static List<TourTicketModel> getValues(){
            return Arrays.asList(
                    new TourTicketModel(
                            1, 1, "Tour Đà Nẵng|50 vé – TP. Hồ chí Minh| Vietjet Air",
                            "Một tour du lịch tham quan Thành phố Đà Nẵng 5 ngày 4 đêm",
                            "22/8/2023", 50, 50, 1, "17000000"
                    ),
                    new TourTicketModel(
                            2, 1, "Tour Đà Nẵng|50 vé – Hà Nội| VietNam Airlines",
                            "Một tour du lịch tham quan Thành phố Đà Nẵng 5 ngày 4 đêm",
                            "25/8/2023", 50, 50, 1, "12000000"
                    ),
                    new TourTicketModel(
                            2, 1, "Tour Đà Nẵng|50 vé – Cà Mau| Bamboo Airways",
                            "Một tour du lịch tham quan Thành phố Đà Nẵng 5 ngày 4 đêm",
                            "29/8/2023", 50, 50, 1, "10000000"
                    )
            );
        }
    }

}
