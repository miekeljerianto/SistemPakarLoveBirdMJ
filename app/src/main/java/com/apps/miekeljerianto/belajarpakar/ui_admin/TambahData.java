package com.apps.miekeljerianto.belajarpakar.ui_admin;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.miekeljerianto.belajarpakar.R;
import com.apps.miekeljerianto.belajarpakar.database.DatabasedbHelper;

public class TambahData extends Activity {

    DatabasedbHelper mydb_penyakit;
    DatabasedbHelper mydb_gejala;

    private Cursor cursor;
    TextView add_tapil1, add_tapil2, add_tapil3, add_tapil4;

    EditText edt_add1, edt_add2, edt_add3, edt_add4;
    //tambah edit text Gejala
    TextView tmplYa, tmplTdk, tmpMulai, tmpSelesai;
    EditText edtYa, edtTdk, edtMulai, edtSelesai;

    Button btn_simpan_penyakit, btn_simpan_gejala, btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        mydb_penyakit = new DatabasedbHelper(this);
        mydb_gejala = new DatabasedbHelper(this);

        edt_add1 = (EditText) findViewById(R.id.edit_text_tambah1);
        edt_add1.setHint("P001");
        edt_add2 = (EditText) findViewById(R.id.edit_text_tambah2);
        edt_add2.setHint("nama penyakit");
        edt_add3 = (EditText) findViewById(R.id.edit_text_tambah3);
        edt_add3.setHint("Penyebab");
        edt_add4 = (EditText) findViewById(R.id.edit_text_tambah4);
        edt_add4.setHint("Solusi");
        edtYa = (EditText) findViewById(R.id.edit_text_ya);
        edtYa.setVisibility(View.INVISIBLE);
        edtTdk = (EditText) findViewById(R.id.edit_text_tdk);
        edtTdk.setVisibility(View.INVISIBLE);
        edtMulai = (EditText) findViewById(R.id.edit_text_tambah_mulai);
        edtMulai.setVisibility(View.INVISIBLE);
        edtSelesai = (EditText) findViewById(R.id.edit_text_selesai);
        edtSelesai.setVisibility(View.INVISIBLE);

        tmplYa = (TextView) findViewById(R.id.tampil_ya);
        tmplYa.setVisibility(View.INVISIBLE);
        tmplTdk = (TextView) findViewById(R.id.tampil_tdk);
        tmplTdk.setVisibility(View.INVISIBLE);
        tmpMulai = (TextView) findViewById(R.id.mulai);
        tmpMulai.setVisibility(View.INVISIBLE);


// ========>  TambahData Penyakit
        //Button
        btn_simpan_gejala = (Button) findViewById(R.id.btn_data_gejala);
        btn_simpan_penyakit = (Button) findViewById(R.id.btn_simpan_data_penyakit);

        btn_simpan_penyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db_tambah_penyakit = mydb_gejala.getWritableDatabase();
                db_tambah_penyakit.execSQL("insert into tbl_penyakit (kode_penyakit, nama_penyakit, penyebab, solusi) values('" +
                        edt_add1.getText().toString() + "','" +
                        edt_add2.getText().toString() + "','" +
                        edt_add3.getText().toString() + "','" +
                        edt_add4.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil disimpan", Toast.LENGTH_LONG).show();
            }
        });

// ======>  penyakit

        btn_simpan_gejala.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                SQLiteDatabase db_gejala = mydb_penyakit.getWritableDatabase();
                db_gejala.execSQL("INSERT INTO tbl_gejala (kode_gejala, nama_gejala) VALUES('" +
                        edt_add1.getText().toString() + "','" +
                        edt_add2.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil disimpan", Toast.LENGTH_LONG).show();
                        //menonaktifkan


            }
        });
    }
}