package com.example.osapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.osapp.AddContact;
import com.example.osapp.Conversation;
import com.example.osapp.R;
import com.example.osapp.models.Contact;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView name;
        private final TextView last;
        private final TextView lastDate;

        private ContactViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            last = itemView.findViewById(R.id.last);
            lastDate = itemView.findViewById(R.id.lastDate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

    private final LayoutInflater mInFlater;
    private List<Contact> contacts;
    public RecycleViewClickListener listener;

    public ContactsListAdapter(Context context, RecycleViewClickListener listener) {
        this.mInFlater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInFlater.inflate(R.layout.contact_layout, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position){
        if(contacts != null){
            final Contact current = contacts.get(position);
            holder.name.setText(current.getName());
            holder.last.setText(current.getLast());
            holder.lastDate.setText(current.getLastdate());
        }
    }

    public void setContacts(List<Contact> c){
        contacts = c;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if (contacts != null){
            return contacts.size();
        } else {
            return 0;
        }
    }

    public List<Contact> getContacts() {return contacts;}

    public interface RecycleViewClickListener{
        void onClick(View v, int position);
    }
}
