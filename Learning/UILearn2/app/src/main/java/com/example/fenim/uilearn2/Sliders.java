package com.example.fenim.uilearn2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = MLog.class,
        parentColumns = "id",
        childColumns = "slide_id"))
public class Sliders {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int slide_id;

    public String sType;

    public int sVal;

    public Sliders(String sType, int sVal, int slide_id) {
        this.slide_id = slide_id;
        this.sType = sType;
        this.sVal = sVal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}