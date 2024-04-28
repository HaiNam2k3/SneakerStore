package model;

/**
 *
 * @author ADMIN
 */
public class chiTietSanPham {
    private int MaCTSP;
    private int SoLuong;
    private float DonGia;
    private int MaSP;
    private String TenCLDe;
    private int TenSize;
    private String TenMS;
    private String TenCL;
        private float MucGG;
    private String MoTa;

    private String tenSP;

    public chiTietSanPham() {
    }

    public chiTietSanPham(int MaCTSP, int SoLuong, float DonGia, int MaSP, String TenCLDe, int TenSize, String TenMS, String TenCL, String MoTa, float MucGG, String tenSP) {
        this.MaCTSP = MaCTSP;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.MaSP = MaSP;
        this.TenCLDe = TenCLDe;
        this.TenSize = TenSize;
        this.TenMS = TenMS;
        this.TenCL = TenCL;
        this.MoTa = MoTa;
        this.MucGG = MucGG;
        this.tenSP = tenSP;
    }

    public int getMaCTSP() {
        return MaCTSP;
    }

    public void setMaCTSP(int MaCTSP) {
        this.MaCTSP = MaCTSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenCLDe() {
        return TenCLDe;
    }

    public void setTenCLDe(String TenCLDe) {
        this.TenCLDe = TenCLDe;
    }

    public int getTenSize() {
        return TenSize;
    }

    public void setTenSize(int TenSize) {
        this.TenSize = TenSize;
    }

    public String getTenMS() {
        return TenMS;
    }

    public void setTenMS(String TenMS) {
        this.TenMS = TenMS;
    }

    public String getTenCL() {
        return TenCL;
    }

    public void setTenCL(String TenCL) {
        this.TenCL = TenCL;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public float getMucGG() {
        return MucGG;
    }

    public void setMucGG(float MucGG) {
        this.MucGG = MucGG;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    

}
