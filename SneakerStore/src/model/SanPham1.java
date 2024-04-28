package model;

import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
public class SanPham1 {

    private int MaSP;
    private String TenSP;
    private LocalDate NgayNhap;
    private LocalDate NgayCapNhat;
    private boolean TrangThai;
    private DanhMuc1 MaDM;

    public SanPham1() {
    }

    public SanPham1(int MaSP, String TenSP, LocalDate NgayNhap, LocalDate NgayCapNhat, boolean TrangThai, DanhMuc1 MaDM) {
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

    public String getTenSP() {
        return TenSP;
    }

    public LocalDate getNgayNhap() {
        return NgayNhap;
    }

    public LocalDate getNgayCapNhat() {
        return NgayCapNhat;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public DanhMuc1 getMaDM() {
        return MaDM;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setNgayNhap(LocalDate NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public void setNgayCapNhat(LocalDate NgayCapNhat) {
        this.NgayCapNhat = NgayCapNhat;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public void setMaDM(DanhMuc1 MaDM) {
        this.MaDM = MaDM;
    }

    public SanPham1(String TenSP) {
        this.TenSP = TenSP;
    }

    public SanPham1(int MaSP) {
        this.MaSP = MaSP;
    }

    public SanPham1(String TenSP, boolean TrangThai) {
        this.TenSP = TenSP;
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return TenSP;
    }
    

}
