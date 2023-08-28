package com.example.traveltripapplication.data.repository;

import com.example.traveltripapplication.data.database.DatabaseHelper;
import com.example.traveltripapplication.model.OrderModel;
import com.example.traveltripapplication.model.TourTicketModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class OrderRepository {

    public static CompletableFuture<Long> createOrder(OrderModel orderModel){
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long id = DatabaseHelper.mOrderTourHelper().insert(orderModel);
            if(id >= 0){
                long id2 = DatabaseHelper.mOrderTicketHelper().insert(id, orderModel.getOrder_tickets());
                if(id2 < 0) id = -1;
            }
            return id;
        });
        return future;
    }

    public static CompletableFuture<ArrayList<OrderModel>> getOrderByUserId(long userId){
        CompletableFuture<ArrayList<OrderModel>> future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mOrderTourHelper().getOrderByUserId(userId);
        });

        return future;
    }

    public static CompletableFuture<ArrayList<TourTicketModel>> getTicketByOrderId(long orderId){
        CompletableFuture<ArrayList<TourTicketModel>> future = CompletableFuture.supplyAsync(() ->
                DatabaseHelper.mTourTicketHelper().getTicketByOrderID(orderId));

        return future;
    }

    public static CompletableFuture<Integer> cancelOrder(long orderId){
        return CompletableFuture.supplyAsync(() -> DatabaseHelper.mOrderTourHelper().changeState(orderId,2));
    }
}
