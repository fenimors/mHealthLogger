package com.example.fenim.roomdatatesting;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by fenim on 2/28/2018.
 */


@Database(entities = { Note.class }, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public static final String DB_NAME ="notesdb.db";

    public abstract NoteDao getNoteDao();

    private static NoteDatabase noteDB;

    public static NoteDatabase getInstance(Context context) { if (null == noteDB) { noteDB = buildDatabaseInstance(context); } return noteDB; }

    private static NoteDatabase buildDatabaseInstance(Context context) { return Room.databaseBuilder(context, NoteDatabase.class, DB_NAME) .allowMainThreadQueries().build(); }

    public void cleanUp(){ noteDB = null; }

}