package com.example.android.message;

import android.content.ContentUris;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lenovo on 30-01-2018.
 */

public class MessageAdapter extends RecyclerView.Adapter <MessageAdapter.MessageHolder>{

    Context context;
    TextView mMsg;
    ArrayList<Message> messages = new ArrayList<>();

    public MessageAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;

    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return new MessageHolder(li.inflate(R.layout.item_message,parent,false));
    }

    public void updateMessage (ArrayList<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        holder.tv.setText(messages.get(position).getMessage());
    }

    public void bindMessage(String msg){

    }
    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder{

        TextView tv;

        public MessageHolder(View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tvMessage);

        }
    }

}
