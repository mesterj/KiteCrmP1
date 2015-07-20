package com.kite.joco.kitecrmp1.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Set;

/**
 * Created by Joco on 2015.03.20..
 */
public class CallEndReceiver extends BroadcastReceiver {
    String filename = "CallerNum";
    SharedPreferences callinglogpref;
    final String sharedprefile = "CALLLOGPREF";
    final String TAG = "KITECRMDEMO";

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

        // A h�v�s v�g�nek figyel�se
        // ha a h�v�s befejez�dik �s m�g nem volt ilyen sz�mmal senki a telefonk�nyvben akkor el�sz�r az �j kapcsolat felvitl�hez val� k�perny� indul el, benne a telefonsz�mmal.

        if (stateval != null && stateval.equals("IDLE")) {
            Log.i(TAG, "CALL ENDED");
            Log.i(TAG, stateval);
            Log.i(TAG, "Most kellene h�vni az activityt!");
            Log.i(TAG, "callerNumber value is " + callerNumber);
            Log.i(TAG, " called number value is_: " + calledNumber);
            if (!callerNumber.equals("")) {
                //Intent newContactIntent = new Intent(context, NewContactActivity.class);
                //newContactIntent.putExtra("Number", callerNumber);
                //newContactIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               // editor.remove("CALLER_NUMBER");
                //editor.commit();
                //context.startActivity(newContactIntent);
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

        // Toast.makeText(context, "A h�vott sz�m : " + calledNumber, Toast.LENGTH_LONG).show();
        // intent for create new memo
        // Ez a r�sz akkor kell ha minket h�vnak.


    else if(stateval!=null&&stateval.equals("RINGING"))

    {
        Log.i(TAG, " CALL_STARTED");
        Log.i(TAG, stateval);
        Log.i(TAG, " incoming number : " + bundle.getString("incoming_number"));
        //Toast.makeText(context, " A h�v� sz�ma: " + bundle.getString("incoming_number"), Toast.LENGTH_LONG).show();

        //SHaredPref r�sz
        editor.putString("CALLER_NUMBER", bundle.getString("incoming_number"));
        editor.commit();

        // SharedPref ki�r�s
        editor.putString("CALLER_NUMBER", bundle.getString("incoming_number"));

        // Fil�s r�sz
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


        // Fil�b�l �gy volt
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
