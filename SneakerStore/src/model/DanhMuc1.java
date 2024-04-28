package model;

/**
 *
 * @author ADMIN
 */
public class DanhMuc1 {
    private int MaDM;
    private String TenDanhMuc;
    private boolean TrangThai;
    
    public DanhMuc1(){
    }

    public DanhMuc1(int MaDM, String TenDanhMuc, boolean TrangThai) {
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

    public DanhMuc1(String TenDanhMuc, boolean TrangThai) {
        this.TenDanhMuc = TenDanhMuc;
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return TenDanhMuc;
    }
    
    
    
    
    
}
