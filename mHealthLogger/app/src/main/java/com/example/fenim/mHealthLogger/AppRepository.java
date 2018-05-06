package com.example.fenim.mHealthLogger;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;


import java.util.List;
import java.util.concurrent.ExecutionException;

public class AppRepository {
    private MLogDao rmlogdao;
    private LiveData<List<MLog>> rmAllLogs;
    private LiveData<List<MLog>> rmDateLogs;

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        rmlogdao = db.mlogDao();
        rmAllLogs = rmlogdao.getAllLogs();
    }

    LiveData<List<MLog>> getrmAllLogs() {
        return rmAllLogs;
    }


    public List<MLog> getFromTable(String year) {
        List<MLog> mlogs = null;
        try {
            mlogs = new getFromTableAsyncTask(rmlogdao).execute(year).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return mlogs;
    }
    private static class getFromTableAsyncTask extends AsyncTask<String, Void, List<MLog>> {

        private MLogDao mAsyncTaskDao;

        public getFromTableAsyncTask(MLogDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<MLog> doInBackground(String... year) {
            return mAsyncTaskDao.getFromTable(year[0]);
        }

        @Override
        protected void onPostExecute(List<MLog> mlog) {
            super.onPostExecute(mlog);
        }
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

    public MLog getMlogByID(int uID) {
        MLog mlog = null;
        try {
            mlog = new getMlogByIDAsyncTask(rmlogdao).execute(uID).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return mlog;
    }
    private static class getMlogByIDAsyncTask extends AsyncTask<Integer, Void, MLog> {

        private MLogDao mAsyncTaskDao;

        public getMlogByIDAsyncTask(MLogDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected MLog doInBackground(Integer... uID) {
            MLog mlog = mAsyncTaskDao.getMlogByID(uID[0]);
            return mlog;
        }

        @Override
        protected void onPostExecute(MLog mlog) {
            super.onPostExecute(mlog);
        }
    }
}
