package com.example.kominfopangkalabun.model.Pengaduan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PengaduanTanggapanModel {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<PengaduanTanggapanClass> pengaduanTanggapanClass;

    public PengaduanTanggapanModel(String status, List<PengaduanTanggapanClass> pengaduanTanggapanClass) {
        this.status = status;
        this.pengaduanTanggapanClass = pengaduanTanggapanClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PengaduanTanggapanClass> getPengaduanTanggapanClass() {
        return pengaduanTanggapanClass;
    }

    public void setPengaduanTanggapanClass(List<PengaduanTanggapanClass> pengaduanTanggapanClass) {
        this.pengaduanTanggapanClass = pengaduanTanggapanClass;
    }
}
