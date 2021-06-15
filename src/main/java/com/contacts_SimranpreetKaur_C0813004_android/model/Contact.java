package com.contacts_SimranpreetKaur_C0813004_android.model;

public class Contact {

    int id;
    String Fname,Lname , Phone , Email, Address;

    

    public Contact(String fname, String lname, String phone, String email, String address) {

        Fname = fname;
        Lname = lname;
        Phone = phone;
        Email = email;
        Address = address;
    }



    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
