package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MonitoringActivity extends AppCompatActivity {

    Button back, utang, ditolak, diterima;
    TextView kanan, kiri, bulan;
    View vUtang, vDitolak, vDiterima;
    String bulanSekarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);


        final String[] daftarBulan = {"JANUARI", "FEBRUARI", "MARET", "APRIL", "MEI", "JUNI", "JULI", "AGUSTUS", "SEPTEMBER", "OKTOBER", "NOVEMBER", "DESEMBER"};


        back = findViewById(R.id.btnBackMonitoringPekerjaan);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonitoringActivity.this, BawahanActivity.class));
            }
        });

        bulan = findViewById(R.id.bulanMonitoring);
        kanan = findViewById(R.id.monitorKanan);
        kiri = findViewById(R.id.monitorKiri);
        ditolak = findViewById(R.id.daftarMonitoringDitolak);
        diterima = findViewById(R.id.daftarMonitoringDiterima);
        utang =findViewById(R.id.daftarMonitoringBelumDikoreksi);
        vDitolak =findViewById(R.id.viewMonitoringDitolak);
        vDiterima = findViewById(R.id.viewMonitoringDiterima);
        vUtang = findViewById(R.id.viewMonitoringBelumDikoreksi);

        belumActive();

        kanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulanSekarang = bulan.getText().toString();
                int i=indexBulan(bulanSekarang);
                if(i==11)i=-1;
                bulan.setText(daftarBulan[i+1]);
            }
        });
        kiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulanSekarang = bulan.getText().toString();
                int i=indexBulan(bulanSekarang);
                if(i==0)i=12;
                bulan.setText(daftarBulan[i-1]);
            }
        });

        ditolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ditolakActive();
            }
        });
        diterima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diterimaActive();
            }
        });
        utang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                belumActive();
            }
        });

    }

    public int indexBulan(String inputBulan) {
        switch (inputBulan) {
            case "JANUARI":
                return 0;
            case "FEBRUARI":
                return 1;
            case "MARET":
                return 2;
            case "APRIL":
                return 3;
            case "MEI":
                return 4;
            case "JUNI":
                return 5;
            case "JULI":
                return 6;
            case "AGUSTUS":
                return 7;
            case "SEPTEMBER":
                return 8;
            case "OKTOBER":
                return 9;
            case "NOVEMBER":
                return 10;
            case "DESEMBER":
                return 11;
            default:
                return 0;

        }


    }

    public void belumActive(){
        vUtang.setVisibility(View.VISIBLE);
        vDitolak.setVisibility(View.INVISIBLE);
        vDiterima.setVisibility(View.INVISIBLE);
        utang.setBackground(getResources().getDrawable(R.drawable.listtanya));
        ditolak.setBackground(getResources().getDrawable(R.drawable.listtolak_abu));
        diterima.setBackground(getResources().getDrawable(R.drawable.listterima_abu));
        utang.setTextColor(getResources().getColor(R.color.merah));
        ditolak.setTextColor(getResources().getColor(R.color.black));
        diterima.setTextColor(getResources().getColor(R.color.black));

       loadFragment(new FragmentMonitoringBelumDikoreksi());
    }

    public void ditolakActive(){
        vUtang.setVisibility(View.INVISIBLE);
        vDitolak.setVisibility(View.VISIBLE);
        vDiterima.setVisibility(View.INVISIBLE);
        utang.setBackground(getResources().getDrawable(R.drawable.listtanya));
        ditolak.setBackground(getResources().getDrawable(R.drawable.listtolak));
        diterima.setBackground(getResources().getDrawable(R.drawable.listterima_abu));
        utang.setTextColor(getResources().getColor(R.color.black));
        ditolak.setTextColor(getResources().getColor(R.color.merah));
        diterima.setTextColor(getResources().getColor(R.color.black));
        loadFragment(new FragmentMonitoringDitolak());
    }

    public void diterimaActive(){
        vUtang.setVisibility(View.INVISIBLE);
        vDitolak.setVisibility(View.INVISIBLE);
        vDiterima.setVisibility(View.VISIBLE);
        utang.setBackground(getResources().getDrawable(R.drawable.listtanya));
        ditolak.setBackground(getResources().getDrawable(R.drawable.listtolak_abu));
        diterima.setBackground(getResources().getDrawable(R.drawable.listterima));
        utang.setTextColor(getResources().getColor(R.color.black));
        ditolak.setTextColor(getResources().getColor(R.color.black));
        diterima.setTextColor(getResources().getColor(R.color.merah));
        loadFragment(new FragmentMonitoringDiterima());
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm;
        FragmentTransaction ft;
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frameFragmentMonitoring, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }


}
