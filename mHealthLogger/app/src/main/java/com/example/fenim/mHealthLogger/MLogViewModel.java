package com.example.fenim.mHealthLogger;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.fenim.mHealthLogger.AppRepository;
import com.example.fenim.mHealthLogger.MLog;

import java.util.List;

public class MLogViewModel extends AndroidViewModel {

    private AppRepository mRepository;

    private LiveData<List<MLog>> rmAllLogs;

    public MLogViewModel(Application application) {
        super(application);
        mRepository = new AppRepository(application);
        rmAllLogs = mRepository.getrmAllLogs();
    }

    LiveData<List<MLog>> getrmAllLogs() { return rmAllLogs; }

    public void insert(MLog mlog) { mRepository.insert(mlog); }
}
