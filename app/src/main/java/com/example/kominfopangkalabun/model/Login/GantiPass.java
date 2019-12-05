package com.example.kominfopangkalabun.model.Login;

import com.google.gson.annotations.SerializedName;

public class GantiPass {

    @SerializedName("id")
    private String id;

    @SerializedName("nip")
    private String nip;

    @SerializedName("password")
    private String password;

    @SerializedName("foto")
    private String foto;

    public GantiPass(String id, String nip, String password, String foto) {
        this.id = id;
        this.nip = nip;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
