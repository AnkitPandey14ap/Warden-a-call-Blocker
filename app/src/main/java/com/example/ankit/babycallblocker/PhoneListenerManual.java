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
    private final Context context;


    public PhoneListenerManual(Context context) {
        super();
        this.context = context;
        //onCallStateChanged();
    }

    public void onCallStateChanged(int state, String incomingNumber) {
        Log.i("Ankit", "onCallStateChanged0");
        super.onCallStateChanged(state, incomingNumber);
        Log.i("Ankit", "onCallStateChanged");

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i("Ankit", "IDLE");
                phoneRinging = false;
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.i("Ankit", "OFFHOOK");
                phoneRinging = false;
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.i("Ankit", "RINGING");
                endCallIfBlocked(incomingNumber);
                phoneRinging = true;

                break;
        }

    }
    private void endCallIfBlocked(String outGoingNumber) {
        try {
            // Java reflection to gain access to TelephonyManager's
            // ITelephony getter

            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> c = Class.forName(tm.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            com.android.internal.telephony.ITelephony telephonyService = (ITelephony) m.invoke(tm);


                telephonyService = (ITelephony) m.invoke(tm);
                telephonyService.silenceRinger();
                telephonyService.endCall();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
