package com.aggreycliford.barapp.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aggreycliford.barapp.Models.StockDataModel;
import com.aggreycliford.barapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nico on 2/8/2018.
 */

public class StockAdapter extends ArrayAdapter<StockDataModel> {

    ArrayList<StockDataModel>data;
    Context ctx;
    int res;


    public StockAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId,  ArrayList<StockDataModel> data) {
        super(context, resource, textViewResourceId, data);
        this.data = data;
        this.ctx = context;
        this.res = resource;
    }

    private static class ViewHolder{
        TextView txtname;
        TextView txtitems;
        CircleImageView imglogo;
        ImageView edit;
        ImageView delete;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder viewHolder;
        View result;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(res,parent,false);
            viewHolder.txtname = (TextView) convertView.findViewById(R.id.brand_name);
            viewHolder.txtitems = (TextView) convertView.findViewById(R.id.items_total);
            viewHolder.imglogo = (CircleImageView) convertView.findViewById(R.id.brandlogo);
            viewHolder.edit = (ImageView) convertView.findViewById(R.id.edit);
            viewHolder.delete = (ImageView) convertView.findViewById(R.id.delete);

            result = convertView;
            result.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.imglogo.setImageResource(R.drawable.image_colored);
//        viewHolder.imglogo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        viewHolder.txtname.setText(data.get(position).getName());
        viewHolder.txtitems.setText("Total items  : "+data.get(position).getItems());

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog();
            }
        });
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItemsDialog();
            }
        });
        return convertView;
    }

    private void deleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(false);
        builder.setMessage("Are you sure to delete this ?");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ctx,"deleted succefuly", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void updateItemsDialog(){
        AlertDialog.Builder dBuilder = new AlertDialog.Builder(ctx);
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View dialogview = inflater.inflate(R.layout.stock_edit_dialog,null);
        final EditText brandname = (EditText) dialogview.findViewById(R.id.brand_name_edit);
        final EditText itemnumber = (EditText) dialogview.findViewById(R.id.brand_items_edit);

        dBuilder.setCancelable(false);
        final AlertDialog dialog = new AlertDialog.Builder(ctx)
                .setView(dialogview)
                .setPositiveButton("Save", null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null)
                .create();


        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                Button buttonC = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String name = brandname.getText().toString();
                        String num = itemnumber.getText().toString();
                        if((name.isEmpty() || name.length() ==0 ) || (num.isEmpty() || num.length() == 0)){
                            Toast.makeText(ctx, "please fill brand name and number of items", Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            Toast.makeText(ctx, "updated", Toast.LENGTH_SHORT).show();
                            brandname.setText("");
                            itemnumber.setText("");
                            dialog.dismiss();
                        }
                    }
                });


                buttonC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        dialog.show();
    }

}
