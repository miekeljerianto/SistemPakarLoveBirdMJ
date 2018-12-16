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
public class UiFragmentRules extends Fragment {
    public String[] daftar_rules;
     Cursor cursor;

    DatabasedbHelper dbcenter;
    public ListView lv_rules;
    public ArrayAdapter<String> adapter;


    public UiFragmentRules() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ui_fragment_crud, container, false);

        lv_rules = (ListView)rootView. findViewById(R.id.list_item_admin);

        FloatingActionButton btn_fab = (FloatingActionButton)rootView. findViewById(R.id.fab_tambahdata);
        btn_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CRUD_Rules.class);
                startActivity(i);
            }
        });

        //lihat data di list
        dbcenter = new DatabasedbHelper(getActivity());
        SQLiteDatabase db_rules = dbcenter.getReadableDatabase();
        cursor = db_rules .rawQuery("SELECT * FROM tbl_rules", null);
        daftar_rules = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar_rules[cc] = cursor.getString(0).toString();
        }

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, daftar_rules);
        lv_rules.setAdapter(adapter);
        lv_rules.setSelected(true);
        lv_rules.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {

                final String selection = daftar_rules [arg2];
                final CharSequence[] dialogitem = {"Lihat", "Update", "Hapus"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getContext(), CRUD_Rules.class);
                                i.putExtra("id_rules", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getContext(), CRUD_Rules.class);
                                in.putExtra("id_rules", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from tbl_rules where id_rules = '" + selection + "'");
                                Toast.makeText(getActivity(), "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });

        ((ArrayAdapter) lv_rules.getAdapter()).notifyDataSetInvalidated();
        return rootView;
    }
}

