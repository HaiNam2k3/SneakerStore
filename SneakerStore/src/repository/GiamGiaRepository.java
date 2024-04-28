/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.GiamGia;
import utilities.JdbcHelper;

/**
 *
 * @author trung
 */
public class GiamGiaRepository {

    private Connection connection;

    public GiamGiaRepository() throws SQLServerException {
        this.connection = JdbcHelper.getConnection();
    }

    public GiamGia getGiamGiaById(int id) {
        String query = "SELECT * FROM Giam_Gia WHERE MaGG = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int maGG = resultSet.getInt("MaGG");
                String tenMaGiam = resultSet.getString("TenMaGiam");
                float mucGiam = resultSet.getFloat("MucGiam");
                java.sql.Date ngayBatDau = resultSet.getDate("NgayBatDau");
                java.sql.Date ngayKetThuc = resultSet.getDate("NgayKetThuc");
                String ghiChu = resultSet.getString("GhiChu");

                return new GiamGia(maGG, tenMaGiam, mucGiam, ngayBatDau, ngayKetThuc, ghiChu);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    public List<GiamGia> getAllGiamGias() {
        String query = "SELECT * FROM Giam_Gia";
        List<GiamGia> giamGias = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int maGG = resultSet.getInt("MaGG");
                String tenMaGiam = resultSet.getString("TenMaGiam");
                float mucGiam = resultSet.getFloat("MucGiam");
                java.sql.Date ngayBatDau = resultSet.getDate("NgayBatDau");
                java.sql.Date ngayKetThuc = resultSet.getDate("NgayKetThuc");
                String ghiChu = resultSet.getString("GhiChu");

                GiamGia giamGia = new GiamGia(maGG, tenMaGiam, mucGiam, ngayBatDau, ngayKetThuc, ghiChu);
                giamGias.add(giamGia);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return giamGias;
    }

    private void handleSQLException(SQLException e) {

        e.printStackTrace();
    }

    // Thêm giảm giá mới
    public void addGiamGia(GiamGia giamGia) {
        if (giamGia == null) {
            return;
        }

        String sql = """
        INSERT INTO Giam_Gia (TenMaGiam, MucGiam, NgayBatDau, NgayKetThuc, GhiChu)
        VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = JdbcHelper.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, giamGia.getTenMaGiam());
            stm.setFloat(2, giamGia.getMucGiam());
            stm.setDate(3, new java.sql.Date(giamGia.getNgayBatDau().getTime()));
            stm.setDate(4, new java.sql.Date(giamGia.getNgayKetThuc().getTime()));
            stm.setString(5, giamGia.getGhiChu());

            int check = stm.executeUpdate();

            if (check > 0) {
                System.out.println("Thêm giảm giá thành công");
            } else {
                System.out.println("Thêm giảm giá thất bại");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

// Xóa giảm giá
    public void deleteGiamGia(int maGG) {
        String sql = "DELETE FROM Giam_Gia WHERE MaGG = ?";

        try (Connection con = JdbcHelper.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maGG);
            int check = ps.executeUpdate();

            if (check > 0) {
                System.out.println("Xóa giảm giá thành công");
            } else {
                System.out.println("Xóa giảm giá thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    // Update
    public void update(GiamGia giamGia, String index) {
    String sql = """
    UPDATE Giam_Gia
    SET TenMaGiam = ?, MucGiam = ?, NgayBatDau = ?, NgayKetThuc = ?, GhiChu = ?
    WHERE MaGG = ?
    """;

    try (Connection con = JdbcHelper.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
        stm.setString(1, giamGia.getTenMaGiam());
        stm.setFloat(2, giamGia.getMucGiam());
        stm.setDate(3, new java.sql.Date(giamGia.getNgayBatDau().getTime()));
        stm.setDate(4, new java.sql.Date(giamGia.getNgayKetThuc().getTime()));
        stm.setString(5, giamGia.getGhiChu());
        stm.setInt(6, giamGia.getMaGG());

        int check = stm.executeUpdate();

        if (check > 0) {
            System.out.println("Cập nhật thông tin giảm giá thành công");
        } else {
            System.out.println("Cập nhật thông tin giảm giá thất bại");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
