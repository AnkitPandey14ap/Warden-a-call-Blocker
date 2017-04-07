package com.example.ankit.babycallblocker;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class CallManageActivity extends AppCompatActivity {
    Switch outgoingSwitch;
    Switch incomingSwitch;

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



    }
}
