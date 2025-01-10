package com.udemy.restassured.endToEndTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.restassured.endToEndTesting.pojo.payload.CreateOrderPayload;
import com.udemy.restassured.endToEndTesting.pojo.payload.LoginDetails;
import com.udemy.restassured.endToEndTesting.pojo.payload.Orders;
import com.udemy.restassured.endToEndTesting.pojo.payload.ProductDetails;
import com.udemy.restassured.endToEndTesting.pojo.response.AddProductResponse;
import com.udemy.restassured.endToEndTesting.pojo.response.CreateOrder;
import com.udemy.restassured.endToEndTesting.pojo.response.LoginResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class EndToEndTesting {
    private RequestSpecification req;
    private ResponseSpecification res;
    private String token;
    private LoginResponse loginResponse = new LoginResponse();
    private AddProductResponse addProductResponse = new AddProductResponse();
    private CreateOrderPayload createOrderPayload =new CreateOrderPayload();


    @BeforeClass
    public void setup(){
        req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .build();

        res = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
    @Test
    public void testLogin(){
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setUserEmail("anandhi@gmail.com");
        loginDetails.setUserPassword("Password@123");
        System.out.println(loginDetails);
        loginResponse =
                given().log().all()
                .spec(req).body(loginDetails)
                .when().post("/api/ecom/auth/login")
                .then().spec(res)
                .extract().response().as(LoginResponse.class);
        System.out.println(loginResponse.getToken());
//        token = loginResponse.getToken();
    }

    @Test(dependsOnMethods = "testLogin")
    public void testCreateProduct(){
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductName("qwerty");
        productDetails.setProductAddedBy(loginResponse.getUserId());
        productDetails.setProductCategory("fashion");
        productDetails.setProductSubCategory("shirts");
        productDetails.setProductPrice("11500");
        productDetails.setProductDescription("Addias Originals");
        productDetails.setProductFor("women");
        addProductResponse =
//                String response =
                given().log().all()
                        .spec(req)
                        .contentType(ContentType.MULTIPART)
                        .header("Authorization",loginResponse.getToken())
                        .param("productName",productDetails.getProductName())
                        .param("productAddedBy",loginResponse.getUserId())
                        .param("productCategory",productDetails.getProductCategory())
                        .param("productSubCategory",productDetails.getProductSubCategory())
                        .param("productPrice",productDetails.getProductPrice())
                        .param("productDescription",productDetails.getProductDescription())
                        .param("productFor",productDetails.getProductFor())
                        .multiPart("productImage",new File("C:\\Users\\anand\\OneDrive\\Desktop\\picc.jpg"))
                        .when().post("api/ecom/product/add-product")
                        .then().log().all()
//                        .spec(res)
                        .extract().response()
                        .as(AddProductResponse.class);

//        JsonPath js = new JsonPath(response);
//        System.out.println(js);
//        productId = js.getString("productId");
//        System.out.println(addProductResponse.getProductId());
        System.out.println(addProductResponse.getProductId());
    }

    @Test(dependsOnMethods = "testCreateProduct")
    public void testCreateOrder(){
        Orders orders = new Orders();
        orders.setProductOrderedId(addProductResponse.getProductId());
        orders.setCountry("India");
        createOrderPayload.setOrders(orders);
        System.out.println(createOrderPayload.toString());
                CreateOrder createOrder
                        = given().log().all()
                .spec(req).header("Authorization",loginResponse.getToken())
                .body(createOrderPayload)
                .when().post("/api/ecom/order/create-order")
                .then().log().all().statusCode(201)
                .extract().response()
                .as(CreateOrder.class);
    }

    @Test(dependsOnMethods = "testCreateOrder")
    public void testDeleteProduct(){

        String response = given().log().all()
                .spec(req).header("Authorization",loginResponse.getToken())
                .pathParam("productId",addProductResponse.getProductId())
                .when()
                .delete("https://rahulshettyacademy.com/api/ecom/product/delete-product/{productId}")
                .then().spec(res)
                .log().all().extract().asString();
        System.out.println(response);
    }

}
