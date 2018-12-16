package com.apps.miekeljerianto.belajarpakar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.apps.miekeljerianto.belajarpakar.form.FormDiagnosa;
import com.apps.miekeljerianto.belajarpakar.form.ListGejala;
import com.apps.miekeljerianto.belajarpakar.form.ListPenyakit;

public class Index extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button btn1, btn2, btn3, btn4;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        btn1 = (Button) findViewById(R.id.bt1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1();
            }
        });

        btn2 = (Button) findViewById(R.id.bt2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2();
            }
        });

        btn3 = (Button) findViewById(R.id.bt3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button3();
            }
        });

        btn4 = (Button) findViewById(R.id.bt4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button4();
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    } */

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(getBaseContext(), "Tekan kembali untuk Keluar", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //action
        /*if (id == R.id.action_settings) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plan");
            i.putExtra(Intent.EXTRA_TEXT, "Tugas Akhir Dokter Burung");
            startActivity(i);
            return true;
        }
        if (id == R.id.action_about){


        } */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_diagnosa) {
            Intent diagnosa = new Intent(this, FormDiagnosa.class);
            startActivity(diagnosa);
            // Handle the camera action
        } else if (id == R.id.nav_list_gejala) {
            android.support.v4.app.FragmentManager fm_penyakit = getSupportFragmentManager();
            fm_penyakit.beginTransaction().replace(R.id.container, new ListPenyakit())
                    .addToBackStack("").commit();

        }else if (id == R.id.action_about){
            Intent tentang = new Intent(this, TentangActivity.class);
            startActivity(tentang);

        }else if (id == R.id.nav_info_lb){
            Intent info = new Intent(this, InfoActivity.class);
            startActivity(info);

        }else if (id == R.id.nav_list_penyakit) {
            android.support.v4.app.FragmentManager fm_gejala = getSupportFragmentManager();
            fm_gejala.beginTransaction().replace(R.id.container, new ListGejala())
                    .addToBackStack("").commit();

        }else if (id == R.id.action_share) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plan");
            i.putExtra(Intent.EXTRA_TEXT, "Sistem Pakar Penyakit Pada Burung Lovebird");
            startActivity(i);
            return true;

        } else if (id == R.id.nav_exit) {
            finish();


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void button1(){
        Intent i1 = new Intent(this, FormDiagnosa.class);
        startActivity(i1);
    }

    public void button2(){
        Intent i2 = new Intent(this, InfoActivity.class);
        startActivity(i2);
    }

    public void button3(){
        android.support.v4.app.FragmentManager fm_gejala = getSupportFragmentManager();
        fm_gejala.beginTransaction().replace(R.id.container, new ListGejala())
                .addToBackStack("").commit();
    }

    public void button4(){
        android.support.v4.app.FragmentManager fm_penyakit = getSupportFragmentManager();
        fm_penyakit.beginTransaction().replace(R.id.container, new ListPenyakit())
                .addToBackStack("").commit();
    }

}
