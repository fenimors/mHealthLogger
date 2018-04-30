package com.example.fenim.mHealthLogger;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;


import java.util.List;

public class AppRepository {
    private MLogDao rmlogdao;
    private LiveData<List<MLog>> rmAllLogs;

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        rmlogdao = db.mlogDao();
        rmAllLogs = rmlogdao.getAllLogs();
    }

    LiveData<List<MLog>> getrmAllLogs() {
        return rmAllLogs;
    }

    public void insert (MLog mlog) {
        new insertAsyncTask(rmlogdao).execute(mlog);
    }

    private static class insertAsyncTask extends AsyncTask<MLog, Void, Void> {
        private MLogDao mAsyncTaskDao;

        insertAsyncTask(MLogDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MLog... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
