package com.aggreycliford.barapp.Adapters;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aggreycliford.barapp.Models.UserDataModel;
import com.aggreycliford.barapp.R;
import com.github.pavlospt.roundedletterview.RoundedLetterView;

import java.util.ArrayList;

/**
 * Created by nico on 2/7/2018.
 */

public class UsersAdapter extends ArrayAdapter<UserDataModel>{

    Context ctx;
    int res;
    ArrayList<UserDataModel> data;
    public UsersAdapter(Context context, int resource,ArrayList<UserDataModel>data) {
        super(context, resource);
        this.data = data;
        this.ctx = context;
        this.res = resource;

        Log.i("mydata",data.toString());
    }

    public UsersAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, ArrayList<UserDataModel> data) {
        super(context, resource, textViewResourceId, data);
        this.ctx = context;
        this.res = resource;
        this.data = data;
    }

    private static class ViewHolder {
        TextView txtName;
        TextView items;
        TextView costs;
        RoundedLetterView initialLetters;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.single_user_layout, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.user_name);
            viewHolder.items = (TextView) convertView.findViewById(R.id.items_total);
            viewHolder.costs = (TextView) convertView.findViewById(R.id.cost);
            viewHolder.initialLetters = (RoundedLetterView) convertView.findViewById(R.id.initials);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(data.get(position).getNames());
        viewHolder.items.setText(""+data.get(position).getItems());
        viewHolder.costs.setText("Tsh : "+data.get(position).getCosts());
        viewHolder.initialLetters.setTitleText("As");
        // Return the completed view to render on screen
        return convertView;
    }


    @Override
    public int getCount() {
        int i = super.getCount();
        //Toast.makeText(ctx, ""+i, Toast.LENGTH_SHORT).show();
        return i;
    }


//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        // Check if an existing view is being reused, otherwise inflate the view
//        ViewHolder viewHolder; // view lookup cache stored in tag
//
//        final View result;
//
//        if (convertView == null) {
//
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.single_user_layout, parent, false);
//            viewHolder.txtName = (TextView) convertView.findViewById(R.id.user_name);
//            viewHolder.txtType = (TextView) convertView.findViewById(R.id.items);
//            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.items_total);
//
//            result=convertView;
//
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//            result=convertView;
//        }
//
//        viewHolder.txtName.setText(names[position]);
//
//        // Return the completed view to render on screen
//        return convertView;
//    }
}
