package com.example.kominfopangkalabun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kominfopangkalabun.adapter.AbsensiAdapter;
import com.example.kominfopangkalabun.adapter.PekerjaanAdapter;
import com.example.kominfopangkalabun.model.Pekerjaan.Pekerjaan;
import com.example.kominfopangkalabun.retrofit.BaseApiService;

import java.util.ArrayList;
import java.util.List;

public class FragmentMonitoringBelumDikoreksi extends Fragment {
    BaseApiService mApiService;
    View v;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        v = inflater.inflate(R.layout.fragment_monitoring_dbelumdikoreksi,container,false);

        List<Pekerjaan> pekerjaanList = new ArrayList<>();
        recyclerView= v.findViewById(R.id.rvMonitoringBelumDikoreksi);
        layoutManager = new LinearLayoutManager(getContext());
        PekerjaanAdapter menuAdapter = new PekerjaanAdapter(pekerjaanList);
        recyclerView.setAdapter(menuAdapter);
        recyclerView.setLayoutManager(layoutManager);

        return v;
    }
}
