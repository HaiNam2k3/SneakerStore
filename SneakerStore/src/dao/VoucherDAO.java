package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connect.ConnectDB;
import model.Voucherq;

public class VoucherDAO {
    public ArrayList<Voucherq> layThongTinVoucher() {
        ArrayList<Voucherq> list = new ArrayList<Voucherq>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getCon();
            String sql = "SELECT * FROM KHUYEN_MAI";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int MaKM = rs.getInt("MaKM");
                String TenKhuyenMai = rs.getString("TenKhuyenMai");
                Date maGV = rs.getDate("NgayBD");
                Date siSo = rs.getDate("NgayKT");
                double MucGiam = rs.getDouble("MucGiam");
                String MaGiam = rs.getString("MaGiam");
                boolean DonVi = rs.getBoolean("DonVi");
                boolean TrangThai = rs.getBoolean("TrangThai");

                Voucherq voucher = new Voucherq(MaKM, TenKhuyenMai, maGV, siSo, MucGiam, MaGiam, DonVi, TrangThai);
                System.out.println(voucher.toString());
                list.add(voucher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean them(Voucherq voucher) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getCon();
        int n = 0;
        PreparedStatement statement = null;
        String sql = "INSERT INTO KHUYEN_MAI VALUES (?,?,?,?,?,?,?)";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, voucher.getTenKhuyenMai());

            java.sql.Date sqlDateBD = new java.sql.Date(voucher.getNgayBD().getTime());
            statement.setDate(2, sqlDateBD);
            java.sql.Date sqlDateKT = new java.sql.Date(voucher.getNgayKT().getTime());
            statement.setDate(3, sqlDateKT);

            statement.setDouble(4, voucher.getMucGiam());
            statement.setString(5, voucher.getMaGiam());
            statement.setBoolean(6, voucher.isDonVi());
            statement.setBoolean(7, voucher.isTrangThai());
            n = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    public boolean sua(Voucherq voucher) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getCon();
        int n = 0;
        PreparedStatement statement = null;
        String sql = "UPDATE KHUYEN_MAI Set TenKhuyenMai=?, NgayBD=?, NgayKT=?, MucGiam=?, MaGiam=?, DonVi=?, TrangThai=? WHERE MaKM=?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, voucher.getTenKhuyenMai());

            java.sql.Date sqlDateBD = new java.sql.Date(voucher.getNgayBD().getTime());
            statement.setDate(2, sqlDateBD);
            java.sql.Date sqlDateKT = new java.sql.Date(voucher.getNgayKT().getTime());
            statement.setDate(3, sqlDateKT);

            statement.setDouble(4, voucher.getMucGiam());
            statement.setString(5, voucher.getMaGiam());
            statement.setBoolean(6, voucher.isDonVi());
            statement.setBoolean(7, voucher.isTrangThai());
            statement.setInt(8, voucher.getMaKM());
            n = statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    public boolean xoa(int maKM) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getCon();
        int n = 0;
        PreparedStatement statement = null;
        String sql = "DELETE FROM KHUYEN_MAI WHERE MaKM = ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setInt(1, maKM);
            n = statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n > 0;
    }
}
