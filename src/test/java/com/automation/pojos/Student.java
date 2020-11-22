package com.automation.pojos;
/**
 * {
 * "studentId": 10662,
 * "firstName": "Vera",
 * "lastName": "Jakson",
 * "batch": 14,
 * "joinDate": "02/02/2020",
 * "birthDate": "02/02/1985",
 * "password": "123",
 * "subject": "string",
 * "gender": "F",
 * "admissionNo": "string",
 * "major": "abc",
 * "section": "12",
 * "contact": {
 * "contactId": 10662,  ----> since contact is not a single value ,so we have to create another pojo class "contact"
 * "phone": "7738557985",
 * "emailAddress": "aaa@gmail.com",
 * "premanentAddress": "123 main str"
 * },
 * "company": {
 * "companyId": 10662,
 * "companyName": "Cybertek",
 * "title": "string",
 * "startDate": "string",--> company is the same as contact ,so we need to create another pojo class "company"
 * "address": {
 * "addressId": 10662,
 * "street": " 100 S Clark str", ---> address is same as contact ,so we need to create another pojo class "address"
 * "city": "Chicago",
 * "state": "IL",
 * "zipCode": 60606
 * }
 * }
 * },
 */

public class Student {

    private int studentId;
    private String firstName;
    private String lstName;
    private int batch;
    private String joinDate;
    private String birthDate;
    private String password;
    private String subject;
    private String gender;
    private String admissionNo;
    private String major;
    private String section;
    private Contact contact;
    private Company company;
}
