package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connect.ConnectDB;
import model.HoaDonChiTietq;

public class HoaDonChiTietDAO {

//    public List<Map<Integer, Object>> layThongTinHoaDonChiTiet() {
//        List<Map<Integer, Object>> list = new ArrayList<>();
//
//        try {
//            ConnectDB.getInstance().connect();
//            Connection con = ConnectDB.getCon();
//            String sql = "SELECT \n"
//                    + "    HDCT.MaHDCT,\n"
//                    + "    HD.MaHD,\n"
//                    + "    HDCT.MaCTSP,\n"
//                    + "    SP.TenSP,\n"
//                    + "    CLDG.TenChatLieuDe,\n"
//                    + "    SZ.KichThuoc,\n"
//                    + "    MS.TenMau,\n"
//                    + "    CL.TenChatLieu,\n"
//                    + "    HDCT.SoLuong,\n"
//                    + "    HDCT.DonGia\n"
//                    + "FROM \n"
//                    + "    HOA_DON_CHI_TIET AS HDCT\n"
//                    + "JOIN \n"
//                    + "    HOA_DON AS HD ON HDCT.MaHD = HD.MaHD\n"
//                    + "JOIN \n"
//                    + "    CHI_TIET_SAN_PHAM AS CTSP ON HDCT.MaCTSP = CTSP.MaCTSP\n"
//                    + "JOIN \n"
//                    + "    SAN_PHAM AS SP ON CTSP.MaSP = SP.MaSP\n"
//                    + "JOIN \n"
//                    + "    CHAT_LIEU_DE_GIAY AS CLDG ON CTSP.MaCLDe = CLDG.MaCLDe\n"
//                    + "JOIN \n"
//                    + "    SIZE AS SZ ON CTSP.MaSize = SZ.MaSize\n"
//                    + "JOIN \n"
//                    + "    MAU_SAC AS MS ON CTSP.MaMS = MS.MaMS\n"
//                    + "JOIN \n"
//                    + "    CHAT_LIEU AS CL ON CTSP.MaCL = CL.MaCL; ";
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(sql);
//            // MaHDCT MaCTSP MaHD MaTT SoLuong DonGia MaKM GhiChu
//            while (rs.next()) {
//                int MaHDCT = rs.getInt("MaHDCT");
//                int MaHD = rs.getInt("MaHD");
//                int MaCTSP = rs.getInt("MaCTSP");
//                String tenSP = rs.getString("TenSP");
//                String tenCLD = rs.getString("TenChatLieuDe");
//                int size = rs.getInt("KichThuoc");
//                String tenMau = rs.getString("TenMau");
//                String tenChatLieu = rs.getString("TenChatLieu");
//
//                int SoLuong = rs.getInt("SoLuong");
//                double DonGia = rs.getDouble("DonGia");
//
//                Map<Integer, Object> map = new HashMap<>();
//                HoaDonChiTietq hoaDonChiTiet = new HoaDonChiTietq(MaHDCT, MaCTSP, MaHD, SoLuong, DonGia);
//                map.put(1, hoaDonChiTiet);
//                map.put(2, tenCLD);
//                map.put(3, size);
//                map.put(4, tenMau);
//                map.put(5, tenChatLieu);
//                map.put(6, tenSP);
//
//                list.add(map);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }

    public List<HoaDonChiTietq> timKiemHoaDonBangMa(int maHD) {
        List<HoaDonChiTietq> danhSachHoaDon = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getCon();

            String sql = "SELECT \n"
                    + "    HDCT.MaHDCT, \n"
                    + "    HD.MaHD, \n"
                    + "    HDCT.MaCTSP, \n"
                    + "    NV.HoTen AS HoTenNV, \n"
                    + "    TTKH.TenKH, \n"
                    + "    SP.TenSP, \n"
                    + "    HDCT.SoLuong, \n"
                    + "    HDCT.DonGia, \n"
                    + "    CLDe.TenChatLieuDe, \n"
                    + "    SIZE.KichThuoc, \n"
                    + "    MS.TenMau, \n"
                    + "    HD.TongTien, \n"
                    + "    KM.MucGiam, \n"
                    + "    HDM.SoTienConLai\n"
                    + "FROM \n"
                    + "    HOA_DON_CHI_TIET HDCT\n"
                    + "LEFT JOIN \n"
                    + "    HOA_DON HD ON HDCT.MaHD = HD.MaHD\n"
                    + "LEFT JOIN \n"
                    + "    SAN_PHAM SP ON HDCT.MaCTSP = SP.MaSP\n"
                    + "LEFT JOIN \n"
                    + "    CHI_TIET_SAN_PHAM CTSP ON HDCT.MaCTSP = CTSP.MaCTSP\n"
                    + "LEFT JOIN\n"
                    + "    NHAN_VIEN NV ON HD.MaNV = NV.MaNV\n"
                    + "LEFT JOIN \n"
                    + "    THONG_TIN_KH TTKH ON HD.MaTTKH = TTKH.MaTTKH\n"
                    + "LEFT JOIN \n"
                    + "    CHAT_LIEU_DE_GIAY CLDe ON CTSP.MaCLDe = CLDe.MaCLDe\n"
                    + "LEFT JOIN \n"
                    + "    SIZE ON CTSP.MaSize = SIZE.MaSize\n"
                    + "LEFT JOIN \n"
                    + "    MAU_SAC MS ON CTSP.MaMS = MS.MaMS\n"
                    + "LEFT JOIN\n"
                    + "    HOA_DON_KHUYEN_MAI HDM ON HD.MaHD = HDM.MaHD\n"
                    + "LEFT JOIN \n"
                    + "    KHUYEN_MAI KM ON HDM.MaKM = KM.MaKM\n"
                    + "where HD.MAHD=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, maHD);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int MaHDCT = rs.getInt("MaHDCT");
                int MaHD = rs.getInt("MaHD");
                int MaCTSP = rs.getInt("MaCTSP");
                int SoLuong = rs.getInt("SoLuong");
                double DonGia = rs.getDouble("DonGia");
                String hoTenNV = rs.getString("HoTenNV");
                String tenKH = rs.getString("TenKH");
                String tenSP = rs.getString("TenSP");
                String tenCLD = rs.getString("TenChatLieuDe");
                int size = rs.getInt("KichThuoc");
                String tenMau = rs.getString("TenMau");
                double tongTien = rs.getDouble("TongTien");
                double mucGiam = rs.getDouble("MucGiam");
                double soTienConLai = rs.getDouble("SoTienConLai");

                HoaDonChiTietq hoaDonChiTiet = new HoaDonChiTietq(MaHDCT, MaCTSP, MaHD, SoLuong, DonGia, hoTenNV, tenKH, tenSP, tenCLD, size, tenMau, mucGiam, soTienConLai, tongTien);

                danhSachHoaDon.add(hoaDonChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }
}
