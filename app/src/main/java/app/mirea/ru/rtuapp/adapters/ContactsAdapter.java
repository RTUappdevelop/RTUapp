package app.mirea.ru.rtuapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import app.mirea.ru.rtuapp.R;
import app.mirea.ru.rtuapp.models.Contact;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Contact> contacts;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        this.contacts = contacts;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.contactName.setText(contact.getName());
        holder.contactPhoneNumber.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView contactName, contactPhoneNumber;
        ViewHolder(View view){
            super(view);
            contactName = view.findViewById(R.id.contactName);
            contactPhoneNumber = view.findViewById(R.id.contactPhoneNumber);
        }
    }
}
