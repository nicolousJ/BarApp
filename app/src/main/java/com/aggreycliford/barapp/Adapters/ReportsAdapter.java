package com.aggreycliford.barapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aggreycliford.barapp.R;
import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;

/**
 * Created by nico on 2/12/2018.
 */

public class ReportsAdapter extends ArrayAdapter {
    Context ctx;
    ExpandingList expandingList;
    ExpandingItem item;
    TextView Title;

    public ReportsAdapter(@NonNull Context context, int resource, int textViewResourceId,ExpandingList expandingList) {
        super(context, resource, textViewResourceId);
        this.ctx =context;
        this.expandingList = expandingList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        item = expandingList.createNewItem(R.layout.expanding_layout);
        Title  =  item.findViewById(R.id.title);
            Title.setText("Maji");
            item.createSubItems(5);
            View subItemZero = item.getSubItemView(position);
            ((TextView) subItemZero.findViewById(R.id.sub_title)).setText("Cool");

        return item;
    }
}
