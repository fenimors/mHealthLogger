package com.example.fenim.uilearn2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface slidersDao {
    @Query("SELECT * FROM Sliders")
    List<Sliders> getAllSliders();

    @Query("SELECT * FROM Sliders WHERE slide_id=:slide_id")
    List<Sliders> findSlidersForMLog(final int slide_id);

    @Insert
    void insertAll(Sliders... sliders);
}


