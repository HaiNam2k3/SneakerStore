/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoanh
 */
public class HoaDonChiTietq {

  private int MaHDCT;
    private int MaCTSP;
    private int MaHD;
    private int SoLuong;
    private double DonGia;
    private String HoTenNV;
     private String TenKH;
      private String TenSP;
       private String TenCLD;
        private int Size;
         private String TenMau;
          private double MucGiam;
           private double SoTienConLai;
           private double TongTien;

    public HoaDonChiTietq() {
    }

    public HoaDonChiTietq(int MaHDCT, int MaCTSP, int MaHD, int SoLuong, double DonGia, String HoTenNV, String TenKH, String TenSP, String TenCLD, int Size, String TenMau, double MucGiam, double SoTienConLai, double TongTien) {
        this.MaHDCT = MaHDCT;
        this.MaCTSP = MaCTSP;
        this.MaHD = MaHD;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.HoTenNV = HoTenNV;
        this.TenKH = TenKH;
        this.TenSP = TenSP;
        this.TenCLD = TenCLD;
        this.Size = Size;
        this.TenMau = TenMau;
        this.MucGiam = MucGiam;
        this.SoTienConLai = SoTienConLai;
        this.TongTien = TongTien;
    }

    public int getMaHDCT() {
        return MaHDCT;
    }

    public void setMaHDCT(int MaHDCT) {
        this.MaHDCT = MaHDCT;
    }

    public int getMaCTSP() {
        return MaCTSP;
    }

    public void setMaCTSP(int MaCTSP) {
        this.MaCTSP = MaCTSP;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public String getHoTenNV() {
        return HoTenNV;
    }

    public void setHoTenNV(String HoTenNV) {
        this.HoTenNV = HoTenNV;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getTenCLD() {
        return TenCLD;
    }

    public void setTenCLD(String TenCLD) {
        this.TenCLD = TenCLD;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }

    public String getTenMau() {
        return TenMau;
    }

    public void setTenMau(String TenMau) {
        this.TenMau = TenMau;
    }

    public double getMucGiam() {
        return MucGiam;
    }

    public void setMucGiam(double MucGiam) {
        this.MucGiam = MucGiam;
    }

    public double getSoTienConLai() {
        return SoTienConLai;
    }

    public void setSoTienConLai(double SoTienConLai) {
        this.SoTienConLai = SoTienConLai;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }
           
           
}
