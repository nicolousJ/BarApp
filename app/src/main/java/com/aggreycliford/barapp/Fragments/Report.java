package com.aggreycliford.barapp.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aggreycliford.barapp.Models.CategoryModel;
import com.aggreycliford.barapp.R;
import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Report.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Report#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Report extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ExpandingList expandingList;
    ExpandingItem item;
    TextView Title;
    String[]category_names = {"Beer","Juice","Water"};
    float[]catprice ={5500,122000,89500};
    String[]beers ={"Balimi","Konyagi","Serengeti"};
    float[]beerprices ={1200,2300,7000};
    String[]juices ={"Azam","Mo-juice","Mo-juice2"};
    float[]juiceprices ={1500,5300,8000};
    String[]water ={"Uhai","Kilimanjaro","Afya"};
    float[]waterprices ={1600,6900,17000};

    ArrayList<CategoryModel>data;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Report() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Report.
     */
    // TODO: Rename and change types and number of parameters
    public static Report newInstance(String param1, String param2) {
        Report fragment = new Report();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_report, container, false);
        expandingList = v.findViewById(R.id.expanding_list_main);
        item = expandingList.createNewItem(R.layout.expanding_layout);
         Title  =  item.findViewById(R.id.title);


        data = new ArrayList<>();
        for (int i=0; i<category_names.length;i++){
            CategoryModel model = new CategoryModel();
            model.setCat_name(category_names[i]);
            model.setPrice(catprice[i]);
            if(i == 0){
                model.setSubctas(beers);
                model.setSubprices(beerprices);
                data.add(model);

            }
            if(i==1){
                model.setSubctas(juices);
                model.setSubprices(juiceprices);
                data.add(model);
            }
            if(i==2){
                model.setSubctas(water);
                model.setSubprices(waterprices);
                data.add(model);

            }
        }

         for (int i =0; i< category_names.length; i++){

             Title.setText(category_names[i]);
                 if(i == 0){

                     for(int j= 0; j<beers.length; j++) {
                         item.createSubItems(beers.length);
                         View subItemZero = item.getSubItemView(j);
                         ((TextView) subItemZero.findViewById(R.id.sub_title)).setText(beers[j]);
                         ((TextView) subItemZero.findViewById(R.id.sub_amount)).setText(""+beerprices[j]);
                     }
                 }


                 item.setIndicatorColorRes(R.color.colorPrimary);
                 item.setIndicatorIconRes(R.drawable.ic_create_black_24dp);

         }



        //get a sub item View


        return  v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
