package com.example.fenim.mHealthLogger;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class MLogAdapter extends RecyclerView.Adapter<MLogAdapter.MLogViewHolder> {

    class MLogViewHolder extends RecyclerView.ViewHolder {
        //private final TextView MLogItemView;
        private final TextView firstName;
        private final TextView lastName;
        private final TextView note;
        private final TextView date;
        private final TextView seekBar1;
        private final TextView seekBar2;
        private final TextView seekBar3;
        private final TextView seekBar4;
       // private final TextView sliderArray;


        private MLogViewHolder(View itemView) {
            super(itemView);
//            MLogItemView = itemView.findViewById(R.id.textView);
            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);
            note = itemView.findViewById(R.id.note);
            date = itemView.findViewById(R.id.date);
            seekBar1 = itemView.findViewById(R.id.seekBar1);
            seekBar2 = itemView.findViewById(R.id.seekBar2);
            seekBar3 = itemView.findViewById(R.id.seekBar3);
            seekBar4 = itemView.findViewById(R.id.seekBar4);
            //sliderArray = itemView.findViewById(R.id.sliderArray);
        }
    }

    private final LayoutInflater mInflater;
    private List<MLog> mlogs;

    MLogAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MLogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.mlog_row, parent, false);
        return new MLogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MLogViewHolder holder, int position) {
        if (mlogs != null) {
            MLog current = mlogs.get(position);
           // holder.MLogItemView.setText(current.getFirstName());    //probably will have to expand

            holder.firstName.setText(current.getFirstName());
            holder.lastName.setText(current.getLastName());
            holder.note.setText(current.getNote());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(current.getDate());
            holder.date.setText(formattedDate);

            holder.seekBar1.setVisibility(View.GONE); //TODO: Basically have a global constant for each of theese then it can all be set in settings
            holder.seekBar1.setText("Thing1: " + current.getSlider().charAt(0));
            holder.seekBar2.setText("Thing2: " + current.getSlider().charAt(1));
            holder.seekBar3.setText("Thing3: " + current.getSlider().charAt(2));
            holder.seekBar4.setText("Thing4: " + current.getSlider().charAt(3));
        }
        else {
            holder.firstName.setText("NothingF");
            holder.lastName.setText("NothingL");
            holder.note.setText("NothingN");
            holder.date.setText("NothingD");
            holder.seekBar1.setText("NothingD");
            holder.seekBar2.setText("NothingD");
            holder.seekBar3.setText("NothingD");
            holder.seekBar4.setText("NothingD");
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
