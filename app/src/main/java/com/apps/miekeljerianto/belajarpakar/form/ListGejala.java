package com.apps.miekeljerianto.belajarpakar.form;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.apps.miekeljerianto.belajarpakar.R;
import com.apps.miekeljerianto.belajarpakar.database.DatabasedbHelper;
import com.apps.miekeljerianto.belajarpakar.ui_admin.LihatData;

public class ListGejala extends Fragment {

    String [] daftar_gejala;
    ListView lv_gejala;


     DatabasedbHelper mydb;
     Cursor cursor;


    public ArrayAdapter<String> adapter;
    public ListGejala() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_item_, container, false);

        lv_gejala = (ListView)rootView. findViewById(R.id.list_gejala);

        mydb = new DatabasedbHelper(getActivity());
        SQLiteDatabase db_gejala = mydb.getReadableDatabase();

        cursor = db_gejala.rawQuery("SELECT * FROM tbl_gejala", null);

        daftar_gejala = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar_gejala[cc] = cursor.getString(1).toString(); //getString menampilkan kolum diListView 1, 2
        }

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, daftar_gejala);
        lv_gejala.setAdapter(adapter);
        lv_gejala.setSelected(true);

        //onclick
        lv_gejala.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar_gejala[arg2];
                Intent i = new Intent(getContext(), LihatData.class);
                i.putExtra("nama_gejala", selection);
                startActivity(i);
            }
        });



        lv_gejala.setSelected(true);
        ((ArrayAdapter) lv_gejala.getAdapter()).notifyDataSetInvalidated();

        return rootView;
    }

}

