package com.example.aman.a11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Feedback extends AppCompatActivity {

    EditText Subject, Feed;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        button = (Button) findViewById(R.id.button_feedback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Feedback();
            }
        });
    }



    public void Feedback(){
        Subject = (EditText) findViewById(R.id.subject);
        String subject = Subject.getText().toString();
        Feed = (EditText) findViewById(R.id.feed);
        String feed = Feed.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL , new String[] { "Aman031092@gmail.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "" + subject);
        intent.putExtra(Intent.EXTRA_TEXT, ""+feed);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
