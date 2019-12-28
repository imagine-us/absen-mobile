package com.example.kominfopangkalabun;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.kominfopangkalabun.model.Absensi.CekAbsensi;
import com.example.kominfopangkalabun.model.Absensi.DoAbsensi;
import com.example.kominfopangkalabun.model.Absensi.DoAbsensiModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
    String statusid,tanggal, longitude, latitude, pnsid, keterangan;
    Calendar currentTime;
    String nip,id,nama;
    double longi, lati;
    private SharedPreferences sp;

    LocationManager locationManager;
    String provider;
    Location location;
    Button datang, pulang;

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_peta,container,false);


        this.sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        nip = this.sp.getString("key_nip",null);
        id = this.sp.getString("key_id",null);
        nama = this.sp.getString("key_nama",null);

        currentTime = Calendar.getInstance();
        tanggal = "" + currentTime.getTime();
//
        locationManager =(LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

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

                  //  Toast.makeText(getActivity(),"lat:"+lati+"longi"+longi,Toast.LENGTH_LONG).show();
                    LatLng latLng = new LatLng(lati,longi);
                    googleMap.addMarker(new MarkerOptions().position(latLng)
                            .title("Posisi Anda Sekarang"));
                    float zoom = (float)16.0;
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
                }
            });
        }

        // R.id.map is a FrameLayout, not a Fragment
        getChildFragmentManager().beginTransaction().replace(R.id.mapFrame, mapFragment).commit();

        mApiService = UtilsApi.getAPIService();
       datang = v.findViewById(R.id.buttonAbsensiCameraDatang);
       pulang = v.findViewById(R.id.buttonAbsensiCameraPulang);

       datang.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mApiService.requestCekStatusAbsensi(id).enqueue(new Callback<DoAbsensiModel>() {
                   @Override
                   public void onResponse(Call<DoAbsensiModel> call, Response<DoAbsensiModel> response) {
                       List<DoAbsensi> doAbsensiList = response.body().getDoAbsensiList();
                       statusid = doAbsensiList.get(0).getSt_id();
                       keterangan = "D";
                       dispatchTakePictureIntent();
                   }

                   @Override
                   public void onFailure(Call<DoAbsensiModel> call, Throwable t) {
                       Toast.makeText(getContext(),"Gagal",Toast.LENGTH_SHORT).show();
                   }
               });
           }
       });

       pulang.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mApiService.requestCekStatusAbsensi(id).enqueue(new Callback<DoAbsensiModel>() {
                   @Override
                   public void onResponse(Call<DoAbsensiModel> call, Response<DoAbsensiModel> response) {
                       List<DoAbsensi> doAbsensiList = response.body().getDoAbsensiList();
                       statusid = doAbsensiList.get(0).getSt_id();
                       keterangan = "P";
                       dispatchTakePictureIntent();
                   }

                   @Override
                   public void onFailure(Call<DoAbsensiModel> call, Throwable t) {
                       Toast.makeText(getContext(),"Gagal",Toast.LENGTH_SHORT).show();
                   }
               });
           }
       });


//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//           public void onClick(View v) {
////
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
//                          } else{
//                                //Toast.makeText(getActivity(), "Anda tidak dapat absen untuk hari ini", Toast.LENGTH_LONG).show();
//                                showDialogCannot();
//                            }
//                        }
//                        else{
//                            showDialogCannot();
//                            //Toast.makeText(getActivity(), "Anda tidak dapat absen untuk hari ini", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getActivity(), ""+statusid+" "+id+" "+lati+" "+longi, Toast.LENGTH_LONG).show();
                mApiService.insertAbsen(statusid,id,""+lati,""+longi,keterangan).enqueue(new Callback<ResponseBody>() {
                  @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                       // Toast.makeText(getActivity(), "Absen Sukses:" + tanggal + " - " + longi + " - " + lati, Toast.LENGTH_LONG).show();
                        showDialogSuccsess();
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

    private void showDialogCannot(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        // set title dialog
        alertDialogBuilder.setTitle("Konfirmasi");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Anda tidak dapat melakukan absensi pada saat ini.")
                .setIcon(R.drawable.logo)
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    private void showDialogSuccsess(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        // set title dialog
        alertDialogBuilder.setTitle("Konfirmasi");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Anda berhasil melakukan absensi.")
                .setIcon(R.drawable.logo)
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();

        this.sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        nip = this.sp.getString("key_nip",null);
        id = this.sp.getString("key_id",null);
        nama = this.sp.getString("key_nama",null);

//        Toast.makeText(getContext(),"Pegawai id" + id,Toast.LENGTH_SHORT).show();

        mApiService.requestCekAbsensi(id).enqueue(new Callback<CekAbsensi>() {
            @Override
            public void onResponse(Call<CekAbsensi> call, Response<CekAbsensi> response) {

                if(response.body().getStatusId().equals("true")){
                   if(response.body().getDatang().equals("0")){
                       datang.setText("Absen Datang");
                   }
                   else if(response.body().getDatang().equals("1")){
                       datang.setText("Anda Sudah Absen Datang");
                       datang.setEnabled(false);
                   }
                   else if(response.body().getDatang().equals("2")){
                       datang.setText("Tidak Ada Absen Pulang");
                       datang.setEnabled(false);
                   }

                    if(response.body().getPulang().equals("0")){
                        pulang.setText("Absen Pulang");
                    }
                    else if(response.body().getPulang().equals("1")){
                        pulang.setText("Anda Sudah Absen Pulang");
                        pulang.setEnabled(false);
                    }
                    else if(response.body().getPulang().equals("2")){
                        pulang.setText("Tidak Ada Absen Pulang");
                        pulang.setEnabled(false);
                    }
                }
                else{
                    datang.setText("Tidak Ada Absen Datang");
                    pulang.setText("Tidak Ada Absen Pulang");
                    datang.setEnabled(false);
                    pulang.setEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<CekAbsensi> call, Throwable t) {
                Toast.makeText(getContext(),"Gagal",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean checkJarak(double lat1, double lon1, double lat2, double lon2){
        final int R = 6371; // Radious of the earth
        Double latDistance = toRad(lat2-lat1);
        Double lonDistance = toRad(lon2-lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = R * c;

        if(distance<=150){ return true;}
        else {return false;}


    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }
}