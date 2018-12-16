package com.apps.miekeljerianto.belajarpakar.ui_admin;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.apps.miekeljerianto.belajarpakar.R;
import com.apps.miekeljerianto.belajarpakar.database.DatabasedbHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class UiFragmentGejala extends Fragment {
    public String[] daftar_gejala;
     Cursor cursor;

    private static DatabasedbHelper dbcenter;
    public ListView lv_gejala;
    public ArrayAdapter<String> adapter;

    public static FragmentActivity ma;
    public UiFragmentGejala() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ui_fragment_crud, container, false);

        lv_gejala = (ListView)rootView. findViewById(R.id.list_item_admin);
        FloatingActionButton btn_fab = (FloatingActionButton)rootView. findViewById(R.id.fab_tambahdata);
        btn_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), TambahData.class);

                startActivity(i);
            }
        });

        //lihat data di list
        dbcenter = new DatabasedbHelper(getActivity());
        SQLiteDatabase db_gejala = dbcenter.getReadableDatabase();
        cursor = db_gejala.rawQuery("SELECT * FROM tbl_gejala", null);
        daftar_gejala = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar_gejala[cc] = cursor.getString(1).toString();
            //cursor ingin melihat databse dari table ke 1 nama gejala
            //dan ubah lihat gejala => paramenter getString(1) yaitu nama_gejala
            // kemudian ubah data di LihatData.java ==> samakan nama_gejala
        }

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, daftar_gejala);
        lv_gejala.setAdapter(adapter);
        lv_gejala.setSelected(true);
        lv_gejala.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {

                final String selection = daftar_gejala [arg2];
                final CharSequence[] dialogitem = {"Lihat", "Update", "Hapus"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getContext(), LihatData.class);
                                i.putExtra("nama_gejala", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in_update = new Intent(getContext(), UpdateData.class);
                                in_update.putExtra("nama_gejala", selection);
                                startActivity(in_update);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from tbl_gejala WHERE nama_gejala = '" + selection + "'");
                                Toast.makeText(getActivity(), "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });

        ((ArrayAdapter) lv_gejala.getAdapter()).notifyDataSetInvalidated();
        return rootView;
    }
}

