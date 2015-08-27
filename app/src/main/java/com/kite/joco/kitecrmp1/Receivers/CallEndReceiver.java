package com.kite.joco.kitecrmp1.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Window;

import com.kite.joco.kitecrmp1.activities.NewContactActivity;

import java.util.Set;

/**
 * Created by Joco on 2015.03.20..
 */
public class CallEndReceiver extends BroadcastReceiver {
    String filename = "CallerNum";
    SharedPreferences callinglogpref;
    final String sharedprefile = "CALLLOGPREF";
    final String TAG = "KITECRMP1";

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();

        Set<String> keys = bundle.keySet();

        callinglogpref = context.getSharedPreferences(sharedprefile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = callinglogpref.edit();

        /*
        The value of incoming_number key in the bundle is the called number, maybe passing for content of the new memo.
         */

        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        CustomPhoneStateListener myStateListener = new CustomPhoneStateListener();

        telephony.listen(myStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        //String calledNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        String callerNumber = loadConfig(context, "CALLER_NUMBER");
        String calledNumber = loadConfig(context, "CALLED_NUMBER");

        String stateval = bundle.getString("state");

        // A hívás végének figyelése
        // ha a hívás befejeződik és még nem volt ilyen számmal senki a telefonkönyvben akkor először az új kapcsolat felvitléhez való képernyő indul el, benne a telefonszámmal.

        if (stateval != null && stateval.equals("IDLE")) {
            Log.i(TAG, "CALL ENDED");
            Log.i(TAG, stateval);
            Log.i(TAG, "Most kellene hívni az activityt!");
            Log.i(TAG, "callerNumber value is " + callerNumber);
            Log.i(TAG, " called number value is_: " + calledNumber);
            if (!callerNumber.equals("")) {
                Intent newContactIntent = new Intent(context, NewContactActivity.class);
                newContactIntent.putExtra("Number", callerNumber);
                newContactIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               editor.remove("CALLER_NUMBER");
               editor.commit();
             //  context.startActivity(newContactIntent);
            }
            if (!calledNumber.equals("")) {
                //Intent newContIntent1 = new Intent(context, NewContactActivity.class);
               // newContIntent1.putExtra("Number", calledNumber);
               // newContIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               // editor.remove("CALLED_NUMBER");
               // editor.commit();
               // context.startActivity(newContIntent1);
            }
        }

        // Toast.makeText(context, "A hívott szám : " + calledNumber, Toast.LENGTH_LONG).show();
        // intent for create new memo
        // Ez a rész akkor kell ha minket hívnak.


    else if(stateval!=null&&stateval.equals("RINGING"))

    {
        Log.i(TAG, " CALL_STARTED");
        Log.i(TAG, stateval);
        Log.i(TAG, " incoming number : " + bundle.getString("incoming_number"));
        //Toast.makeText(context, " A hívó száma: " + bundle.getString("incoming_number"), Toast.LENGTH_LONG).show();

        //SHaredPref rész
        editor.putString("CALLER_NUMBER", bundle.getString("incoming_number"));
        editor.commit();

        // SharedPref kiírás
        editor.putString("CALLER_NUMBER", bundle.getString("incoming_number"));

        // Filés rész
            /*FileOutputStream outputStream;
            try {
                outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(bundle.getString("incoming_number").getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
    }

    else

    {
        Log.i(TAG, "Nem tudom ilyenkor mi van");
    }

}

    private String loadConfig(Context context, String Key) {
        // Sharedpref-fel


        String callernum = callinglogpref.getString(Key, "");
        return callernum;


        // Filéből így volt
        /*try {
            InputStream in = context.openFileInput(filename);
            if (in != null) {
                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                StringBuilder buf = new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str);
                }
                in.close();
                return buf.toString();
            }
        } catch (IOException iex) {
            return null;
        }*/
        //return null;
    }
}
