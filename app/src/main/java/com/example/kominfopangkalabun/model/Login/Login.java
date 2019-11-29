package com.example.kominfopangkalabun.model.Login;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("id")
    private String id;

    @SerializedName("nip")
    private String nip;

    @SerializedName("nama")
    private String nama;


    @SerializedName("foto")
    private String foto;

    public Login(String status, String message, String id, String nip, String nama, String foto) {
        this.status = status;
        this.message = message;
        this.id = id;
        this.nip = nip;
        this.nama = nama;
        this.foto = foto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
