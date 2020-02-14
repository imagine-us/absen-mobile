package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.util.Calendar;

public class PengaduanActivityBaru extends AppCompatActivity {

    Button btnpengaduanSemua, btnpengaduanDiterima, btnpengaduanProses, btnpengaduanBelum, btnKembali;
    View vwpengaduanSemua, vwpengaduanDiterima, vwpengaduanDiproses, vwpengaduanBelum;
    ImageView imgpengaduantambah;
    TextView kanan, kiri, bulan;
    String bulanSekarang;
    private SharedPreferences sp;
    Bundle bd;
    int flag = 1;
    String id;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan_baru);
        mApiService = UtilsApi.getAPIService();
        bd = new Bundle();
        int month = Calendar.getInstance().get(Calendar.MONTH);
        bd.putString("bulan",""+(month+1));

        final String[] daftarBulan = {"JANUARI", "FEBRUARI", "MARET", "APRIL", "MEI", "JUNI", "JULI", "AGUSTUS", "SEPTEMBER", "OKTOBER", "NOVEMBER", "DESEMBER"};
        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);

        id = this.sp.getString("key_id",null);


        btnKembali = findViewById(R.id.btnBackPengaduanBaru);
        btnpengaduanSemua = findViewById(R.id.daftarPengaduanBaruAll);
        btnpengaduanDiterima = findViewById(R.id.daftarPengaduanBaruSudah);
        btnpengaduanProses = findViewById(R.id.daftarPengaduanBaruDiproses);
        btnpengaduanBelum = findViewById(R.id.daftarPengaduanBaruBelum);
        vwpengaduanSemua = findViewById(R.id.viewPengaduanBaruAll);
        vwpengaduanDiterima = findViewById(R.id.viewPengaduanBaruSudah);
        vwpengaduanDiproses = findViewById(R.id.viewPengaduanBaruDiproses);
        vwpengaduanBelum = findViewById(R.id.viewPengaduanBaruBelum);
        imgpengaduantambah = findViewById(R.id.TambahPengaduanBaru);

        bulan = findViewById(R.id.bulanPengaduanBaru);
        kanan = findViewById(R.id.kananPengaduanBaru);
        kiri = findViewById(R.id.kiriPengaduanBaru);

        bulan.setText(daftarBulan[month]);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        imgpengaduantambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), FormPengaduanActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        btnpengaduanSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pengaduanSemuaActive();
            }
        });

        btnpengaduanDiterima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pengaduanDiterimaActive();
            }
        });

        btnpengaduanProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pengaduanProsesActive();
            }
        });

        btnpengaduanBelum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pengaduanBelumActive();
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
                    pengaduanSemuaActive();
                }
                else if(flag == 2){
                    pengaduanDiterimaActive();
                }
                else if(flag == 3){
                    pengaduanProsesActive();
                }
                else if(flag == 4){
                    pengaduanBelumActive();
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
                    pengaduanSemuaActive();
                }
                else if(flag == 2){
                    pengaduanDiterimaActive();
                }
                else if(flag == 3){
                    pengaduanProsesActive();
                }
                else if(flag == 4){
                    pengaduanBelumActive();
                }
            }
        });

        pengaduanSemuaActive();
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm;
        FragmentTransaction ft;
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frameFragmentPengaduanBaru, fragment);
        ft.addToBackStack(null);
        ft.commit();
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

    private void pengaduanSemuaActive(){
        flag=1;
        vwpengaduanSemua.setVisibility(View.VISIBLE);
        vwpengaduanBelum.setVisibility(View.INVISIBLE);
        vwpengaduanDiproses.setVisibility(View.INVISIBLE);
        vwpengaduanDiterima.setVisibility(View.INVISIBLE);
        btnpengaduanSemua.setTextColor(getResources().getColor(R.color.merah));
        btnpengaduanDiterima.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanProses.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanBelum.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanSemua.setBackground(getResources().getDrawable(R.drawable.all));
        btnpengaduanDiterima.setBackground(getResources().getDrawable(R.drawable.listterima_abu));
        btnpengaduanProses.setBackground(getResources().getDrawable(R.drawable.tanya_abu));
        btnpengaduanBelum.setBackground(getResources().getDrawable(R.drawable.listtolak_abu));
        FragmentPengaduanBaruSemua fragmentPengaduanBaruSemua = new FragmentPengaduanBaruSemua();
        fragmentPengaduanBaruSemua.setArguments(bd);
        loadFragment(fragmentPengaduanBaruSemua);
    }

    private void pengaduanDiterimaActive(){
        flag=2;
        vwpengaduanSemua.setVisibility(View.INVISIBLE);
        vwpengaduanBelum.setVisibility(View.INVISIBLE);
        vwpengaduanDiproses.setVisibility(View.INVISIBLE);
        vwpengaduanDiterima.setVisibility(View.VISIBLE);
        btnpengaduanSemua.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanDiterima.setTextColor(getResources().getColor(R.color.merah));
        btnpengaduanProses.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanBelum.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanSemua.setBackground(getResources().getDrawable(R.drawable.all_abu));
        btnpengaduanDiterima.setBackground(getResources().getDrawable(R.drawable.listterima));
        btnpengaduanProses.setBackground(getResources().getDrawable(R.drawable.tanya_abu));
        btnpengaduanBelum.setBackground(getResources().getDrawable(R.drawable.listtolak_abu));
        FragmentPengaduanBaruDiterima fpa = new FragmentPengaduanBaruDiterima();
        fpa.setArguments(bd);
        loadFragment(fpa);
    }

    private void pengaduanProsesActive(){
        flag=3;
        vwpengaduanSemua.setVisibility(View.INVISIBLE);
        vwpengaduanBelum.setVisibility(View.INVISIBLE);
        vwpengaduanDiproses.setVisibility(View.VISIBLE);
        vwpengaduanDiterima.setVisibility(View.INVISIBLE);
        btnpengaduanSemua.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanDiterima.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanProses.setTextColor(getResources().getColor(R.color.merah));
        btnpengaduanBelum.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanSemua.setBackground(getResources().getDrawable(R.drawable.all_abu));
        btnpengaduanDiterima.setBackground(getResources().getDrawable(R.drawable.listterima_abu));
        btnpengaduanProses.setBackground(getResources().getDrawable(R.drawable.listtanya));
        btnpengaduanBelum.setBackground(getResources().getDrawable(R.drawable.listtolak_abu));
        FragmentPengaduanBaruDiproses fpa = new FragmentPengaduanBaruDiproses();
        fpa.setArguments(bd);
        loadFragment(fpa);
    }

    private void pengaduanBelumActive(){
        flag=4;
        vwpengaduanSemua.setVisibility(View.INVISIBLE);
        vwpengaduanBelum.setVisibility(View.VISIBLE);
        vwpengaduanDiproses.setVisibility(View.INVISIBLE);
        vwpengaduanDiterima.setVisibility(View.INVISIBLE);
        btnpengaduanSemua.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanDiterima.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanProses.setTextColor(getResources().getColor(R.color.black));
        btnpengaduanBelum.setTextColor(getResources().getColor(R.color.merah));
        btnpengaduanSemua.setBackground(getResources().getDrawable(R.drawable.all_abu));
        btnpengaduanDiterima.setBackground(getResources().getDrawable(R.drawable.listterima_abu));
        btnpengaduanProses.setBackground(getResources().getDrawable(R.drawable.tanya_abu));
        btnpengaduanBelum.setBackground(getResources().getDrawable(R.drawable.listtolak));
        FragmentPengaduanBaruBelum fpa = new FragmentPengaduanBaruBelum();
        fpa.setArguments(bd);
        loadFragment(fpa);
    }
}
