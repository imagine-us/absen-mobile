package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PekerjaanBaruActivity extends AppCompatActivity {

    Button btnpekerjaanSemua, btnpekerjaanDiterima, btnpekerjaanDitolak, btnpekerjaanBelumDikoreksi, btnKembali;
    View vwpekerjaanSemua, vwpekerjaanDiterima, vwpekerjaanDitolak, vwpekerjaanBelumDikoreksi;
    ImageView imgpekerjaantambah;
    TextView kanan, kiri, bulan;
    String bulanSekarang;
    private SharedPreferences sp;
    Bundle bd;
    int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pekerjaan_baru);

        bd = new Bundle();
//        DateFormat dateFormat = new SimpleDateFormat("MM");
//        Date date = new Date();
        int month = Calendar.getInstance().get(Calendar.MONTH);
        bd.putString("bulan",""+(month+1));


        final String[] daftarBulan = {"JANUARI", "FEBRUARI", "MARET", "APRIL", "MEI", "JUNI", "JULI", "AGUSTUS", "SEPTEMBER", "OKTOBER", "NOVEMBER", "DESEMBER"};
        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);

        btnKembali = findViewById(R.id.btnBackMonitoring);
        btnpekerjaanSemua = findViewById(R.id.daftarPekerjaanAll);
        btnpekerjaanDiterima = findViewById(R.id.daftarPekerjaanDiterima);
        btnpekerjaanDitolak = findViewById(R.id.daftarPekerjaanDitolak);
        btnpekerjaanBelumDikoreksi = findViewById(R.id.daftarPekerjaanBelumDikoreksi);
        vwpekerjaanSemua = findViewById(R.id.viewPekerjaanAll);
        vwpekerjaanDiterima = findViewById(R.id.viewPekerjaanDiterima);
        vwpekerjaanDitolak = findViewById(R.id.viewPekerjaanDitolak);
        vwpekerjaanBelumDikoreksi = findViewById(R.id.viewPekerjaanBelumDikoreksi);
        imgpekerjaantambah = findViewById(R.id.TambahPekerjaan);

        bulan = findViewById(R.id.bulanPekerjaan);
        kanan = findViewById(R.id.kananPekerjaan);
        kiri = findViewById(R.id.kiriPekerjaan);

        bulan.setText(daftarBulan[month]);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgpekerjaantambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FormPekerjaanActivity.class);
                startActivity(intent);
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


        kanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulanSekarang = bulan.getText().toString();
                int i=indexBulan(bulanSekarang);
                if(i==11){
                    i= 0;
                }
                else{
                    i = i+1;
                }
                bulan.setText(daftarBulan[i]);
                bd.putString("bulan",""+(i+1));
                if(flag==1){
                    pekerjaanSemuaActive();
                }
                else if(flag == 2){
                    pekerjaanDiterimaActive();
                }
                else if(flag == 3){
                    pekerjaanDitolakActive();
                }
                else if(flag == 4){
                    pekerjaanBelumDikoreksiActive();
                }
            }
        });
        kiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulanSekarang = bulan.getText().toString();
                int i=indexBulan(bulanSekarang);
                if(i==0){
                    i=11;
                }
                else{
                    i = i - 1;
                }
                bulan.setText(daftarBulan[i]);
                bd.putString("bulan",""+(i+1));
                if(flag==1){
                    pekerjaanSemuaActive();
                }
                else if(flag == 2){
                    pekerjaanDiterimaActive();
                }
                else if(flag == 3){
                    pekerjaanDitolakActive();
                }
                else if(flag == 4){
                    pekerjaanBelumDikoreksiActive();
                }
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
        flag=1;
        vwpekerjaanBelumDikoreksi.setVisibility(View.INVISIBLE);
        vwpekerjaanDitolak.setVisibility(View.INVISIBLE);
        vwpekerjaanDiterima.setVisibility(View.INVISIBLE);
        vwpekerjaanSemua.setVisibility(View.VISIBLE);
        btnpekerjaanSemua.setTextColor(getResources().getColor(R.color.merah));
        btnpekerjaanBelumDikoreksi.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDitolak.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDiterima.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanSemua.setBackground(getResources().getDrawable(R.drawable.all));
        btnpekerjaanBelumDikoreksi.setBackground(getResources().getDrawable(R.drawable.tanya_abu));
        btnpekerjaanDitolak.setBackground(getResources().getDrawable(R.drawable.listtolak_abu));
        btnpekerjaanDiterima.setBackground(getResources().getDrawable(R.drawable.listterima_abu));
        FragmentPekerjaanAll fpa = new FragmentPekerjaanAll();
        fpa.setArguments(bd);
