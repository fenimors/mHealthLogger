package com.example.fenim.uilearn2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class MLogAdapter extends RecyclerView.Adapter<MLogAdapter.ViewHolder> {

    List<MLog> mlogs;

    public MLogAdapter(List<MLog> mlogs) {
        this.mlogs = mlogs;
    }

    @Override
    public MLogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mlog_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MLogAdapter.ViewHolder holder, int position) {
        holder.firstName.setText(mlogs.get(position).getFirstName());
        holder.lastName.setText(mlogs.get(position).getLastName());
        holder.note.setText(mlogs.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return mlogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName;
        public TextView lastName;
        public TextView note;
        public ViewHolder(View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);
            note = itemView.findViewById(R.id.note);
        }
    }
}

