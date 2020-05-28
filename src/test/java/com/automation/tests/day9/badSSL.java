package com.automation.tests.day9;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class badSSL {

    @Test
    public void badSSLCertificateTest(){
        /*
        no valid certificate --no handshake, no secure connection
         */

        baseURI = "https://untrusted-root.badssl.com/";
        Response response = given().relaxedHTTPSValidation().get().prettyPeek();
        System.out.println(response.statusCode());


    }

}
