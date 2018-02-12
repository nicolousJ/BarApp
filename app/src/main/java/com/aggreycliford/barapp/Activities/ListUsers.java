package com.aggreycliford.barapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.aggreycliford.barapp.Adapters.UsersAdapter;
import com.aggreycliford.barapp.Models.UserDataModel;
import com.aggreycliford.barapp.R;

import java.util.ArrayList;

public class ListUsers extends AppCompatActivity {

    String[] names = {"nicolous","Naamini","Mashindano","peter","stella","Luke","Fabian","Jaja","Victor"};
    int[] costs ={0,12000,45000,1000,8000,94000,0,7600,13000};
    int[]items = {0,2,4,1,2,6,0,3,2};

    ListView lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lists = (ListView) findViewById(R.id.list);
        ArrayList<UserDataModel>data = new ArrayList<>();
        for(int i=0; i< names.length; i++){
            UserDataModel model = new UserDataModel();
            model.setNames(names[i]);
            model.setCosts(costs[i]);
            model.setItems(items[i]);
            data.add(model);
        }
        UsersAdapter adapter = new UsersAdapter(ListUsers.this,R.layout.single_user_layout,data);
        UsersAdapter adapter1 = new UsersAdapter(ListUsers.this,R.layout.single_user_layout,R.id.user_name,data);
        Log.i("myadapter",""+adapter.getCount());
        lists.setAdapter(adapter1);
//
//        AccountSettingAdapter adapter = new AccountSettingAdapter(AccountSettings.this,R.layout.account_setting_item,settings);
//        settings_list.setAdapter(adapter);
    }

}
