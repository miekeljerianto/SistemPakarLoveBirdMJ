package com.apps.miekeljerianto.belajarpakar.form;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.apps.miekeljerianto.belajarpakar.R;
import com.apps.miekeljerianto.belajarpakar.database.DatabasedbHelper;

public class FormHasilDiagnosa extends AppCompatActivity {


    private static DatabasedbHelper datapenyakit;
     SQLiteDatabase db2;

    Cursor cursor;

// =====>Menampilkan Hasil Diagnosa
    TextView final_result_Gejala;
    TextView tvHasilPenyakit;
    TextView tvHasilPenyebab;
    TextView tvHasilSolusi;


    Button btn_home;
    Button btn_mulai_lagi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity_hasil_diagnosa);
//..........Mengambil database
        datapenyakit = new DatabasedbHelper(this);
        db2 = datapenyakit.getReadableDatabase();

        final_result_Gejala    = (TextView)findViewById(R.id.HasilListGejala);
        tvHasilPenyakit        = (TextView)findViewById(R.id.HasilPenyakit);
        tvHasilPenyebab        = (TextView)findViewById(R.id.HasilPenyebab);
        tvHasilSolusi          = (TextView)findViewById(R.id.HasilSolusi);

//..............Mengarahkan ke table penyakit berdasarakna kode_penyakit
        cursor = this.datapenyakit.getReadableDatabase().rawQuery("SELECT * FROM tbl_penyakit WHERE kode_penyakit = '" +
               getIntent().getStringExtra("nama")+"' ", null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);


// =======> setText(cursor.getString().tosString > untuk menampilkan data dari table
      // ==> Jangan Lupa Buat Variable TextView Tampil
         /* menampilkan hasil dari diagnosa yaitu :
                1. nama Penyakit = >
                2. Solusi
             */
            final_result_Gejala.setText(cursor.getString(0).toString());
            tvHasilPenyakit.setText(cursor.getString(1).toString());
            tvHasilPenyebab.setText(cursor.getString(2).toString());
            tvHasilSolusi.setText(cursor.getString(3).toString());


            final_result_Gejala.setText(getIntent().getStringExtra("namagejala"));


        }else{
            final_result_Gejala.setText(getIntent().getStringExtra("namagejala"));
            final_result_Gejala.setText("Tidak ada gejala yang dipilih");


        }
    }
}
