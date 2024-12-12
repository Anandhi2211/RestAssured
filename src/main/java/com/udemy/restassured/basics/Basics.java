package com.udemy.restassured.basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

    public static void main(String[] args){

        //POST METHOD
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String postResponse = given().log().all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"https://rahulshettyacademy.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}")
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();
        System.out.println("RESULT"+postResponse);
        JsonPath js = new JsonPath(postResponse);
        String placeID = js.getString("place_id");
        System.out.println("Place_ID:"+placeID);

        //UPDATE METHOD
        String putResponse = given().log().all()
                .queryParam("key","qaclick123")
                .body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\"70 NEWAnandhi walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .header("Content-Type","application/json")
                .when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        System.out.println("PutResponse:"+postResponse);

        //GET Method
        String updatedResponse = given().log().all().queryParam("key","qaclick123").
                queryParam("place_id",placeID)
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().response().asString();
        System.out.println("Updated Result"+updatedResponse);





    }






}
