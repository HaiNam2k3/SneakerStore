/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoanh
 */
public class HoaDonq {

    private int MaHD;
    private String NgayTao;
    private double TongTien;
    private String TrangThai;

    public HoaDonq() {
    }

    public HoaDonq(int MaHD, String NgayTao, double TongTien, String TrangThai) {
        this.MaHD = MaHD;
        this.NgayTao = NgayTao;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }

    public HoaDonq(int MaHD, String NgayTao, String TrangThai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
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

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
   

    

}
