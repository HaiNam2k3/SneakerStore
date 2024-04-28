package model;

/**
 *
 * @author ADMIN
 */
public class DanhMucc {
    private int MaDM;
    private String TenDanhMuc;
    private boolean TrangThai;
    
    public DanhMucc(){
    }

    public DanhMucc(int MaDM, String TenDanhMuc, boolean TrangThai) {
        this.MaDM = MaDM;
        this.TenDanhMuc = TenDanhMuc;
        this.TrangThai = TrangThai;
    }

    public int getMaDM() {
        return MaDM;
    }

    public void setMaDM(int MaDM) {
        this.MaDM = MaDM;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String TenDanhMuc) {
        this.TenDanhMuc = TenDanhMuc;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public DanhMucc(String TenDanhMuc, boolean TrangThai) {
        this.TenDanhMuc = TenDanhMuc;
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return TenDanhMuc;
    }
    
    
    
}
