package app.mirea.ru.rtuapp;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import app.mirea.ru.rtuapp.models.Contact;

public class Contacts extends AppCompatActivity {

    ArrayList<Contact> StoreContacts ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        getContactsIntoArrayList();
    }

    public void getContactsIntoArrayList(){

        Cursor cursor;
        Contact contact = new Contact();
        String name;
        String phoneNumber;


        cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            contact.setName(name);
            System.out.println("Name  " + name);
            System.out.println("__________________");


            try{
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contact.setPhoneNumber(phoneNumber);
            System.out.println("PhoneNumber  " + phoneNumber);}
            catch (Exception e){
                System.out.println(e);
            }

//            StoreContacts.add(contact);
        }

//        for (Contact test:StoreContacts) {
//            System.out.println("Name" + test.getName() + "PhoneNumber" + test.getPhoneNumber());
//        }

        cursor.close();

    }

}
