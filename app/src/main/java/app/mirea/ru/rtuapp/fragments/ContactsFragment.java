package app.mirea.ru.rtuapp.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.mirea.ru.rtuapp.MVP.ContactsModel;
import app.mirea.ru.rtuapp.R;
import app.mirea.ru.rtuapp.adapters.ContactsAdapter;
import app.mirea.ru.rtuapp.models.Contact;


public class ContactsFragment extends Fragment {

    List<Contact> StoreContacts = new ArrayList<>();
    RecyclerView contacts;
    ContactsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        contacts = view.findViewById(R.id.contacts_list);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int permissionStatus = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS);

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            StoreContacts = ContactsModel.getContactsIntoArrayList(StoreContacts, getContext());
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        contacts.setLayoutManager(linearLayoutManager);

        adapter = new ContactsAdapter(getContext(), StoreContacts);

        contacts.setAdapter(adapter);

    }
}
