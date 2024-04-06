package com.example.shivammart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shivammart.Drawer_Activity;
import com.example.shivammart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, password;
    private FirebaseAuth auth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

//        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity( new Intent( RegisterActivity.this, MainActivity.class ) );
            finish();
        }
        name = findViewById( R.id.name );
        email = findViewById( R.id.email );
        password = findViewById( R.id.password );

        sharedPreferences = getSharedPreferences( "onBoardingScreen", MODE_PRIVATE );
        boolean isFirstTime = sharedPreferences.getBoolean( "firstTime", true );
        if (isFirstTime) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean( "firstTime", false );
            editor.commit();

            if (sharedPreferences.getString( "status", "logout" ).equals( "login" )) {
                Intent intent = new Intent( this, Drawer_Activity.class );
                startActivity( intent );
                onBackPressed();

            }
        }
    }


    public void signup(View view) {

        String username = name.getText().toString();
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();

        if (TextUtils.isEmpty( username )) {
            Toast.makeText( this, "Enter Name!", Toast.LENGTH_SHORT ).show();
            return;
        }

        if (TextUtils.isEmpty( useremail )) {
            Toast.makeText( this, "Enter Email Address !", Toast.LENGTH_SHORT ).show();
            return;
        }

        if (TextUtils.isEmpty( userpassword )) {
            Toast.makeText( this, "Enter Password!", Toast.LENGTH_SHORT ).show();
            return;
        }
        if (userpassword.length() < 6) {
            Toast.makeText( this, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT ).show();
            return;
        }

        auth.createUserWithEmailAndPassword( useremail, userpassword ).addOnCompleteListener( RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText( RegisterActivity.this, "Successfully Register", Toast.LENGTH_SHORT ).show();
                    startActivity( new Intent( RegisterActivity.this, Sign_up_Activity.class ) );
                } else {
                    Toast.makeText( RegisterActivity.this, "Registration Failed" + task.getException(), Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    public void signin(View view) {
        startActivity( new Intent( RegisterActivity.this, Sign_up_Activity.class ) );

    }
}