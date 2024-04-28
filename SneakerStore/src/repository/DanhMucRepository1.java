package repository;

import java.util.ArrayList;
import model.DanhMuc1;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.JdbcHelper1;

/**
 *
 * @author ADMIN
 */
public class DanhMucRepository1 {

    public ArrayList<DanhMuc1> getAllDanhMuc() {
        ArrayList<DanhMuc1> listDanhMuc = new ArrayList<>();
        Connection cn = JdbcHelper1.getConnection();
        Statement stm;
        String sql = "SELECT * FROM DANH_MUC";
        try {
            stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                DanhMuc1 danhMuc = new DanhMuc1(rs.getInt("MaDM"), rs.getString("TenDanhMuc"), rs.getBoolean("TrangThai"));
                listDanhMuc.add(danhMuc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDanhMuc;
    }

    public Integer addDanhMuc(DanhMuc1 danhMuc) {
        Integer row = null;
        String sql = "INSERT INTO DANH_MUC(TenDanhMuc,TrangThai)\n"
                + "VALUES(?,?)";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, danhMuc.getTenDanhMuc());
            pstm.setBoolean(2, danhMuc.isTrangThai());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer updateDanhMuc(DanhMuc1 danhMuc) {
        Integer row = null;
        String sql = "UPDATE DANH_MUC\n"
                + "SET TenDanhMuc = ?, TrangThai = ?\n"
                + "WHERE MaDM = ?";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, danhMuc.getTenDanhMuc());
            pstm.setBoolean(2, danhMuc.isTrangThai());
            pstm.setInt(3, danhMuc.getMaDM());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer deleteDanhMuc(int MaDM) {
        Integer row = null;
        Connection cn = JdbcHelper1.getConnection();
        String sql = "DELETE DANH_MUC\n"
                + "WHERE MaDM = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, MaDM);
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return row;
    }
}
