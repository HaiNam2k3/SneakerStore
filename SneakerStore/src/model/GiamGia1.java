package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class GiamGia1 {

    private int MaGG;
    private String TenMaGiam;
    private float MucGiam;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private String GhiChu;

    public GiamGia1() {
    }

    public GiamGia1(int MaGG, String TenMaGiam, float MucGiam, Date NgayBatDau, Date NgayKetThuc, String GhiChu) {
        this.MaGG = MaGG;
        this.TenMaGiam = TenMaGiam;
        this.MucGiam = MucGiam;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.GhiChu = GhiChu;
    }

    public int getMaGG() {
        return MaGG;
    }

    public String getTenMaGiam() {
        return TenMaGiam;
    }

    public float getMucGiam() {
        return MucGiam;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setMaGG(int MaGG) {
        this.MaGG = MaGG;
    }

    public void setTenMaGiam(String TenMaGiam) {
        this.TenMaGiam = TenMaGiam;
    }

    public void setMucGiam(float MucGiam) {
        this.MucGiam = MucGiam;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public GiamGia1(String TenMaGiam) {
        this.TenMaGiam = TenMaGiam;
    }

    public GiamGia1(int MaGG) {
        this.MaGG = MaGG;
    }

    public GiamGia1(int MaGG, String TenMaGiam) {
        this.MaGG = MaGG;
        this.TenMaGiam = TenMaGiam;
    }

    @Override
    public String toString() {
        return TenMaGiam;
    }

}
