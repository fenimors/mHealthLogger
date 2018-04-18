package com.example.fenim.uilearn2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

class MLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //List<MLog> mlogs;
    //List<Sliders> sliders;

    private static final int VIEW_TYPE_MLOG = 0;
    private static final int VIEW_TYPE_SLIDER= 1;

    /*
    public MLogAdapter(List<MLog> mlogs, List<Sliders> sliders) {
        this.mlogs = mlogs;
        this.sliders = sliders;
    }*/
    private List<Object> items = new ArrayList<>();



    private static class MLogViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName;
        public TextView lastName;
        public TextView note;
        public TextView date;
        public TextView sliders;

        public MLogViewHolder(View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);
            note = itemView.findViewById(R.id.note);
            date = itemView.findViewById(R.id.date);
            sliders = itemView.findViewById(R.id.sliders);
        }


        public void bind(MLog mlogs) {
            // display your object

            firstName.setText(mlogs.getFirstName());
            lastName.setText(mlogs.getLastName());
            note.setText(mlogs.getNote());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(mlogs.getDate());
            date.setText(formattedDate);
        }
    }

    private static class SliderViewHolder extends RecyclerView.ViewHolder {
        public TextView sType;
        public TextView sVal;

        public SliderViewHolder(View itemView) {
            super(itemView);
            sType = itemView.findViewById(R.id.sType);
            sVal = itemView.findViewById(R.id.sVal);
        }


        public void bind(Sliders sliders) {
            // display your object
        }
    }






    public MLogAdapter(List<Object> items) {
        this.items.addAll(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mlog_row, parent, false);
        return new ViewHolder(view);
        */
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_MLOG) {
            layoutInflater.inflate(R.layout.mlog_row, parent, false);

            return new MLogViewHolder(view);
        } else {
            layoutInflater.inflate(R.layout.slider_row, parent, false);

            return new SliderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {


        Object item = items.get(position);

        if (viewHolder instanceof MLogViewHolder) {
            ((MLogViewHolder) viewHolder).bind((MLog) item);
        } else {
            ((SliderViewHolder) viewHolder).bind((Sliders) item);
        }



    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof MLog) {
            return VIEW_TYPE_MLOG;
        }
        else if (items.get(position) instanceof Sliders) {
            return VIEW_TYPE_SLIDER;
        }
        return 9;
    }

    @Override
    public int getItemCount() {
        return mlogs.size();
    }

}

