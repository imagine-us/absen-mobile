package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kominfopangkalabun.model.Pengaduan.PengaduanTanggapanClass;
import com.example.kominfopangkalabun.model.Pengaduan.PengaduanTanggapanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaduanTanggapan extends AppCompatActivity {

    BaseApiService mApiService;
    TextView tvTanggalPengaduan,tvTanggalTanggapan, tvIsiPengaduan, tvIsiTanggapan, tvSubjekTanggapan;
    Button kembaliTanggapan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan_tanggapan);

        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        final String idpengaduaan = intent.getStringExtra("Id");

        tvSubjekTanggapan = findViewById(R.id.txtSubjekPengaduanDetailTanggapan);
        tvTanggalPengaduan = findViewById(R.id.txtTanggalPengaduanDetailTanggapan);
        tvTanggalTanggapan = findViewById(R.id.txtTanggalTanggapanDetailTanggapan);
        tvIsiPengaduan = findViewById(R.id.txtIsiPengaduanDetailTanggapan);
        tvIsiTanggapan = findViewById(R.id.txtIsiTanggapanDetailTanggapan);
        kembaliTanggapan = findViewById(R.id.btnBackPengaduanTanggapan);

        kembaliTanggapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Call<PengaduanTanggapanModel> call = mApiService.requstPengaduanDetail(idpengaduaan);
        call.enqueue(new Callback<PengaduanTanggapanModel>() {
            @Override
            public void onResponse(Call<PengaduanTanggapanModel> call, Response<PengaduanTanggapanModel> response) {
              if(response.isSuccessful()){
                  List<PengaduanTanggapanClass> pengaduanTanggapanClasses = response.body().getPengaduanTanggapanClass();
                  tvTanggalPengaduan.setText("Tanggal Pengaduan : " + pengaduanTanggapanClasses.get(0).getTanggalPengaduan());
                  tvTanggalTanggapan.setText("Tanggal Tanggapan : " + pengaduanTanggapanClasses.get(0).getTanggalTanggapan());
                  tvIsiPengaduan.setText(pengaduanTanggapanClasses.get(0).getIsiPengaduan());
                  tvIsiTanggapan.setText(pengaduanTanggapanClasses.get(0).getIsiTanggapan());
                  tvSubjekTanggapan.setText("Subjek : " + pengaduanTanggapanClasses.get(0).getSubjekPengaduan());
              }
              else{
                  Toast.makeText(getApplicationContext(),"Tanggapan Error",Toast.LENGTH_SHORT).show();
              }
            }

            @Override
            public void onFailure(Call<PengaduanTanggapanModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });

//         Toast.makeText(getApplicationContext(), idpengaduaan,Toast.LENGTH_SHORT).show();

    }
}
