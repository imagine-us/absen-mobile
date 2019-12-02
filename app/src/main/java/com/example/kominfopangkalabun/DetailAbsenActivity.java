package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailAbsenActivity extends AppCompatActivity {

    TextView waktu;
    Button back;

    private SupportMapFragment mapFragment;
    double longi, lati;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absen);


        Intent intent = getIntent();
        String[] detailAbsensi = intent.getStringArrayExtra("Detail");

        longi = Double.parseDouble(detailAbsensi[2]);
        lati = Double.parseDouble(detailAbsensi[3]);

        waktu= findViewById(R.id.waktuDetailPresensi);
        waktu.setText(detailAbsensi[0]+"  "+detailAbsensi[1]);

        back= findViewById(R.id.btnBackDetailPresensi);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(DetailAbsenActivity.this, PresensiBaruActivity.class);
                i.putExtra("flag","riwayat");
                startActivity(i);
            }
        });

        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                    //  Toast.makeText(getActivity(),"lat:"+lati+"longi"+longi,Toast.LENGTH_LONG).show();
                    LatLng latLng = new LatLng(lati,longi);
                    googleMap.addMarker(new MarkerOptions().position(latLng)
                            .title("Posisi Absensi Anda"));
                    float zoom = (float)16.0;
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
                }
            });
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mapDetail, mapFragment);
        ft.commit();



    }




}
