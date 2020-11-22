package com.automation.pojos;

import com.google.gson.annotations.SerializedName;

/*
contact": {
        * "contactId": 10662,
        * "phone": "7738557985",
        * "emailAddress": "aaa@gmail.com",
        * "premanentAddress": "123 main str"
        * },

 */
public class Contact {

    private int contactId;
    private String phone;
    private String emailAddress;
    @SerializedName("premanentAddress")//bug
    private String permanentAddress;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", phone='" + phone + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                '}';
    }
}
