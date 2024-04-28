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
public class hoaDon {

    private int MaHD;
    private String tenNV;
    private String NgayTao;
    private double TongTien;
    private boolean TrangThai;
    private int MaTTKH;
    private int MaNV;
    private int MaCTSP;
    private int tongHH;

    public hoaDon(int tongHH) {
        this.tongHH = tongHH;
    }

    public int getTongHH() {
        return tongHH;
    }

    public int getMaCTSP() {
        return MaCTSP;
    }

    public void setMaCTSP(int MaCTSP) {
        this.MaCTSP = MaCTSP;
    }

    public void setTongHH(int tongHH) {
        this.tongHH = tongHH;
    }
    
    
    public hoaDon() {
    }

    public hoaDon(int MaHD,String tenNV, String NgayTao, double TongTien, boolean TrangThai, int MaTTKH, int MaNV) {
        this.MaHD = MaHD;
        this.tenNV=tenNV;
        this.NgayTao = NgayTao;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
        this.MaTTKH = MaTTKH;
        this.MaNV = MaNV;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getMaTTKH() {
        return MaTTKH;
    }

    public void setMaTTKH(int MaTTKH) {
        this.MaTTKH = MaTTKH;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }
    
    
    
}
