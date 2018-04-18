package com.example.fenim.mHealthLogger.DB;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fenim.mHealthLogger.R;

import java.text.SimpleDateFormat;
import java.util.List;

class MLogAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<MLog> mlogs;

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

