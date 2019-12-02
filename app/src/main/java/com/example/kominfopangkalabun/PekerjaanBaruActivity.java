package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PekerjaanBaruActivity extends AppCompatActivity {

    Button btnpekerjaanSemua, btnpekerjaanDiterima, btnpekerjaanDitolak, btnpekerjaanBelumDikoreksi, btnKembali;
    View vwpekerjaanSemua, vwpekerjaanDiterima, vwpekerjaanDitolak, vwpekerjaanBelumDikoreksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pekerjaan_baru);


        btnKembali = findViewById(R.id.btnBackMonitoring);
        btnpekerjaanSemua = findViewById(R.id.daftarPekerjaanAll);
        btnpekerjaanDiterima = findViewById(R.id.daftarPekerjaanDiterima);
        btnpekerjaanDitolak = findViewById(R.id.daftarPekerjaanDitolak);
        btnpekerjaanBelumDikoreksi = findViewById(R.id.daftarPekerjaanBelumDikoreksi);
        vwpekerjaanSemua = findViewById(R.id.viewPekerjaanAll);
        vwpekerjaanDiterima = findViewById(R.id.viewPekerjaanDiterima);
        vwpekerjaanDitolak = findViewById(R.id.viewPekerjaanDitolak);
        vwpekerjaanBelumDikoreksi = findViewById(R.id.viewPekerjaanBelumDikoreksi);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pekerjaanSemuaActive();

        try{
            if(getIntent().getExtras().getString("flag").equals("pekerjaanSemua")) pekerjaanSemuaActive();
        }catch(Exception e){

        }

        btnpekerjaanSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pekerjaanSemuaActive();
            }
        });

        btnpekerjaanDiterima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pekerjaanDiterimaActive();
            }
        });

        btnpekerjaanDitolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pekerjaanDitolakActive();
            }
        });

        btnpekerjaanBelumDikoreksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pekerjaanBelumDikoreksiActive();
            }
        });

    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm;
        FragmentTransaction ft;
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frameFragmentPekerjaan, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    private void pekerjaanSemuaActive() {
        vwpekerjaanBelumDikoreksi.setVisibility(View.INVISIBLE);
        vwpekerjaanDitolak.setVisibility(View.INVISIBLE);
        vwpekerjaanDiterima.setVisibility(View.INVISIBLE);
        vwpekerjaanSemua.setVisibility(View.VISIBLE);
        btnpekerjaanSemua.setTextColor(getResources().getColor(R.color.merah));
        btnpekerjaanBelumDikoreksi.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDitolak.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDiterima.setTextColor(getResources().getColor(R.color.black));

        loadFragment(new FragmentPekerjaanAll());
    }

    private void pekerjaanDiterimaActive() {
        vwpekerjaanBelumDikoreksi.setVisibility(View.INVISIBLE);
        vwpekerjaanDitolak.setVisibility(View.INVISIBLE);
        vwpekerjaanDiterima.setVisibility(View.VISIBLE);
        vwpekerjaanSemua.setVisibility(View.INVISIBLE);
        btnpekerjaanSemua.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanBelumDikoreksi.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDitolak.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDiterima.setTextColor(getResources().getColor(R.color.merah));

        loadFragment(new FragmentPekerjaanDiterima());
    }

    private void pekerjaanDitolakActive() {
        vwpekerjaanBelumDikoreksi.setVisibility(View.INVISIBLE);
        vwpekerjaanDitolak.setVisibility(View.VISIBLE);
        vwpekerjaanDiterima.setVisibility(View.INVISIBLE);
        vwpekerjaanSemua.setVisibility(View.INVISIBLE);
        btnpekerjaanSemua.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanBelumDikoreksi.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDitolak.setTextColor(getResources().getColor(R.color.merah));
        btnpekerjaanDiterima.setTextColor(getResources().getColor(R.color.black));

        loadFragment(new FragmentPekerjaanDitolak());
    }

    private void pekerjaanBelumDikoreksiActive() {
        vwpekerjaanBelumDikoreksi.setVisibility(View.VISIBLE);
        vwpekerjaanDitolak.setVisibility(View.INVISIBLE);
        vwpekerjaanDiterima.setVisibility(View.INVISIBLE);
        vwpekerjaanSemua.setVisibility(View.INVISIBLE);
        btnpekerjaanSemua.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanBelumDikoreksi.setTextColor(getResources().getColor(R.color.merah));
        btnpekerjaanDitolak.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDiterima.setTextColor(getResources().getColor(R.color.black));

        loadFragment(new FragmentPekerjaanBelumDikoreksi());
    }
}
