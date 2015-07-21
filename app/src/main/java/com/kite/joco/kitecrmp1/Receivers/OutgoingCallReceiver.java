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

    private static final String TAG="KITECRMP1";
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

        // El kell menteni az app saját sharedpreferences filéjébe. Ha már van ilyen érték benn azt ki kell törölni, vagy törljem az egész sharedprefs-t?
        // Egy érték törlése: SharedPreferenes.Editor.remove(KEY) commit()-nak kell követnie
        // Egész tartalom törlése: SharedPreferences.Editor.clear() commit itt is kell.

        /*
        A folyamat: Hívást índít a user a telefonon. Ha az alkalmazásból indította, akkor a hívott kapcsolat már megvan, azt nem kell keresni.
        Az egész keresés történettel nem kell foglalkozni. Mindent lehet törölni és kilépni. A hívás végét figyelő receivernek kell jelezni, hogy melyik partnerrel indítsa a memót.

        Ha nem a programból indul akkor a számot el kell rakni és keresését indítani az adatbázisban. Ha nincs meg a szám akkor új kontakt és utána új memó.
        Ha megvan a szám tulajdonosa akkor csak új memó

        A hívás végét figyelő programnak a memó vagy newcontact intent hívás előtt ki kell törölnie a sharedprefs-t.

         */

    }
}
