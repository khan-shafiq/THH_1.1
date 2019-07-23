package com.example.hijab;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    private TextView txtme;
    private Button btnSubmit;
    private TextView new_user1;
    private ProgressDialog progressDialog;
    private TextView forgetpass;

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
        forgetpass=(TextView)findViewById(R.id.forgetpass);


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

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_sigin.this,forgetpassword.class));
            }
        });

    }
    private void validate(final String username, String password){

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
                   /* Toast.makeText(activity_sigin.this, "Login Successful!!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(activity_sigin.this,MainActivity.class));
*/
                   checkEmailverification();
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

    private void checkEmailverification() {
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag=firebaseUser.isEmailVerified();
        if(emailflag){
            finish();
            Toast.makeText(activity_sigin.this, "Login Successful!!!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(activity_sigin.this,MainActivity.class));
        }
        else{
            Toast.makeText(this, "Please verify your Email to sign in", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

   /* public void HomeScreen(View view) {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }*/
}
