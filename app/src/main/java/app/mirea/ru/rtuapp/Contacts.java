package app.mirea.ru.rtuapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.mirea.ru.rtuapp.models.Contact;

public class Contacts extends AppCompatActivity {

    List<Contact> StoreContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        getContactsIntoArrayList();
    }

    public void getContactsIntoArrayList(){

        Cursor cursor;
        Contact contact = new Contact();
        String name = "";
        String phoneNumber = "";
        String id;
        ContentResolver cr = getContentResolver();

            cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                    null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Log.i("Names", name);
                    contact.setName(name);

                    if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                    {
                        try {
                            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                            phones.moveToFirst();
                            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            Log.i("Number", phoneNumber);
                            contact.setPhoneNumber(phoneNumber);
                            phones.close();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                    StoreContacts.add(contact);
                }
        }
    }

}
