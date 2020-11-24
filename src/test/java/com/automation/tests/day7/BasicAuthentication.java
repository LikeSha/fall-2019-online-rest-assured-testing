package com.automation.tests.day7;

import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class BasicAuthentication {


    @Test
    public void spartanAuthentication(){
        //in the given part, we provide request specifications
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");

        given().
                auth().basic("user", "user").
                when().
                get("/spartans").prettyPeek().
                then().
                statusCode(200);// request is successful ,and status code is 200
    }

    @Test
    public void authorizationTest(){ // this test is adding new spartan test
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        Spartan spartan = new Spartan("Araz", "Male", 343242342343L);
        given().
                auth().basic("user", "user").
                body(spartan).
                contentType(ContentType.JSON).
                when().
                post("/spartans").prettyPeek().
                then().
                statusCode(403);
        /**
         * user - doesn't have rights to add, delete or edit users. Only read.
         * admin--has permission to add new users. so if you change this part :auth().basic("user", "user").
         * to admin : auth().basic("admin", "admin"). you can add spartan ,because its allowed .then your statusCode
         * also not 403 anymore .it would be 201
         * 403 - Forbidden access. You logged in, but you are trying to do something that you are not allowed.
         * Authentication problem - you didn't login
         * Authorization problem - you logged in but cannot do some actions.
         */
    }
    @Test
    public void authenticationTest(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        //if don't provide credentials, we must get 401 status code
        get("/spartans").prettyPeek().then().statusCode(401);
    }

    /*
      With Selenium WebDriver, we are testing User Interface (UI) of web applications, but never works servers.
      Web service, returns data as Json OR xml
      Restful services return as Json -- which protocol do we use to get data from the Restful server? HTTP !
      get, put, post , delete, patch.....

      We are communicating with web service according to HTTP protocol! If it is SOAP service, we use SOAP protocol.
      SOAP protocol based in HTTP. but Restful service ( Rest is an architech , its NOT A PROTOCOL!) ITS  a set of
      principles to develop web services.

      when we access data through UI or Restful service ,we all use HTTP protocol, what is the difference between them?
      ContentType !
      what is the contentType of UI ? HTML,
      what is the contentType of Restful ? Json or xml , most of time are Json.
     */

    @Test
     public void authenticationTest2(){
        baseURI = "http://practice.cybertekschool.com";

        given().
                auth().basic("admin","admin").
        when().
                get("/basic_auth").prettyPeek().
        then().
                statusCode(200).
                contentType(ContentType.HTML);
     }

}
