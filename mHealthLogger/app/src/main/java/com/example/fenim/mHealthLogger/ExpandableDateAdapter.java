package com.example.fenim.mHealthLogger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;
/*
class MyChildItem {
    public long id;
}
*/
class MyGroupItem {
    public long id;
    List<MLog> children;
}

class ExpandableDateAdapter extends AbstractExpandableItemAdapter<ExpandableDateAdapter.MyGroupVH, ExpandableDateAdapter.MLogViewHolder> {

    Context mContext;
    List<MyGroupItem> items;
    private List<MLog> mlogs;

    private AbstractExpandableDataProvider mProvider;

    public ExpandableDateAdapter(AbstractExpandableDataProvider dataProvider) {
        mProvider = dataProvider;
        setHasStableIds(true); // this is required for expandable feature.
    }

    public static abstract class MyBaseViewHolder extends AbstractExpandableItemViewHolder {
        public FrameLayout mContainer;
        public TextView mTextView;

        public MyBaseViewHolder(View v) {
            super(v);
            mContainer = v.findViewById(R.id.container);
            mTextView = v.findViewById(android.R.id.text1);
        }
    }

    public class MLogViewHolder extends MyBaseViewHolder{

        public TextView firstName;
        public TextView lastName;
        public TextView note;
        public TextView date;
        public TextView[] seekBar;



        public MLogViewHolder(View itemView, Context mContext) {

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
        }
    }


    public static class MyGroupVH extends MyBaseViewHolder {
        public ExpandableItemIndicator mIndicator;

        public MyGroupVH(View v) {
            super(v);
            mIndicator = v.findViewById(R.id.indicator);
        }
    }

    //The corresponding RecyclerView.Adapter's method is getItemCount().
    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildCount(int groupPosition) {
       // return items.get(groupPosition).size();
        if (mlogs != null)
            return mlogs.size();
        else return 0;
    }

    //The corresponding RecyclerView.Adapter's method is getItemId().
    @Override
    public long getGroupId(int groupPosition) {
        return items.get(groupPosition).id;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return items.get(groupPosition).children.get(childPosition).getId();
    }

    //The corresponding RecyclerView.Adapter's method is onCreateViewHolder().
    @Override
    public MyGroupVH onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_item, parent, false);
        return new MyGroupVH(v);
    }

    @Override
    public MLogViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mlog_row, parent, false);
       // View itemView = mInflater.inflate(R.layout.mlog_row, parent, false);
        return new MLogViewHolder(v, mContext);
    }

    //The corresponding RecyclerView.Adapter's method is onBindViewHolder().
    @Override
    public void onBindGroupViewHolder(MyGroupVH holder, int groupPosition, int viewType) {
        MyGroupItem item = items.get(groupPosition);
        holder.mTextView.setText("TEST");
    }

    @Override
    public void onBindChildViewHolder(MLogViewHolder holder, int groupPosition, int childPosition, int viewType) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        if (mlogs != null) {

            MLog current = items.get(groupPosition).children.get(childPosition);
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
       // holder.text.setText(...);
    }

    //Just returns true for make group items automatically respond to click events.
    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupVH holder, int groupPosition, int x, int y, boolean expand) {
        return true;
    }
}