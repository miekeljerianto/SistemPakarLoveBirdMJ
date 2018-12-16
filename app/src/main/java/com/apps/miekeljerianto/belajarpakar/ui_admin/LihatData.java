package com.apps.miekeljerianto.belajarpakar.ui_admin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apps.miekeljerianto.belajarpakar.R;
import com.apps.miekeljerianto.belajarpakar.database.DatabasedbHelper;
import com.apps.miekeljerianto.belajarpakar.form.ListPenyakit;

public class LihatData extends AppCompatActivity {
public String [] daftar_penyakit;
DatabasedbHelper mydb;
protected Cursor cursor;
ListPenyakit ma;


TextView txt1, txt2,  tampil_getpenyebab, tampil_getsolusi;
TextView    txtTampilKd, tmplNmGjll, tmpPenyebab, tmpSolusi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        mydb = new DatabasedbHelper(this);

        txt1 = (TextView)findViewById(R.id.text_tampil1); txtTampilKd = (TextView)findViewById(R.id.tampil_kd); txtTampilKd.setText("Kode Penyakit");
        txt2 = (TextView)findViewById(R.id.text_tampil2); tmplNmGjll = (TextView)findViewById(R.id.tampil_nm_gjl); tmplNmGjll.setText("Nama Penyakit");

        tampil_getpenyebab = (TextView)findViewById(R.id.text_tampil_Getpenyebab); tmpPenyebab = (TextView)findViewById(R.id.text_penyebab);
        tampil_getsolusi = (TextView)findViewById(R.id.text_tampil_Getsolusi); tmpSolusi = (TextView)findViewById(R.id.text_solusi);


        //lihat data admin =>  dari taable penyakit ADMIN
        //UiFragmnetListPenyakit
        SQLiteDatabase db_penyakit = mydb.getReadableDatabase();
        cursor = db_penyakit.rawQuery("SELECT * FROM tbl_penyakit WHERE kode_penyakit= '" +
                getIntent().getStringExtra("kode_penyakit") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
             txt1.setText(cursor.getString(0).toString());
            txt2.setText(cursor.getString(1).toString());
            tampil_getpenyebab.setText(cursor.getString(2).toString()); tmpPenyebab.setText("Penyebab");
            tampil_getsolusi.setText(cursor.getString(3).toString()); tmpSolusi.setText("Solusi");
        }

// =================================== ListGejala.java
        SQLiteDatabase db_list_gejala = mydb.getReadableDatabase();
        cursor = db_list_gejala.rawQuery("SELECT * FROM tbl_gejala WHERE nama_gejala= '" +
                getIntent().getStringExtra("nama_gejala") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            txt1.setText(cursor.getString(0).toString());
            txt2.setText(cursor.getString(0).toString());
        }



// ==================================ListNama Penyakit
        //ListPenyakit => Untuk Melihat data
        //melihat data dari ListPenyakit
        SQLiteDatabase db_lihat_gejala = mydb.getReadableDatabase();
        cursor = db_lihat_gejala.rawQuery("SELECT * FROM tbl_penyakit WHERE nama_penyakit = '" +
                getIntent().getStringExtra("nama_penyakit") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            txt1.setText(cursor.getString(0).toString());
            txt2.setText(cursor.getString(1).toString());
            tampil_getpenyebab.setText(cursor.getString(2).toString()); tmpPenyebab.setText("Penyebab");
            tampil_getsolusi.setText(cursor.getString(3).toString()); tmpSolusi.setText("Solusi");
        }


// =========================>  UiFragmentGejala
        SQLiteDatabase db_gejala = mydb.getReadableDatabase();
        cursor = db_gejala.rawQuery("SELECT * FROM tbl_gejala WHERE nama_gejala = '" +
                getIntent().getStringExtra("nama_gejala") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            txt1.setText(cursor.getString(0).toString()); //menampilkan colum pertamam
            txt2.setText(cursor.getString(1).toString());
            tampil_getpenyebab.setVisibility(View.INVISIBLE);
            tampil_getsolusi.setVisibility(View.INVISIBLE);
        }


// ==============================>UiFragmentRules
        SQLiteDatabase db_rules = mydb.getReadableDatabase();
        cursor = db_rules.rawQuery("SELECT * FROM tbl_rules WHERE id_rules = '" +
                getIntent().getStringExtra("id_rules") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            txt1.setText(cursor.getString(0).toString());
            txt2.setText(cursor.getString(1).toString());
            tampil_getpenyebab.setText(cursor.getString(2).toString());
            tampil_getsolusi.setText(cursor.getString(3).toString());
        }
    }

}


