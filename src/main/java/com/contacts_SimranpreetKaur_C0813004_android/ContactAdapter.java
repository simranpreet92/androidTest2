package com.contacts_SimranpreetKaur_C0813004_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.contacts_SimranpreetKaur_C0813004_android.room.Contact;
import com.contacts_SimranpreetKaur_C0813004_android.room.ContactRoomDb;
import com.contacts_SimranpreetKaur_C0813004_android.util.DatabaseHelper;
import com.s20.databasedemo_android.R;

import java.util.Arrays;
import java.util.List;

public class ContactAdapter extends ArrayAdapter {

    private static final String TAG = "ContactAdapter";

    Context context;
    int layoutRes;
    List<Contact> contactList;

    DatabaseHelper sqLiteDatabase;
    ContactRoomDb contactRoomDb;

    public ContactAdapter(@NonNull Context context, int resource, List<Contact> contactList) {
        super(context, resource, contactList);
        this.contactList = contactList;
        this.context = context;
        this.layoutRes = resource;
        contactRoomDb = ContactRoomDb.getInstance(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = convertView;
        if (v == null) v = inflater.inflate(layoutRes, null);
        TextView FnameTV = v.findViewById(R.id.tv_Fname);
        TextView LnameTV = v.findViewById(R.id.tv_Lname);
        TextView EmailTV = v.findViewById(R.id.tv_Email);
        TextView PhoneTV = v.findViewById(R.id.tv_Phone);
        TextView AddressTV = v.findViewById(R.id.tv_Address);

        final Contact contact = contactList.get(position);
        FnameTV.setText(contact.getFname());
        LnameTV.setText(contact.getLname());
        EmailTV.setText(contact.getEmail());
        PhoneTV.setText(contact.getPhone());
        AddressTV.setText(contact.getAddress());

        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                callContact(contact.getPhone());
                return false;
            }
            private void callContact(String Phone) {
               Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", Phone, null));
                context.startActivity(intent);

            }});

                v.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact(contact);
            }

            private void updateContact(final Contact contact) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View view = layoutInflater.inflate(R.layout.dialog_update_contact, null);
                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                final EditText etFname = view.findViewById(R.id.et_Fname);
                final EditText etLname = view.findViewById(R.id.et_Lname);
                final EditText etEmail = view.findViewById(R.id.et_Email);
                final EditText etPhone = view.findViewById(R.id.et_Phone);
                final EditText etAddress = view.findViewById(R.id.et_Address);


                String[] deptArray = context.getResources().getStringArray(R.array.contacts);
                etFname.setText(contact.getFname());
                etLname.setText(contact.getLname());
                etEmail.setText(contact.getEmail());
                etPhone.setText(contact.getPhone());
                etAddress.setText(contact.getAddress());

                view.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Fname = etFname.getText().toString().trim();
                        String Lname = etLname.getText().toString().trim();
                        String Email = etEmail.getText().toString().trim();
                        String Phone = etPhone.getText().toString().trim();
                        String Address = etAddress.getText().toString().trim();


                        if (Fname.isEmpty()) {
                            etFname.setError(" first name field cannot be empty");
                            etFname.requestFocus();
                            return;
                        }

                        if (Lname.isEmpty()) {
                            etLname.setError("last name field cannot be empty");
                            etLname.requestFocus();
                            return;
                        }

                        if (Email.isEmpty()) {
                            etEmail.setError("email cannot be empty");
                            etEmail.requestFocus();
                            return;
                        }

                        if (Phone.isEmpty()) {
                            etPhone.setError("Phone cannot be empty");
                            etPhone.requestFocus();
                            return;
                        }

                        if (Address.isEmpty()) {
                            etAddress.setError("address cannot be empty");
                            etAddress.requestFocus();
                            return;
                        }

                        // Room
                        contactRoomDb.contactDao().updateContact(contact.getId(),
                                Fname , Lname , Email , Phone ,Address);
                        loadContacts();
                        alertDialog.dismiss();
                    }
                });
            }
        });

        v.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact(contact);
            }

            private void deleteContact(final Contact contact) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Room

                        contactRoomDb.contactDao().deleteContact(contact.getId());
                      int a = contactRoomDb.contactDao().getAllContacts().size() - 1;


                        loadContacts();


                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "The contact (" + contact.getFname() + ") is not deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        Log.d(TAG, "getView: " + getCount());
        return v;


    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    private void loadContacts() {


        contactList = contactRoomDb.contactDao().getAllContacts();
        notifyDataSetChanged();
    }
}










