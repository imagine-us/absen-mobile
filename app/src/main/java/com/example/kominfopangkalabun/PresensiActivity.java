package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.kominfopangkalabun.adapter.AbsensiAdapter;
import com.example.kominfopangkalabun.model.Absensi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PresensiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Absensi absensi;
    List<Absensi> absensiList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    ImageView profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presensi);

        profil = findViewById(R.id.imageProfilPresensi);
        Picasso.with(this).load(R.drawable.rudiantara).transform(new PicassoCircleTransformation()).into(profil);


        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
        absensiList.add(absensi);

        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
        absensiList.add(absensi);

        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
        absensiList.add(absensi);

        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
        absensiList.add(absensi);

        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
        absensiList.add(absensi);

        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
        absensiList.add(absensi);

        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
        absensiList.add(absensi);

        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
        absensiList.add(absensi);

        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
        absensiList.add(absensi);

        recyclerView= findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(this);
        AbsensiAdapter menuAdapter = new AbsensiAdapter(absensiList);
        recyclerView.setAdapter(menuAdapter);
        recyclerView.setLayoutManager(layoutManager);


    }
}
