package com.example.hijab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class activity_register extends AppCompatActivity {


    private EditText edtFullName;
    private EditText edtMobile;
    private EditText edtEmail;
    private EditText edtPass;

    private Button btnRegister;
    private Button btnSignin;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth= getInstance();

        btnRegister=(Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(validate()){

                    String user_email=edtEmail.getText().toString().trim();
                    String user_password=edtPass.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(activity_register.this, "Registration Successful!!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(activity_register.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(activity_register.this, "Registration Failed!!!", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                    // uploading data to the firebase
                }
                else{
                    Toast.makeText(activity_register.this, "hello", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validate() {
        Boolean result = false;
        String namepattern= "[a-zA-Z]";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String name = edtFullName.getText().toString();
        String Mobile = edtMobile.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();

        if (name.isEmpty() || Mobile.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "None of the field must be Empty", Toast.LENGTH_SHORT).show();
            /*startActivity(new Intent(activity_register.this,activity_register.class));*/
            result=false;
        }

        else {
            if(name.length()>2 && name.matches(namepattern)){
                if(Mobile.length()==10){
                    if(email.matches(emailPattern)){
                        if(password.length()>7){
                            result=true;
                        }

                    }

                }
            }
        }


        return result;
    }
    private void setupUIViews(){

        edtFullName = (EditText) findViewById(R.id.edtFullName);
        edtMobile = (EditText) findViewById(R.id.edtMobile);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPass);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        FirebaseAuth firebaseauth;
        firebaseauth = getInstance();
    }


    public void signin(View view) {
        Intent i=new Intent(activity_register.this,activity_sigin.class);
        startActivity(i);
    }
}
/*
    public void HomeScreen(View view) {
        Intent i=new Intent(activity_register.this,MainActivity.class);
        startActivity(i);
    }*/


