package com.example.ankit.babycallblocker;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import java.util.logging.Logger;

public class CallManageActivity extends AppCompatActivity {
    private static final String TAG = "Ankit";
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


                    PackageManager pm  = CallManageActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(CallManageActivity.this, OutgoingCallReceiver.class);
                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);



                }
                else {
                    Toast.makeText(CallManageActivity.this, "Stop", Toast.LENGTH_SHORT).show();
                    ed.putBoolean("OutgoingSwitchIsChecked", false);
                    ed.commit();

                    PackageManager pm  = CallManageActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(CallManageActivity.this, OutgoingCallReceiver.class);
                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);


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

                    PackageManager pm  = CallManageActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(CallManageActivity.this, IncomingCallReceiver.class);
                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);

                }
                else {
                    Toast.makeText(CallManageActivity.this, "Stop", Toast.LENGTH_SHORT).show();
                    ed1.putBoolean("IncomingSwitchIsChecked", false);
                    ed1.commit();

                    PackageManager pm  = CallManageActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(CallManageActivity.this, IncomingCallReceiver.class);
                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);

                }
            }
        });


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.PROCESS_OUTGOING_CALLS) + ContextCompat
                .checkSelfPermission(this,
                        Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.PROCESS_OUTGOING_CALLS) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                Log.i("Ankit", "inner if");

            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS,Manifest.permission.CALL_PHONE,Manifest.permission.READ_CONTACTS},
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

        askAutoStartPermission();


    }

    private void askAutoStartPermission() {
        String xiaomi = "Xiaomi";
        String deviceManufacturer = android.os.Build.MANUFACTURER;

        final String CALC_PACKAGE_NAME = "com.miui.securitycenter";
        final String CALC_PACKAGE_ACITIVITY = "com.miui.permcenter.autostart.AutoStartManagementActivity";
        if (deviceManufacturer.equalsIgnoreCase(xiaomi)) {
            AlertDialog.Builder alertDialog= new AlertDialog.Builder(CallManageActivity.this);
            alertDialog.setTitle("Make sure Warden have Autostart permission");
            alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    try {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(CALC_PACKAGE_NAME, CALC_PACKAGE_ACITIVITY));
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Log.i(TAG, "Failed to launch AutoStart Screen ", e);
                    } catch (Exception e) {
                        Log.i(TAG, "Failed to launch AutoStart Screen ");
                    }

                }
            });
            alertDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub


                }
            });
            alertDialog.show();

        }



    }


}





