package com.example.kominfopangkalabun.model.Absensi;

import com.google.gson.annotations.SerializedName;

public class Absensi {

    //inisiasi variabel
    @SerializedName("al_id")
    private String absenid;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("waktu")
    private String jam;

    @SerializedName("al_long")
    private String longitude;

    @SerializedName("al_lat")
    private String latitude;


    @SerializedName("pns_id")
    private String pns_id;

    @SerializedName("ket")
    private String keterangan;

    //constructor kelas absensi
    public Absensi(String absenid,String tanggal, String jam, String longitude, String latitude, String pns_id, String keterangan) {
        this.absenid = absenid;
        this.tanggal = tanggal;
        this.jam = jam;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pns_id = pns_id;
        this.keterangan = keterangan;
    }

    //method setter getter absensi
    public String getAbsenid() {
        return absenid;
    }

    public void setAbsenid(String absenid) {
        this.absenid = absenid;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPns_id() {
        return pns_id;
    }

    public void setPns_id(String pns_id) {
        this.pns_id = pns_id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
