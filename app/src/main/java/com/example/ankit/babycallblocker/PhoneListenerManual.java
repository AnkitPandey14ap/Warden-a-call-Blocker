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
                Log.i("Ankit", "IDLE "+incomingNumber);
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

    public void endCallIfBlocked(String outGoingNumber) {
        Log.i("Ankit", "endCallIfBlocked method");
        try {

            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            Class<?> c = Class.forName(tm.getClass().getName());

            Method m = c.getDeclaredMethod("getITelephony");

            m.setAccessible(true);

            com.android.internal.telephony.ITelephony telephonyService = (ITelephony) m.invoke(tm);

                telephonyService = (ITelephony) m.invoke(tm);

            //telephonyService.silenceRinger();
                telephonyService.endCall();


        } catch (Exception e) {
            Log.i("Ankit", "error" + e);
            e.printStackTrace();
        }
    }
}
