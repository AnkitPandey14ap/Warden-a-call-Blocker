package com.example.ankit.babycallblocker;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;

/**
 * Created by ankit on 7/4/17.
 */

public class PhoneListenerManual extends PhoneStateListener {

    public static Boolean phoneRinging = false;
    Context context;

    public PhoneListenerManual(Context context) {
        this.context = context;

    }


    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        Log.i("Ankit", "onCallStateChanged");

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i("Ankit", "IDLE");
                endCallIfBlocked(incomingNumber);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.i("Ankit", "OFFHOOK "+incomingNumber);
                endCallIfBlocked(incomingNumber);
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.i("Ankit", "RINGING "+incomingNumber);
                endCallIfBlocked(incomingNumber);
                break;
        }


    }

    private void endCallIfBlocked(String outGoingNumber) {
        Log.i("Ankit", "1");
        try {
            Log.i("Ankit", "2");
            // Java reflection to gain access to TelephonyManager's
            // ITelephony getter

            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Log.i("Ankit", "3");

            Class<?> c = Class.forName(tm.getClass().getName());
            Log.i("Ankit", "4");

            Method m = c.getDeclaredMethod("getITelephony");
            Log.i("Ankit", "5");

            m.setAccessible(true);
            Log.i("Ankit", "6");

            com.android.internal.telephony.ITelephony telephonyService = (ITelephony) m.invoke(tm);
            Log.i("Ankit", "6");

                telephonyService = (ITelephony) m.invoke(tm);
            Log.i("Ankit", "7");

            //telephonyService.silenceRinger();
                telephonyService.endCall();
            Log.i("Ankit", "8");


        } catch (Exception e) {
            Log.i("Ankit", "error" + e);
            e.printStackTrace();
        }
        Log.i("Ankit", "9");
    }
}
