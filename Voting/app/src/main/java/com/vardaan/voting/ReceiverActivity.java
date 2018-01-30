package com.vardaan.voting;

import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class ReceiverActivity extends AppCompatActivity {

    char selectedOption;
    int countA=0, countB=0, countC=0, countD=0;
    MicrophoneListener microphoneListener = null;
    StreamDecoder sDecoder = null;
    ByteArrayOutputStream decodedStream = new ByteArrayOutputStream();
    Timer refreshTimer = null;

    int counter = 0,length;
    String temp;
    Uri mCreateDataUri = null;
    String mCreateDataType = null;
    String mCreateDataExtraText = null;

    Handler mHandler = new Handler();

    TextView optionA;
    TextView optionB;
    TextView optionC;
    TextView optionD;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        button = findViewById(R.id.button);


        if( microphoneListener == null )
        {

            listen();
            //setTextListen.setText("Stop listening");
        }
        else
        {
            //setTextListen = (TextView) findViewById(R.id.textListenCard);

            stopListening();
            //setTextListen.setText("Listen");
        }
        optionA = findViewById(R.id.textView);
        optionB = findViewById(R.id.textView2);
        optionC = findViewById(R.id.textView3);
        optionD = findViewById(R.id.textView4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                }, 500, 500);

    }

    private void updateViews() {

        if (selectedOption == 'A'){

            countA++;
            String update = "Option A : " + countA;
            optionA.setText(update);
            selectedOption = '0';

        }

        if (selectedOption == 'B'){

            countB++;
            String update = "Option B : " + countB;
            optionB.setText(update);
            selectedOption = '0';

        }

        if (selectedOption == 'C'){

            countC++;
            String update = "Option C : " + countC;
            optionC.setText(update);
            selectedOption = '0';
        }

        if (selectedOption == 'D'){

            countD++;
            String update = "Option D : " + countD;
            optionD.setText(update);
            selectedOption = '0';
        }
    }

    private void updateResults()
    {
        if( microphoneListener != null )
        {
            Log.v("Main", "output decoded msg is -- >>> "+ decodedStream.toString());
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
                decodedStream.reset();




            /*textListen.setText(decodedStream.toString());
            textStatus.setText(sDecoder.getStatusString());*/
        }
        else
        {
           // textStatus.setText("");
        }

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

}
