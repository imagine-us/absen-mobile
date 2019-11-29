package com.example.kominfopangkalabun.model.Pekerjaan;

import com.google.gson.annotations.SerializedName;

public class Pekerjaan {

    @SerializedName("pekerjaan_id")
    private String pekerjaanId;

    @SerializedName("rincian_pekerjaan_id")
    private String pekerjaanRincian;

    @SerializedName("nama_pekerjaan")
    private String pekerjaanNama;

    @SerializedName("tanggal")
    private String pekerjaanTanggal;

    public Pekerjaan(String pekerjaanId, String pekerjaanRincian, String pekerjaanNama, String pekerjaanTanggal) {
        this.pekerjaanId = pekerjaanId;
        this.pekerjaanRincian = pekerjaanRincian;
        this.pekerjaanNama = pekerjaanNama;
        this.pekerjaanTanggal = pekerjaanTanggal;
    }

    public String getPekerjaanId() {
        return pekerjaanId;
    }

    public void setPekerjaanId(String pekerjaanId) {
        this.pekerjaanId = pekerjaanId;
    }

    public String getPekerjaanRincian() {
        return pekerjaanRincian;
    }

    public void setPekerjaanRincian(String pekerjaanRincian) {
        this.pekerjaanRincian = pekerjaanRincian;
    }

    public String getPekerjaanNama() {
        return pekerjaanNama;
    }

    public void setPekerjaanNama(String pekerjaanNama) {
        this.pekerjaanNama = pekerjaanNama;
    }

    public String getPekerjaanTanggal() {
        return pekerjaanTanggal;
    }

    public void setPekerjaanTanggal(String pekerjaanTanggal) {
        this.pekerjaanTanggal = pekerjaanTanggal;
    }
}
