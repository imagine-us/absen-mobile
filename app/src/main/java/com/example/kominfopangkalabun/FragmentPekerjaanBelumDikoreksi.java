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

import com.example.kominfopangkalabun.adapter.PekerjaanAdapter;
import com.example.kominfopangkalabun.model.Pekerjaan.Pekerjaan;
import com.example.kominfopangkalabun.model.Pekerjaan.PekerjaanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPekerjaanBelumDikoreksi extends Fragment {
    BaseApiService mApiService;
    View v;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private SharedPreferences sp;
    String bulan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        v = inflater.inflate(R.layout.fragment_pekerjaan_dbelumdikoreksi,container,false);

        mApiService = UtilsApi.getAPIService();
        this.sp = getContext().getSharedPreferences("sp", Context.MODE_PRIVATE);

        bulan = getArguments().getString("bulan");

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Call<PekerjaanModel> call = mApiService.requestPekerjaanHistoryStatus(this.sp.getString("key_nip",null),"0", bulan);
        call.enqueue(new Callback<PekerjaanModel>() {
            @Override
            public void onResponse(Call<PekerjaanModel> call, Response<PekerjaanModel> response) {
                if(response.isSuccessful()){
                    List<Pekerjaan> pekerjaanList = response.body().getPekerjaanModelList();
                    recyclerView = v.findViewById(R.id.rvPekerjaanBelumDikoreksi);
                    layoutManager = new LinearLayoutManager(v.getContext());
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
    }
}
