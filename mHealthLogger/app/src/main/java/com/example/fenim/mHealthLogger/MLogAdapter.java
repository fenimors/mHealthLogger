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

public class MLogAdapter extends RecyclerView.Adapter<MLogAdapter.MLogViewHolder> {
    Context mContext;
    class MLogViewHolder extends RecyclerView.ViewHolder {
        //private final TextView MLogItemView;
        private final TextView firstName;
        private final TextView lastName;
        private final TextView note;
        private final TextView date;
        private final TextView[] seekBar;
     //  private final TextView seekBar2;
      //  private final TextView seekBar3;
      //  private final TextView seekBar4;


        private MLogViewHolder(View itemView) {
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
            //seekBar[0] = itemView.findViewById(R.id.seekBar1);
           // seekBar2 = itemView.findViewById(R.id.seekBar2);
           // seekBar3 = itemView.findViewById(R.id.seekBar3);
          //  seekBar4 = itemView.findViewById(R.id.seekBar4);
            //sliderArray = itemView.findViewById(R.id.sliderArray);
        }
    }

    private final LayoutInflater mInflater;
    private List<MLog> mlogs;

    MLogAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public MLogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.mlog_row, parent, false);
        return new MLogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MLogViewHolder holder, int position) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        if (mlogs != null) {

        /*    if( !sharedPref.getBoolean("key_slider1", true))
                holder.seekBar[0].setVisibility(View.GONE);
            if( !sharedPref.getBoolean("key_slider2", true))
                holder.seekBar2.setVisibility(View.GONE);
            if( !sharedPref.getBoolean("key_slider3", true))
                holder.seekBar3.setVisibility(View.GONE);
            if( !sharedPref.getBoolean("key_slider4", true))
                holder.seekBar4.setVisibility(View.GONE);*/


            MLog current = mlogs.get(position);

            holder.firstName.setText(current.getFirstName());
            holder.lastName.setText(current.getLastName());
            holder.note.setText(current.getNote());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(current.getDate());
            holder.date.setText(formattedDate);


          //  holder.seekBar1.setVisibility(View.GONE); //TODO: Basically have a global constant for each of theese then it can all be set in settings

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



/*

    public MLogAdapter(List<MLog> mlogs) {
        this.mlogs = mlogs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mlog_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.firstName.setText(mlogs.get(position).getFirstName());
        //holder.lastName.setText(mlogs.get(position).getLastName());
        //holder.note.setText(mlogs.get(position).getNote());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(mlogs.get(position).getDate());
        //holder.date.setText(formattedDate);

    }

    @Override
    public int getItemCount() {
        return mlogs.size();
    }

}
*/
