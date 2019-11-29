package com.example.kominfopangkalabun.model.Absensi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CekAbsensiModel {

    @SerializedName("status")
    private String statusCekAbsensi;

    @SerializedName("message")
    private String messageCekAbsensi;

    @SerializedName("data")
    private List<CekAbsensi> listCekAbsensi;

    public CekAbsensiModel(String statusCekAbsensi, String messageCekAbsensi, List<CekAbsensi> listCekAbsensi) {
        this.statusCekAbsensi = statusCekAbsensi;
        this.messageCekAbsensi = messageCekAbsensi;
        this.listCekAbsensi = listCekAbsensi;
    }

    public String getStatusCekAbsensi() {
        return statusCekAbsensi;
    }

    public void setStatusCekAbsensi(String statusCekAbsensi) {
        this.statusCekAbsensi = statusCekAbsensi;
    }

    public String getMessageCekAbsensi() {
        return messageCekAbsensi;
    }

    public void setMessageCekAbsensi(String messageCekAbsensi) {
        this.messageCekAbsensi = messageCekAbsensi;
    }

    public List<CekAbsensi> getListCekAbsensi() {
        return listCekAbsensi;
    }

    public void setListCekAbsensi(List<CekAbsensi> listCekAbsensi) {
        this.listCekAbsensi = listCekAbsensi;
    }
}
