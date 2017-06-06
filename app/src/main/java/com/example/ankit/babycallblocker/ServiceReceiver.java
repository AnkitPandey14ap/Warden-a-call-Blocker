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

    /*public void onDestroy() {
        telephony.listen(null, PhoneStateListener.LISTEN_NONE);
    }*/

    private void endCallIfBlocked() {
        Log.i("Ankit", "1");
        try {
            Log.i("Ankit", "2");
            // Java reflection to gain access to TelephonyManager's
            // ITelephony getter

            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Log.i("Ankit", "3");

            Class<?> c = Class.forName(tm.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            com.android.internal.telephony.ITelephony telephonyService = (ITelephony) m.invoke(tm);
            Log.i("Ankit", "4");

            telephonyService = (ITelephony) m.invoke(tm);
            Log.i("Ankit", "5");

            telephonyService.silenceRinger();
            Log.i("Ankit", "6");

            telephonyService.endCall();
            Log.i("Ankit", "7");


        } catch (Exception e) {
            Log.i("Ankit", "ex "+String.valueOf(e));
            e.printStackTrace();
        }
        Log.i("Ankit", "8");
    }


}