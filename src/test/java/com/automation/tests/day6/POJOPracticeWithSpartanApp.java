package com.automation.tests.day6;

import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class POJOPracticeWithSpartanApp {

    @BeforeAll
    public static void beforeAll() {
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        authentication = basic("admin", "admin");
    }

    @Test
    public void addSpartanTest() {
        /**
         * {
         *   "gender": "Male",
         *   "name": "Nursultan",
         *   "phone": "123112312312"
         * }
         */

        Map<String, String> spartan = new HashMap<>();
        spartan.put("gender", "Male");
        spartan.put("name", "Nursultan");
        spartan.put("phone", "123112312312");

        RequestSpecification requestSpecification = given().
                auth().basic("admin", "admin").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(spartan);

        Response response = given().
                auth().basic("admin", "admin").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(spartan).
                when().
                post("/spartans").prettyPeek();

        response.then().statusCode(201);
        response.then().body("success", is("A Spartan is Born!"));

        //deserialization how we store this jason response data as an object into java? below line of code answer this question.
        Spartan spartanResponse = response.jsonPath().getObject("data", Spartan.class);
        Map<String, Object> spartanResponseMap = response.jsonPath().getObject("data", Map.class);

        System.out.println(spartanResponse);
        System.out.println(spartanResponseMap);

        //spartanResponse is a Spartan
        System.out.println(spartanResponse instanceof Spartan);// must be true
    }

    @Test
    @DisplayName("Retrieve exiting user, update his name and verify that name was updated successfully.")
    public void updateSpartanTest(){
        int userToUpdate = 101;
        String name = "Nursultan";

        //HTTP PUT request to update exiting record, for example exiting spartan.
        //PUT - requires to provide ALL parameters in body

        Spartan spartan = new Spartan(name, "Male", 123112312312L);

        //get spartan from web service this code is using for in case spartan has lots of parameters.
        Spartan spartanToUpdate = given().
                auth().basic("admin", "admin").
                accept(ContentType.JSON).// accept(ContentType.JSON) = import  contentType(ContentType.JSON) = export
                when().
                get("/spartans/{id}", userToUpdate).as(Spartan.class);

        //update property that you need without affecting other properties
        System.out.println("Before update: "+spartanToUpdate);
        spartanToUpdate.setName(name);//change only name
        System.out.println("After update: "+spartanToUpdate);

        //request to update existing user with id 101  ,THIS BELOW LINENS OF CODES IS AFTER WE SET NAME (update the name)
        //we send it back to server the new info.
        Response response = given().
                auth().basic("admin", "admin").
                contentType(ContentType.JSON).// accept(ContentType.JSON) = import  contentType(ContentType.JSON) = export
                body(spartanToUpdate).
                when().
                put("/spartans/{id}", userToUpdate).prettyPeek(); // from this line of code ,we know it is
        //a PUT request , now its more clear the samilarity and difference of POST request and PUT request is :
        // in both request ,we provide the entire JSON body for request , BUT when POST request ,
        // we dont specify the user id because its gonna be a new user, new instance. like this :
        // when().
        //      post("/spartans").prettyPeek();
        // in case of PUT request ,we are updating existing instance, in this case ,We are gonna to update
        //user 101 , so our code is like this :
        //when().
        //     put("/spartans/{id}", userToUpdate).prettyPeek();

        //verify that status code is 204 after update , 204 code with no body no content.
        response.then().statusCode(204);
        System.out.println("##############################################");
        //to get user with id 101, the one that we've just updated
        given().
                auth().basic("admin", "admin").
                when().
                get("/spartans/{id}", userToUpdate).prettyPeek().
                then().
                statusCode(200).body("name", is(name));
        //verify that name is correct, after update

        /*
        200	     OK	              We received the request
        201	     Created	      We received the request and created something.
                                  For example, we received a file upload request and a file is created.
        204	     No content	      Incorrect locale used
        400	     Bad request	  Incorrect parameters (input data)
        401	     Unauthorized	  Authentication error: invalid API key, miscalculated dev hash, etc. More details.
        5xx	     Error	          Please report a bug to support@oneskyapp.com
         */

    }
}