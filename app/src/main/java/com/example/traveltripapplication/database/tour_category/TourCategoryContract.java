package com.example.traveltripapplication.database.tour_category;

import android.provider.BaseColumns;

import java.util.Arrays;
import java.util.List;

public class TourCategoryContract {
    public TourCategoryContract() {
    }

    public static final class TourCategoryEntry{
        public static final String TABLE_NAME = "tour_category";
        public static final String _ID = BaseColumns._ID;
        public static final String TOUR_ID = "tour_id";
        public static final String CATEGORY_ID = "category_id";
    }

    public static final class TourCategoryDataTemplates{
        public static List<TourCategoryModel> getValues(){
            return Arrays.asList(
                    new TourCategoryModel(1,1),
                    new TourCategoryModel(1,3),
                    new TourCategoryModel(2, 1),
                    new TourCategoryModel(2,4),
                    new TourCategoryModel(3,1),
                    new TourCategoryModel(3,5),
                    new TourCategoryModel(4,2),
                    new TourCategoryModel(4,3),
                    new TourCategoryModel(5, 1),
                    new TourCategoryModel(5,4)
            );
        }
    }

    public static class TourCategoryModel{
        private long tour_id;
        private long category_id;

        public TourCategoryModel() {}

        public TourCategoryModel(long tour_id, long category_id) {
            this.tour_id = tour_id;
            this.category_id = category_id;
        }

        public long getTour_id() {
            return tour_id;
        }

        public void setTour_id(long tour_id) {
            this.tour_id = tour_id;
        }

        public long getCategory_id() {
            return category_id;
        }

        public void setCategory_id(long category_id) {
            this.category_id = category_id;
        }
    }
}
