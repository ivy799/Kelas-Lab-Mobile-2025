package com.example.tp_8;

import android.database.Cursor;

import com.example.tp_8.Database.DatabaseContract;

import java.util.ArrayList;
import java.util.Date;

public class MappingHelper {
    public static ArrayList<Note> mapCursorToArrayList(Cursor cursor){
        ArrayList<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.DESCRIPTION));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.TIME));
            notes.add(new Note(title, description, time, id));
        }

        return notes;
    }
}
