package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PresensiBaruActivity extends AppCompatActivity {

    Button btnAbsensi, btnRiwayat;
    View vAbsensi, vRiwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presensi_baru);

        //-----
        btnAbsensi=findViewById(R.id.btnAbsensi);
        btnRiwayat=findViewById(R.id.btnRiwayat);
        vAbsensi=findViewById(R.id.viewAbsensi);
        vRiwayat=findViewById(R.id.viewRiwayat);

        btnAbsensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vAbsensi.setVisibility(View.VISIBLE);
                vRiwayat.setVisibility(View.INVISIBLE);
                btnAbsensi.setTextColor(getResources().getColor(R.color.merah));
                btnRiwayat.setTextColor(getResources().getColor(R.color.black));

            }
        });


        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vAbsensi.setVisibility(View.INVISIBLE);
                vRiwayat.setVisibility(View.VISIBLE);
                btnAbsensi.setTextColor(getResources().getColor(R.color.black));
                btnRiwayat.setTextColor(getResources().getColor(R.color.merah));
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameUtama, new FragmentPeta());
        ft.commit();

    }
}
