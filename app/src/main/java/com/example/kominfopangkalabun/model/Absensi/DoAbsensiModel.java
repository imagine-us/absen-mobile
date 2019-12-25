package com.example.kominfopangkalabun.model.Absensi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DoAbsensiModel {

    @SerializedName("data")
    private List<DoAbsensi> doAbsensiList;

    @SerializedName("status")
    private String status;

    public DoAbsensiModel(List<DoAbsensi> doAbsensiList, String status) {
        this.doAbsensiList = doAbsensiList;
        this.status = status;
    }

    public List<DoAbsensi> getDoAbsensiList() {
        return doAbsensiList;
    }

    public void setDoAbsensiList(List<DoAbsensi> doAbsensiList) {
        this.doAbsensiList = doAbsensiList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
