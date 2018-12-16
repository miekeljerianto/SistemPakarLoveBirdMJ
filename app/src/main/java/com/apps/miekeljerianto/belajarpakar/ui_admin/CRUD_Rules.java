package com.apps.miekeljerianto.belajarpakar.ui_admin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.miekeljerianto.belajarpakar.R;
import com.apps.miekeljerianto.belajarpakar.database.DatabasedbHelper;

public class CRUD_Rules extends AppCompatActivity {
    //variable
private static DatabasedbHelper mydb;
private static DatabasedbHelper mydb_add;
private static DatabasedbHelper mydb_lihat;

    Cursor cursor;
EditText edt1, edt2, edt3, edt4, edt5, edt6;
Button btnUpdate, btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_rules);


        edt1 = (EditText)findViewById(R.id.edt_rules_id);
        edt2 = (EditText)findViewById(R.id.edt_rules_nmgejala);
        edt3 = (EditText)findViewById(R.id.ya);
        edt4 = (EditText)findViewById(R.id.tdk);
        edt5 = (EditText)findViewById(R.id.rules_mulai);
        edt6 = (EditText)findViewById(R.id.selesai_rules);

        mydb = new DatabasedbHelper(this);
        mydb_add = new DatabasedbHelper(this);
        mydb_lihat = new DatabasedbHelper(this);

        //SQLiteDatabase db_lihat = mydb_lihat.getReadableDatabase();
        //cursor = db_lihat.rawQuery("SELECT * FROM tbl_rules WHERE id_rules")


        SQLiteDatabase db_rules = mydb.getReadableDatabase();
        cursor = db_rules.rawQuery("SELECT * FROM tbl_rules WHERE id_rules='" +
        getIntent().getStringExtra("id_rules") + "'", null);

        cursor.move(0);
            if (cursor.getCount() >0 ){
                cursor.moveToFirst();
                edt1.setText(cursor.getString(0).toString());
                edt2.setText(cursor.getString(1).toString());
                edt3.setText(cursor.getString(2).toString());
                edt4.setText(cursor.getString(3).toString());
                edt5.setText(cursor.getString(4).toString());
                edt6.setText(cursor.getString(5).toString());
            }
    }

// ===========================> TAMBAH DATA


// ===========> button onClick CREAT UPDATE
public void onClickAdd(View v){
    SQLiteDatabase db_add = mydb_add.getWritableDatabase();
    db_add.execSQL("INSERT INTO tbl_rules (id_rules, nama_gejala_rules, ya_rules, tidak_rules, mulai_rules, selesai_rules) VALUES ('" +
            edt1.getText().toString() + "','" +
            edt2.getText().toString() + "','" +
            edt3.getText().toString() + "','" +
            edt4.getText().toString() + "','" +
            edt5.getText().toString() + "','" +
            edt6.getText().toString() + "')");
            Toast.makeText(getApplicationContext(), "Data berhasil Ditambah", Toast.LENGTH_SHORT).show();

}
    public void onClickUpdate(View view) {
        SQLiteDatabase db_update = mydb.getWritableDatabase();
        db_update.execSQL("UPDATE tbl_rules SET selesai_rules='" + edt6.getText().toString() +
                "', mulai_rules='" + edt5.getText().toString() +
                "', tidak_rules='" + edt4.getText().toString() +
                "', ya_rules='" + edt3.getText().toString() +
                "', nama_gejala_rules='" + edt2.getText().toString() +
                "', id_rules='" + edt1.getText().toString() + "'where id_rules='" + edt1.getText().toString() + "'");
            Toast.makeText(getApplicationContext(), "Update Berhasil", Toast.LENGTH_SHORT).show();
        }
    public void onClickReset(View view){
        edt1.setText(""); edt2.setText(""); edt3.setText(""); edt4.setText(""); edt5.setText(""); edt6.setText("");
    }
}
