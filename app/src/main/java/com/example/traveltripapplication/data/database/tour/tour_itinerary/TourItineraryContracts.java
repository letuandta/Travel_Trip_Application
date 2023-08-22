package com.example.traveltripapplication.data.database.tour.tour_itinerary;

import android.provider.BaseColumns;

import com.example.traveltripapplication.model.TourItineraryModel;

import java.util.Arrays;
import java.util.List;

public class TourItineraryContracts {
    public TourItineraryContracts() {
    }

    public static final class TourItineraryEntry{
        public static final String TABLE_NAME = "tour_itinerary";
        public static final String _ID = BaseColumns._ID;
        public static final String TOUR_ID = "tour_id";
        public static final String DAY = "day";
        public static final String CONTENT = "content";
    }

    public static final class TourItineraryDataTemplates{

        public static  List<TourItineraryModel> getValues(){
            return Arrays.asList(
                    new TourItineraryModel(1, 1, 1,
                            "7:30 Đón khách ra sân bay\n9:00 Lên máy bay\n10:20 Đến Đà Nẵng\n12:00 lên xe đi tham quan"),
                    new TourItineraryModel(2, 1, 2, "7:30 Đón khách lên xe\n9:00 Đến nơi tham quan"),
                    new TourItineraryModel(3, 1, 3, "7:30 Đón khách lên xe\n9:00 Đến nơi tham quan"),
                    new TourItineraryModel(4, 1, 4, "7:30 Đón khách lên xe\n9:00 Đến nơi tham quan"),
                    new TourItineraryModel(5, 1, 5, "7:30 Đón khách lên xe\n9:00 Đến nơi tham quan")
            );
        }
    }
}
