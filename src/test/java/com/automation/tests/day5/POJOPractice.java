package com.automation.tests.day5;

import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class POJOPractice {

    @BeforeAll
    public static void beforeAll(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
    }

    @Test
    public void getUser(){
        Response response =   given().
                auth().
                basic("admin", "admin").
                when().
                get("/spartans/{id}", 393).prettyPeek();
        //Get the body and map it to a Java object.
        // For JSON responses this requires that you have either Jackson or Gson
        //this is a deserialization

        Spartan spartan = response.as(Spartan.class);
        System.out.println(spartan);

        //deserialization : POJO <-JSON
        //serialization:    POJO->JSON
        //both operations are done with a help of GSon
        //RestAssured automatically calls GSon for these operations

        Map<String ,? > spartanAsMap = response.as(Map.class);
        System.out.println(spartanAsMap);

    }

}
