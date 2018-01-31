package com.example.android.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lenovo on 31-01-2018.
 */

public class RightMessageAdapter extends RecyclerView.Adapter<RightMessageAdapter.RightMessageHolder> {

    Context context;
    ArrayList<Message> messages = new ArrayList<>();

    public RightMessageAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public RightMessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        return new RightMessageHolder(li.inflate(R.layout.item_message_right,parent,false));
    }

    @Override
    public void onBindViewHolder(RightMessageHolder holder, int position) {
        holder.tv.setText(messages.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RightMessageHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public RightMessageHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvRight);

        }
    }
}
