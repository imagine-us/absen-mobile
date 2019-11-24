package com.example.kominfopangkalabun.model.Absensi;

import com.google.gson.annotations.SerializedName;

public class Absensi {

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("waktu")
    private String jam;

    @SerializedName("al_long")
    private String longitude;

    @SerializedName("al_lat")
    private String latitude;

    //yang dimaksud lokasi ini data yang mana
    private String lokasi;

    @SerializedName("pns_id")
    private String pns_id;

    public Absensi(String tanggal, String jam, String longitude, String latitude, String pns_id) {
        this.tanggal = tanggal;
        this.jam = jam;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pns_id = pns_id;
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
}
