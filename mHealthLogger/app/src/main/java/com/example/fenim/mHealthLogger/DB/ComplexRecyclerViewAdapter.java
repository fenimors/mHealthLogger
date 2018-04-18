package com.example.fenim.mHealthLogger.DB;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fenim.mHealthLogger.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    // The items to display in your RecyclerView
    private List<Object> items;

    private final int MLOG = 0, SLIDERS = 1;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ComplexRecyclerViewAdapter(List<Object> items) {
        this.items = items;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof MLog) {
            return MLOG;
        } else if (items.get(position) instanceof Sliders) {
            return SLIDERS;
        }
        return -1;
    }

    /**
     * This method creates different RecyclerView.ViewHolder objects based on the item view type.\
     *
     * @param viewGroup ViewGroup container for the item
     * @param viewType type of view to be inflated
     * @return viewHolder to be inflated
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case MLOG:
                View v1 = inflater.inflate(R.layout.mlog_row, viewGroup, false);
                viewHolder = new ViewHolder(v1);
                break;
            case SLIDERS:
                View v2 = inflater.inflate(R.layout.slider_row, viewGroup, false);
                viewHolder = new ViewHolder2(v2);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                break;
        }
        return viewHolder;
    }

    /**
     * This method internally calls onBindViewHolder(ViewHolder, int) to update the
     * RecyclerView.ViewHolder contents with the item at the given position
     * and also sets up some private fields to be used by RecyclerView.
     *
     * @param viewHolder The type of RecyclerView.ViewHolder to populate
     * @param position Item position in the viewgroup.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case MLOG:
                ViewHolder vh1 = (ViewHolder) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case SLIDERS:
                ViewHolder2 vh2 = (ViewHolder2) viewHolder;
                configureViewHolder2(vh2, position);
                break;
            default:
                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configureDefaultViewHolder(RecyclerViewSimpleTextViewHolder vh, int position) {
        vh.getLabel().setText((CharSequence) items.get(position).toString());
    }

    private void configureViewHolder1(ViewHolder vh1, int position) {
        MLog mlog = (MLog) items.get(position);
        if (mlog != null) {
            vh1.getFirstName().setText(mlog.getFirstName());
            vh1.getLastName().setText(mlog.getLastName());
            vh1.getNote().setText(mlog.getNote());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(mlog.getDate());
            vh1.getDate().setText(formattedDate);

        }
    }

    private void configureViewHolder2(ViewHolder2 vh2, int position) {
        Sliders sliders = (Sliders) items.get(position);
        if (sliders != null) {
            vh2.get_sType().setText(sliders.getS_type());
            vh2.get_sVal().setText(Integer.toString(sliders.getS_val()));

        }
    }
}
