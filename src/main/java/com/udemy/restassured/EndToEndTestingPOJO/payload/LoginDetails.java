package com.udemy.restassured.EndToEndTestingPOJO.payload;

public class LoginDetails {
   private String userEmail;
   private String userPassword;

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
