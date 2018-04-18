package com.example.fenim.uilearn2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView firstName;
    public TextView lastName;
    public TextView note;
    public TextView date;

    public ViewHolder(View itemView) {
        super(itemView);
        firstName = itemView.findViewById(R.id.first_name);
        lastName = itemView.findViewById(R.id.last_name);
        note = itemView.findViewById(R.id.note);
        date = itemView.findViewById(R.id.date);
    }
}