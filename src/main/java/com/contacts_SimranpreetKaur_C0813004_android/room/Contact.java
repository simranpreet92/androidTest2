package com.contacts_SimranpreetKaur_C0813004_android.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

@Entity(tableName = "Contact")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    public int id;


    @NonNull
    @ColumnInfo(name = "Fname")
    public String Fname;

    @NonNull
    @ColumnInfo(name = "Lname")
    public String Lname;

    @NonNull
    @ColumnInfo(name = "Email")
    public String Email;

    @NonNull
    @ColumnInfo(name = "Phone")
    public String Phone;

    @NonNull
    @ColumnInfo(name = "Address")
    public String Address;

    public Contact(@NonNull String Fname, @NonNull String Lname, @NonNull String Email,  @NonNull String Phone,  @NonNull String Address) {
       this.Fname = Fname;
       this.Lname = Lname;
       this.Email = Email;
       this.Phone = Phone;
       this.Address = Address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getFname() {
        return Fname;
    }

    public void setFname(@NonNull String fname) {
        Fname = fname;
    }

    @NonNull
    public String getLname() {
        return Lname;
    }

    public void setLname(@NonNull String lname) {
        Lname = lname;
    }

    @NonNull
    public String getEmail() {
        return Email;
    }

    public void setEmail(@NonNull String email) {
        Email = email;
    }

    @NonNull
    public String getPhone() {
        return Phone;
    }

    public void setPhone(@NonNull String phone) {
        Phone = phone;
    }

    @NonNull
    public String getAddress() {
        return Address;
    }

    public void setAddress(@NonNull String address) {
        Address = address;
    }
}
