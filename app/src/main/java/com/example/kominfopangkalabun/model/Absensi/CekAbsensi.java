package com.example.kominfopangkalabun.model.Absensi;

import com.google.gson.annotations.SerializedName;

//kelas untuk menerima pengembalian dari server dalam melakukan cek

public class CekAbsensi {

    @SerializedName("status")
    private String statusId;

    @SerializedName("datang")
    private String datang;

    @SerializedName("pulang")
    private String pulang;

    public CekAbsensi(String statusId, String datang, String pulang) {
        this.statusId = statusId;
        this.datang = datang;
        this.pulang = pulang;
    }

    public CekAbsensi(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getDatang() {
        return datang;
    }

    public void setDatang(String datang) {
        this.datang = datang;
    }

    public String getPulang() {
        return pulang;
    }

    public void setPulang(String pulang) {
        this.pulang = pulang;
    }
}
