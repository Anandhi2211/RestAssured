package com.udemy.restassured.googlemapApiSerialization;

import com.udemy.restassured.googlemapApiSerialization.pojogooglemap.AddLocationDetails;
import com.udemy.restassured.googlemapApiSerialization.pojogooglemap.Location;
import com.udemy.restassured.googlemapApiSerialization.pojogooglemap.Place;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GoogleMapApiBuilders {
    private String place_id;
    RequestSpecification req;
    ResponseSpecification res;

    @BeforeClass
    public void setUp(){

        req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .addQueryParam("key","qaclick123")
                .build();

        res = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
    @Test
    public void testAddPlace(){
        AddLocationDetails ad = new AddLocationDetails();
        Location location = new Location();
        location.setLat("-38.383494");
        location.setLng("33.427362");
        ad.setAddress("29, side layout, cohen 09");
        ad.setAccuracy("50");
        ad.setLanguage("French-IN");
        ad.setName("Frontline house");
        ad.setWebsite("http://google.com");
        ad.setLocation(location);
        ad.setPhone_number("(+91) 983 893 3937");
        ad.setTypes("shoe park");
        ad.setTypes("shop");
//        RestAssured.baseURI="https://rahulshettyacademy.com";
//        String response = given().log().all()
//                .queryParam("key","qaclick123")
//                .body(ad)
//                .when().post("/maps/api/place/add/json")
//                .then().statusCode(200).extract().response().asString();
//        System.out.println(response);


        String responseNew =
                given().log().all()
                .spec(req).body(ad)
                .when().post("/maps/api/place/add/json")
                .then()
                .spec(res).extract().response().asString();
        System.out.println(responseNew);

        JsonPath js = new JsonPath(responseNew);
        place_id = js.getString("place_id");
    }

    @Test(dependsOnMethods = {"testAddPlace"})
    public void testGetPlace(){
        Place place = new Place();
        place.setPlace_id(place_id);
        System.out.println(place_id);

//        RestAssured.baseURI="https://rahulshettyacademy.com";
//        String response =
//                given().log().all()
//                        .queryParam("key","qaclick123")
//                        .queryParam("place_id",place.getPlace_id())
//                        .when().get(" /maps/api/place/get/json")
//                        .then().statusCode(200).extract().response().asString();
//        System.out.println(response);

        String response = given().log().all()
                .spec(req).queryParam("place_id",place.getPlace_id())
                .when().get("/maps/api/place/get/json")
                .then().spec(res).extract().response().asString();
                System.out.println(response);

    }

    @Test(dependsOnMethods = {"testGetPlace"})
    public void testDeletePlace(){
        Place place = new Place();
        place.setPlace_id(place_id);

//        RestAssured.baseURI="https://rahulshettyacademy.com";
//        String response =
//                given().log().all()
//                .queryParam("key","qaclick123")
//                        .body(place)
////                .body("{\n" +
////                        "    \"place_id\":\""+place_id+"\"\n" +
////                        "}")
//                .when().post("/maps/api/place/delete/json")
//                .then().statusCode(200).extract().response().asString();

        String response = given().log().all()
                .spec(req).body(place)
                .when().post("/maps/api/place/delete/json")
                .then()
                .spec(res).extract().response().asString();
        System.out.println(response);
    }


}
