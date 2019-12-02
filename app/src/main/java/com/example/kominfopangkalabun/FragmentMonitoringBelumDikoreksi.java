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

import com.example.kominfopangkalabun.retrofit.BaseApiService;

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



        return v;
    }
}
