package com.automation.tests.day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanTests {

    String BASE_URL = "http://3.90.112.152:8000";
    //URI (Uniform Resource Identifier)= URL + URN =http://www.google.com/index.html
    //URL (Uniform Resource Locator) = http://www.google.com
    //URN (Uniform Resource Name)    =  /index.html

    @Test
    @DisplayName("Get list of all spartans")
    public void getAllSpartans(){
       given().baseUri(BASE_URL).when().get("/api/spartans").then().statusCode(200);
    }
}
