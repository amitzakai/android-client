package com.example.osapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import com.example.osapp.Conversation;
import com.example.osapp.R;
import com.example.osapp.models.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    class MessageViewHolder extends RecyclerView.ViewHolder {

        private final TextView content;
        //private final TextView created;

        private MessageViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
          //  created = itemView.findViewById(R.id.time);
        }
    }

    private final LayoutInflater mInFlater;
    private List<Message> messages;

    public MessageAdapter(Context context) {mInFlater = LayoutInflater.from(context);}

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View itemView = mInFlater.inflate(R.layout.message_right, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position){
        if(messages != null){
            final Message current = messages.get(position);
            holder.content.setText(current.getContent());
            //holder.created.setText(current.getCreated());
        }
    }

    public void setMessages(List<Message> c){
        messages = c;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if (messages != null){
            return messages.size();
        } else {
            return 0;
        }
    }

    public List<Message> getMessages() {return messages;}

}

