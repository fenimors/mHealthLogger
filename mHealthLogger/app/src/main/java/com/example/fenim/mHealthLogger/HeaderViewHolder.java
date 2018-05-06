package com.example.fenim.mHealthLogger;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class HeaderViewHolder extends com.xwray.groupie.ViewHolder{

    public TextView title;



    public HeaderViewHolder(View itemView) {

        super(itemView);

        title = itemView.findViewById(R.id.text);

    }
}
