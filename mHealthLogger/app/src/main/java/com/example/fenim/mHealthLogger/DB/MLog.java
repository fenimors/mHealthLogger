package com.example.fenim.mHealthLogger.DB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class MLog {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    public MLog(String firstName, String lastName, String note, long date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.note = note;
        this.date = date;

    }

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private  String lastName;

    @ColumnInfo(name = "note")
    private String note;

    @ColumnInfo(name = "date")
    private long date;



    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
