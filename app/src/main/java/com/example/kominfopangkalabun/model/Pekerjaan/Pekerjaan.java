package com.example.kominfopangkalabun.model.Pekerjaan;

import com.google.gson.annotations.SerializedName;

public class Pekerjaan {

    @SerializedName("id")
    private String id;

    @SerializedName("pekerjaan_id")
    private String pekerjaanId;

    @SerializedName("rincian_pekerjaan_id")
    private String pekerjaanRincian;

    @SerializedName("nama_pekerjaan")
    private String pekerjaanNama;

    @SerializedName("tanggal")
    private String pekerjaanTanggal;

    @SerializedName("status")
    private String pekerjaanStatus;

    public Pekerjaan(String id, String pekerjaanId, String pekerjaanRincian, String pekerjaanNama, String pekerjaanTanggal, String pekerjaanStatus) {
        this.id = id;
        this.pekerjaanId = pekerjaanId;
        this.pekerjaanRincian = pekerjaanRincian;
        this.pekerjaanNama = pekerjaanNama;
        this.pekerjaanTanggal = pekerjaanTanggal;
        this.pekerjaanStatus = pekerjaanStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPekerjaanStatus() {
        return pekerjaanStatus;
    }

    public void setPekerjaanStatus(String pekerjaanStatus) {
        this.pekerjaanStatus = pekerjaanStatus;
    }
}
