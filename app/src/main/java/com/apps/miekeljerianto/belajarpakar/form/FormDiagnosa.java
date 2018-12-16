package com.apps.miekeljerianto.belajarpakar.form;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.miekeljerianto.belajarpakar.R;
import com.apps.miekeljerianto.belajarpakar.adapter.AdapterDiagnosa;
import com.apps.miekeljerianto.belajarpakar.database.DatabasedbHelper;

import java.util.ArrayList;
import java.util.List;

public class FormDiagnosa extends AppCompatActivity {

    DatabasedbHelper dbGejala;
    DatabasedbHelper dbPenyakit;
    DatabasedbHelper dbRules;

    SQLiteDatabase db;
    SQLiteDatabase db2;
    SQLiteDatabase db3;
    SQLiteDatabase db4;


    TextView tvPertanyaanGejala; //menampilkan hasil dari gejala2

    Cursor cursor;



    String kode_main;
    String kode_main_solusi;

    private String kode_tidak;
    private String kode_ya;
    private String mulai;
    private String selesai;
    private Button lanjut;
    private RadioButton radioButton_tidak;
    private RadioButton radioButton_ya;
    private RadioGroup radioGroup;

    private String result;
    private List<String> arrDiagnosa = new ArrayList<String>();

    String strHasilGejala = "";
    String strHasilPenyebab = "";
    String strHasilSolusi = "";

    AdapterDiagnosa adapter_diagnosa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity_diagnosa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //untuk mengeset Objek dari adapter dan menampilkan di formDiagnosa
        dbGejala = null;
        adapter_diagnosa = new AdapterDiagnosa();

        result = "";
        strHasilPenyebab = "";
        strHasilSolusi = "";

        kode_ya = "";
        kode_tidak = "";

        kode_main = "";
        kode_main_solusi = "";

        mulai = "";
        selesai = "";


        tvPertanyaanGejala = (TextView) findViewById(R.id.DiagnosaPertanyaan);
        lanjut = (Button) findViewById(R.id.lanjut);


        //Menulis data DATA DARI SQLITE
        dbGejala = new DatabasedbHelper(this);
        db = dbGejala.getWritableDatabase();
        dbGejala.createTableGejala(db);
        dbGejala.isiTableGejala(db);

        dbPenyakit = new DatabasedbHelper(this);
        db2 = dbPenyakit.getWritableDatabase();
        dbPenyakit.createTablePenyakit(db2);
        dbPenyakit.isiTablePenyakit(db2);

        dbRules = new DatabasedbHelper(this);
        db3 = dbRules.getWritableDatabase();
        dbRules.createTableRules(db3);
        dbRules.isiTabelRules(db3);

        showText();

