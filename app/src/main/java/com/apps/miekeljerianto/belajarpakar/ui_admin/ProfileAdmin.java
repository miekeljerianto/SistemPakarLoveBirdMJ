package com.apps.miekeljerianto.belajarpakar.ui_admin;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps.miekeljerianto.belajarpakar.R;

public class ProfileAdmin extends AppCompatActivity {

EditText edt1, edt2;
Button btn_login, btn_reset;

ProgressBar pd_loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_login);

        pd_loading = (ProgressBar)findViewById(R.id.loading);
        pd_loading.setVisibility(View.GONE);

        edt1 = (EditText)findViewById(R.id.ui_login_username);
        edt2 = (EditText)findViewById(R.id.ui_login_password);
        btn_login = (Button)findViewById(R.id.btn_ui_login);
        btn_reset = (Button)findViewById(R.id.btn_ui_reset);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(""); edt2.setText("");

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Keyusername = edt1.getText().toString();
                String Keypassword = edt2.getText().toString();

                if (Keyusername.isEmpty() && Keypassword.isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Tidak Bole Kosong", Toast.LENGTH_SHORT).show();
                }
                if (Keyusername.equals("1") && Keypassword.equals("1")){
                    pd_loading.setVisibility(View.VISIBLE);
                    ObjectAnimator anim = ObjectAnimator.ofInt(pd_loading, "progress", 8000, 80000);
                    anim.setDuration(15000);
                    anim.setInterpolator(new DecelerateInterpolator());
                    anim.start();
                    Toast.makeText(getApplicationContext(), "Login Sukses", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ProfileAdmin.this, UiDashboardAdmin.class);
                    startActivity(i);






                }
                else {
                    Toast.makeText(getApplicationContext(), "Gagal Login", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
