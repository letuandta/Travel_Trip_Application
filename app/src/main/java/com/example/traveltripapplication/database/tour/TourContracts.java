package com.example.traveltripapplication.database.tour;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.example.traveltripapplication.model.TourModel;

import java.util.Arrays;
import java.util.List;

public class TourContracts {
    public TourContracts() {
    }

    public static final class TourEntry{
        public static final String TABLE_NAME = "tour";
        public static final String _ID = BaseColumns._ID;
        public static final String TOUR_CODE = "tour_code";
        public static final String TITLE = "title";
        public static final String DURATION = "duration";
        public static final String LOCATION = "location";
        public static final String ACTIVE = "active";
        public static final String THUMBNAIL = "thumbnail";
        public static final String EXPERIENCE = "experience";
        public static final String MORE_INFORMATION = "more_information";
    }

    public static final class TourDataTemplates{
        public static List<TourModel> getValues(){
            List<TourModel> tours = Arrays.asList(
                    new TourModel(1, "DN001", "Tour tham quan Thành phố Đà Nẵng",
                            5, "Đà Nẵng", 1,
                            "https://i.pinimg.com/564x/ef/69/c5/ef69c56e319d6db337527303f3501ee1.jpg",
                            "Tận hưởng khung cảnh tuyệt vời của đỉnh Bà Nà khi đứng trên cầu Vàng\n Ngắm nhìn những  cảnh đẹp ở núi Chúa từ buồn cáp treo\nGhé thăm làng Pháp với những kiến trúc Pháp tinh tế\n vui chơi ở công viên Fantasy Park",
                            "Liên hệ hotline: 0364971541 để biết thêm chi tiết."),
                    new TourModel(2, "NT001", "Tour nghĩ dưỡng Nha Trang",
                            3,"Nha Trang, Khánh Hòa", 0,
                            "https://i.pinimg.com/564x/8f/79/6e/8f796ee0447b98316ea7c71ed29fc52a.jpg",
                            "",
                            ""),
                    new TourModel(3, "HUE001", "Tour lịch sử kinh thành Huế",
                            2, "Thừa Thiên Huế", 0,
                            "https://i.pinimg.com/564x/d7/3a/7f/d73a7f4225944538b3a7868c7bdc1b81.jpg",
                            "",""),
                    new TourModel(4, "BK001", "Tour tham quan Bangkok",
                            6, "Bangkok, Thái Lan", 0,
                            "https://i.pinimg.com/564x/79/8a/bd/798abde3577cbfb51a7caec35be264b1.jpg","",""),
                    new TourModel(5, "PQ001", "Tour nghĩ dưỡng Phú Quốc",
                            4, "Phú Quốc, Kiên Giang", 0,
                            "https://i.pinimg.com/564x/34/0f/c7/340fc799d2c8a9421e65bcf114fb3994.jpg","","")
            );
            return tours;
        }
    }
}
