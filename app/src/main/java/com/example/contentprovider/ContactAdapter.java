package com.example.contentprovider;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    List<Contact> list;
    Context context;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Contact> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = list.get(position);
        if(contact == null) {
            return;
        }

        holder.contact_name.setText(contact.getContact_name());
        holder.contact_phone.setText(contact.getContact_phone());
        holder.contact_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("name", contact.getContact_name());
                intent.putExtra("phone", contact.getContact_phone());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView contact_phone, contact_name;
        LinearLayout contact_card;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_phone = itemView.findViewById(R.id.contact_phone);
            contact_name = itemView.findViewById(R.id.contact_name);
            contact_card = itemView.findViewById(R.id.contact_card);
        }
    }


}
