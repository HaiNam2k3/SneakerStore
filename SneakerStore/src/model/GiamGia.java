/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author trung
 */
public class GiamGia {
    private int maGG;
    private String tenMaGiam;
    private Float mucGiam;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String ghiChu;

    public GiamGia() {
    }

    public GiamGia(int maGG, String tenMaGiam, Float mucGiam, Date ngayBatDau, Date ngayKetThuc, String ghiChu) {
        this.maGG = maGG;
        this.tenMaGiam = tenMaGiam;
        this.mucGiam = mucGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.ghiChu = ghiChu;
    }

    public int getMaGG() {
        return maGG;
    }

    public void setMaGG(int maGG) {
        this.maGG = maGG;
    }

    public String getTenMaGiam() {
        return tenMaGiam;
    }

    public void setTenMaGiam(String tenMaGiam) {
        this.tenMaGiam = tenMaGiam;
    }

    public Float getMucGiam() {
        return mucGiam;
    }

    public void setMucGiam(Float mucGiam) {
        this.mucGiam = mucGiam;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
    
}

