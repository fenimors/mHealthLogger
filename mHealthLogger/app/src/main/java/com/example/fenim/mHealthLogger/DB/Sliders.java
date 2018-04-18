package com.example.fenim.mHealthLogger.DB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//@Entity(foreignKeys = @ForeignKey(entity = MLog.class,
//        parentColumns = "id",
//        childColumns = "slide_id"))
@Entity
public class Sliders {

    public Sliders(String s_type, int s_val, int slide_id) {
        this.s_type = s_type;
        this.s_val = s_val;
        this.slide_id = slide_id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "slide_id")
    private int slide_id;

    @ColumnInfo(name = "s_type")
    private String s_type;

    @ColumnInfo(name = "s_val")
    private int s_val;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSlide_id() {
        return slide_id;
    }

    public void setSlide_id(int slide_id) {
        this.slide_id = slide_id;
    }

    public String getS_type() {
        return s_type;
    }

    public void setS_type(String s_type) {
        this.s_type = s_type;
    }

    public int getS_val() {
        return s_val;
    }

    public void setS_val(int s_val) {
        this.s_val = s_val;
    }
}