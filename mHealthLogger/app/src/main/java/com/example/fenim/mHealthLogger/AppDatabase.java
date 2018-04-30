package com.example.fenim.mHealthLogger;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {MLog.class}, version = 7)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract MLogDao mlogDao();


    private static AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database").fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MLogDao mDao;

        PopulateDbAsync(AppDatabase db) {
            mDao = db.mlogDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            MLog mlog = new MLog("Hello", "World", "Cool note bro", 99999999, "1234");
            mDao.insert(mlog);
           // mlog = new MLog("Hello2", "World2", "Cool note bro2", 999999992);
            mDao.insert(mlog);
            return null;
        }
    }
}