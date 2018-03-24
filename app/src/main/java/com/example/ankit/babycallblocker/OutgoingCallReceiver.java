package com.example.ankit.babycallblocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class OutgoingCallReceiver extends BroadcastReceiver {
    Context context;

    static boolean stateChangeListener=true;
    public void onReceive(final Context context, Intent intent) {

            Log.i("Ankit", "onReceive Outgoing");

            TelephonyManager telephony = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            telephony.listen(new PhoneListenerManual(context), PhoneStateListener.LISTEN_CALL_STATE);

    }


}