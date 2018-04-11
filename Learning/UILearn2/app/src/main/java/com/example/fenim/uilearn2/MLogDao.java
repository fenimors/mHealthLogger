package com.example.fenim.uilearn2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MLogDao {
    @Query("SELECT * FROM MLog")
    List<MLog> getAllLogs();

    @Insert
    void insertAll(MLog... mlogs);
}

