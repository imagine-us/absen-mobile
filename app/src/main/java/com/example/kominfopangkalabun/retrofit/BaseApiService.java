package com.example.kominfopangkalabun.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApiService {

    // Fungsi ini untuk memanggil API
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("nip") String nip,
                                    @Field("password") String password);


    // Fungsi ini untuk memanggil API
    @FormUrlEncoded
    @POST("insert_task.php")
    Call<ResponseBody> insertTask(@Field("judul") String judul,
                                    @Field("detail") String detail);

}
