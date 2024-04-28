package model;

/**
 *
 * @author ADMIN
 */
public class ChatLieuMatGiay1 {

    private int MaCL;
    private String TenChatLieu;
    private boolean TrangThai;

    public ChatLieuMatGiay1() {
    }

    public ChatLieuMatGiay1(int MaCL, String TenChatLieu, boolean TrangThai) {
        this.MaCL = MaCL;
        this.TenChatLieu = TenChatLieu;
        this.TrangThai = TrangThai;
    }

    public int getMaCL() {
        return MaCL;
    }

    public void setMaCL(int MaCL) {
        this.MaCL = MaCL;
    }

    public String getTenChatLieu() {
        return TenChatLieu;
    }

    public void setTenChatLieu(String TenChatLieu) {
        this.TenChatLieu = TenChatLieu;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public ChatLieuMatGiay1(int MaCL) {
        this.MaCL = MaCL;
    }

    public ChatLieuMatGiay1(String TenChatLieu) {
        this.TenChatLieu = TenChatLieu;
    }
    

    @Override
    public String toString() {
        return TenChatLieu;
    }

}
