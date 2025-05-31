package com.example.tp_8.Database;

import android.provider.BaseColumns;

import java.util.Date;

public class DatabaseContract {
    public static String TABLE_NAME = "Notes";

    public static final class NotesColumns implements BaseColumns {
        public static String TITLE = "title";
        public static String DESCRIPTION = "description";
        public static String TIME = "time";
    }

}
