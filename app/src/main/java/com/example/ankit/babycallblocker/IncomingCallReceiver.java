package com.example.ankit.babycallblocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;

public class IncomingCallReceiver extends BroadcastReceiver {
    Context context;
    String savedNumber;

    static boolean stateChangeListener=true;
    public void onReceive(final Context context, Intent intent) {
        Log.i("Ankit", "onReceive Incoming");


        Bundle extras = intent.getExtras();
        if (extras != null) {
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            Log.w("Ankit", state);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String phoneNumber = extras
                        .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Log.w("Ankit", phoneNumber);

                PhoneListenerManual phoneListenerManual = new PhoneListenerManual(context);
                phoneListenerManual.endCallIfBlocked(phoneNumber);
            }
        }


        /*





        if (stateChangeListener){
            stateChangeListener = false;
            Log.i("Ankit", "onReceive");

            TelephonyManager telephony = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            telephony.listen(new PhoneListenerManual(context), PhoneStateListener.LISTEN_CALL_STATE);

        }
*/
    }


}