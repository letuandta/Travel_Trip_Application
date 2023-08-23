package com.example.traveltripapplication.data.database.order.order_state;

import android.provider.BaseColumns;

import java.util.Arrays;
import java.util.List;

public class OrderStateContracts {
    public OrderStateContracts() {
    }

    public static final class OrderStateEntry implements BaseColumns {
        public static final String TABLE_NAME = "order_state";
        public static final String _ID = BaseColumns._ID;
        public static final String STATE_NAME = "state_name";
    }

    public static class OrderStateDataTemplates{
        public static List<OrderStateModel> getValues(){
            return Arrays.asList(
                    new OrderStateModel(1,"active"),
                    new OrderStateModel(2, "cancel")
            );
        }
    }

    public static class OrderStateModel{
        private long _id;
        private String stateName;

        public OrderStateModel() {
        }

        public OrderStateModel(long _id, String stateName) {
            this._id = _id;
            this.stateName = stateName;
        }

        public long get_id() {
            return _id;
        }

        public void set_id(long _id) {
            this._id = _id;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }
    }
}
