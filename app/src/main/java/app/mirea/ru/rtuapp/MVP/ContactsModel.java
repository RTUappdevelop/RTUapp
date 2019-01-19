package app.mirea.ru.rtuapp.MVP;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.List;

import app.mirea.ru.rtuapp.models.Contact;

public class ContactsModel {

    static List<Contact> StoreContacts;

    public static List<Contact> getContactsIntoArrayList(List<Contact> list, Context context){

        Cursor cursor;
        String name;
        String phoneNumber;
        String id;
        ContentResolver cr = context.getContentResolver();

        StoreContacts = list;

        cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Contact contact = new Contact();
                id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Log.i("Names", name);
                contact.setName(name);

                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                {
                    try {
                        Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                null,
                                null);
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
        return StoreContacts;
    }
}
