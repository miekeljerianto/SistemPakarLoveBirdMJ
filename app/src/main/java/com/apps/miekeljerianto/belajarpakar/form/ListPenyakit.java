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

public class ListPenyakit extends Fragment {

    public String[] daftar_penyakit;
    ListView listview_penyakit;

    private static DatabasedbHelper dbcenter;
    protected Cursor cursor;


    public ArrayAdapter<String> adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_item_, container, false);


        listview_penyakit = (ListView)rootView. findViewById(R.id.list_gejala);


        //lihat data di list
        dbcenter = new DatabasedbHelper(getActivity());
        SQLiteDatabase db_lihat_penyakit = dbcenter.getReadableDatabase();
        cursor = db_lihat_penyakit.rawQuery("SELECT * FROM tbl_penyakit ", null);
        daftar_penyakit = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar_penyakit[cc] = cursor.getString(1).toString();
        }

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, daftar_penyakit);
        listview_penyakit.setAdapter(adapter);
        listview_penyakit.setSelected(true);



       listview_penyakit.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar_penyakit[arg2];
                        Intent i = new Intent(getContext(), LihatData.class);
                        i.putExtra("nama_penyakit", selection);
                        startActivity(i);

                    }
                });

        ((ArrayAdapter) listview_penyakit.getAdapter()).notifyDataSetInvalidated();
        return rootView;
         }

}

