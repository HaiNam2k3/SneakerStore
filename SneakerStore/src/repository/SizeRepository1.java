package repository;

import java.util.ArrayList;
import model.Size1;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.JdbcHelper1;

/**
 *
 * @author ADMIN
 */
public class SizeRepository1 {

    public ArrayList<Size1> getAllSize() {
        ArrayList<Size1> listSize = new ArrayList<>();
        Connection cn = JdbcHelper1.getConnection();
        String sql = "select * from SIZE";
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Size1 size = new Size1(rs.getInt("MaSize"), rs.getInt("KichThuoc"), rs.getBoolean("TrangThai"));
                listSize.add(size);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SizeRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSize;
    }

    public Integer addSize(Size1 size) {
        Integer row = null;
        String sql = "INSERT INTO SIZE(KichThuoc,TrangThai)\n"
                + "VALUES (?,?)";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, size.getKichThuoc());
            pstm.setBoolean(2, size.isTrangThai());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SizeRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer updateSize(Size1 size) {
        Integer row = null;
        String sql = "UPDATE SIZE\n"
                + "SET KichThuoc = ?, TrangThai = ?\n"
                + "WHERE MaSize = ?";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, size.getKichThuoc());
            pstm.setBoolean(2, size.isTrangThai());
            pstm.setInt(3, size.getMaSize());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SizeRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer deleteSize(int MaSize) {
        Integer row = null;
        Connection cn = JdbcHelper1.getConnection();
        String sql = "DELETE SIZE\n"
                + "WHERE MaSize = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, MaSize);
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SizeRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }
}
