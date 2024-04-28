/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author hoanh
 */
public class KhachHang {
    private int maKH ;
    private String tenKH;
    private String sdt;
    private String ngayCN;
    private int trangThai;

    public KhachHang() {
    }

    public KhachHang(int maKH, String tenKH, String sdt, String ngayCN, int trangThai) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.ngayCN = ngayCN;
        this.trangThai = trangThai;
    }
    
    public KhachHang(String ten, String sdt, String ngayCN, int trangThai) {
        this.tenKH = ten;
        this.sdt = sdt;
        this.ngayCN = ngayCN;
        this.trangThai = trangThai;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgayCN() {
        return ngayCN;
    }

    public void setNgayCN(String ngayCN) {
        this.ngayCN = ngayCN;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public Object[] toDarow() {
        return new Object[]{
            this.getMaKH(), this.getTenKH(), this.getSdt(), this.getNgayCN(), this.getTrangThai()
        };
    }
    
}
