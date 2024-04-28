package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class sanPham {
    private int MaSP;
    private String TenSP;
    private Date NgayNhap;
    private Date NgayCapNhat;
    private boolean TrangThai;
    private int MaDM;

    public sanPham() {
    }

    public sanPham(int MaSP, String TenSP, Date NgayNhap, Date NgayCapNhat, boolean TrangThai, int MaDM) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.NgayNhap = NgayNhap;
        this.NgayCapNhat = NgayCapNhat;
        this.TrangThai = TrangThai;
        this.MaDM = MaDM;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public Date getNgayCapNhat() {
        return NgayCapNhat;
    }

    public void setNgayCapNhat(Date NgayCapNhat) {
        this.NgayCapNhat = NgayCapNhat;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getMaDM() {
        return MaDM;
    }

    public void setMaDM(int MaDM) {
        this.MaDM = MaDM;
    }
    
    
}
