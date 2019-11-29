package com.example.kominfopangkalabun.model.Pengaduan;

import com.google.gson.annotations.SerializedName;

public class PengaduanTanggapanModel {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private PengaduanTanggapanClass pengaduanTanggapanClass;

    public PengaduanTanggapanModel(String status, PengaduanTanggapanClass pengaduanTanggapanClass) {
        this.status = status;
        this.pengaduanTanggapanClass = pengaduanTanggapanClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PengaduanTanggapanClass getPengaduanTanggapanClass() {
        return pengaduanTanggapanClass;
    }

    public void setPengaduanTanggapanClass(PengaduanTanggapanClass pengaduanTanggapanClass) {
        this.pengaduanTanggapanClass = pengaduanTanggapanClass;
    }
}
