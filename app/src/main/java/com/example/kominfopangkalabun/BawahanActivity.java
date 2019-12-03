package com.example.kominfopangkalabun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kominfopangkalabun.adapter.BawahanAdapter;
import com.example.kominfopangkalabun.model.Monitoring.Bawahan;
import com.example.kominfopangkalabun.model.Monitoring.BawahanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BawahanActivity extends AppCompatActivity {

    Button back;
    RecyclerView rv;
    LinearLayoutManager layoutManager;
    BaseApiService mApiService;
    private SharedPreferences sp;
    String nip,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bawahan);

        back = findViewById(R.id.btnBackMonitoringBawahan);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BawahanActivity.this, MainActivity.class));
            }
        });


        mApiService = UtilsApi.getAPIService();
        this.sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        nip = this.sp.getString("key_nip",null);
        id = this.sp.getString("key_id",null);

        Call<BawahanModel> call = mApiService.requestBawahan(nip);
        call.enqueue(new Callback<BawahanModel>() {
            @Override
            public void onResponse(Call<BawahanModel> call, Response<BawahanModel> response) {
                if(response.isSuccessful()){
                    List<Bawahan> bawahanList = response.body().getData();
                    rv= findViewById(R.id.rvBawahan);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    BawahanAdapter menuAdapter = new BawahanAdapter(bawahanList);
                    rv.setAdapter(menuAdapter);
                    rv.setLayoutManager(layoutManager);
                }

                else{
                    Toast.makeText(BawahanActivity.this, "Data Bawahan Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BawahanModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });



    }
}
