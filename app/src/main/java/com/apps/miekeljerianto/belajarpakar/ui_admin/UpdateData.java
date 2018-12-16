package com.apps.miekeljerianto.belajarpakar.ui_admin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.miekeljerianto.belajarpakar.R;
import com.apps.miekeljerianto.belajarpakar.database.DatabasedbHelper;

public class UpdateData extends AppCompatActivity {
    //variable


    Cursor cursor;
    private static DatabasedbHelper dbGejala;
    private static DatabasedbHelper dbPenyakit;
    private static DatabasedbHelper dbRules;

    EditText edt1, edt2, edt3, edt4;
    Button btn_SavePenyaakit, btnSaveGejala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        dbGejala = new DatabasedbHelper(this);
        dbPenyakit = new DatabasedbHelper(this);
        dbRules = new DatabasedbHelper(this);


        edt1 = (EditText) findViewById(R.id.edit_text_tampil1);
        edt2 = (EditText) findViewById(R.id.edit_text_tampil2);
        edt3 = (EditText) findViewById(R.id.edit_text_tampil3);
        edt4 = (EditText) findViewById(R.id.edit_text_tampil4);

        btn_SavePenyaakit = (Button) findViewById(R.id.btn_update_penyakit);



// =========> Table Gejala        //DB => TABLE GEJALA\
        dbGejala = new DatabasedbHelper(this);
        final SQLiteDatabase db_gejala = dbGejala.getReadableDatabase();
        cursor = db_gejala.rawQuery("SELECT * FROM tbl_gejala WHERE nama_gejala='" +
                getIntent().getStringExtra("nama_gejala") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            edt1.setText(cursor.getString(0).toString());
            edt2.setText(cursor.getString(1).toString());
            edt3.setVisibility(View.INVISIBLE);
            edt4.setVisibility(View.INVISIBLE);
        }
        btn_SavePenyaakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db_gejala = dbGejala.getWritableDatabase();
                db_gejala.execSQL("UPDATE tbl_gejala set nama_gejala='" + edt2.getText().toString() +
                        "', kode_gejala='" + edt1.getText().toString() + "' WHERE kode_gejala='" +
                        edt1.getText().toString() + "'");
                Toast.makeText(getApplicationContext(), "Berhasil diupdate", Toast.LENGTH_LONG).show();

            }
        });


// ===============> TablePenyakit
        //DB => TABLE PENYAKIT
        dbPenyakit = new DatabasedbHelper(this);
        final SQLiteDatabase db_penyakit = dbPenyakit.getReadableDatabase();
        cursor = db_penyakit.rawQuery("SELECT * FROM tbl_penyakit WHERE kode_penyakit = '" +
                getIntent().getStringExtra("kode_penyakit") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            edt1.setText(cursor.getString(0).toString());
            edt2.setText(cursor.getString(1).toString());
            edt3.setText(cursor.getString(2).toString());
            edt4.setText(cursor.getString(3).toString());

            //TABLE UiFragmentRules
            //Rules
            dbRules = new DatabasedbHelper(this);
            final SQLiteDatabase db_rules = dbRules.getReadableDatabase();
            cursor = db_rules.rawQuery("SELECT * FROM tbl_rules WHERE id_rules = '" +
                    getIntent().getStringExtra("id_rules") + "'", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToPosition(0);
                edt1.setText(cursor.getString(0).toString());
                edt2.setText(cursor.getString(1).toString());
                edt3.setText(cursor.getString(2).toString());
                edt4.setText(cursor.getString(3).toString());
            }


            btn_SavePenyaakit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ///update data TABLE pENYAKIT
                    SQLiteDatabase db = dbPenyakit.getWritableDatabase();
                    db.execSQL("update tbl_penyakit set solusi='" + edt4.getText().toString() + //update ke editText 3 yang inggin diupdate
                            "', penyebab='" + edt3.getText().toString() + //update SET nama penyakit ke EditText 2 yang ingin di update
                            "', nama_penyakit='" + edt2.getText().toString() + //update SET nama penyakit ke EditText 2 yang ingin di update

                            //karna ini primary key di ambil dari konisi
                            "', kode_penyakit='" + edt1.getText().toString() + "' where kode_penyakit='" +
                            edt1.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(), "Berhasil diupdate", Toast.LENGTH_LONG).show();

                }


            });

        }
    }
    public void onClickReset_(View view){
        edt1.setText(""); edt2.setText(""); edt4.setText("");
    }
}


