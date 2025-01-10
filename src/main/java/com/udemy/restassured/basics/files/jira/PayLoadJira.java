package com.udemy.restassured.basics.files.jira;

public class PayLoadJira {

    public static String getCreateIssueBody(){
        return "{\n" +
                "\n" +
                "\"fields\": {\n" +
                "\n" +
                "\"project\": {\n" +
                "\n" +
                "\"key\": \"SCRUM\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"issuetype\": {\n" +
                "\n" +
                "\"name\": \"Bug\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"summary\": \"Test With RestAssured\",\n" +
                "\n" +
                "\"description\": [\"value\",\"This is test desc\"]\n" +
                "\n" +
                "}\n" +
                "\n" +
                "}";
    }
}
