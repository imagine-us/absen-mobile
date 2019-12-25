package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.hardware.Camera.CameraInfo;

import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAbsensiActivity extends AppCompatActivity {

    TextView tanggalHariIni, lokasiHariIni, txtnama, txtnip;
    Calendar currentTime;
    ImageView profil, absensi;
    String tanggal, longitude, latitude, pnsid;
    Bitmap imageAbsen;
    String nip,id,nama;

    private SharedPreferences sp;
    BaseApiService mApiService;


    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageAbsen=imageBitmap;

            if(checkAWS(imageAbsen)){
                mApiService.insertAbsen(getIntent().getExtras().getString("st_id"),pnsid,latitude,longitude,"D").enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(FormAbsensiActivity.this, "Absen Sukses:" + tanggal + " - " + longitude + " - " + latitude, Toast.LENGTH_LONG).show();

                        startActivity(new Intent(FormAbsensiActivity.this,PresensiActivity.class));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(FormAbsensiActivity.this, "Absen Gagal", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_absensi);
        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);
        nip = this.sp.getString("key_nip",null);
        id = this.sp.getString("key_id",null);
        nama = this.sp.getString("key_nama",null);

        pnsid=id;
        mApiService = UtilsApi.getAPIService();
//
        currentTime = Calendar.getInstance();
        txtnama= findViewById(R.id.namaProfilAbsensi);
        txtnip= findViewById(R.id.nipProfilAbsensi);
        txtnama.setText(nama);
        txtnip.setText(nip);
        tanggalHariIni = findViewById(R.id.detailAbsensiHariIni);
        tanggalHariIni.setText("" + currentTime.getTime());
        tanggal = "" + currentTime.getTime();
        imageAbsen=null;
//        // GET CURRENT LOCATION
        lokasiHariIni = findViewById(R.id.lokasiAbsensiHariIni);
        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    // Do it all with location
                    Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                    // Display in Toast
                    lokasiHariIni.setText("Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                    latitude = "" + location.getLatitude();
                    longitude = "" + location.getLongitude();


                }
            }
        });


        profil = findViewById(R.id.imageProfilAbsensi);
        Picasso.with(this).load(this.sp.getString("key_foto",null)).transform(new PicassoCircleTransformation()).into(profil);

        absensi = findViewById(R.id.iconAmbilPresensi);
        absensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dispatchTakePictureIntent();



            }
        });

    }

    public boolean checkAWS(Bitmap image){
        // cek aws
        if(image!=null) {
            return true;
        }
        return false;
    }


}
