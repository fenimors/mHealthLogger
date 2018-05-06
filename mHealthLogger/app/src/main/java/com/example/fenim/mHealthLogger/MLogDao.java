package com.example.fenim.mHealthLogger;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MLogDao {
    @Query("SELECT * FROM MLog")
    LiveData<List<MLog>> getAllLogs();

    @Query("SELECT * FROM MLog WHERE strftime('%Y', date) = :year")
    List<MLog> getFromTable(String year);

    @Query("SELECT * FROM MLog WHERE MLog.id = :id")
    MLog getMlogByID(int id);

    @Insert
    void insertAll(MLog... mlogs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MLog mlog);

  /*  @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAtId(MLog mlog, int id);*/

    @Query("DELETE FROM MLog")
    void deleteAll();
}

