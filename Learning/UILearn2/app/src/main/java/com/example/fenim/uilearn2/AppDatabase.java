package com.example.fenim.uilearn2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MLog.class, Sliders.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MLogDao mlogDao();
    public abstract slidersDao slidersDao();
}
