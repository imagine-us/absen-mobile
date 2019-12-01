package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kominfopangkalabun.retrofit.FragmentRiwayatPresensi;

public class PresensiBaruActivity extends AppCompatActivity {

    Button btnAbsensi, btnRiwayat;
    View vAbsensi, vRiwayat;
    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presensi_baru);

        //-----
        btnAbsensi=findViewById(R.id.btnAbsensi);
        btnRiwayat=findViewById(R.id.btnRiwayat);
        vAbsensi=findViewById(R.id.viewAbsensi);
        vRiwayat=findViewById(R.id.viewRiwayat);

        btnAbsensi.setTextColor(getResources().getColor(R.color.merah));
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frameUtama, new FragmentPeta());
        ft.commit();

        btnAbsensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vAbsensi.setVisibility(View.VISIBLE);
                vRiwayat.setVisibility(View.INVISIBLE);
                btnAbsensi.setTextColor(getResources().getColor(R.color.merah));
                btnRiwayat.setTextColor(getResources().getColor(R.color.black));

                ft.replace(R.id.frameUtama, new FragmentPeta());
                ft.addToBackStack(null);
                ft.commit();

            }
        });


        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vAbsensi.setVisibility(View.INVISIBLE);
                vRiwayat.setVisibility(View.VISIBLE);
                btnAbsensi.setTextColor(getResources().getColor(R.color.black));
                btnRiwayat.setTextColor(getResources().getColor(R.color.merah));

                ft.replace(R.id.frameUtama, new FragmentRiwayatPresensi());
                ft.addToBackStack(null);
                ft.commit();
            }
        });



    }
}
