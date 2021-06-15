package com.contacts_SimranpreetKaur_C0813004_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.contacts_SimranpreetKaur_C0813004_android.room.Contact;
import com.contacts_SimranpreetKaur_C0813004_android.room.ContactRoomDb;
import com.contacts_SimranpreetKaur_C0813004_android.util.DatabaseHelper;
import com.s20.databasedemo_android.R;

import java.util.List;

public class searchContact  extends AppCompatActivity  {

    DatabaseHelper sqLiteDatabase;

    private ContactRoomDb contactRoomDb;
    List<Contact> contactList;
    EditText name ;
    TextView tvData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_contact);

        name = (EditText)findViewById(R.id.enter_name);
        }


    public void search(View view)
    {

        System.out.println("entered 1");
        System.out.println("entered 2");
         System.out.println("Found");




        }
    }











