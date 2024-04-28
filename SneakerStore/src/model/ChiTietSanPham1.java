package model;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPham1 {

    private int MaCTSP;
    private int SoLuong;
    private float DonGia;
    private SanPham1 MaSP;
    private ChatLieuDeGiay1 MaCLDe;
    private Size1 MaSize;
    private MauSac1 MaMS;
    private ChatLieuMatGiay1 MaCL;
    private String MoTa;
    private GiamGia1 MaGG;

    public ChiTietSanPham1() {
    }

    public ChiTietSanPham1(int MaCTSP, int SoLuong, float DonGia, SanPham1 MaSP, ChatLieuDeGiay1 MaCLDe, Size1 MaSize, MauSac1 MaMS, ChatLieuMatGiay1 MaCL, String MoTa, GiamGia1 MaGG) {
        this.MaCTSP = MaCTSP;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.MaSP = MaSP;
        this.MaCLDe = MaCLDe;
        this.MaSize = MaSize;
        this.MaMS = MaMS;
        this.MaCL = MaCL;
        this.MoTa = MoTa;
        this.MaGG = MaGG;
    }

    public int getMaCTSP() {
        return MaCTSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public float getDonGia() {
        return DonGia;
    }

    public SanPham1 getMaSP() {
        return MaSP;
    }

    public ChatLieuDeGiay1 getMaCLDe() {
        return MaCLDe;
    }

    public Size1 getMaSize() {
        return MaSize;
    }

    public MauSac1 getMaMS() {
        return MaMS;
    }

    public ChatLieuMatGiay1 getMaCL() {
        return MaCL;
    }

    public String getMoTa() {
        return MoTa;
    }

    public GiamGia1 getMaGG() {
        return MaGG;
    }

    public void setMaCTSP(int MaCTSP) {
        this.MaCTSP = MaCTSP;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public void setMaSP(SanPham1 MaSP) {
        this.MaSP = MaSP;
    }

    public void setMaCLDe(ChatLieuDeGiay1 MaCLDe) {
        this.MaCLDe = MaCLDe;
    }

    public void setMaSize(Size1 MaSize) {
        this.MaSize = MaSize;
    }

    public void setMaMS(MauSac1 MaMS) {
        this.MaMS = MaMS;
    }

    public void setMaCL(ChatLieuMatGiay1 MaCL) {
        this.MaCL = MaCL;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public void setMaGG(GiamGia1 MaGG) {
        this.MaGG = MaGG;
    }

    public ChiTietSanPham1(int MaCTSP, int SoLuong, float DonGia, SanPham1 MaSP, ChatLieuDeGiay1 MaCLDe, Size1 MaSize, MauSac1 MaMS, ChatLieuMatGiay1 MaCL, String MoTa) {
        this.MaCTSP = MaCTSP;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.MaSP = MaSP;
        this.MaCLDe = MaCLDe;
        this.MaSize = MaSize;
        this.MaMS = MaMS;
        this.MaCL = MaCL;
        this.MoTa = MoTa;
    }

}
