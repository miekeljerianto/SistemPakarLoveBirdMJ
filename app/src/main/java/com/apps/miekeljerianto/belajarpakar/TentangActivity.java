package com.apps.miekeljerianto.belajarpakar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apps.miekeljerianto.belajarpakar.ui.tentang.TentangFragment;

public class TentangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentang_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TentangFragment.newInstance())
                    .commitNow();
        }
    }
}
