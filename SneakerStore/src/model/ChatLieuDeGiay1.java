package model;

/**
 *
 * @author ADMIN
 */
public class ChatLieuDeGiay1 {

    private int MaCLDe;
    private String TenChatLieuDe;
    private boolean TrangThai;

    public ChatLieuDeGiay1() {
    }

    public ChatLieuDeGiay1(int MaCLDe, String TenChatLieuDe, boolean TrangThai) {
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

    public ChatLieuDeGiay1(int MaCLDe) {
        this.MaCLDe = MaCLDe;
    }

    public ChatLieuDeGiay1(String TenChatLieuDe) {
        this.TenChatLieuDe = TenChatLieuDe;
    }
    

    @Override
    public String toString() {
        return TenChatLieuDe;
    }

}
