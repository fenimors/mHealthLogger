package com.example.fenim.mHealthLogger;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MLogViewHolder extends RecyclerView.ViewHolder{

    public TextView firstName;
    public TextView lastName;
    public TextView note;
    public TextView date;
    public TextView[] seekBar;



    public MLogViewHolder(View itemView, Context mContext) {

        super(itemView);

        firstName = itemView.findViewById(R.id.first_name);
        lastName = itemView.findViewById(R.id.last_name);
        note = itemView.findViewById(R.id.note);
        date = itemView.findViewById(R.id.date);

        seekBar = new TextView[5];
        String BId = "seekBar";
        for (int i = 0; i < Constant.SLIDER_COUNT; i++)
        {
            seekBar[i] = itemView.findViewById(
                    itemView.getResources().getIdentifier(BId+(i+1),
                            "id", mContext.getPackageName()));
        }
    }
}
