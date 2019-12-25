package com.example.kominfopangkalabun.retrofit;

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

import com.example.kominfopangkalabun.R;
import com.example.kominfopangkalabun.adapter.AbsensiAdapter;
import com.example.kominfopangkalabun.model.Absensi.Absensi;
import com.example.kominfopangkalabun.model.Absensi.AbsensiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRiwayatPresensi extends Fragment {

    View v;
    RecyclerView recyclerView;
    BaseApiService mApiService;
    LinearLayoutManager layoutManager;
    String nip,id,nama;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_presensi,container,false);

        this.sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        nip = this.sp.getString("key_nip",null);
        id = this.sp.getString("key_id",null);
        nama = this.sp.getString("key_nama",null);

        mApiService = UtilsApi.getAPIService();

        Call<AbsensiModel> call = mApiService.requestAbsensiHistori(id);
        call.enqueue(new Callback<AbsensiModel>() {
            @Override
            public void onResponse(Call<AbsensiModel> call, Response<AbsensiModel> response) {
                if(response.isSuccessful()){
                    List<Absensi> absensiList = response.body().getListAbsensi();
                    recyclerView= v.findViewById(R.id.rv);
                    layoutManager = new LinearLayoutManager(getContext());
                    AbsensiAdapter menuAdapter = new AbsensiAdapter(absensiList);
                    recyclerView.setAdapter(menuAdapter);
                    recyclerView.setLayoutManager(layoutManager);
                }
                else{
                    Toast.makeText(getActivity(), "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AbsensiModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });



        return v;
    }
}