//        loadFragment(new FragmentPekerjaanAll());
        loadFragment(fpa);
    }

    private void pekerjaanDiterimaActive() {
        flag=2;
        vwpekerjaanBelumDikoreksi.setVisibility(View.INVISIBLE);
        vwpekerjaanDitolak.setVisibility(View.INVISIBLE);
        vwpekerjaanDiterima.setVisibility(View.VISIBLE);
        vwpekerjaanSemua.setVisibility(View.INVISIBLE);
        btnpekerjaanSemua.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanBelumDikoreksi.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDitolak.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDiterima.setTextColor(getResources().getColor(R.color.merah));
        btnpekerjaanSemua.setBackground(getResources().getDrawable(R.drawable.all_abu));
        btnpekerjaanBelumDikoreksi.setBackground(getResources().getDrawable(R.drawable.tanya_abu));
        btnpekerjaanDitolak.setBackground(getResources().getDrawable(R.drawable.listtolak_abu));
        btnpekerjaanDiterima.setBackground(getResources().getDrawable(R.drawable.listterima));
        FragmentPekerjaanDiterima fpd = new FragmentPekerjaanDiterima();
        fpd.setArguments(bd);
        loadFragment(fpd);
    }

    private void pekerjaanDitolakActive() {
        flag=3;
        vwpekerjaanBelumDikoreksi.setVisibility(View.INVISIBLE);
        vwpekerjaanDitolak.setVisibility(View.VISIBLE);
        vwpekerjaanDiterima.setVisibility(View.INVISIBLE);
        vwpekerjaanSemua.setVisibility(View.INVISIBLE);
        btnpekerjaanSemua.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanBelumDikoreksi.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDitolak.setTextColor(getResources().getColor(R.color.merah));
        btnpekerjaanDiterima.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanSemua.setBackground(getResources().getDrawable(R.drawable.all_abu));
        btnpekerjaanBelumDikoreksi.setBackground(getResources().getDrawable(R.drawable.tanya_abu));
        btnpekerjaanDitolak.setBackground(getResources().getDrawable(R.drawable.listtolak));
        btnpekerjaanDiterima.setBackground(getResources().getDrawable(R.drawable.listterima_abu));
        FragmentPekerjaanDitolak fpd = new FragmentPekerjaanDitolak();
        fpd.setArguments(bd);
        loadFragment(fpd);
    }

    private void pekerjaanBelumDikoreksiActive() {
        flag=4;
        vwpekerjaanBelumDikoreksi.setVisibility(View.VISIBLE);
        vwpekerjaanDitolak.setVisibility(View.INVISIBLE);
        vwpekerjaanDiterima.setVisibility(View.INVISIBLE);
        vwpekerjaanSemua.setVisibility(View.INVISIBLE);
        btnpekerjaanSemua.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanBelumDikoreksi.setTextColor(getResources().getColor(R.color.merah));
        btnpekerjaanDitolak.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanDiterima.setTextColor(getResources().getColor(R.color.black));
        btnpekerjaanSemua.setBackground(getResources().getDrawable(R.drawable.all_abu));
        btnpekerjaanBelumDikoreksi.setBackground(getResources().getDrawable(R.drawable.listtanya));
        btnpekerjaanDitolak.setBackground(getResources().getDrawable(R.drawable.listtolak_abu));
        btnpekerjaanDiterima.setBackground(getResources().getDrawable(R.drawable.listterima_abu));
        FragmentPekerjaanBelumDikoreksi fbd = new FragmentPekerjaanBelumDikoreksi();
        fbd.setArguments(bd);
        loadFragment(fbd);
    }


//    private void lemparFragment(Bundle b,int flag){
//
//        if(flag==1){
//            FragmentPekerjaanAll f = new FragmentPekerjaanAll();
//            f.setArguments(b);
//            loadFragment(f);
//        } else if(flag==2){
//            FragmentPekerjaanAll f = new FragmentPekerjaanAll();
//            f.setArguments(b);
//            loadFragment(f);
//        } else if(flag==3){
//            FragmentPekerjaanAll f = new FragmentPekerjaanAll();
//            f.setArguments(b);
//            loadFragment(f);
//        }
//
//    }

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
}
