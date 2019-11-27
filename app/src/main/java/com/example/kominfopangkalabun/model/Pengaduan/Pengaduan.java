package com.example.kominfopangkalabun.model.Pengaduan;

import com.google.gson.annotations.SerializedName;

public class Pengaduan {

    @SerializedName("id")
    private String pengaduanId;

    @SerializedName("id_user")
    private String pengaduanPnsId;

//    @SerializedName("unor")
//    private String pengaduanUnor;

    @SerializedName("pengaduan")
    private String pengaduanIsi;

    @SerializedName("status")
    private String pengaduanStatus;

    @SerializedName("file")
    private String pengaduanFile;

    @SerializedName("tanggal_pengaduan")
    private String pengaduanTanggalPengaduan;

//    @SerializedName("tanggal_tanggapan")
//    private String getPengaduanTanggalTanggapan;

    public Pengaduan(String pengaduanId, String pengaduanPnsId, String pengaduanIsi, String pengaduanStatus, String pengaduanFile, String pengaduanTanggalPengaduan) {
        this.pengaduanId = pengaduanId;
        this.pengaduanPnsId = pengaduanPnsId;
        this.pengaduanIsi = pengaduanIsi;
        this.pengaduanStatus = pengaduanStatus;
        this.pengaduanFile = pengaduanFile;
        this.pengaduanTanggalPengaduan = pengaduanTanggalPengaduan;
//        this.getPengaduanTanggalTanggapan = getPengaduanTanggalTanggapan;
    }

    public Pengaduan(String pengaduanStatus, String pengaduanTanggalPengaduan) {
        this.pengaduanStatus = pengaduanStatus;
        this.pengaduanTanggalPengaduan = pengaduanTanggalPengaduan;
    }

    public String getPengaduanId() {
        return pengaduanId;
    }

    public void setPengaduanId(String pengaduanId) {
        this.pengaduanId = pengaduanId;
    }

    public String getPengaduanPnsId() {
        return pengaduanPnsId;
    }

    public void setPengaduanPnsId(String pengaduanPnsId) {
        this.pengaduanPnsId = pengaduanPnsId;
    }

    public String getPengaduanIsi() {
        return pengaduanIsi;
    }

    public void setPengaduanIsi(String pengaduanIsi) {
        this.pengaduanIsi = pengaduanIsi;
    }

    public String getPengaduanStatus() {
        return pengaduanStatus;
    }

    public void setPengaduanStatus(String pengaduanStatus) {
        this.pengaduanStatus = pengaduanStatus;
    }

    public String getPengaduanFile() {
        return pengaduanFile;
    }

    public void setPengaduanFile(String pengaduanFile) {
        this.pengaduanFile = pengaduanFile;
    }

    public String getPengaduanTanggalPengaduan() {
        return pengaduanTanggalPengaduan;
    }

    public void setPengaduanTanggalPengaduan(String pengaduanTanggalPengaduan) {
        this.pengaduanTanggalPengaduan = pengaduanTanggalPengaduan;
    }

//    public String getGetPengaduanTanggalTanggapan() {
//        return getPengaduanTanggalTanggapan;
//    }
//
//    public void setGetPengaduanTanggalTanggapan(String getPengaduanTanggalTanggapan) {
//        this.getPengaduanTanggalTanggapan = getPengaduanTanggalTanggapan;
//    }
}
