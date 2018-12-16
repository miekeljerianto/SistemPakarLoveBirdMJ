package com.apps.miekeljerianto.belajarpakar.ui_admin;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
public class UiFragmentPenyakit extends Fragment {
    private String[] daftar;
    public ListView ListView01;

      Cursor cursor;
     private static DatabasedbHelper dbcenter;

    public ListView lv;
    public ArrayAdapter<String> adapter;

    public static UiFragmentPenyakit ma;
    FloatingActionButton fab_add;

    public UiFragmentPenyakit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ui_fragment_crud, container, false);


        lv = (ListView)rootView. findViewById(R.id.list_item_admin);

        //click => tambah data
        FloatingActionButton btn_add = (FloatingActionButton)rootView. findViewById(R.id.fab_tambahdata);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), TambahData.class);
                startActivity(i);
            }
        });

       //lihat data di list
        dbcenter = new DatabasedbHelper(getActivity());
        SQLiteDatabase db_tambah_penyakit = dbcenter.getReadableDatabase();
        cursor = db_tambah_penyakit.rawQuery("SELECT * FROM tbl_penyakit", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
        }

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, daftar);
        lv.setAdapter(adapter);
        lv.setSelected(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {

                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat", "Update", "Hapus"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getContext(), LihatData.class);
                                i.putExtra("kode_penyakit", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getContext(), UpdateData.class);
                                in.putExtra("kode_penyakit", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from tbl_penyakit where kode_penyakit = '" + selection + "'");
                                Toast.makeText(getActivity(), "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });

        ((ArrayAdapter) lv.getAdapter()).notifyDataSetInvalidated();
        return rootView;
    }








/*
        FloatingActionButton fab_add = (FloatingActionButton) RootView.findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Belum Ada Aktivitas", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    return RootView;
    }
    */

}

