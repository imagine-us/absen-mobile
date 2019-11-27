package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kominfopangkalabun.model.Pengaduan.PengaduanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPengaduanActivity extends AppCompatActivity {

    Button btnPengaduanKembali, btnPengaduanKirim;
    EditText edtIsiPengaduan;
    BaseApiService mApiService;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pengaduan);

        edtIsiPengaduan = findViewById(R.id.edtPengaduan);
        btnPengaduanKembali = findViewById(R.id.btnFormPengaduanKembali);
        btnPengaduanKirim = findViewById(R.id.btnFormPengaduanKirim);
        mApiService = UtilsApi.getAPIService();
        context = this;

        btnPengaduanKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnPengaduanKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isiPengaduan = edtIsiPengaduan.getText().toString();
                //seharusnya id pns
                String noPns = "2";

                Call<PengaduanModel> call = mApiService.insertPengaduan(noPns,isiPengaduan);
                call.enqueue(new Callback<PengaduanModel>() {
                    @Override
                    public void onResponse(Call<PengaduanModel> call, Response<PengaduanModel> response) {
                        Toast.makeText(context,"Pengaduan Terkirim",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context,PengaduanActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<PengaduanModel> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
            }
        });
    }
}
