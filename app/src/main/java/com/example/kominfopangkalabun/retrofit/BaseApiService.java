package com.example.kominfopangkalabun.retrofit;

import com.example.kominfopangkalabun.PengaduanTanggapan;
import com.example.kominfopangkalabun.model.Absensi.AbsensiModel;
import com.example.kominfopangkalabun.model.Absensi.CekAbsensi;
import com.example.kominfopangkalabun.model.Absensi.CekAbsensiModel;
import com.example.kominfopangkalabun.model.Agenda.AgendaModel;
import com.example.kominfopangkalabun.model.Kegiatan.Kegiatan;
import com.example.kominfopangkalabun.model.Kegiatan.KegiatanModel;
import com.example.kominfopangkalabun.model.Login.GantiPass;
import com.example.kominfopangkalabun.model.Login.Login;
import com.example.kominfopangkalabun.model.Monitoring.BawahanModel;
import com.example.kominfopangkalabun.model.Pekerjaan.PekerjaanModel;
import com.example.kominfopangkalabun.model.Pengaduan.PengaduanModel;
import com.example.kominfopangkalabun.model.Pengaduan.PengaduanTanggapanModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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


    // Fungsi login
    @GET("login")
    Call<Login> tryLogin(@Query("nip") String nip,
                                 @Query("password") String password);

     //Fungsi ambil absensi
     @GET("absensihist")
     Call<AbsensiModel> requestAbsensiHistori(@Query("id") String id);

    @GET("cekabsen")
    Call<CekAbsensiModel> requestCekAbsensi(@Query("id") String id);

     //Fungsi ambil data pengaduan untuk recycle view
     @GET("pengaduan")
     Call<PengaduanModel> requestPengaduan(@Query("pnsid") String id);

    @FormUrlEncoded
    @POST("inputpengaduan")
    Call<PengaduanModel> insertPengaduan(@Field("pnsid") String pnsid,
                                   @Field("pengaduan") String pengaduan);

    @GET("pekerjaanhist")
    Call<PekerjaanModel> requestPekerjaanHistory(@Query("nip") String nip, @Query("bulan") String bulan);

    @GET("pekerjaanhiststatus")
    Call<PekerjaanModel> requestPekerjaanHistoryStatus(@Query("nip") String nip,@Query("status") String status, @Query("bulan") String bulan);

    @GET("pekerjaandetail")
    Call<KegiatanModel> requestPekerjaanDetail(@Query("pid") String pid);

    @FormUrlEncoded
    @POST("inputpekerjaan")
    Call<PekerjaanModel> insertPekerjaan(@Field("nip") String nip, @Field("uraian") String uraian, @Field("analisis") String analisis, @Field("tgl") String tgl,
                                         @Field("waktumulai") String waktumulai, @Field("durasi") String durasi, @Field("pekerjaan") String pekerjaan,@Field("hasil") String hasil);
    @FormUrlEncoded
    @PUT("updatepekerjaan")
    Call<KegiatanModel> updateStatusPekerjaan(@Field("id") String id,@Field("status") String status);

    @GET("pengaduandetail")
    Call<PengaduanTanggapanModel> requstPengaduanDetail(@Query("aduanid") String id);

    @GET("bawahan")
    Call<BawahanModel> requestBawahan(@Query("nip") String nip);

    @GET("agenda")
    Call<AgendaModel> requestAgenda(@Query("pnsid") String id);


    @FormUrlEncoded
    @PUT("editprofile")
    Call<GantiPass> editprofile(@Field("id") String id, @Field("nip") String nip, @Field("password") String password, @Field("foto") String foto);

    @FormUrlEncoded
    @POST("inputagenda")
    Call<AgendaModel> insertagenda(@Field("pnsid") String pnsid,@Field("agenda") String agenda, @Field("tanggal") String Tanggal);
}
