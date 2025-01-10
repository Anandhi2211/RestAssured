package com.udemy.restassured.basics;

import com.udemy.restassured.files.library.PayLoadFile;
import io.restassured.path.json.JsonPath;

public class ReadJsonFile {
    public static void main(String[] args){

        JsonPath js = new JsonPath(PayLoadFile.coursePrice());
        System.out.println(js.getInt("courses.size()"));
        int size = js.getInt("courses.size()");
        System.out.println(js.getInt("dashboard.purchaseAmount"));
        System.out.println(js.getString("courses[0].title"));
        int price = js.getInt("dashboard.purchaseAmount");
        int sum = 0;
        for(int i = 0 ; i<size ; i++){
            System.out.println("Title: "+js.getString("courses["+i+"].title"));
            System.out.println("Price: "+js.getInt("courses["+i+"].price"));
            if(js.getString("courses["+i+"].title").equals("RPA")){
                System.out.println("Copies:"+js.getInt("courses["+i+"].copies"));
            }
            sum = sum + js.getInt("courses["+i+"].price");
            if(sum == price){
                System.out.println("Price Matches");
            }
        }
        System.out.println("Sum:"+sum);
    }


}
