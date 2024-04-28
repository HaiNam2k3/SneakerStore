package repository;

import java.util.ArrayList;
import model.SanPham1;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChiTietSanPham1;
import model.DanhMuc1;
import utilities.JdbcHelper1;

/**
 *
 * @author ADMIN
 */
public class SanPhamRopository1 {

    public ArrayList<SanPham1> getAllSanPham() {
        ArrayList<SanPham1> listSanPham = new ArrayList<>();
        Connection cn = JdbcHelper1.getConnection();
        Statement stm;
        String sql = "SELECT * FROM SAN_PHAM JOIN DANH_MUC\n"
                + "ON DANH_MUC.MaDM = SAN_PHAM.MaDM\n"
                + "WHERE DANH_MUC.TrangThai = 1";
        try {
            stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham1 sanPham = new SanPham1();
                sanPham.setMaSP(rs.getInt("MaSP"));
                sanPham.setTenSP(rs.getString("TenSP"));
                sanPham.setNgayNhap(LocalDate.parse(rs.getString("NgayNhap")));
                sanPham.setNgayCapNhat(LocalDate.parse(rs.getString("NgayCapNhat")));
                sanPham.setTrangThai(rs.getBoolean("TrangThai"));

                DanhMuc1 danhMuc = new DanhMuc1();
                danhMuc.setMaDM(rs.getInt("MaDM"));
                danhMuc.setTenDanhMuc(rs.getString("TenDanhMuc"));
                danhMuc.setTrangThai(rs.getBoolean("TrangThai"));

                sanPham.setMaDM(danhMuc);
                listSanPham.add(sanPham);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }

    public Integer addSanPham(SanPham1 sanPham) {
        Integer row = null;
        String sql = "INSERT INTO SAN_PHAM\n"
                + "VALUES(?,?,?,?,?)";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, sanPham.getTenSP());
            pstm.setString(2, sanPham.getNgayNhap().toString());
            pstm.setString(3, sanPham.getNgayCapNhat().toString());
            pstm.setBoolean(4, sanPham.isTrangThai());
            pstm.setInt(5, sanPham.getMaDM().getMaDM());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer updateSanPham(SanPham1 sanPham) {
        Integer row = null;
        String sql = "UPDATE SAN_PHAM\n"
                + "SET TenSP = ?, NgayCapNhat = ?, TrangThai = ?, MaDM = ?\n"
                + "WHERE MaSP = ?";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, sanPham.getTenSP());
            pstm.setString(2, sanPham.getNgayCapNhat().toString());
            pstm.setBoolean(3, sanPham.isTrangThai());
            pstm.setInt(4, sanPham.getMaDM().getMaDM());
            pstm.setInt(5, sanPham.getMaSP());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer deleteSanPham(int MaSP) {
        Integer row = null;
        Connection cn = JdbcHelper1.getConnection();
        String sql = "DELETE SAN_PHAM\n"
                + "WHERE MaSP = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, MaSP);
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }


    
}
