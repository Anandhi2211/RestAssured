package com.udemy.restassured.EndToEndTestingPOJO.response;

import java.util.ArrayList;
import java.util.List;

public class CreateOrder {
    private List<String> orders;
    private List<String> productOrderId;
    private String message;

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    public void setProductOrderId(List<String> productOrderId) {
        this.productOrderId = productOrderId;
    }
    //    @Override
//    public String toString() {
//        return "{" +
//                "orders=" + orders +
//                ", productOrderId=" + productOrderId +
//                ", message='" + message + '\'' +
//                '}';
//    }

    public List<String> getOrders() {
        if(this.orders==null){
            this.orders=new ArrayList<>();
        }
        return this.orders;
    }

    public List<String> getProductOrderId() {
        if(this.productOrderId==null){
            this.productOrderId = new ArrayList<>();
        }
        return this.productOrderId;
    }

    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}



//package com.udemy.restassured.endToEndTesting.pojo.response;
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//public class CreateOrder {
//    private List<String> orders;
//    private List<String> productOrderId;
//    private String message;
//
//    // Getters and setters
//    public List<String> getOrders() {
//        if (this.orders == null) {
//            this.orders = new ArrayList<>();
//        }
//        return this.orders;
//    }
//
//    public void setOrders(List<String> orders) {
//        this.orders = orders;
//    }
//
//    public void addOrder(String order) {
//        if (this.orders == null) {
//            this.orders = new ArrayList<>();
//        }
//        this.orders.add(order);
//    }
//
//    public List<String> getProductOrderId() {
//        if (this.productOrderId == null) {
//            this.productOrderId = new ArrayList<>();
//        }
//        return this.productOrderId;
//    }
//
//    public void setProductOrderId(List<String> productOrderId) {
//        this.productOrderId = productOrderId;
//    }
//
//    public void addProductOrderId(String productOrderId) {
//        if (this.productOrderId == null) {
//            this.productOrderId = new ArrayList<>();
//        }
//        this.productOrderId.add(productOrderId);
//    }
//
//    public String getMessage() {
//        return this.message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    @Override
//    public String toString() {
//        return "{" +
//                "orders=" + orders +
//                ", productOrderId=" + productOrderId +
//                ", message='" + message + '\'' +
//                '}';
//    }
//}
