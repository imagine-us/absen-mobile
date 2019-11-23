package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

public class FormAbsensiActivity extends AppCompatActivity {

    TextView tanggalHariIni, lokasiHariIni;
    Calendar currentTime;
    ImageView profil, absensi;
    String tanggal,longitude,latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_absensi);


//
        currentTime = Calendar.getInstance();
        tanggalHariIni = findViewById(R.id.detailAbsensiHariIni);
        tanggalHariIni.setText(""+currentTime.getTime());
        tanggal=""+currentTime.getTime();

//        // GET CURRENT LOCATION
        lokasiHariIni= findViewById(R.id.lokasiAbsensiHariIni);
        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    // Do it all with location
                    Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                    // Display in Toast
                   lokasiHariIni.setText("Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
//                  //  Toast.makeText(FormAbsensiActivity.this,
//                            "Lat : " + location.getLatitude() + " Long : " + location.getLongitude(),
//                            Toast.LENGTH_LONG).show();
                    latitude=""+location.getLatitude();
                    longitude=""+location.getLongitude();
                }
            }
        });


        profil = findViewById(R.id.imageProfilAbsensi);
        Picasso.with(this).load(R.drawable.rudiantara).transform(new PicassoCircleTransformation()).into(profil);

        absensi = findViewById(R.id.iconAmbilPresensi);
        absensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Toast.makeText(FormAbsensiActivity.this,""+tanggal+" - "+longitude+" - "+latitude,Toast.LENGTH_LONG).show();


            }
        });

    }



}
