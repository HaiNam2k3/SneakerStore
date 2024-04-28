package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChatLieuDeGiay1;
import model.ChatLieuMatGiay1;
import utilities.JdbcHelper1;

/**
 *
 * @author ADMIN
 */
public class ChatLieuDGRepository1 {

    // Chất liệu Đế giày
    public ArrayList<ChatLieuDeGiay1> getAllChatLieDG() {
        ArrayList<ChatLieuDeGiay1> listChatLieuDG = new ArrayList<>();
        Connection cn = JdbcHelper1.getConnection();
        String sql = "SELECT * FROM CHAT_LIEU_DE_GIAY";
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ChatLieuDeGiay1 chatLieu = new ChatLieuDeGiay1(rs.getInt("MaCLDe"), rs.getString("TenChatLieuDe"), rs.getBoolean("TrangThai"));
                listChatLieuDG.add(chatLieu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChatLieuMGRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listChatLieuDG;
    }

    public Integer addChatLieuDG(ChatLieuDeGiay1 cldg) {
        Integer row = null;
        String sql = "INSERT INTO CHAT_LIEU_DE_GIAY(TenChatLieuDe,TrangThai)\n"
                + "VALUES(?,?)";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, cldg.getTenChatLieuDe());
            pstm.setBoolean(2, cldg.isTrangThai());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer updateChatLieuDG(ChatLieuDeGiay1 cldg) {
        Integer row = null;
        String sql = "UPDATE CHAT_LIEU_DE_GIAY\n"
                + "SET TenChatLieuDe = ?, TrangThai = ?\n"
                + "WHERE MaCLDe = ?";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, cldg.getTenChatLieuDe());
            pstm.setBoolean(2, cldg.isTrangThai());
            pstm.setInt(3, cldg.getMaCLDe());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer deleteChatLieuDG(int MaCLDe) {
        Integer row = null;
        Connection cn = JdbcHelper1.getConnection();
        String sql = "DELETE CHAT_LIEU_DE_GIAY\n"
                + "WHERE MaCLDe = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, MaCLDe);
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }
}
