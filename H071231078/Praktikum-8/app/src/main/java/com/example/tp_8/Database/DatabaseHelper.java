package com.example.tp_8.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static final String SQL_CREATE_TABLE_NOTE =
            String.format(
                    "CREATE TABLE %S"
                            + " (%s INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL)",
                    DatabaseContract.TABLE_NAME,
                    DatabaseContract.NotesColumns._ID,
                    DatabaseContract.NotesColumns.TITLE,
                    DatabaseContract.NotesColumns.DESCRIPTION,
                    DatabaseContract.NotesColumns.TIME
            );


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(db);
    }
}
