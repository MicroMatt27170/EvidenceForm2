package com.matthew.micromatt.evidenceform.DatabaseHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.matthew.micromatt.evidenceform.DatabaseHandler.CurpContract.*;

public class CurpDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "CurpGenerator.db";

    public CurpDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
