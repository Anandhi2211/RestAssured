package com.udemy.restassured.basics;

import com.udemy.restassured.files.PayLoadFile;
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
        RestAssured.baseURI="http://216.10.245.166";
        String addBookResponse = given().log().all()
                .header("Content-Type","application/json")
                .body(PayLoadFile.getAddInfo(aisle,isbn))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
//        System.out.println("REst"+addBookResponse);
        JsonPath js = new JsonPath(addBookResponse);
        System.out.println("Msg:"+js.getString("Msg"));
        System.out.println("ID:"+js.getString("ID"));
//        String bookId = js.getString("ID");

        //get Book

    }

//    @Test(dataProvider = "BookDetails")
    public void getBook(){
//        String getResponse = given().log().all().queryParam("ID","111abc")
//                .when().get("Library/GetBook.php")
//                .then().assertThat().statusCode(200)
//                .extract().response().asString();
//        System.out.println("Result"+getResponse);
    }

    @Test(dataProvider = "BookDetails")
    public void deleteBook(String isbn,String aisle){
        String deleteResponse = given().log().all()
                .header("Content-Type","application/json")
                .body(PayLoadFile.getDeleteInfo(isbn,aisle))
                .when().post("/Library/DeleteBook.php")
                .then().log().all().assertThat()
                .statusCode(200).extract().response().asString();

        JsonPath js = new JsonPath(deleteResponse);
        System.out.println("Msg:"+js.getString("msg"));
    }

}
