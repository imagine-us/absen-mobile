package com.example.kominfopangkalabun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kominfopangkalabun.retrofit.BaseApiService;

public class FragmentPekerjaanDiterima extends Fragment {
    BaseApiService mApiService;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        v = inflater.inflate(R.layout.fragment_pekerjaan_diterima,container,false);

        return v;
    }
}
