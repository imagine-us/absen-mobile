package com.example.kominfopangkalabun.model.Absensi;

import com.google.gson.annotations.SerializedName;

public class DoAbsensi {

    @SerializedName("st_id")
    private String st_id;

    @SerializedName("pns_id")
    private String pns_id;

    @SerializedName("st_tgl")
    private String st_tgl;

    @SerializedName("st_lang")
    private String st_lang;

    @SerializedName("st_long")
    private String st_long;

    @SerializedName("st_status")
    private String st_status;

    @SerializedName("st_ket")
    private String st_ket;

    public DoAbsensi(String st_id, String pns_id, String st_tgl, String st_lang, String st_long, String st_status, String st_ket) {
        this.st_id = st_id;
        this.pns_id = pns_id;
        this.st_tgl = st_tgl;
        this.st_lang = st_lang;
        this.st_long = st_long;
        this.st_status = st_status;
        this.st_ket = st_ket;
    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public String getPns_id() {
        return pns_id;
    }

    public void setPns_id(String pns_id) {
        this.pns_id = pns_id;
    }

    public String getSt_tgl() {
        return st_tgl;
    }

    public void setSt_tgl(String st_tgl) {
        this.st_tgl = st_tgl;
    }

    public String getSt_lang() {
        return st_lang;
    }

    public void setSt_lang(String st_lang) {
        this.st_lang = st_lang;
    }

    public String getSt_long() {
        return st_long;
    }

    public void setSt_long(String st_long) {
        this.st_long = st_long;
    }

    public String getSt_status() {
        return st_status;
    }

    public void setSt_status(String st_status) {
        this.st_status = st_status;
    }

    public String getSt_ket() {
        return st_ket;
    }

    public void setSt_ket(String st_ket) {
        this.st_ket = st_ket;
    }
}
