package model;

import java.util.Date;

// MaKM	TenKhuyenMai	NgayBD	NgayKT	MucGiam	MaGiam	DonVi	TrangThai
public class Voucher {
    private int MaKM;
    private String TenKhuyenMai;
    private Date NgayBD;
    private Date NgayKT;
    private float MucGiam;
    private String MaGiam;
    private String DonVi;
    private String  TrangThai;

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
        Voucher other = (Voucher) obj;
        if (MaKM != other.MaKM)
            return false;
        return true;
    }

    public Voucher(String tenKhuyenMai, Date ngayBD, Date ngayKT, float mucGiam, String maGiam, String donVi,
            String trangThai) {
        TenKhuyenMai = tenKhuyenMai;
        NgayBD = ngayBD;
        NgayKT = ngayKT;
        MucGiam = mucGiam;
        MaGiam = maGiam;
        DonVi = donVi;
        TrangThai = trangThai;
    }

    public Voucher(int maKM, String tenKhuyenMai, Date ngayBD, Date ngayKT, float mucGiam, String maGiam,
            String donVi, String trangThai) {
        MaKM = maKM;
        TenKhuyenMai = tenKhuyenMai;
        NgayBD = ngayBD;
        NgayKT = ngayKT;
        MucGiam = mucGiam;
        MaGiam = maGiam;
        DonVi = donVi;
        TrangThai = trangThai;
    }

    public Voucher() {
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

    public float getMucGiam() {
        return MucGiam;
    }

    public void setMucGiam(float mucGiam) {
        MucGiam = mucGiam;
    }

    public String getMaGiam() {
        return MaGiam;
    }

    public void setMaGiam(String maGiam) {
        MaGiam = maGiam;
    }

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String donVi) {
        DonVi = donVi;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    @Override
    public String toString() {
        return "Voucher [MaKM=" + MaKM + ", TenKhuyenMai=" + TenKhuyenMai + ", NgayBD=" + NgayBD + ", NgayKT=" + NgayKT
                + ", MucGiam=" + MucGiam + ", MaGiam=" + MaGiam + ", DonVi=" + DonVi + ", TrangThai=" + TrangThai + "]";
    }

}
