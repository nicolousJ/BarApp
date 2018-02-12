package com.aggreycliford.barapp.Fragments;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.aggreycliford.barapp.R;

public class Add_Items extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    EditText brandName;
    EditText totalItems;
    EditText price;
    EditText SKU;
    Button  btn_normalForm_save;


    EditText spirit_name;
    EditText s_total;
    EditText s_price;
    EditText no_of_tots ;
    EditText price_of_tot;
    Button save_spirit_btn;

    LinearLayout spirit,general;
    String[]categories ={"Wine","Bear","Sprit","Soda","Juice","Fresh water"};

    private OnFragmentInteractionListener mListener;

    public Add_Items() {

    }

    public static Add_Items newInstance(String param1, String param2) {
        Add_Items fragment = new Add_Items();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add__items, container, false);
        final Spinner category =  v.findViewById(R.id.categories);
        LinearLayout newCat = v.findViewById(R.id.newcat);
         general = v.findViewById(R.id.general_layout);
         spirit = v.findViewById(R.id.spirit_layout);
         general.setVisibility(View.VISIBLE);
         spirit.setVisibility(View.GONE);

        // capturing data when dealing with the normal form of items
         brandName = v.findViewById(R.id.brand_name);
         totalItems = v.findViewById(R.id.items_total);
         price = v.findViewById(R.id.items_price);
         SKU = v.findViewById(R.id.items_sku);
         btn_normalForm_save = v.findViewById(R.id.general_save_btn);

        // capturing data when dealing with the spirit kind of items that needs extra information on their collection

        spirit_name = v.findViewById(R.id.brand_name_for_S);
        s_total = v.findViewById(R.id.items_total);
        s_price = v.findViewById(R.id.full_items_price);
        no_of_tots = v.findViewById(R.id.No_of_tots);
        price_of_tot = v.findViewById(R.id.price_of_tot_per_botle);
        save_spirit_btn = v.findViewById(R.id.spirit_save_btn);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,categories);
        category.setAdapter(adapter);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(category.getSelectedItem().toString().equals("Sprit")){
                    general.setVisibility(View.GONE);
                    spirit.setVisibility(View.VISIBLE);
                }else{
                    general.setVisibility(View.VISIBLE);
                    spirit.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        newCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInsertcategoryDialog();
            }
        });
        return v;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    private void showInsertcategoryDialog(){

        AlertDialog.Builder dBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        View dialogview = inflater.inflate(R.layout.new_category_dialog,null);
        final EditText catname = (EditText) dialogview.findViewById(R.id.cat_edit);
        final AlertDialog dialog = new AlertDialog.Builder(getContext())
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
                        String name = catname.getText().toString();
                        if (name.isEmpty() || name.length() ==0){
                            Toast.makeText(getContext(), "Fill the category name", Toast.LENGTH_SHORT).show();
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
}
