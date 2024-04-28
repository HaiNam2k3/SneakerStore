package repository;

import java.util.ArrayList;
import model.MauSac1;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.JdbcHelper1;

/**
 *
 * @author ADMIN
 */
public class MauSacRepository1 {

    public ArrayList<MauSac1> getAllMauSac() {
        ArrayList<MauSac1> listMauSac = new ArrayList<>();
        Connection cn = JdbcHelper1.getConnection();
        String sql = "SELECT * FROM MAU_SAC";
        Statement stm;
        try {
            stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                MauSac1 mauSac = new MauSac1(rs.getInt("MaMS"), rs.getString("TenMau"), rs.getBoolean("TrangThai"));
                listMauSac.add(mauSac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMauSac;
    }

    public Integer addMauSac(MauSac1 mauSac) {
        Integer row = null;
        String sql = "INSERT INTO MAU_SAC(TenMau,TrangThai)\n"
                + "VALUES(?,?)";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, mauSac.getTenMau());
            pstm.setBoolean(2, mauSac.isTrangThai());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer updateMauSac(MauSac1 mauSac) {
        Integer row = null;
        String sql = "UPDATE MAU_SAC\n"
                + "SET TenMau = ?, TrangThai = ?\n"
                + "WHERE MaMS = ?";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, mauSac.getTenMau());
            pstm.setBoolean(2, mauSac.isTrangThai());
            pstm.setInt(3, mauSac.getMaMS());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer deleteMauSac(int MaMS) {
        Integer row = null;
        Connection cn = JdbcHelper1.getConnection();
        String sql = "DELETE MAU_SAC\n"
                + "WHERE MaMS = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, MaMS);
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }
}
