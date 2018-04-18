package com.example.fenim.mHealthLogger.DB;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fenim.mHealthLogger.R;

public class ViewHolder2 extends RecyclerView.ViewHolder {

    public ViewHolder2(View itemView) {
        super(itemView);
        s_type = itemView.findViewById(R.id.sType);
        s_val = itemView.findViewById(R.id.sVal);

    }

    private TextView s_type;

    private TextView s_val;

    public TextView get_sType() {
        return s_type;
    }

    public void set_sType(TextView s_type) {
        this.s_type = s_type;
    }

    public TextView get_sVal() {
        return s_val;
    }

    public void set_sVal(TextView s_val) {
        this.s_val = s_val;
    }
}
