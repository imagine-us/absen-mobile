package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kominfopangkalabun.model.Kegiatan.Kegiatan;
import com.example.kominfopangkalabun.model.Kegiatan.KegiatanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMonitoringActivity extends AppCompatActivity {


    TextView uraian,suburaian,tanggal,waktu,durasi,judul,detail;
    Button back, tolak, terima;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_monitoring);

        Intent intent = getIntent();
        final String idpekerjaan = intent.getExtras().getString("idpekerjaan");
        mApiService = UtilsApi.getAPIService();

        uraian = findViewById(R.id.hsilUraianTugas2);
        suburaian = findViewById(R.id.hsilSubUraianTugas2);
        tanggal = findViewById(R.id.hsilTanggalKerja2);
        waktu = findViewById(R.id.hsilJam2);
        durasi = findViewById(R.id.hsilDurasi2);
        judul = findViewById(R.id.hsilNamaPekerjaan2);
        detail = findViewById(R.id.hsilDetailPekerjaan2);
        back = findViewById(R.id.btnBackDetailPekerjaan2);
        tolak = findViewById(R.id.btnTolak);
        terima = findViewById(R.id.btnTerima);

        Call<KegiatanModel> call = mApiService.requestPekerjaanDetail(idpekerjaan);
        call.enqueue(new Callback<KegiatanModel>() {
            @Override
            public void onResponse(Call<KegiatanModel> call, Response<KegiatanModel> response) {
                if(response.isSuccessful()){
                    List<Kegiatan> kegiatanList = response.body().getKegiatanList();
                    uraian.setText("Tes");
                    suburaian.setText(kegiatanList.get(0).getNama_rincian());

                    String[] tanggal1 = kegiatanList.get(0).getTanggal_kegiatan().split(" ");
                    String[] tanggal2 = tanggal1[0].split("-");
                    tanggal.setText(tanggal2[2] + "-" + tanggal2[1] + "-" + tanggal2[0]);

                    String[] waktu1 = kegiatanList.get(0).getWaktu_mulai_kegiatan().split(" ");
                    waktu.setText(waktu1[1]);

                    String[] waktu2 = kegiatanList.get(0).getWaktu_akhir_kegiatan().split(" ");
                    durasi.setText(waktu2[1]);

                    judul.setText(kegiatanList.get(0).getNama_kegiatan());

                    detail.setText(kegiatanList.get(0).getOutput_kegiatan());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Detail Pekerjaan Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KegiatanModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        terima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<KegiatanModel> call = mApiService.updateStatusPekerjaan(idpekerjaan,"2");
                call.enqueue(new Callback<KegiatanModel>() {
                    @Override
                    public void onResponse(Call<KegiatanModel> call, Response<KegiatanModel> response) {
                        Toast.makeText(getApplicationContext(),"Pekerjaan Diterima",Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<KegiatanModel> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
            }
        });

        tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<KegiatanModel> call = mApiService.updateStatusPekerjaan(idpekerjaan,"1");
                call.enqueue(new Callback<KegiatanModel>() {
                    @Override
                    public void onResponse(Call<KegiatanModel> call, Response<KegiatanModel> response) {
                        Toast.makeText(getApplicationContext(),"Pekerjaan Ditolak",Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<KegiatanModel> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
            }
        });
    }
}
