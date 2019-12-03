package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

public class DetailPekerjaanActivity extends AppCompatActivity {

    TextView uraian,suburaian,tanggal,waktu,durasi,judul,detail;
    Button back;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pekerjaan);

        Intent intent = getIntent();
        String idpekerjaan = intent.getExtras().getString("idpekerjaan");
        Toast.makeText(getApplicationContext(),idpekerjaan,Toast.LENGTH_LONG).show();
        mApiService = UtilsApi.getAPIService();

        

        uraian = findViewById(R.id.hsilUraianTugas2);
        suburaian = findViewById(R.id.hsilSubUraianTugas2);
        tanggal = findViewById(R.id.hsilTanggalKerja2);
        waktu = findViewById(R.id.hsilJam2);
        durasi = findViewById(R.id.hsilDurasi2);
        judul = findViewById(R.id.hsilNamaPekerjaan2);
        detail = findViewById(R.id.hsilDetailPekerjaan2);
    }
}
