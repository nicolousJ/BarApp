package com.aggreycliford.barapp.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aggreycliford.barapp.Models.UserDataModel;
import com.aggreycliford.barapp.R;
import com.github.pavlospt.roundedletterview.RoundedLetterView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by nico on 2/7/2018.
 */

public class UsersAdapter extends ArrayAdapter<UserDataModel>{

    String lastColor = "#E91E63";
    String[] colors ={"#E91E63","#F44336","#4A148C","#880E4F","#2196F3","#00BCD4","#1B5E20"};
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
        RelativeLayout edit;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        String color =null;
        color = getColor();
        lastColor = color;
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
            viewHolder.initialLetters.setBackgroundColor(Color.parseColor(color));
            viewHolder.edit = (RelativeLayout) convertView.findViewById(R.id.edit_image);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        String user = data.get(position).getNames();
        viewHolder.txtName.setText(user);
        viewHolder.items.setText(""+data.get(position).getItems());
        viewHolder.costs.setText("Tsh : "+data.get(position).getCosts());
        viewHolder.initialLetters.setTitleText(user.substring(0,1));
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdateUeser();
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }


    @Override
    public int getCount() {
        int i = super.getCount();
        //Toast.makeText(ctx, ""+i, Toast.LENGTH_SHORT).show();
        return i;
    }

    private void DialogUpdateUeser(){

        AlertDialog.Builder dBuilder = new AlertDialog.Builder(ctx);
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View dialogview = inflater.inflate(R.layout.edit_user_dialog,null);
        final EditText username = (EditText) dialogview.findViewById(R.id.name_edit);
        final AlertDialog dialog = new AlertDialog.Builder(ctx)
                .setView(dialogview)
                .setPositiveButton("Save", null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        String name = username.getText().toString();
                        if (name.isEmpty() || name.length() ==0){
                            Toast.makeText(ctx, "Fill the user name", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //Dismiss once everything is OK.
                        dialog.dismiss();
                    }
                });
            }
        });
        dialog.show();
    }

    private String getColor() {
        String color ="";
        Random r = new Random();
        int position = r.nextInt(6 - 0) + 1;
        color = colors[position];
        if(color.equals(lastColor)){
            color =  getColor();
        }
        return color;
    }
}
