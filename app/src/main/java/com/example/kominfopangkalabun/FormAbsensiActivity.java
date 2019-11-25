package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
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

    TextView tanggalHariIni, lokasiHariIni;
    Calendar currentTime;
    ImageView profil, absensi;
    String tanggal, longitude, latitude, pnsid;
    Bitmap imageAbsen;

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
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_absensi);

        pnsid="123";
        mApiService = UtilsApi.getAPIService();
//
        currentTime = Calendar.getInstance();
        tanggalHariIni = findViewById(R.id.detailAbsensiHariIni);
        tanggalHariIni.setText("" + currentTime.getTime());
        tanggal = "" + currentTime.getTime();

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
        Picasso.with(this).load(R.drawable.rudiantara).transform(new PicassoCircleTransformation()).into(profil);

        absensi = findViewById(R.id.iconAmbilPresensi);
        absensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dispatchTakePictureIntent();

                if(checkAWS(imageAbsen)){
                    mApiService.insertAbsen("true",pnsid,latitude,longitude).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Toast.makeText(FormAbsensiActivity.this, "Absen Sukses:" + tanggal + " - " + longitude + " - " + latitude, Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(FormAbsensiActivity.this, "Absen Gagal", Toast.LENGTH_LONG).show();

                        }
                    });
                }

            }
        });

    }

    public boolean checkAWS(Bitmap image){
        // cek aws
        return true;
    }


}
