package com.example.fenim.mHealthLogger.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MLog.class, Sliders.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MLogDao mlogDao();
    public abstract slidersDao slidersDao();
}
