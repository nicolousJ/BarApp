package com.aggreycliford.barapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aggreycliford.barapp.R;

public class SplashScreen extends AppCompatActivity {

    private EditText pincode;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        initialize();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passcode = pincode.getText().toString();
                if(TextUtils.isEmpty(passcode) || passcode.length() == 0){
                    Toast.makeText(SplashScreen.this, "Jaza Pin code", Toast.LENGTH_SHORT).show();
                }
                if(passcode.equals("admin")){
                    startActivity(new Intent(SplashScreen.this,AdminHome.class));
                }
                if(passcode.equals("seller")){

                }
                if(passcode.equals("user")){

                }


            }
        });
    }

    private void initialize(){
        pincode = (EditText) findViewById(R.id.passcode);
        login = (Button) findViewById(R.id.login);
    }
}
