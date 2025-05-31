package com.example.tp_8.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteHelper {
    public static final String TABLE_NAME = DatabaseContract.TABLE_NAME;

    public static DatabaseHelper databaseHelper;
    private static SQLiteDatabase sqLiteDatabase;
    private static volatile NoteHelper INSTANCE;

    public NoteHelper(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public static NoteHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new NoteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException{
        sqLiteDatabase =databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
    }

    public Cursor queryAll(){
        return sqLiteDatabase.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.NotesColumns._ID + " ASC "
        );
    }

    public Cursor search(String keyword){
        return sqLiteDatabase.query(
                TABLE_NAME,
                null,
                DatabaseContract.NotesColumns.TITLE + " LIKE ?",
                new String[]{"%" + keyword + "%"},
                null,
                null,
                DatabaseContract.NotesColumns._ID + " ASC "
        );
    }

    public long insert(ContentValues contentValues){
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public int update(String id, ContentValues contentValues){
        return sqLiteDatabase.update(TABLE_NAME, contentValues, DatabaseContract.NotesColumns._ID
                + " = ? ", new String[]{id});
    }

    public int deleteById(String id){
            return sqLiteDatabase.delete(TABLE_NAME, DatabaseContract.NotesColumns._ID
                    + " = ? ", new String[]{id});
    }
}
