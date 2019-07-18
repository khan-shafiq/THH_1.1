package com.example.hijab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.IDNA;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_sigin extends AppCompatActivity {

    private EditText edtEmaill;
    private EditText edtPassl;
    private int counter=5;
    private TextView Info;

    private Button btnSubmit;
    private TextView new_user1;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);

        new_user1=(TextView)findViewById(R.id.new_user);
        edtEmaill=(EditText) findViewById(R.id.edtEmaill);
        edtPassl=(EditText) findViewById(R.id.edtPassl);
        btnSubmit=(Button) findViewById(R.id.btnSubmit);
        Info=(TextView)findViewById(R.id.Info);

        Info.setText("No of attemp reamining is 5");

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        progressDialog=new ProgressDialog(this);

        if(user!=null){
            finish();
            startActivity(new Intent(activity_sigin.this,MainActivity.class));
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email_id =edtEmaill.getText().toString();
               String password=edtPassl.getText().toString();
                validate(email_id,password);
            }
        });

        new_user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity_sigin.this,activity_register.class);
                startActivity(i);
            }
        });



    }
    private void validate(String username,String password){

        progressDialog.setMessage("Logging In");
        progressDialog.show();

        if(username.isEmpty() || password.isEmpty()){
            startActivity(new Intent(this,activity_sigin.class));
            Toast.makeText(this, "Fields cannot be Empty", Toast.LENGTH_LONG).show();
        }
        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(activity_sigin.this, "Login Successful!!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(activity_sigin.this,MainActivity.class));
                }
                else{
                    Toast.makeText(activity_sigin.this, "Login failed!!!", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("No of attemp remaining :"+ counter);
                    progressDialog.dismiss();
                    if(counter==0){
                        btnSubmit.setEnabled(false);
                    }
                }

            }
        });
    }

   /* public void HomeScreen(View view) {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }*/
}
