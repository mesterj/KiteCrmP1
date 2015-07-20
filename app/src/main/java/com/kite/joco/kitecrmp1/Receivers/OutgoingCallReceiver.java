package com.kite.joco.kitecrmp1.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Joco on 2015.05.04..
 */
public class OutgoingCallReceiver extends BroadcastReceiver {

    private static final String TAG="KITECRMDEMO";
    final String sharedprefile = "CALLLOGPREF";

    @Override
        public void onReceive(Context context, Intent intent) {
        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        Log.i(TAG, phoneNumber);
        SharedPreferences calllogprefs = context.getSharedPreferences(sharedprefile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = calllogprefs.edit();
        editor.putString("CALLED_NUMBER",phoneNumber);
        editor.commit();

        //Toast.makeText(context, "Outgoing call catched!: " + phoneNumber, Toast.LENGTH_LONG).show();

        // El kell menteni az app saj�t sharedpreferences fil�j�be. Ha m�r van ilyen �rt�k benn azt ki kell t�r�lni, vagy t�rljem az eg�sz sharedprefs-t?
        // Egy �rt�k t�rl�se: SharedPreferenes.Editor.remove(KEY) commit()-nak kell k�vetnie
        // Eg�sz tartalom t�rl�se: SharedPreferences.Editor.clear() commit itt is kell.

        /*
        A folyamat: H�v�st �nd�t a user a telefonon. Ha az alkalmaz�sb�l ind�totta, akkor a h�vott kapcsolat m�r megvan, azt nem kell keresni.
        Az eg�sz keres�s t�rt�nettel nem kell foglalkozni. Mindent lehet t�r�lni �s kil�pni. A h�v�s v�g�t figyel� receivernek kell jelezni, hogy melyik partnerrel ind�tsa a mem�t.

        Ha nem a programb�l indul akkor a sz�mot el kell rakni �s keres�s�t ind�tani az adatb�zisban. Ha nincs meg a sz�m akkor �j kontakt �s ut�na �j mem�.
        Ha megvan a sz�m tulajdonosa akkor csak �j mem�

        A h�v�s v�g�t figyel� programnak a mem� vagy newcontact intent h�v�s el�tt ki kell t�r�lnie a sharedprefs-t.

         */

    }
}
