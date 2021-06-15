package com.contacts_SimranpreetKaur_C0813004_android.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.contacts_SimranpreetKaur_C0813004_android.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact_database";

    private static final String TABLE_NAME = "contact";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FNAME = "Fname";
    private static final String COLUMN_LNAME = "Lname";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_PHONE = "Phone";
    private static final String COLUMN_ADDRESS = "Address";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FNAME + " VARCHAR(20) NOT NULL, " +
                COLUMN_LNAME + " VARCHAR(20) NOT NULL, " +
                COLUMN_EMAIL + " VARCHAR (20) NOT NULL, " +
                COLUMN_PHONE + " VARCHAR(20) NOT NULL ," +
                COLUMN_ADDRESS + " VARCHAR(20) NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table and then create it
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    // insert

    public boolean addContact(String Fname, String Lname, String Email , String Phone , String Address) {
        // we need a writeable instance of SQLite database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // we need to define a content values in order to insert data into our database
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FNAME, Fname);
        contentValues.put(COLUMN_LNAME, Lname);
        contentValues.put(COLUMN_EMAIL, Email);
        contentValues.put(COLUMN_PHONE, Phone);
        contentValues.put(COLUMN_ADDRESS, Address);

        // the insert method associated to SQLite database instance returns -1 if nothing is inserted
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) != -1;
    }

    /**
     * Query database - select all the employees
     * @return cursor
     * */
    public Cursor getAllEmployees() {
        // we need a readable instance of database
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    /**
     * Update contact in database

     * @return boolean value - true (successful)
     * */
    public boolean updateContact(int id, String Fname, String Lname,String Email , String Phone , String Address) {
        // we need a writeable instance of database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FNAME, Fname);
        contentValues.put(COLUMN_LNAME, Lname);
        contentValues.put(COLUMN_EMAIL , Email);
        contentValues.put(COLUMN_PHONE, Phone);
        contentValues.put(COLUMN_ADDRESS , Address);


        // update method associated to SQLite database instance returns number of rows affected
        return sqLiteDatabase.update(TABLE_NAME,
                contentValues,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }

    /**
     * Delete employee from database table
     * @param id
     * @return true if is successful
     * */
    public boolean deleteContact(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // the delete method associated to the SQLite database instance returns the number of rows affected
        return sqLiteDatabase.delete(TABLE_NAME,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }


   /* public boolean searchContact(String Fname){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        boolean duplicate = false;
        //SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String COUNT_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_FNAME +"=?" ;
        Cursor c = sqLiteDatabase.rawQuery(COUNT_QUERY,null);
        if(c.getCount() > 0){

            duplicate = true;
        }
        return duplicate;
    }*/

    public List<Contact> search(String keyword) {
        System.out.println("search entered");
        List<Contact> contacts = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where " + COLUMN_FNAME + " like ?", new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()) {
                System.out.println("exists");
                contacts = new ArrayList<Contact>();
                do {
                    Contact contact = new Contact(COLUMN_FNAME ,COLUMN_LNAME , COLUMN_PHONE , COLUMN_EMAIL , COLUMN_ADDRESS);
                    //contact.setId(cursor.getInt(0));
                    contact.setFname(cursor.getString(0));
                    contact.setLname(cursor.getString(1));
                    contact.setPhone(cursor.getString(2));
                    contact.setAddress(cursor.getString(3));
                    contact.setEmail(cursor.getString(4));

                    contacts.add(contact);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            contacts = null;
        }
        return contacts;
    }



    public int getContactsCount() {
        String COUNT_QUERY = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(COUNT_QUERY, null);
        return cursor.getCount();

    }

}











