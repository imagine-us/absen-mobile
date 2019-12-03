package com.example.kominfopangkalabun.model.Agenda;

import com.google.gson.annotations.SerializedName;

public class Agenda {

    @SerializedName("id")
    private String id;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("isiagenda")
    private String isiagenda;

    public Agenda(String id, String tanggal, String isiagenda) {
        this.id = id;
        this.tanggal = tanggal;
        this.isiagenda = isiagenda;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getIsiagenda() {
        return isiagenda;
    }

    public void setIsiagenda(String isiagenda) {
        this.isiagenda = isiagenda;
    }
}
