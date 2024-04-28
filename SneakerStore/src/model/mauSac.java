package model;

/**
 *
 * @author ADMIN
 */
public class mauSac {
    private int MaMS;
    private String TenMau;
    private boolean TrangThai;

    public mauSac() {
    }

    public mauSac(int MaMS, String TenMau, boolean TrangThai) {
        this.MaMS = MaMS;
        this.TenMau = TenMau;
        this.TrangThai = TrangThai;
    }

    public int getMaMS() {
        return MaMS;
    }

    public void setMaMS(int MaMS) {
        this.MaMS = MaMS;
    }

    public String getTenMau() {
        return TenMau;
    }

    public void setTenMau(String TenMau) {
        this.TenMau = TenMau;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
