package com.example.kominfopangkalabun.model.Monitoring;

import com.google.gson.annotations.SerializedName;

public class Bawahan {

    @SerializedName("mon_id")
    private String id;

    @SerializedName("mon_nip")
    private String nip;

    @SerializedName("mon_nama")
    private String nama;

    @SerializedName("mon_foto")
    private String foto;

    public Bawahan(String id, String nip, String nama, String foto) {
        this.id = id;
        this.nip = nip;
        this.nama = nama;
        this.foto = foto;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
