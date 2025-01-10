package com.udemy.restassured.basics;

import com.udemy.restassured.files.library.PayLoadFile;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicsTest {
    @DataProvider(name = "BookDetails")
    public Object[][] data(){
        return new Object[][] {
                {"abc","111"},
                {"def","222"}
        };
    }

    @Test(dataProvider = "BookDetails")
    public void addBook(String aisle,String isbn){
        //Add BookDetail
        RestAssured.baseURI="https://rahulshettyacademy.com";
//
//
        String addBookResponse = given()
                .body(PayLoadFile
                        .getAddInfo(isbn,aisle)).when()
                .post("/Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();
        System.out.println("Response starts " + addBookResponse + " ends");
    }
    @Test(dataProvider = "BookDetails")
    public void deleteBook(String aisle,String isbn){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String deleteResponse =
                given().body(PayLoadFile.getDeleteInfo(isbn, aisle))
                        .when()
                        .post("/Library/DeleteBook.php")
                        .then()
                        .extract()
                        .response()
                        .asString();
        System.out.println("Response starts " + deleteResponse + " ends");
    }

}
