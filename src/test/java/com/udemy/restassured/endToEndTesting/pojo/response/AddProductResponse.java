package com.udemy.restassured.endToEndTesting.pojo.response;

public class AddProductResponse {
    private String productId;
    private String message;

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
