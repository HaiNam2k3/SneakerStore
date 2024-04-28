package model;

/**
 *
 * @author ADMIN
 */
public class Size1 {

    private int MaSize;
    private int KichThuoc;
    private boolean TrangThai;

    public Size1() {
    }

    public Size1(int MaSize, int KichThuoc, boolean TrangThai) {
        this.MaSize = MaSize;
        this.KichThuoc = KichThuoc;
        this.TrangThai = TrangThai;
    }

    public int getMaSize() {
        return MaSize;
    }

    public void setMaSize(int MaSize) {
        this.MaSize = MaSize;
    }

    public int getKichThuoc() {
        return KichThuoc;
    }

    public void setKichThuoc(int KichThuoc) {
        this.KichThuoc = KichThuoc;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Size1(int KichThuoc) {
        this.KichThuoc = KichThuoc;
    }
    
    
    @Override
    public String toString() {
        return KichThuoc + "";
    }

}
