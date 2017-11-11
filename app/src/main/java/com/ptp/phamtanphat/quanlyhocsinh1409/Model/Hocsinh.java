package com.ptp.phamtanphat.quanlyhocsinh1409.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Hocsinh implements Serializable{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("TenHocSinh")
    @Expose
    private String tenHocSinh;
    @SerializedName("NamSinh")
    @Expose
    private String namSinh;
    @SerializedName("DiaChi")
    @Expose
    private String diaChi;


    public Hocsinh(String id, String tenHocSinh, String namSinh, String diaChi) {
        this.id = id;
        this.tenHocSinh = tenHocSinh;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenHocSinh() {
        return tenHocSinh;
    }

    public void setTenHocSinh(String tenHocSinh) {
        this.tenHocSinh = tenHocSinh;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

}