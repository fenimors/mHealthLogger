package com.example.fenim.mHealthLogger;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MLogDao {
    @Query("SELECT * FROM MLog")
    LiveData<List<MLog>> getAllLogs();

    @Insert
    void insertAll(MLog... mlogs);

    @Insert
    void insert(MLog mlog);

    @Query("DELETE FROM MLog")
    void deleteAll();
}

