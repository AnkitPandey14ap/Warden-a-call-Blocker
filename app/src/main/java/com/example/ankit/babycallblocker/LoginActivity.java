package com.example.ankit.babycallblocker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class LoginActivity extends AppCompatActivity {
    String t="Ankit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v){
        Log.i(t, "login clicked");
    }
    public void changePasswordActivity(View v){
        Log.i(t, "change passwordclicked");
    }
}
