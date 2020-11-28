package com.automation.tests.day8;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


public class BearerAuthentication {

    @BeforeAll
    public static void setup() {
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";
    }

    @Test
    public void loginTest() {
        Response response = given().
                queryParam("email", "teacherva5@gmail.com").
                queryParam("password", "maxpayne").
                when().
                get("/sign").prettyPeek();// run this code first ,  then in console we can see access token and
                                            //refreshToken generated on the bottom of console
                 // then we store this token into String variable then print as bellow to line of codes.

        String token = response.jsonPath().getString("accessToken");
        System.out.println("Token :: " + token); // token generated success on the very bottom of console
    }

    @Test
    @DisplayName("Negative test: attempt to retrieve list of rooms without authentication token")
    public void getRoomsTest() {
        //422 ok. because anyways we didn't get data
        //but, we supposed to get 401 status code
        get("/api/rooms").prettyPeek().then().statusCode(401);
        // whatever inside get() bracket, its called resource path.
    }

    @Test
    public void getRoomsTest2() {
        //1. Request: to get a token.
        Response response = given().
                queryParam("email", "teacherva5@gmail.com").
                queryParam("password", "maxpayne").
                when().
                get("/sign");
        response.then().log().ifError();

        String token = response.jsonPath().getString("accessToken");

        Response response2 = given().
                auth().oauth2(token).
                when().
                get("/api/rooms").prettyPeek();
    }

    @Test
    public void getAllTeamsTest(){
        Response response = given().
                                      header("Authorization","Bearer " + getToken()).
                            when().get("/api/teams").prettyPeek();
        // we are using method , and Bearer token is specified as header parameter in the request, the header name
        //is "Authorization" , in case of basic authentication, it is also authorization.but in here, the value of
        //authorization part consist of 2 things : type of authentication( Bearer) and value( getToken) .
        //if it is basic it would be basic, if it is Bearer token we specify Bearer token and value.
        //what is getToken()? getToken() it is a method that returns token as as String.

        //Bearer token should be provided as a header parameter in the request
        // name= Authorization, Value= Bearer + TOKEN

        response.then().statusCode(200);
    }

    public String getToken() {
        Response response = given().
                queryParam("email", "teacherva5@gmail.com").
                queryParam("password", "maxpayne").
                when().
                get("/sign");
        response.then().log().ifError();

        String token = response.jsonPath().getString("accessToken");
        System.out.println("Token :: " + token);
        return token;
    }

    public String getToken(String email, String password) {
        Response response = given().
                queryParam("email", email).
                queryParam("password", password).
                when().
                get("/sign");
        response.then().log().ifError();

        String token = response.jsonPath().getString("accessToken");
        System.out.println("Token :: " + token);
        return token;
    }
}