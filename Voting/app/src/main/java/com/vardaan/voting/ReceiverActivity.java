package com.vardaan.voting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiverActivity extends AppCompatActivity {

    char selectedOption;
    int countA, countB, countC, countD;

    TextView optionA;
    TextView optionB;
    TextView optionC;
    TextView optionD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        optionA = findViewById(R.id.textView);
        optionB = findViewById(R.id.textView2);
        optionC = findViewById(R.id.textView3);
        optionD = findViewById(R.id.textView4);

        updateViews();
    }

    private void updateViews() {

        if (selectedOption == 'A'){

            countA++;
            String update = "Option A : " + countA;
            optionA.setText(update);

        }

        if (selectedOption == 'B'){

            countB++;
            String update = "Option C : " + countA;
            optionA.setText(update);

        }

        if (selectedOption == 'C'){

            countA++;
            String update = "Option C : " + countA;
            optionA.setText(update);

        }

        if (selectedOption == 'D'){

            countA++;
            String update = "Option D : " + countA;
            optionA.setText(update);

        }
    }
}
