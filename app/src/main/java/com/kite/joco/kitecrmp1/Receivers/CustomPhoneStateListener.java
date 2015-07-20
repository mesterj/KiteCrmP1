package com.kite.joco.kitecrmp1.Receivers;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Joco on 2015.03.29..
 */
public class CustomPhoneStateListener extends PhoneStateListener {

    private static final String TAG = "KITECRMDEMO";

    public void onCallStateChange(int state, String incomingNumber) {

        Log.v(TAG, "WE ARE INSIDE!!!!!!!!!!!");
        Log.v(TAG, incomingNumber);

        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                Log.d(TAG, "RINGING");
                Log.i(TAG, "A hívó telefonszáma: " + incomingNumber);
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i(TAG, "Number is : " + incomingNumber);
                break;
        }
    }
}