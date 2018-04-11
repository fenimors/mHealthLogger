package com.example.fenim.uilearn2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {

    List<Log> logs;

    public LogAdapter(List<Log> logs) {
        this.logs = logs;
    }

    @Override
    public LogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LogAdapter.ViewHolder holder, int position) {
        holder.firstName.setText(logs.get(position).getFirstName());
        holder.lastName.setText(logs.get(position).getLastName());
        holder.note.setText(logs.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return logs.size();
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

