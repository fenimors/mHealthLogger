package com.example.fenim.mHealthLogger.DB;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fenim.mHealthLogger.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
        super(itemView);
        firstName = itemView.findViewById(R.id.first_name);
        lastName = itemView.findViewById(R.id.last_name);
        note = itemView.findViewById(R.id.note);
        date = itemView.findViewById(R.id.date);
    }

    private TextView firstName;
    private TextView lastName;
    private TextView note;
    private TextView date;

    public TextView getFirstName() {
        return firstName;
    }

    public void setFirstName(TextView firstName) {
        this.firstName = firstName;
    }

    public TextView getLastName() {
        return lastName;
    }

    public void setLastName(TextView lastName) {
        this.lastName = lastName;
    }

    public TextView getNote() {
        return note;
    }

    public void setNote(TextView note) {
        this.note = note;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }
}