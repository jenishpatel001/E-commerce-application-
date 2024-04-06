package com.example.shivammart.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shivammart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_up_Activity extends AppCompatActivity {

    EditText email,password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );
//        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        email = findViewById( R.id.email );
        password= findViewById( R.id.password );

    }

    public void signin(View view) {
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();


        if(TextUtils.isEmpty(useremail)){
            Toast.makeText( this, "Enter Email Address !", Toast.LENGTH_SHORT ).show();
            return;
        }

        if(TextUtils.isEmpty(userpassword)){
            Toast.makeText( this, "Enter Password!", Toast.LENGTH_SHORT ).show();
            return;
        }
        if(userpassword.length()<6){
            Toast.makeText( this, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT ).show();
            return;
        }

        auth.signInWithEmailAndPassword( useremail ,userpassword).addOnCompleteListener( Sign_up_Activity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText( Sign_up_Activity.this, "Login Successfull", Toast.LENGTH_SHORT ).show();
                    startActivity( new Intent(Sign_up_Activity.this,MainActivity.class) );
                }
                else{
                    Toast.makeText( Sign_up_Activity.this, "Error:"+task.getException(), Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    public void signup(View view) {
        startActivity( new Intent(Sign_up_Activity.this,RegisterActivity.class) );
    }
}