package com.example.hijab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {
    private EditText emailfp;
    private EditText passfp;
    private Button resetfp;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        emailfp=(EditText)findViewById(R.id.emailfp);
        passfp=(EditText)findViewById(R.id.passfp);
        resetfp=(Button)findViewById(R.id.resetfp);
        firebaseAuth=FirebaseAuth.getInstance();

        resetfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=emailfp.getText().toString().trim();

                if(useremail.isEmpty()){
                    Toast.makeText(forgetpassword.this, "Please Enter your email", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(forgetpassword.this, "Password reset email send", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(forgetpassword.this,activity_sigin.class));
                            }
                            else{
                                Toast.makeText(forgetpassword.this, "Error in sending password reset email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });



    }
}
