package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kominfopangkalabun.model.Pengaduan.PengaduanTanggapanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaduanTanggapan extends AppCompatActivity {

    BaseApiService mApiService;
    TextView tvTanggalPengaduan,tvTanggalTanggapan;
    EditText edtPengaduanTanggapan,edtHasilTanggapan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan_tanggapan);

        tvTanggalPengaduan = findViewById(R.id.textTanggalPengaduan);
        tvTanggalTanggapan = findViewById(R.id.textTanggalTanggapan);
        edtPengaduanTanggapan = findViewById(R.id.edtPengaduanTanggapan);
        edtHasilTanggapan = findViewById(R.id.edtHasilTanggapan);

        Intent intent = getIntent();
        String idkeluhan = intent.getStringExtra("Id");

       tvTanggalTanggapan.setText(idkeluhan);

       Call<PengaduanTanggapanModel> call = mApiService.requestPengaduanDetail("5");
    }
}
