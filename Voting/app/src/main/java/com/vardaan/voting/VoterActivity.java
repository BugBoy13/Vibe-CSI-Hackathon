package com.vardaan.voting;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VoterActivity extends AppCompatActivity {

    Button optionA;
    Button optionB;
    Button optionC;
    Button optionD;

    String selectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter);

        optionA = findViewById(R.id.optionAButton);
        optionB = findViewById(R.id.optionBButton);
        optionC = findViewById(R.id.optionCButton);
        optionD = findViewById(R.id.optionDButton);

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                perform("0");

                /*AlertDialog alertDialog = new AlertDialog.Builder(VoterActivity.this).create();
                alertDialog.setTitle("Option A Selected");
                alertDialog.setMessage("You will be redirected on dismiss");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });
                alertDialog.show();*/
            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*AlertDialog alertDialog = new AlertDialog.Builder(VoterActivity.this).create();
                alertDialog.setTitle("Option B Selected");
                alertDialog.setMessage("You will be redirected on dismiss");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });
                alertDialog.show();*/
                perform("1");
            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*AlertDialog alertDialog = new AlertDialog.Builder(VoterActivity.this).create();
                alertDialog.setTitle("Option C Selected");
                alertDialog.setMessage("You will be redirected on dismiss");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });
                alertDialog.show();*/
                perform("2");
            }
        });

        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*AlertDialog alertDialog = new AlertDialog.Builder(VoterActivity.this).create();
                alertDialog.setTitle("Option D Selected");
                alertDialog.setMessage("You will be redirected on dismiss");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });
                alertDialog.show();*/
                perform("3");

            }
        });
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

}
