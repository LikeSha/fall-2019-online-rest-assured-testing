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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", address=" + address +
                '}';
    }
}
