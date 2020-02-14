package com.example.kominfopangkalabun;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kominfopangkalabun.adapter.PengaduanAdapter;
import com.example.kominfopangkalabun.model.Pengaduan.Pengaduan;
import com.example.kominfopangkalabun.model.Pengaduan.PengaduanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPengaduanBaruSemua extends Fragment {
    BaseApiService mApiService;
    View v;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private SharedPreferences sp;
    String bulan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_pengaduan_baru_semua ,container,false);

        mApiService = UtilsApi.getAPIService();
        this.sp = getContext().getSharedPreferences("sp", Context.MODE_PRIVATE);

        bulan = getArguments().getString("bulan");

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Call<PengaduanModel> call = mApiService.requestPengaduan(this.sp.getString("key_id",null), bulan, "");
        call.enqueue(new Callback<PengaduanModel>() {
            @Override
            public void onResponse(Call<PengaduanModel> call, Response<PengaduanModel> response) {
                if(response.isSuccessful()){
                    List<Pengaduan> pengaduanList = response.body().getListPengaduan();
                    recyclerView = v.findViewById(R.id.rvPengaduanBaruSemua);
                    layoutManager = new LinearLayoutManager(v.getContext());
                    PengaduanAdapter menuAdapter = new PengaduanAdapter(pengaduanList);
                    recyclerView.setAdapter(menuAdapter);
                    recyclerView.setLayoutManager(layoutManager);
                }
                else{
                    Toast.makeText(v.getContext(),"Pengaduan Masih Kosong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PengaduanModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }
}
