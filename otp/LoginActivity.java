package com.example.mentalhealth.otp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentalhealth.MainActivityM;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import mentalhealth.R;

public class LoginActivity extends AppCompatActivity {

    private TextView loginPageQuestions;

    private EditText loginEmail, loginPassword;
    private TextView forgotPassword;
    private Button loginButton;
    private ProgressDialog loader;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_o);
        mAuth = FirebaseAuth.getInstance();

//        authStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
//                FirebaseUser user = mAuth.getCurrentUser();
//                if (user != null){
//                    Intent intent = new Intent(LoginActivity.this, MainActivityM.class);
//                    startActivity(intent);
//                    finish();
//                }
//
//            }
//        };

        loginPageQuestions = findViewById(R.id.loginPageQuestions);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        forgotPassword = findViewById(R.id.forgotPassword);
        loginButton = findViewById(R.id.loginButton);

        loader = new ProgressDialog(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = loginEmail.getText().toString().trim();
                final String password = loginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    loginEmail.setError("Email is required");
                }
                if (TextUtils.isEmpty(password)){
                    loginPassword.setError("Password is required");
                }

                else {
                    loader.setMessage("Log in in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivityM.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                            loader.dismiss();
                        }
                    });
                }
            }
        });

        loginPageQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                sendSMS();
                Intent intent = new Intent(LoginActivity.this, Select_regestrationActivity.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(authStateListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mAuth.removeAuthStateListener(authStateListener);
//    }

//    protected void sendSMS() {
//        Log.i("Send SMS", "");
//        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//
//        smsIntent.setData(Uri.parse("smsto:"));
//        smsIntent.setType("vnd.android-dir/mms-sms");
//        smsIntent.putExtra("address" , new String ("01234"));
//        smsIntent.putExtra("sms_body" , "Test ");
//
//        try {
//            startActivity(smsIntent);
//            finish();
//            Log.i("Finished sending SMS...", "");
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(LoginActivity.this,
//                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
//        }
//    }
}