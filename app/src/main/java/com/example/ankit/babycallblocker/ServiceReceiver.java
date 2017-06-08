package com.example.ankit.babycallblocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;

public class ServiceReceiver extends BroadcastReceiver {
    TelephonyManager telephony;
    Context context;
    String savedNumber;

    public void onReceive(Context context, Intent intent) {
        Log.i("Ankit", "onReceive");


        /*if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);

            int state;
            if(stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                state = TelephonyManager.CALL_STATE_IDLE;
                Log.i("Ankit", String.valueOf(state));
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                state = TelephonyManager.CALL_STATE_OFFHOOK;
                Log.i("Ankit", String.valueOf(state));
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                state = TelephonyManager.CALL_STATE_RINGING;
                Log.i("Ankit", String.valueOf(state));
            }




            Log.i("Ankit", "onReceive if "+" "+stateStr);



            PhoneListenerManual phoneListener = new PhoneListenerManual(context);
            telephony = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            phoneListener.endCallIfBlocked(savedNumber);
        }*/

        PhoneListenerManual phoneListener = new PhoneListenerManual(context);
        telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);



    }

}