package com.example.mentalhealth.otp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import mentalhealth.R;

public class Patient_Registration_Activity extends AppCompatActivity {

    private TextView regpageQuestion;
    private TextInputEditText registrationFullname,registrationPhoneNumber,
            loginEmail, loginPassord;

    private Button regButton;
    private CircleImageView profileImage;
    private Uri resultUri;

    private Spinner time_spinner,proffecional_spinner,department_spinner;

    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;
    private ProgressDialog loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);


        regpageQuestion = findViewById(R.id.regpageQuestion);

        regpageQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Patient_Registration_Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registrationFullname = findViewById(R.id.Regfullname);;
        registrationPhoneNumber = findViewById(R.id.RegPhoneNo);
        loginPassord = findViewById(R.id.loginPassword);
        regButton = findViewById(R.id.RegButton);
//        profileImage = findViewById(R.id.profileImage);
        loginEmail= findViewById(R.id.loginEmail);
        proffecional_spinner = findViewById(R.id.specializationSpinner);


        loader = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();



//        profileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,1);
//            }
//        });



        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = loginEmail.getText().toString().trim();
                final String password = loginPassord.getText().toString().trim();
                final String fullName = registrationFullname.getText().toString().trim();
                final String PhoneNumber = registrationPhoneNumber.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    loginEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    loginPassord.setError("Password is required");
                    return;
                }
                if (TextUtils.isEmpty(fullName)){
                    registrationFullname.setError("Fullname  is required");
                    return;
                }

                if (TextUtils.isEmpty(PhoneNumber)){
                    registrationPhoneNumber.setError("PhoneNumber  is required");
                    return;
                }


               else {
                    loader.setMessage("Registration in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        String error = task.getException().toString();
                                        Toast.makeText(Patient_Registration_Activity.this, "Error Occured" + error, Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        String currentUserId = mAuth.getCurrentUser().getUid();
                                        userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                                                .child("users").child(currentUserId);
                                        HashMap userInfo = new HashMap();
                                        userInfo.put("name",fullName);
                                        userInfo.put("email",email);
                                        userInfo.put("phonenumber",PhoneNumber);
//                                        userInfo.put("profile",profile);
                                        userInfo.put("type","patient");
                                        userInfo.put("id",currentUserId);
                                        userDatabaseRef.updateChildren(userInfo)
                                                .addOnCompleteListener(new OnCompleteListener() {
                                                    @Override
                                                    public void onComplete(@NonNull Task task) {
                                                        if (task.isSuccessful()){
                                                            Toast.makeText(Patient_Registration_Activity.this, "Details Set Successfully", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(Patient_Registration_Activity.this, LoginActivity.class);
                                                                            startActivity(intent);
                                                                            finish();
                                                                            loader.dismiss();
                                                        }else{
                                                            Toast.makeText(Patient_Registration_Activity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                                                        }
                                                        finish();
                                                        loader.dismiss();
                                                    }
                                                });
                                    }
                                }
                            });
                }
            }
        });
    }
}