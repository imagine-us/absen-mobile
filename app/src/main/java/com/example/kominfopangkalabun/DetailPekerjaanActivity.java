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

public class DetailPekerjaanActivity extends AppCompatActivity {

    TextView uraian,suburaian,tanggal,waktu,durasi,judul,detail;
    Button back;
    BaseApiService mApiService;
    String idpekerjaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pekerjaan);

        Intent intent = getIntent();
        idpekerjaan = intent.getExtras().getString("idpekerjaan");
//        Toast.makeText(getApplicationContext(),idpekerjaan,Toast.LENGTH_LONG).show();
        mApiService = UtilsApi.getAPIService();

        uraian = findViewById(R.id.hsilUraianTugas);
        suburaian = findViewById(R.id.hsilSubUraianTugas);
        tanggal = findViewById(R.id.hsilTanggalKerja);
        waktu = findViewById(R.id.hsilJam);
        durasi = findViewById(R.id.hsilDurasi);
        judul = findViewById(R.id.hsilNamaPekerjaan);
        detail = findViewById(R.id.hsilDetailPekerjaan);
        back = findViewById(R.id.btnBackDetailPekerjaan);

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


    }
}
