package com.example.ankit.babycallblocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class IncomingCallReceiver extends BroadcastReceiver {
    private static final String TAG = "Ankit";
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
                ArrayList<String> numberList = new ArrayList<>();
                numberList.add(phoneNumber);
                if(compareNo(numberList)){
                    PhoneListenerManual phoneListenerManual = new PhoneListenerManual(context);
                    phoneListenerManual.endCallIfBlocked(phoneNumber);
                }
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

    public boolean compareNo(ArrayList<String> some_num){
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor people = context.getContentResolver().query(uri, projection, null, null, null);

        int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int j_count=0;
        String number;
        people.moveToFirst();
        do {

            String name   = people.getString(indexName);
            number = people.getString(indexNumber);

            Log.i(TAG, "compareNo: "+number+" Calling no is "+some_num.get(0));

            if (some_num.contains(number)){
                return true;
            }

            j_count++;


            // Do work...
        } while (people.moveToNext());
        return false;
    }




}