package com.udemy.restassured.endToEndTesting.pojo.payload;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderPayload {
    private List<Orders> orders;

    public List<Orders> getOrders()
    {
        if(this.orders !=null)
        {
            return this.orders;
        }
        return new ArrayList<>();
    }
    public void setOrders(Orders orders) {
        if(this.orders ==null){
            this.orders = new ArrayList<>();
        }
            this.orders.add(orders);
    }
}
