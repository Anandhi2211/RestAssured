package com.udemy.restassured.QAuthClientCredentialsDeSerialization;

import com.udemy.restassured.QAuthClientPOJO.CourseDetails;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class QAuthClientCredentialsDeSerialization {


    @Test
    public void testClientCredentialsQAuth(){

        String[] titles ={"Selenium Webdriver Java","Cypress","Protractor"};
//        RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
        String response = given().log().all()
                .formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope","trust")
                .when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
                .asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String token = js.getString("access_token");
        System.out.println("ACCESS TOKEN:"+js.getString("access_token"));

        String courseDetails = given().log().all().queryParam("access_token",token)
                .when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .asString();
        System.out.println(courseDetails);

        CourseDetails courseDetailsByPojo = given().log().all()
                .queryParam("access_token",token)
                .expect().defaultParser(Parser.JSON)
                .when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .as(CourseDetails.class);


        courseDetailsByPojo.getCourses().getWebAutomation()
                .stream()
                .forEach(s-> System.out.println("Web Automation Title:"+s.getCourseTitle()));

        List<String> actualResult = courseDetailsByPojo.getCourses().getWebAutomation()
                .stream().map(s->s.getCourseTitle())
                .collect(Collectors.toList());
        List<String> expectedResult = Arrays.stream(titles).collect(Collectors.toList());

        Assert.assertEquals(actualResult,expectedResult,"Verified");
    }
}
