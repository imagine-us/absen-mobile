package com.example.kominfopangkalabun.model.Pengaduan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PengaduanModel {
    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<Pengaduan> listPengaduan;

    public PengaduanModel(String status, List<Pengaduan> listPengaduan) {
        this.status = status;
        this.listPengaduan = listPengaduan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pengaduan> getListPengaduan() {
        return listPengaduan;
    }

    public void setListPengaduan(List<Pengaduan> listPengaduan) {
        this.listPengaduan = listPengaduan;
    }
}
