package com.example.traveltripapplication.database.tour.rating;

import android.provider.BaseColumns;

import com.example.traveltripapplication.model.RatingModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class RatingContracts {
    public RatingContracts() {
    }


    public static final class RatingEntry{
        public static final String TABLE_NAME = "rating";
        public static final String _ID = BaseColumns._ID;
        public static final String TOUR_ID = "tour_id";
        public static final String SCORES = "scores";
        public static final String USER_ID = "user_id";
        public static final String MESSAGE = "message";
        public static final String CREATED_DATE = "create_date";
    }

    public static final class RatingDataTemplates{
        static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        static LocalDateTime now = LocalDateTime.now();
        public static List<RatingModel> getValues(){
            return Arrays.asList(
                    new RatingModel(1,1,1,4.5,"Rất hấp dẫn mọi người nên đi", String.valueOf(dtf.format(now))),
                    new RatingModel(1,1,1,4,"Rất hấp dẫn mọi người nên đi 1", String.valueOf(dtf.format(now))),
                    new RatingModel(1,1,1,5,"Rất hấp dẫn mọi người nên đi 2", String.valueOf(dtf.format(now))),
                    new RatingModel(1,1,1,3,"Rất hấp dẫn mọi người nên đi 3", String.valueOf(dtf.format(now)))
            );
        }
    }
}
