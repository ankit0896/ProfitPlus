package com.example.profitplus.model;

public class UserModel {
    String id;
    String fName;
    String lName;
    String email;
    String mobile;
    String gender;
    String country;
    String token;
    Boolean loginStatus;
    public UserModel() {

    }
    public UserModel(String id, String fName, String lName, String email, String mobile, String gender, String token, Boolean status) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.token = token;
        this.loginStatus = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean status) {
        this.loginStatus = status;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", token='" + token + '\'' +
                ", status=" + loginStatus +
                '}';
    }
}