        radioButton_ya = (RadioButton) findViewById(R.id.ya);
        radioButton_tidak = (RadioButton) findViewById(R.id.tidak);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        lanjut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (radioButton_ya.isChecked()) {
                    getYA();
                } else if (radioButton_tidak.isChecked()) {
                    getTIDAK();
                } else {
                    Toast.makeText(FormDiagnosa.this, "Pilih ya atau tidak", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showText() {
        cursor = db.rawQuery("SELECT * FROM tbl_rules where mulai_rules = 'Y'", null);

        if (cursor.moveToFirst()) {
            result = cursor.getString(1);
            kode_ya = cursor.getString(2);
            kode_tidak = cursor.getString(3);
            mulai = cursor.getString(4);
            selesai = cursor.getString(5);
            kode_main = cursor.getString(0);
            while (!cursor.isAfterLast()) {
                result = cursor.getString(1);
                kode_ya = cursor.getString(2);
                kode_tidak = cursor.getString(3);
                mulai = cursor.getString(4);
                selesai = cursor.getString(5);
                kode_main = cursor.getString(0);
                cursor.moveToNext();
            }
        }
        adapter_diagnosa.setDiagnosa(result);
        adapter_diagnosa.setYa(kode_ya);
        adapter_diagnosa.setTidak(kode_tidak);
        tvPertanyaanGejala.setClickable(false);
        tvPertanyaanGejala.setText("Burung anda Mengalami\n\n" + adapter_diagnosa.getDiagnosa() + "?");
    }

    public void getYA() {

        arrDiagnosa.add(adapter_diagnosa.getYa());
         strHasilGejala +=    arrDiagnosa.size() + ". " + adapter_diagnosa.getDiagnosa() + "\n";

        cursor = db.rawQuery("SELECT * FROM tbl_rules where id_rules = '" + adapter_diagnosa.getYa() + "'", null);
        if (cursor.moveToFirst()) {
            result = cursor.getString(1);
            kode_ya = cursor.getString(2);
            kode_tidak = cursor.getString(3);
            mulai = cursor.getString(4);
            selesai = cursor.getString(5);


            kode_main = cursor.getString(0);
            while (!cursor.isAfterLast()) {
                result = cursor.getString(1);
                kode_ya = cursor.getString(2);
                kode_tidak = cursor.getString(3);
                mulai = cursor.getString(4);
                selesai = cursor.getString(5);
                kode_main = cursor.getString(0);
                cursor.moveToNext();
            }
        }

        if (kode_main.equals("P01")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }if (kode_main.equals("P002")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }if (kode_main.equals("P03")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P04")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P05")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P06")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P07")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P008")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P09")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P010")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P11")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P12")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P20")){

               Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
              intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilSolusi);
            startActivity(intent);
            return;
        }

////////////////////////// MENGAMBIL PARAMETERR YA
        if (selesai.equals("Y")){
            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        adapter_diagnosa.setDiagnosa(result);
        adapter_diagnosa.setYa(kode_ya);
        adapter_diagnosa.setTidak(kode_tidak);
        tvPertanyaanGejala.setText("Apakah anda mengalami\n" + adapter_diagnosa.getDiagnosa() + "?");
        radioGroup.clearCheck();
        radioButton_ya.invalidate();
        radioButton_tidak.invalidate();
    }

    //............................mENGAMBIL PARAMETER TIDAK
    public void getTIDAK() {

        cursor = db.rawQuery("SELECT * FROM tbl_rules where id_rules = '" + adapter_diagnosa.getTidak() + "'", null);
        if (cursor.moveToFirst()) {
            result = cursor.getString(1);
            kode_ya = cursor.getString(2);
            kode_tidak = cursor.getString(3);
            mulai = cursor.getString(4);
            selesai = cursor.getString(5);
            kode_main = cursor.getString(0);
            while (!cursor.isAfterLast()) {
                result = cursor.getString(1);
                kode_ya = cursor.getString(2);
                kode_tidak = cursor.getString(3);
                mulai = cursor.getString(4);
                selesai = cursor.getString(5);
                kode_main = cursor.getString(0);
                cursor.moveToNext();
            }
        }


        if (kode_tidak.contentEquals("P01")) {
            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("namanol", "Burung anda tidak terdeteksi penyakit lain yang belum ada di sistem kami");
            intent.putExtra("nama", kode_main);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;

        }
        if (kode_main.equals("P01")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }if (kode_main.equals("P02")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;

        }if (kode_main.equals("P03")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P04")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P05")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P06")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P07")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P08")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P09")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P10")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P11")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P12")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }
        if (kode_main.equals("P20")){

            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            startActivity(intent);
            return;
        }



        if (selesai.equals("Y")){
            Intent intent = new Intent(getApplicationContext(), FormHasilDiagnosa.class);
            intent.putExtra("nama", kode_main);
            intent.putExtra("namagejala", strHasilGejala);
            intent.putExtra("solusi", strHasilSolusi);
            startActivity(intent);
            return;
        }

        adapter_diagnosa.setDiagnosa(result);
        adapter_diagnosa.setYa(kode_ya);
        radioGroup.clearCheck();
        adapter_diagnosa.setTidak(kode_tidak);
        tvPertanyaanGejala.setText("Apakah hewan anda mengalami\n" + adapter_diagnosa.getDiagnosa() + "?");
        radioButton_ya.invalidate();
        radioButton_tidak.invalidate();
    }

}

