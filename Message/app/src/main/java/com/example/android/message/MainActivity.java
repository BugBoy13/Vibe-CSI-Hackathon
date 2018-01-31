package com.example.android.message;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText editText,editText2;
    MicrophoneListener microphoneListener = null;
    StreamDecoder sDecoder = null;
    ByteArrayOutputStream decodedStream = new ByteArrayOutputStream();
    Timer refreshTimer = null;

    int counter=0;

    Handler mHandler = new Handler();
    int flag =0;
    TextView mMsg ;

    String temp;
    RecyclerView rv;
    ArrayList<Message> messages = new ArrayList<>();
    MessageAdapter adapter;
    RightMessageAdapter Radapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.etTypeMessage);

        btn = findViewById(R.id.btnSend);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doIt();
                editText.setText("");
            }
        });

        btn = findViewById(R.id.btnSend2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( microphoneListener == null )
                {
                    //  setTextListen = (TextView) findViewById(R.id.textListenCard);
                    ((Button)view).setText("Stop listening");
                    listen();
                    //  setTextListen.setText("Stop listening");
                }
                else
                {
                    //setTextListen = (TextView) findViewById(R.id.textListenCard);

                    stopListening();
                    ((Button)view).setText("Listen");
                    //setTextListen.setText("Listen");
                }
                doIt2();
                editText.setText("");

            }
        });
    }

    @Override
    protected void onPause() {

        //if( l != null )
        //	l.stopLoop();

        super.onPause();

        if( refreshTimer != null )
        {
            refreshTimer.cancel();
            refreshTimer = null;
        }

        stopListening();
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume()
    {
        super.onResume();

        refreshTimer = new Timer();



        refreshTimer.schedule(
                new TimerTask()
                {
                    @Override
                    public void run()
                    {

                        mHandler.post(new Runnable() // have to do this on the UI thread
                        {
                            public void run()
                            {
                                updateResults();


                            }
                        });

                    }
                }, 500,3000);

    }


    public void doIt() {


        rv = findViewById(R.id.rv);

        editText = findViewById(R.id.etTypeMessage);
        messages.add(new Message(editText.getText().toString()));
        adapter= new MessageAdapter (this,messages) ;
        perform(editText.getText().toString());

        rv.setLayoutManager(new LinearLayoutManager(this));

        rv.setAdapter(adapter);


    }
    public void doIt2(){
        messages.add(new Message(temp));
        Radapter = new RightMessageAdapter(this, messages);
        rv= findViewById(R.id.rv);







        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(Radapter);
    }
    private void listen()
    {
        stopListening();

        decodedStream.reset();

        //the StreamDecoder uses the Decoder to decode samples put in its AudioBuffer
        // StreamDecoder starts a thread
        sDecoder = new StreamDecoder( decodedStream );

        //the MicrophoneListener feeds the microphone samples into the AudioBuffer
        // MicrophoneListener starts a thread

        microphoneListener = new MicrophoneListener(sDecoder.getAudioBuffer());
        System.out.println("Listening");
    }

    private void stopListening()
    {
        if( microphoneListener != null )
            microphoneListener.quit();

        microphoneListener = null;

        if( sDecoder != null )
            sDecoder.quit();

        sDecoder = null;
    }

    private void perform( String input )
    {

        try
        {

            //try to play the file
            System.out.println("Performing " + input);
            AudioUtils.performArray(input.getBytes());
        }

        catch (Exception e){
            System.out.println("Could not encode " + input + " because of " + e);
        }

    }
    private void updateResults()
    {
        if( microphoneListener != null )
        {

            Log.v("Main", "output decoded msg is -- >>> "+ decodedStream.toString());

            if(counter==0)
            {
                temp= decodedStream.toString();
                Log.v("Main", "1st decoded msg is -- >>> "+ temp);
                counter++;
            }
            else if (counter <=1) {
                temp += decodedStream.toString();
                Log.v("Main", "2nd decoded msg is -- >>> "+ temp);
                counter++;
            } else
            {
                stopListening();
            }




            /*adapter = new MessageAdapter(this, messages);
            rv.findViewById(R.id.rv);

            messages.add(new Message(decodedStream.toString()));

            //editText = findViewById(R.id.etTypeMessage);

            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);*/
            /*
            temp = decodedStream.toString();
            if(temp.toLowerCase().contains("0"))
                selectedOption = 'A';
            else if(temp.toLowerCase().contains("1"))
                selectedOption = 'B';
            else if(temp.toLowerCase().contains("2"))
                selectedOption = 'C';
            else if(temp.toLowerCase().contains("3")){
                selectedOption = 'D';
            }
            updateViews();
            decodedStream.reset();*/




            /*textListen.setText(decodedStream.toString());
            textStatus.setText(sDecoder.getStatusString());*/
        }
        else
        {
            // textStatus.setText("");
        }

    }
    public class MessageAdapter extends RecyclerView.Adapter <MessageAdapter.MessageHolder>{

        Context context;
        TextView mMsg;
        ArrayList<Message> messages = new ArrayList<>();

        public MessageAdapter(Context context, ArrayList<Message> messages) {
            this.context = context;
            this.messages = messages;

        }

        @Override
        public MessageAdapter.MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    public class RightMessageAdapter extends RecyclerView.Adapter<RightMessageAdapter.RightMessageHolder> {

        Context context;
        ArrayList<Message> messages = new ArrayList<>();

        public RightMessageAdapter(Context context, ArrayList<Message> messages) {
            this.context = context;
            this.messages = messages;
        }

        @Override
        public RightMessageAdapter.RightMessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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


}