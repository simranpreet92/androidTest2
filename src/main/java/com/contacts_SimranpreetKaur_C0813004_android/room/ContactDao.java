package com.contacts_SimranpreetKaur_C0813004_android.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insertContact(Contact contact);

    @Query("DELETE FROM Contact")
    void deleteAllContacts();


    @Query("DELETE FROM Contact WHERE id = :id" )
    int deleteContact(int id);

    @Query("UPDATE Contact SET Fname = :Fname, Lname = :Lname, Email = :Email , Phone =:Phone , Address = :Address WHERE id = :id")
    int updateContact(int id, String Fname, String Lname, String Email , String Phone , String Address);

    @Query("SELECT * FROM Contact ORDER BY Fname")
    List<Contact> getAllContacts();

    @Query("SELECT * FROM Contact WHERE Fname = :Fname ")
    int search(String Fname);

}