package com.example.fenim.mHealthLogger;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MLogDao {
    @Query("SELECT * FROM MLog ORDER BY MLog.date")
    LiveData<List<MLog>> getAllLogs();

    @Query("SELECT * FROM MLog WHERE date BETWEEN :i AND :j")
    List<MLog> getFromTable(Long i, Long j);

    @Query("SELECT * FROM MLog WHERE MLog.id = :id")
    MLog getMlogByID(int id);

    @Query("SELECT * FROM MLog ORDER BY MLog.date")
    List<MLog> getMlogByDate();

    @Insert
    void insertAll(MLog... mlogs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MLog mlog);

  /*  @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAtId(MLog mlog, int id);*/

    @Query("DELETE FROM MLog")
    void deleteAll();

    @Query("DELETE FROM MLog WHERE MLog.id = :id")
    int deleteMlogByID(int id);
}

