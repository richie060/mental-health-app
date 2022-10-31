package com.example.mentalhealth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import mentalhealth.R;

public class EmailActivity extends AppCompatActivity {
    String edTo;
    EditText edSubject;
    EditText edMessage;
    Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        String receiver=getIntent().getStringExtra("email");

//        edTo = findViewById(R.id.edto);
        edSubject = findViewById(R.id.edsubject);
        edMessage = findViewById(R.id.edmessage);
        btSend =findViewById(R.id.btSend);

        edTo = receiver;

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("mailto:" + edTo.toString()));
                intent.putExtra(Intent.EXTRA_SUBJECT, edSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, edMessage.getText().toString());
                startActivity(intent);
            }
        });

    }
}