package com.example.kominfopangkalabun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.kominfopangkalabun.model.Absensi.CekAbsensi;
import com.example.kominfopangkalabun.model.Absensi.CekAbsensiModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class FragmentPeta extends Fragment {

    BaseApiService mApiService;
    private SupportMapFragment mapFragment;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageAbsen;
    String statusid,tanggal, longitude, latitude, pnsid;
    Calendar currentTime;
    String nip,id,nama;
    double longi, lati;
    private SharedPreferences sp;

    LocationManager locationManager;
    String provider;
    Location location;


    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_peta,container,false);


//        this.sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
//        nip = this.sp.getString("key_nip",null);
//        id = this.sp.getString("key_id",null);
//        nama = this.sp.getString("key_nama",null);

        currentTime = Calendar.getInstance();
        tanggal = "" + currentTime.getTime();
//        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(getContext());
//        mFusedLocation.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null) {
//                    // Do it all with location
//                    Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
//                    // Display in Toast
//
//                    latitude = "" + location.getLatitude();
//                    lati = location.getLatitude();
//                    longitude = "" + location.getLongitude();
//                    longi = location.getLongitude();
//                }
//            }
//        });
        locationManager =(LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
//        Criteria c=new Criteria();
        //if we pass false than
        //it will check first satellite location than Internet and than Sim Network
//        provider=locationManager.getBestProvider(c, false);
        if ((ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
//            location=locationManager.getLastKnownLocation(provider);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if(location!=null)
        {
            longi =location.getLongitude();
            lati =location.getLatitude();
        }
        else
        {
//            textViewLongLat.setText("No Provider");
        }

        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                    Toast.makeText(getActivity(),"lat:"+lati+"longi"+longi,Toast.LENGTH_LONG).show();
                    LatLng latLng = new LatLng(lati,longi);
                    googleMap.addMarker(new MarkerOptions().position(latLng)
                            .title("Posisi Anda Sekarang"));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                }
            });
        }

        // R.id.map is a FrameLayout, not a Fragment
        getChildFragmentManager().beginTransaction().replace(R.id.mapFrame, mapFragment).commit();

//        mApiService = UtilsApi.getAPIService();
//        Button b = v.findViewById(R.id.buttonAbsensiCamera);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mApiService.requestCekAbsensi(id).enqueue(new Callback<CekAbsensiModel>() {
//                    @Override
//                    public void onResponse(Call<CekAbsensiModel> call, Response<CekAbsensiModel> response) {
////
//                        if (response.isSuccessful()) {
//                            List<CekAbsensi> listcekabsensi = response.body().getListCekAbsensi();
//                            String status = response.body().getStatusCekAbsensi();
//
//                            if (status.equals("true")) {
//
//                                statusid = listcekabsensi.get(0).getStatusId();
//                                dispatchTakePictureIntent();
//                            } else{
//                                Toast.makeText(getActivity(), "Anda tidak dapat absen untuk hari ini", Toast.LENGTH_LONG).show();
//                            }
//                        }
//                        else{
//                            Toast.makeText(getActivity(), "Anda tidak dapat absen untuk hari ini", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<CekAbsensiModel> call, Throwable t) {
//                        Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        });

        return v;
    }


    //-----------------------------------------------------



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageAbsen=imageBitmap;

            if(checkAWS(imageAbsen)){
                mApiService.insertAbsen(statusid,pnsid,latitude,longitude).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getActivity(), "Absen Sukses:" + tanggal + " - " + longitude + " - " + latitude, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity(), "Absen Gagal", Toast.LENGTH_LONG).show();

                    }
                });
            }

        }
    }

    public boolean checkAWS(Bitmap image){
        // cek aws
        if(image!=null) {
            return true;
        }
        return false;
    }


}
