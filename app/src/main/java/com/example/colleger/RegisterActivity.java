package com.example.colleger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    SharedPreferences sp ;
    String name , emailAddress ;
    private ImageButton backBtn ;
    private Button registerBtn;
    private Button alreadyBtn;
    private EditText userFirstName,userLastName,userMail,userPassword,userPassword2 ;
    private ProgressBar progressBar ;
    static public FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        backBtn=findViewById(R.id.backBtn);
        alreadyBtn=findViewById(R.id.alreadyBtn);
        userFirstName = findViewById(R.id.userFirstName);
        userLastName = findViewById(R.id.userLastName);
        sp = getSharedPreferences("userData", Context.MODE_PRIVATE);
        userMail = findViewById(R.id.userMail);
        userPassword = findViewById(R.id.userPassword);
        userPassword2 = findViewById(R.id.userPassword2);
        registerBtn = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = userFirstName.getText().toString().trim();
                emailAddress = userMail.getText().toString().trim();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name",name);
                editor.putString("email",emailAddress);
                editor.commit();
                String email = userMail.getText().toString().trim();
                String firstName = userFirstName.getText().toString().trim();
                String lastName = userLastName.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                String password2 = userPassword2.getText().toString().trim();

                if(TextUtils.isEmpty(firstName)){
                    userFirstName.setError("First Name is Required!");
                }
                if(TextUtils.isEmpty(lastName)){
                    userLastName.setError("Last Name is Required");
                }
                if(TextUtils.isEmpty(email)){
                    userMail.setError("Email is Required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    userPassword.setError("Password is Required!");
                    return;
                }
                if(TextUtils.isEmpty(password2)){
                    userPassword2.setError("Re-Enter password is required");
                }

                if(password.length() < 8){
                    userPassword.setError("Password must be a minimum of 8 characters!!");
                    return;
                }
                if(!password2.equals(password) ){
                    userPassword2.setError("Passwords do not match!!");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"User Registered Successfully!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"User Not Registered!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, StartActivity.class));
            }
        });
        alreadyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}