package com.automation.pojos;
/*
"company": {
        * "companyId": 10662,
        * "companyName": "Cybertek",
        * "title": "string",
        * "startDate": "string",--> company is the same as contact ,so we need to create another pojo class "company"
        * "address": {
        * "addressId": 10662,
        * "street": " 100 S Clark str",
        * "city": "Chicago",
        * "state": "IL",
        * "zipCode": 60606
        * }
        * }
        */
 // this class represent company object.
public class Company {

    private int companyId;
    private String companyName;
    private String title;
    private String startDate;
    private Address address;
}
