package com.example.fenim.roomdatatesting;

/**
 * Created by fenim on 2/28/2018.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.fenim.roomdatatesting.Note;

import java.util.List;



@Dao
public interface NoteDao {

    public static final String TABLE_NAME_NOTE ="notes";
    public static final String DB_NAME ="notesdb.db";
    @Query("SELECT * FROM user "+ TABLE_NAME_NOTE)
    List<Note> getAll();


    /*
    * Insert the object in database
    * @param note, object to be inserted
    */
    @Insert
    void insert(Note note);

    /*
    * update the object in database
    * @param note, object to be updated
    */
    @Update
    void update(Note repos);

    /*
    * delete the object from database
    * @param note, object to be deleted
    */
    @Delete
    void delete(Note note);

    /*
    * delete list of objects from database
    * @param note, array of objects to be deleted
    */
    @Delete
    void delete(Note... note);      // Note... is varargs, here note is an array

}