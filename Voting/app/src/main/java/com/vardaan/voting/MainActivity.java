package com.vardaan.voting;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button voterButton;
    Button receiverButton;
    Uri mCreateDataUri = null;
    String mCreateDataType = null;
    String mCreateDataExtraText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voterButton = findViewById(R.id.voterButton);
        receiverButton = findViewById(R.id.receiverButton);

        final Intent intent = getIntent();
        final String action = intent.getAction();
        if (Intent.ACTION_SEND.equals(action))
        {

            mCreateDataUri = intent.getData();
            mCreateDataType = intent.getType();

            if( mCreateDataUri == null )
            {
                mCreateDataUri = intent.getParcelableExtra( Intent.EXTRA_STREAM );


            }

            mCreateDataExtraText = intent.getStringExtra( Intent.EXTRA_TEXT );

            if( mCreateDataUri == null )
                mCreateDataType = null;

            // The new entry was created, so assume all will end well and
            // set the result to be returned.
            setResult(RESULT_OK, (new Intent()).setAction(null));
        }

        voterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, VoterActivity.class));
                finish();
            }
        });

        receiverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ReceiverActivity.class));
                finish();

            }
        });
    }
}
