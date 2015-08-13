package com.kite.joco.kitecrmp1.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Joco on 2015.05.24..
 */
@Database(name = CrmDatabase.DATABASE_NAME,version = CrmDatabase.DATABASE_VERSION, foreignKeysSupported = true)
public class CrmDatabase {
    public static final String DATABASE_NAME = "CRMdb";
    public static final int DATABASE_VERSION = 3;
}
