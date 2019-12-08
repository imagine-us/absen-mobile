package com.example.kominfopangkalabun.model.Monitoring;

import com.google.gson.annotations.SerializedName;

public class Bawahan {

    @SerializedName("id")
    private String id;

    @SerializedName("PNS_PNSNIP")
    private String nip;

    @SerializedName("PNS_PNSNAM")
    private String nama;

    public Bawahan(String id, String nip, String nama) {
        this.id = id;
        this.nip = nip;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}
