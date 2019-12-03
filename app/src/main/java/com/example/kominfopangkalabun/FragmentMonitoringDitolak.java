package com.example.kominfopangkalabun;

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

import com.example.kominfopangkalabun.adapter.PekerjaanAdapter;
import com.example.kominfopangkalabun.model.Pekerjaan.Pekerjaan;
import com.example.kominfopangkalabun.model.Pekerjaan.PekerjaanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMonitoringDitolak extends Fragment {
    BaseApiService mApiService;
    View v;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    String bulan, idbawahan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        v = inflater.inflate(R.layout.fragment_monitoring_ditolak,container,false);
        mApiService = UtilsApi.getAPIService();

        bulan = getArguments().getString("bulan");
        idbawahan = getArguments().getString("idbawahan");

        Call<PekerjaanModel> call = mApiService.requestPekerjaanHistoryStatus(idbawahan,"2",bulan);
        call.enqueue(new Callback<PekerjaanModel>() {
            @Override
            public void onResponse(Call<PekerjaanModel> call, Response<PekerjaanModel> response) {
                if(response.isSuccessful()){
                    List<Pekerjaan> pekerjaanList = response.body().getPekerjaanModelList();
                    recyclerView= v.findViewById(R.id.rvMonitoringDiterima);
                    layoutManager = new LinearLayoutManager(getContext());
                    PekerjaanAdapter menuAdapter = new PekerjaanAdapter(pekerjaanList);
                    recyclerView.setAdapter(menuAdapter);
                    recyclerView.setLayoutManager(layoutManager);
                }
                else{
                    Toast.makeText(v.getContext(),"Pekerjaan Masih Kosong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PekerjaanModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });

        return v;
    }
}
