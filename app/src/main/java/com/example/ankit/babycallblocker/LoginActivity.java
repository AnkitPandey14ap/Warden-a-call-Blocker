package com.example.ankit.babycallblocker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.orangegangsters.lollipin.lib.managers.AppLock;

public class LoginActivity extends AppCompatActivity {
    private static final String MY_PREFS_NAME = "password";
    String t="Ankit";
    EditText passwordEditText;
    String enteredPswd;

    private static final int REQUEST_CODE_ENABLE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LOG-IN");

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("pswd","0000");
        editor.commit();


    }

    public void login(View v)
    {
        Log.i(t, "login clicked");
        enteredPswd=passwordEditText.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String password = sharedPreferences.getString("pswd", "0000");

        Log.i("Ankit",password+" "+enteredPswd);

        if(enteredPswd.equals(password)){
            Intent intent = new Intent(LoginActivity.this, CallManageActivity.class);
            startActivity(intent);
            finish();
        } else if (TextUtils.isEmpty(enteredPswd)) {
            passwordEditText.setError("Please Enter Password");
            passwordEditText.setText(enteredPswd);
            passwordEditText.requestFocus();
        }else{
            passwordEditText.setError("Wrong Password");
            passwordEditText.requestFocus();
        }


    }
    public void changePasswordActivity(View v)
    {
        Log.i(t, "change passwordclicked");

        Intent intent = new Intent(LoginActivity.this, CustomPinActivity.class);

        intent.putExtra(AppLock.EXTRA_TYPE, AppLock.ENABLE_PINLOCK);
        startActivityForResult(intent, REQUEST_CODE_ENABLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_CODE_ENABLE:
                Toast.makeText(this, "PinCode enabled", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
