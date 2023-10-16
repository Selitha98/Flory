package com.example.flory;

public class UserData {

    private String username;

    private String userphone;
    private String useremail;
    private String userPassword;
    private String role;

    private String key;


    public UserData() {

    }

    public UserData(String username, String userphone, String useremail, String userPassword, String role) {
        this.username = username;
        this.userphone = userphone;
        this.useremail = useremail;
        this.userPassword = userPassword;
        this.role = role;
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
