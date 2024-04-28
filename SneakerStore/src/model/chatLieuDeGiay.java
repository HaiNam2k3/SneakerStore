package model;

/**
 *
 * @author ADMIN
 */
public class chatLieuDeGiay {
    private int MaCLDe;
    private String TenChatLieuDe;
    private boolean TrangThai;

    public chatLieuDeGiay() {
    }

    public chatLieuDeGiay(int MaCLDe, String TenChatLieuDe, boolean TrangThai) {
        this.MaCLDe = MaCLDe;
        this.TenChatLieuDe = TenChatLieuDe;
        this.TrangThai = TrangThai;
    }

    public int getMaCLDe() {
        return MaCLDe;
    }

    public void setMaCLDe(int MaCLDe) {
        this.MaCLDe = MaCLDe;
    }

    public String getTenChatLieuDe() {
        return TenChatLieuDe;
    }

    public void setTenChatLieuDe(String TenChatLieuDe) {
        this.TenChatLieuDe = TenChatLieuDe;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
