package com.example.fenim.roomdatatesting;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.fenim.roomdatatesting.NoteDatabase;
import com.example.fenim.roomdatatesting.model.Note;
import com.example.fenim.roomdatatesting.NoteDao;

import java.lang.ref.WeakReference;

public class AddNoteActivity extends AppCompatActivity {

    private TextInputEditText et_title,et_content;
    private NoteDatabase noteDatabase;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
        noteDatabase = NoteDatabase.getInstance(AddNoteActivity.this);
        Button button = findViewById(R.id.but_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // fetch data and create note object
                note = new Note(1, et_content.getText().toString(), et_title.getText().toString());

                // create worker thread to insert data into database
                new InsertTask(AddNoteActivity.this,note).execute();
            }
        });
    }


    private void setResult(Note note, int flag){
        setResult(flag,new Intent.putExtra("note",note));
        finish();
    }

    private static class InsertTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<AddNoteActivity> activityReference;
        private Note note;

        // only retain a weak reference to the activity
        InsertTask(AddNoteActivity context, Note note) {
            activityReference = new WeakReference<>(context);
            this.note = note;
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected Boolean doInBackground(Void... objs) {
            activityReference.get().noteDatabase.getNoteDao().insert(note);
            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool){
                activityReference.get().setResult(note,1);
            }
        }
    }

}