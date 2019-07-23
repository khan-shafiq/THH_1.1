package com.example.hijab;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class activity_register extends AppCompatActivity {


    private EditText edtFullName;
    private EditText edtMobile;
    private EditText edtEmail;
    private EditText edtPass;

    private Button btnRegister;
    private ProgressDialog progressDialog;
    //private Button btnSignin;
    String name,Mobile,email,password;


    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setupUIViews();

        firebaseAuth= FirebaseAuth.getInstance();

        edtFullName=(EditText)findViewById(R.id.edtFullName);
        edtMobile=(EditText)findViewById(R.id.edtMobile);
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtPass=(EditText)findViewById(R.id.edtPass);
        progressDialog=new ProgressDialog(this);

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
                                sendEmailverification();
                                /*sendUserData();
                                Toast.makeText(activity_register.this, "Registration Successfull,Upload completed!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(activity_register.this,activity_sigin.class));*/
                            }
                            else{
                                Toast.makeText(activity_register.this, "Registration Failed!!!", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                    // uploading data to the firebase
                }
                else{
                    Toast.makeText(activity_register.this, "Please Enter the data properly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validate() {

        progressDialog.setMessage("Registration in process");
        progressDialog.show();

        Boolean result = false;
        String namepattern= "[a-zA-Z]";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

         name = edtFullName.getText().toString();
         Mobile = edtMobile.getText().toString();
         email = edtEmail.getText().toString();
         password = edtPass.getText().toString();

        if (name.isEmpty() || Mobile.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "None of the field must be Empty", Toast.LENGTH_SHORT).show();
            /*startActivity(new Intent(activity_register.this,activity_register.class));*/

        }

        else {
            result=true;
            /*if(name.length()>2 && name.matches(namepattern)){
                if(Mobile.length()==10){
                    if(email.matches(emailPattern)){
                        if(password.length()>7){
                            result=true;
                        }

                    }

                }
            }*/
        }


        return result;
    }



    private void sendEmailverification() {
        progressDialog.dismiss();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(activity_register.this, "Registration Successfull,Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(activity_register.this,activity_sigin.class));
                    }
                    else {
                        Toast.makeText(activity_register.this, "Verification mail has'nt been send", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData(){

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myref=firebaseDatabase.getReference("users");
        Userprof userprofile =new Userprof(name,Mobile,email);
        myref.setValue(userprofile);
    }


    private void setupUIViews(){

        edtFullName = (EditText) findViewById(R.id.edtFullName);
        edtMobile = (EditText) findViewById(R.id.edtMobile);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPass);
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


