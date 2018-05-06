package com.example.fenim.mHealthLogger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.View;

import com.xwray.groupie.Item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MlogItem extends Item<MLogViewHolder> {

    private String firstName;
    private String lastName;
    private String note;
    private Long date;
    private String slider;
    Context mContext;

    public MlogItem(MLog thing, Context context) {
        mContext = context;
        this.firstName = thing.getFirstName();
        this.lastName = thing.getLastName();
        this.note = thing.getNote();
        this.date = thing.getDate();
        this.slider = thing.getSlider();

    }

    @Override
    public MLogViewHolder createViewHolder(@NonNull View itemView) {
        return new MLogViewHolder(itemView, mContext);
    }

    @Override
    public void bind(MLogViewHolder viewHolder, int position) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        viewHolder.firstName.setText(firstName);
        viewHolder.lastName.setText(lastName);
        viewHolder.note.setText(note);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(date);
        viewHolder.date.setText(formattedDate);
        for (int i = 0; i < Constant.SLIDER_COUNT - 1; i++)
        {
            if( !sharedPref.getBoolean("key_slider" + (i+1), true))
                viewHolder.seekBar[i].setVisibility(View.GONE);

            viewHolder.seekBar[i].setText(Constant.SLIDER_NAMES[i] + slider.charAt(i));
        }
    }
    @Override
    public int getLayout() {
        return R.layout.mlog_row;
    }
}
