package com.example.bookcarservicing.Model;

import java.util.Date;

public class Book {
    private String user_id;
    private String fullname;
    private String phonenumber;
    private String address;
    private String Carselection;
    private String locationselection;
    private Date dateselection;

    public Book(String user_id, String fullname, String phonenumber, String address, String Carselection, String locationselection, Date dateselection) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.address = address;
        this.Carselection = Carselection;
        this.locationselection = locationselection;
        this.dateselection = dateselection;

    }

    public String getUser_id() { return user_id; }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public  String getCarselection() {
        return Carselection;
    }

    public void setCarselection(String Carselection) {
        this.Carselection = Carselection;
    }

    public  String getLocationselection() {
        return locationselection;
    }

    public void setLocationselection(String locationselection) {this. locationselection = locationselection;}

    public Date getDateselection() {return  dateselection;}

    public void setdateselection (Date Dateselection) {this.dateselection = dateselection;}




    }











