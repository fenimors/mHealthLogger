package com.example.fenim.uilearn2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MLog.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MLogDao mlogDao();
}
