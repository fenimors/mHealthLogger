package com.example.fenim.mHealthLogger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.View;

import com.xwray.groupie.Item;


public class HeaderItem extends Item<HeaderViewHolder> {

    private String title;

    public HeaderItem(String title) {
        this.title = title;

    }

    @Override
    public HeaderViewHolder createViewHolder(View itemView) {
        return new HeaderViewHolder(itemView);
    }

    @Override
    public void bind(HeaderViewHolder viewHolder, int position) {
        viewHolder.title.setText(title);
    }
    @Override
    public int getLayout() {
        return R.layout.item_card;
    }
}
