package com.example.baeminfake.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baeminfake.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;

public class LoginOptionActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private Button lgInsta, lgMail;
    private LoginButton lgFaceb;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_options_activity);

        lgInsta = findViewById(R.id.with_insta);
        lgFaceb = findViewById(R.id.with_fb);
        lgMail = findViewById(R.id.with_mail);


        firebaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        lgFaceb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginOptionActivity.this, "" + loginResult.getAccessToken().getUserId(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                lgInsta.setText("Login canceled");
            }

            @Override
            public void onError(FacebookException error) {
                lgInsta.setText("Login failed");
            }
        });

        lgMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginOptionActivity.this, AuthorActivity.class));
            }
        });
    }
}
