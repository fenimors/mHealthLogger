package com.finepointmobile.famouspeople;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateUser extends AppCompatActivity{

    private static final String TAG = "CreateUser";

    EditText firstname;
    EditText lastname;
    EditText email;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.create_user);

         firstname = findViewById(R.id.first_name);
         lastname = findViewById(R.id.last_name);
         email = findViewById(R.id.email);
         button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Save to database
                Log.d(TAG, "onClick: firstName: " + firstname.getText().toString());
            }
        });
    }
}
