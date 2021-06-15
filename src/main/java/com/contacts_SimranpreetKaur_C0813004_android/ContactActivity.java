package com.contacts_SimranpreetKaur_C0813004_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.contacts_SimranpreetKaur_C0813004_android.room.Contact;
import com.contacts_SimranpreetKaur_C0813004_android.room.ContactRoomDb;
import com.contacts_SimranpreetKaur_C0813004_android.util.DatabaseHelper;
import com.s20.databasedemo_android.R;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {


    // instance of DatabaseOpenHelper class
    DatabaseHelper sqLiteDatabase;

    // Room db instance
    private ContactRoomDb contactRoomDb;

    List<Contact> contactList;
    ListView contactListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        contactListView = findViewById(R.id.lv_contacts);
        contactList = new ArrayList<>();


        // Room
        contactRoomDb = ContactRoomDb.getInstance(this);
        loadContacts();
    }

    private void loadContacts() {

        contactList = contactRoomDb.contactDao().getAllContacts();


        ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.list_layout_contact, contactList);
        contactListView.setAdapter(contactAdapter);
    }
}








