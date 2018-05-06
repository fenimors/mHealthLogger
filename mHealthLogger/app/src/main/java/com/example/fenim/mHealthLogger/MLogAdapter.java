package com.example.fenim.mHealthLogger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class MLogAdapter extends RecyclerView.Adapter<MLogViewHolder> {
    Context mContext;

    private final LayoutInflater mInflater;
    private List<MLog> mlogs;

    MLogAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public MLogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.mlog_row, parent, false);
        return new MLogViewHolder(itemView, mContext);
    }

    @Override
    public void onBindViewHolder(MLogViewHolder holder, int position) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        if (mlogs != null) {

            MLog current = mlogs.get(position);

            holder.firstName.setText(current.getFirstName());
            holder.lastName.setText(current.getLastName());
            holder.note.setText(current.getNote());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(current.getDate());

            holder.date.setText(formattedDate);


            for (int i = 0; i < Constant.SLIDER_COUNT; i++)
            {
                if( !sharedPref.getBoolean("key_slider" + (i+1), true))
                    holder.seekBar[i].setVisibility(View.GONE);

                holder.seekBar[i].setText(Constant.SLIDER_NAMES[i] + current.getSlider().charAt(i));
            }

        }
        else {
            holder.firstName.setText("NothingF");
            holder.lastName.setText("NothingL");
            holder.note.setText("NothingN");
            holder.date.setText("NothingD");
            for (int i = 0; i < Constant.SLIDER_COUNT; i++) {
                holder.seekBar[i].setText("NothingD");
            }
        }
    }

    void setMlogs(List<MLog> rmlogs) {
        mlogs = rmlogs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mlogs != null)
            return mlogs.size();
        else return 0;
    }

}

