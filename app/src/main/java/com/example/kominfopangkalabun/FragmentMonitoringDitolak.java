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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        v = inflater.inflate(R.layout.fragment_monitoring_ditolak,container,false);

        List<Pekerjaan> pekerjaanList = new ArrayList<>();
        recyclerView= v.findViewById(R.id.rvMonitoringDitolak);
        layoutManager = new LinearLayoutManager(getContext());
        PekerjaanAdapter menuAdapter = new PekerjaanAdapter(pekerjaanList);
        recyclerView.setAdapter(menuAdapter);
        recyclerView.setLayoutManager(layoutManager);

        return v;
    }
}
