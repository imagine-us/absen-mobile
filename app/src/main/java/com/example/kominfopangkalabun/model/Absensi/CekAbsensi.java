package com.example.kominfopangkalabun.model.Absensi;

import com.google.gson.annotations.SerializedName;

public class CekAbsensi {

    @SerializedName("status")
    private String cekAbsensiStatus;

    public CekAbsensi(String cekAbsensiStatus) {
        this.cekAbsensiStatus = cekAbsensiStatus;
    }

    public String getCekAbsensiStatus() {
        return cekAbsensiStatus;
    }

    public void setCekAbsensiStatus(String cekAbsensiStatus) {
        this.cekAbsensiStatus = cekAbsensiStatus;
    }
}
