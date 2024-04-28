package model;

import java.util.Date;

// MaKM	TenKhuyenMai	NgayBD	NgayKT	MucGiam	MaGiam	DonVi	TrangThai
public class Voucherq {
    private int MaKM;
    private String TenKhuyenMai;
    private Date NgayBD;
    private Date NgayKT;
    private double MucGiam;
    private String MaGiam;
    private boolean DonVi;
    private boolean TrangThai;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + MaKM;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Voucherq other = (Voucherq) obj;
        if (MaKM != other.MaKM)
            return false;
        return true;
    }

    public Voucherq(String tenKhuyenMai, Date ngayBD, Date ngayKT, double mucGiam, String maGiam, boolean donVi,
            boolean trangThai) {
        TenKhuyenMai = tenKhuyenMai;
        NgayBD = ngayBD;
        NgayKT = ngayKT;
        MucGiam = mucGiam;
        MaGiam = maGiam;
        DonVi = donVi;
        TrangThai = trangThai;
    }

    public Voucherq(int maKM, String tenKhuyenMai, Date ngayBD, Date ngayKT, double mucGiam, String maGiam,
            boolean donVi, boolean trangThai) {
        MaKM = maKM;
        TenKhuyenMai = tenKhuyenMai;
        NgayBD = ngayBD;
        NgayKT = ngayKT;
        MucGiam = mucGiam;
        MaGiam = maGiam;
        DonVi = donVi;
        TrangThai = trangThai;
    }

    public Voucherq() {
    }

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int maKM) {
        MaKM = maKM;
    }

    public String getTenKhuyenMai() {
        return TenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        TenKhuyenMai = tenKhuyenMai;
    }

    public Date getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(Date ngayBD) {
        NgayBD = ngayBD;
    }

    public Date getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(Date ngayKT) {
        NgayKT = ngayKT;
    }

    public double getMucGiam() {
        return MucGiam;
    }

    public void setMucGiam(double mucGiam) {
        MucGiam = mucGiam;
    }

    public String getMaGiam() {
        return MaGiam;
    }

    public void setMaGiam(String maGiam) {
        MaGiam = maGiam;
    }

    public boolean isDonVi() {
        return DonVi;
    }

    public void setDonVi(boolean donVi) {
        DonVi = donVi;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    @Override
    public String toString() {
        return "Voucher [MaKM=" + MaKM + ", TenKhuyenMai=" + TenKhuyenMai + ", NgayBD=" + NgayBD + ", NgayKT=" + NgayKT
                + ", MucGiam=" + MucGiam + ", MaGiam=" + MaGiam + ", DonVi=" + DonVi + ", TrangThai=" + TrangThai + "]";
    }

}
