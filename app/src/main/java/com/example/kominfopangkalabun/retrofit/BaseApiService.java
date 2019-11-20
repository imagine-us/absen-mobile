package com.example.kominfopangkalabun.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApiService {

    // Fungsi ini untuk memanggil API
    @FormUrlEncoded
    @GET("login")
    Call<ResponseBody> loginRequest(@Field("nip") String nip,
                                    @Field("password") String password);

    // mengambil histori absen luar
    @FormUrlEncoded
    @GET("absensihist")
    Call<ResponseBody> getHistori(@Field(("id")) String id);

    // mencek apakah bisa absen saat ini
    @FormUrlEncoded
    @GET("cekabsen")
    Call<ResponseBody> cekAbsen(@Field(("id")) String id);

    // memasukkan data absensi
    @FormUrlEncoded
    @POST("absensi")
    Call<ResponseBody> insertAbsen(@Field("statusid") String statusid,
                                   @Field("pnsid") String pnsid,
                                   @Field("latitude") String latitude,
                                   @Field("longitude") String longitude);

    // Fungsi ini untuk memanggil API
    @FormUrlEncoded
    @POST("insert_task.php")
    Call<ResponseBody> insertTask(@Field("judul") String judul,
                                  @Field("detail") String detail);



}
