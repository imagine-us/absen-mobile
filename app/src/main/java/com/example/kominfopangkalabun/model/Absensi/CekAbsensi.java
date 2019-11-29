package com.example.kominfopangkalabun.model.Absensi;

import com.google.gson.annotations.SerializedName;

public class CekAbsensi {

    @SerializedName("st_id")
    private String statusId;

    public CekAbsensi(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
