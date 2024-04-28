package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import connect.ConnectDB;

import model.HoaDonq;

public class HoaDonDAO {

    public List<HashMap<Integer, Object>> layThongTinHoaDon() {
        List<HashMap<Integer, Object>> list = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getCon();
            String sql = "SELECT HOA_DON.*, NHAN_VIEN.HoTen\n"
                    + "FROM HOA_DON\n"
                    + "JOIN NHAN_VIEN ON HOA_DON.MaNV = NHAN_VIEN.MaNV;";

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                HashMap<Integer, Object> hashMap = new HashMap<>();
                HoaDonq hoaDon = new HoaDonq();

                int MaHD = rs.getInt("MaHD");
                String hoTenNhanVien = rs.getString("HoTen");
//                String tenKH = rs.getString("TenKH");
//                String tenSP = rs.getString("TenSP");
//                int soLuong = rs.getInt("SoLuong");
//               float donGia = rs.getFloat("DonGia");
                double tongTien = rs.getDouble("TongTien");
//                String tenKhuyenMai = rs.getString("TenKhuyenMai");
//                float mucGiam = rs.getFloat("MucGiam");
//                float SoTienConLai = rs.getFloat("SoTienConLai");
                String NgayTao = rs.getString("NgayTao");

                String TrangThai = rs.getString("TrangThai");

                hoaDon.setMaHD(MaHD);
                hoaDon.setNgayTao(NgayTao);
                hoaDon.setTongTien(tongTien);
                hoaDon.setTrangThai(TrangThai);

                hashMap.put(1, hoaDon);
                hashMap.put(2, hoTenNhanVien);
//                hashMap.put(3, tenKH);
//               hashMap.put(4, tenSP);
//               hashMap.put(5, soLuong);
//                hashMap.put(6, donGia);
//                hashMap.put(7, tenKhuyenMai);
//                hashMap.put(8, mucGiam);
//                hashMap.put(9, SoTienConLai);

                list.add(hashMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public HashMap<Integer, Object> timKiemHoaDonBangMa(int maHD) {
        HashMap<Integer, Object> hashMap = new HashMap<>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getCon();
            String sql = "SELECT HOA_DON.*, NHAN_VIEN.HoTen\n"
                    + "FROM HOA_DON\n"
                    + "JOIN NHAN_VIEN ON HOA_DON.MaNV = NHAN_VIEN.MaNV where mahd = ?";
            PreparedStatement statement = null;
            statement = con.prepareStatement(sql);
            statement.setInt(1, maHD);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                int MaHD = rs.getInt("MaHD");
                String hoTenNhanVien = rs.getString("HoTen");
//                String tenKH = rs.getString("TenKH");
//                String tenSP = rs.getString("TenSP");
//                int soLuong = rs.getInt("SoLuong");
//               float donGia = rs.getFloat("DonGia");
                double TongTien = rs.getDouble("TongTien");
//                String tenKhuyenMai = rs.getString("TenKhuyenMai");
//                float mucGiam = rs.getFloat("MucGiam");
//                float SoTienConLai = rs.getFloat("SoTienConLai");
                String NgayTao = rs.getString("NgayTao");

                String TrangThai = rs.getString("TrangThai");

                HoaDonq HoaDon = new HoaDonq(MaHD, NgayTao, TongTien, TrangThai);
                hashMap.put(1, HoaDon);
                hashMap.put(2, hoTenNhanVien);
//                hashMap.put(3, tenKH);
//                hashMap.put(4, tenSP);
//               hashMap.put(5, soLuong);
//                hashMap.put(6, donGia);
//                hashMap.put(7, tenKhuyenMai);
//                hashMap.put(8, mucGiam);
//                hashMap.put(9, SoTienConLai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hashMap;
    }
}
