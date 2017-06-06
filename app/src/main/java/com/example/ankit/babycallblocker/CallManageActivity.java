package com.example.ankit.babycallblocker;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class CallManageActivity extends AppCompatActivity {
    Switch outgoingSwitch;
    Switch incomingSwitch;

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_manage);

        outgoingSwitch = (Switch) findViewById(R.id.outgoingSwitch);
        incomingSwitch = (Switch) findViewById(R.id.incomingSwitch);


        SharedPreferences sp = getSharedPreferences("OutgoingSwitchIsChecked", MODE_PRIVATE);
        final SharedPreferences.Editor ed = sp.edit();

        outgoingSwitch.setChecked(sp.getBoolean("OutgoingSwitchIsChecked",false));

        outgoingSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(outgoingSwitch.isChecked()){
                    Toast.makeText(CallManageActivity.this, "Start", Toast.LENGTH_SHORT).show();
                    ed.putBoolean("OutgoingSwitchIsChecked", true);
                    ed.commit();
                }
                else {
                    Toast.makeText(CallManageActivity.this, "Stop", Toast.LENGTH_SHORT).show();
                    ed.putBoolean("OutgoingSwitchIsChecked", false);
                    ed.commit();
                }
            }
        });
        //For Incoming button

        SharedPreferences sp1 = getSharedPreferences("IncomingSwitchIsChecked", MODE_PRIVATE);
        final SharedPreferences.Editor ed1 = sp1.edit();
        incomingSwitch.setChecked(sp1.getBoolean("IncomingSwitchIsChecked",false));


        incomingSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(incomingSwitch.isChecked()){
                    Toast.makeText(CallManageActivity.this, "Start", Toast.LENGTH_SHORT).show();
                    ed1.putBoolean("IncomingSwitchIsChecked", true);
                    ed1.commit();
                }
                else {
                    Toast.makeText(CallManageActivity.this, "Stop", Toast.LENGTH_SHORT).show();
                    ed1.putBoolean("IncomingSwitchIsChecked", false);
                    ed1.commit();
                }
            }
        });





        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.PROCESS_OUTGOING_CALLS);
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.PROCESS_OUTGOING_CALLS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.PROCESS_OUTGOING_CALLS)) {

                Log.i("Ankit", "inner if");

            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                Log.i("Ankit", "requsted outgoing call");


            }
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {

                Log.i("Ankit", "inner if");

            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                Log.i("Ankit", "requsted CALL_PHONE");


            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {

                Log.i("Ankit", "inner if");

            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                Log.i("Ankit", "requsted READ PHONE_STATE");


            }
        }
    }


    }




