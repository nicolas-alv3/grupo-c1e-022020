package com.desapp.grupoc1e022019.model.builder;

import com.desapp.grupoc1e022019.model.Order;
import com.desapp.grupoc1e022019.model.observer.TimeObservable;
import com.desapp.grupoc1e022019.model.orderState.OrderState;
import com.desapp.grupoc1e022019.model.orderState.PendingOrder;

import java.util.Observable;

public class OrderBuilder {
    private OrderState state = new PendingOrder();
    private Double stars = 0d;

    public static OrderBuilder anOrder(){
        return new OrderBuilder();
    }

    public Order build() {
        return new Order(state,stars);
    }

}
