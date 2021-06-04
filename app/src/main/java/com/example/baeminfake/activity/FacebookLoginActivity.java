package com.example.baeminfake.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baeminfake.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;


public class FacebookLoginActivity extends AppCompatActivity {

    private TextView result;
    private LoginButton login;
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_login_activity);
        login = findViewById(R.id.login_fb);
        result = findViewById(R.id.fb_login_result);

        firebaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity(new Intent(FacebookLoginActivity.this, MainActivity.class));
                Log.d("User ID: ", loginResult.getAccessToken().getUserId());
                Log.d("Auth Token: ", loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                result.setText("Login canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                result.setText("Login failed.");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}