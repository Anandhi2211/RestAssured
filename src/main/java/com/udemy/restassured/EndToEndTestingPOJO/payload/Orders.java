package com.udemy.restassured.EndToEndTestingPOJO.payload;

public class Orders {
    private String country;
    private String productOrderedId;
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getProductOrderedId() {
        return this.productOrderedId;
    }

    public void setProductOrderedId(String productOrderedId) {
        this.productOrderedId = productOrderedId;
    }
}
