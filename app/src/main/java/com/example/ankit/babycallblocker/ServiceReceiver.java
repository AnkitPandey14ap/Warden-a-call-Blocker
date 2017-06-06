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

    public void onReceive(Context context, Intent intent) {
        Log.i("Ankit", "onReceive");

        PhoneListenerManual phoneListener = new PhoneListenerManual(context);
        telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);



    }

}