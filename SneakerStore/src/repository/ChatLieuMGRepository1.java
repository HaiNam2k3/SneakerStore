package repository;

import java.util.ArrayList;
import model.ChatLieuMatGiay1;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.JdbcHelper1;

/**
 *
 * @author ADMIN
 */
public class ChatLieuMGRepository1 {

    //Chất liệu mặt giày
    public ArrayList<ChatLieuMatGiay1> getAllChatLieuMG() {
        ArrayList<ChatLieuMatGiay1> listChatLieuMG = new ArrayList<>();
        Connection cn = JdbcHelper1.getConnection();
        String sql = "SELECT * FROM CHAT_LIEU";
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ChatLieuMatGiay1 chatLieu = new ChatLieuMatGiay1(rs.getInt("MaCL"), rs.getString("TenChatLieu"), rs.getBoolean("TrangThai"));
                listChatLieuMG.add(chatLieu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChatLieuMGRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listChatLieuMG;
    }

    public Integer addChatLieuMG(ChatLieuMatGiay1 chatLieu) {
        Integer row = null;
        String sql = "INSERT INTO CHAT_LIEU(TenChatLieu,TrangThai)\n"
                + "VALUES(?,?)";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, chatLieu.getTenChatLieu());
            pstm.setBoolean(2, chatLieu.isTrangThai());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer updateChatLieu(ChatLieuMatGiay1 chatLieu) {
        Integer row = null;
        String sql = "UPDATE CHAT_LIEU\n"
                + "SET TenChatLieu = ?, TrangThai = ?\n"
                + "WHERE MaCL = ?";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, chatLieu.getTenChatLieu());
            pstm.setBoolean(2, chatLieu.isTrangThai());
            pstm.setInt(3, chatLieu.getMaCL());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
        public Integer deleteChatLieu(int MaCL) {
        Integer row = null;
        Connection cn = JdbcHelper1.getConnection();
        String sql = "DELETE CHAT_LIEU\n"
                + "WHERE MaCL = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, MaCL);
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return row;
    }
}
