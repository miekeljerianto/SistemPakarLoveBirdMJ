package com.apps.miekeljerianto.belajarpakar.ui_admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.apps.miekeljerianto.belajarpakar.Index;
import com.apps.miekeljerianto.belajarpakar.R;

public class UiDashboardAdmin extends AppCompatActivity {

FrameLayout container;
BottomNavigationView bnv;

Button btn_reset;
AlertDialog ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_dashboard_admin);


        container = (FrameLayout)findViewById(R.id.framelayout_container);
        bnv = (BottomNavigationView)findViewById(R.id.bottom_dashboard);
         btn_reset = (Button)findViewById(R.id.btn_ui_reset);

        ImageView btn_profile = (ImageView)findViewById(R.id.ui_admin_profile);

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(UiDashboardAdmin.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_ui_login, null);




                mBuilder.setView(mView);
                AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();
            }
        });



        ImageView btn_keluar = (ImageView)findViewById(R.id.img_keluar);
        btn_keluar.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              AlertDialog builder = new AlertDialog.Builder(UiDashboardAdmin.this)
                                                      .setMessage("Apa kalian ingin Exit?")
                                                      .setCancelable(false)
                                                      .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                          public void onClick(DialogInterface dialog, int id) {
                                                              Intent i = new Intent(UiDashboardAdmin.this, Index.class);
                                                              startActivity(i);
                                                          }
                                                      })
                                                      .setNegativeButton("No", null)
                                                      .show();


                                          }
                                      });




        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_ui_1:
                        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id. framelayout_container, new UiFragmentPenyakit())
                                .addToBackStack("").commit();
                        return true;
                    case R.id.bottom_ui_2:
                        android.support.v4.app.FragmentManager fm2 = getSupportFragmentManager();
                        fm2.beginTransaction().replace(R.id.framelayout_container, new UiFragmentGejala())
                                .addToBackStack("").commit();
                        return true;
                    case R.id.bottom_ui_3:
                        android.support.v4.app.FragmentManager fm3 = getSupportFragmentManager();
                        fm3.beginTransaction().replace(R.id.framelayout_container, new UiFragmentRules())
                                .addToBackStack("").commit();


                        return true;
                }

                return true;
            }
        });
    }
}
