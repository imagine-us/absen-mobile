package com.example.kominfopangkalabun.model.Kegiatan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KegiatanModel {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<Kegiatan> kegiatanList;

    public KegiatanModel(String status, List<Kegiatan> kegiatanList) {
        this.status = status;
        this.kegiatanList = kegiatanList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Kegiatan> getKegiatanList() {
        return kegiatanList;
    }

    public void setKegiatanList(List<Kegiatan> kegiatanList) {
        this.kegiatanList = kegiatanList;
    }
}
