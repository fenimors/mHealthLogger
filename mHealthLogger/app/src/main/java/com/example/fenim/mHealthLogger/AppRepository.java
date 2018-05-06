package com.example.fenim.mHealthLogger;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;


import java.util.List;
import java.util.concurrent.ExecutionException;

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


    public List<MLog> getrmDateLogs() {
        List<MLog> mlogs = null;
        try {
            mlogs = new getrmDateLogsAsyncTask(rmlogdao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return mlogs;
    }
    private static class getrmDateLogsAsyncTask extends AsyncTask<Void, Void, List<MLog>> {

        private MLogDao mAsyncTaskDao;

        public getrmDateLogsAsyncTask(MLogDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<MLog> doInBackground(Void... Void) {
            return mAsyncTaskDao.getMlogByDate();
        }

        @Override
        protected void onPostExecute(List<MLog> mlog) {
            super.onPostExecute(mlog);
        }
    }



    public List<MLog> getFromTable(Long i, Long j) {
        List<MLog> mlogs = null;
        try {
            mlogs = new getFromTableAsyncTask(rmlogdao).execute(i, j).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return mlogs;
    }
    private static class getFromTableAsyncTask extends AsyncTask<Long, Void, List<MLog>> {

        private MLogDao mAsyncTaskDao;

        public getFromTableAsyncTask(MLogDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<MLog> doInBackground(Long... params) {
            return mAsyncTaskDao.getFromTable(params[0], params[1]);
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

    public Integer deleteMlogByID(int uID) {
        int mlog = 0;
        try {
            mlog = new deleteMlogByIDAsyncTask(rmlogdao).execute(uID).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return mlog;
    }
    private static class deleteMlogByIDAsyncTask extends AsyncTask<Integer, Void, Integer> {

        private MLogDao mAsyncTaskDao;

        public deleteMlogByIDAsyncTask(MLogDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Integer doInBackground(Integer... uID) {
            int mlog = mAsyncTaskDao.deleteMlogByID(uID[0]);
            return mlog;
        }
    }
}
