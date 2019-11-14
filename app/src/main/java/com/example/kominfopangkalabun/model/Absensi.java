package com.example.kominfopangkalabun.model;

public class Absensi {

    String tanggal;
    String jam;
    String longitude;
    String latitude;
    String lokasi;
    String nip;

    public Absensi(String tanggal, String jam, String longitude, String latitude, String lokasi, String nip) {
        this.tanggal = tanggal;
        this.jam = jam;
        this.longitude = longitude;
        this.latitude = latitude;
        this.lokasi = lokasi;
        this.nip = nip;
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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
