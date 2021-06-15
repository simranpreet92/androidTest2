package com.contacts_SimranpreetKaur_C0813004_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.contacts_SimranpreetKaur_C0813004_android.room.Contact;
import com.contacts_SimranpreetKaur_C0813004_android.room.ContactDao;
import com.contacts_SimranpreetKaur_C0813004_android.room.ContactRoomDb;
import com.contacts_SimranpreetKaur_C0813004_android.util.DatabaseHelper;
import com.contacts_SimranpreetKaur_C0813004_android.searchContact;
import com.s20.databasedemo_android.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    DatabaseHelper sqLiteDatabase;

    private ContactRoomDb contactRoomDb;
    public int a =0;
    EditText etFname, etLname ,etEmail , etPhone , etAddress;
    TextView totalContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFname = findViewById(R.id.et_Fname);
        etLname = findViewById(R.id.et_Lname);
        etEmail = findViewById(R.id.et_Email);
        etPhone = findViewById(R.id.et_Phone);
        etAddress = findViewById(R.id.et_Address);



        findViewById(R.id.btn_add_contact).setOnClickListener(this);
        findViewById(R.id.tv_view_contacts).setOnClickListener(this);


        // Room db
        contactRoomDb = ContactRoomDb.getInstance(this);

        totalContacts = findViewById(R.id.tv_totalContacts);

        a = contactRoomDb.contactDao().getAllContacts().size();

        String ttl = String.valueOf(a);
        System.out.println("TOTAL CONTACTS ADDED: " +ttl);
        totalContacts.setText("TOTAL CONTACTS ADDED: "+ttl);
    }




    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_contact:
                addContact();
                break;
            case R.id.tv_view_contacts:
                startActivity(new Intent(this, ContactActivity.class));
                break;
                }

    }

    public void search(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, searchContact.class);
        myIntent.putExtra("Fname" , String.valueOf(etFname));
        startActivity(myIntent);


    }

    private void addContact() {
        String Fname = etFname.getText().toString().trim();
        String Lname = etLname.getText().toString().trim();
        String Email = etEmail.getText().toString().trim();
        String Phone = etPhone.getText().toString().trim();
        String Adddress = etAddress.getText().toString().trim();

        if (Fname.isEmpty()) {
            etFname.setError("first name field cannot be empty");
            etFname.requestFocus();
            return;
        }
        if (Lname.isEmpty()) {
            etLname.setError(" last name field cannot be empty");
            etLname.requestFocus();
            return;
        }
        if (Email.isEmpty()) {
            etEmail.setError("Email field cannot be empty");
            etEmail.requestFocus();
            return;
        }
        if (Phone.isEmpty()) {
            etPhone.setError("name field cannot be empty");
            etPhone.requestFocus();
            return;
        }
        if (Adddress.isEmpty()) {
            etAddress.setError("Address field cannot be empty");
            etAddress.requestFocus();
            return;
        }

ContentResolver cr = getBaseContext().getContentResolver(); //Activity/Application android.content.Context

         Contact contact = new Contact(Fname , Lname , Email , Phone ,Adddress);
        contactRoomDb.contactDao().insertContact(contact);
        Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
        a = a + 1;
        String ttl = String.valueOf(a);
        totalContacts.setText("TOTAL CONTACTS ADDED: "+ttl);
        clearFields();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        clearFields();
           }

    private void clearFields() {
        etFname.setText("");
        etLname.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etAddress.setText("");

        etFname.clearFocus();
        etLname.clearFocus();
        etPhone.clearFocus();
        etEmail.clearFocus();
        etAddress.clearFocus();
    }
}










