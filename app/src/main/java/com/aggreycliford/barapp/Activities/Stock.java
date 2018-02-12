package com.aggreycliford.barapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.aggreycliford.barapp.Adapters.StockAdapter;
import com.aggreycliford.barapp.Models.StockDataModel;
import com.aggreycliford.barapp.R;

import java.util.ArrayList;

public class Stock extends AppCompatActivity {

    String[]names = {"Balimi","Konyagi","Serengeti","Safari","Dodoma","Stanna"};
    int[]items ={50,22,48,70,80,10};
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list = (ListView) findViewById(R.id.list);
        ArrayList<StockDataModel> data = new ArrayList<>();
        for(int i=0;i<names.length;i++){
            StockDataModel model = new StockDataModel();
            model.setName(names[i]);
            model.setItems(items[i]);
            data.add(model);
        }

        StockAdapter adapter = new StockAdapter(Stock.this,R.layout.single_stock_item,R.id.brand_name,data);
        list.setAdapter(adapter);
    }

}
