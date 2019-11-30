package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kominfopangkalabun.model.Pengaduan.PengaduanTanggapanClass;
import com.example.kominfopangkalabun.model.Pengaduan.PengaduanTanggapanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaduanTanggapan extends AppCompatActivity {

    BaseApiService mApiService;
    TextView tvTanggalPengaduan,tvTanggalTanggapan;
    EditText edtPengaduanTanggapan,edtHasilTanggapan;
    String idkeluhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan_tanggapan);

        tvTanggalPengaduan = findViewById(R.id.textTanggalPengaduan);
        tvTanggalTanggapan = findViewById(R.id.textTanggalTanggapan);
        edtPengaduanTanggapan = findViewById(R.id.edtPengaduanTanggapan);
        edtHasilTanggapan = findViewById(R.id.edtHasilTanggapan);

        Call<PengaduanTanggapanModel> call = mApiService.requstPengaduanDetail("5");
        call.enqueue(new Callback<PengaduanTanggapanModel>() {
            @Override
            public void onResponse(Call<PengaduanTanggapanModel> call, Response<PengaduanTanggapanModel> response) {
                Log.e("error",response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<PengaduanTanggapanModel> call, Throwable t) {

            }
        });

    }
}
