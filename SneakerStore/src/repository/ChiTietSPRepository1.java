package repository;

import java.util.ArrayList;
import model.ChiTietSanPham1;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChatLieuDeGiay1;
import model.ChatLieuMatGiay1;
import model.GiamGia1;
import model.MauSac1;
import model.SanPham1;
import model.Size1;
import utilities.JdbcHelper1;

/**
 *
 * @author ADMIN
 */
public class ChiTietSPRepository1 {

    public ArrayList<ChiTietSanPham1> getAllCTSP() {
        ArrayList listCTSP = new ArrayList();
        Connection cn = JdbcHelper1.getConnection();
        String sql = "SELECT * FROM CHI_TIET_SAN_PHAM\n"
                + "JOIN SAN_PHAM \n"
                + "ON CHI_TIET_SAN_PHAM.MaSP = SAN_PHAM.MaSP\n"
                + "JOIN CHAT_LIEU_DE_GIAY\n"
                + "ON CHI_TIET_SAN_PHAM.MaCLDe = CHAT_LIEU_DE_GIAY.MaCLDe\n"
                + "JOIN SIZE\n"
                + "ON CHI_TIET_SAN_PHAM.MaSize = SIZE.MaSize\n"
                + "JOIN MAU_SAC\n"
                + "ON CHI_TIET_SAN_PHAM.MaMS = MAU_SAC.MaMS\n"
                + "JOIN CHAT_LIEU\n"
                + "ON CHI_TIET_SAN_PHAM.MaCL = CHAT_LIEU.MaCL\n"
                + "LEFT JOIN Giam_Gia\n"
                + "ON CHI_TIET_SAN_PHAM.MaGG = Giam_Gia.MaGG";
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ChiTietSanPham1 ctsp = new ChiTietSanPham1();
                ctsp.setMaCTSP(rs.getInt("MaCTSP"));
                ctsp.setSoLuong(rs.getInt("SoLuong"));
                ctsp.setDonGia(rs.getFloat("DonGia"));
                SanPham1 sanPham = new SanPham1();
                sanPham.setMaSP(rs.getInt("MaSP"));
                sanPham.setTenSP(rs.getString("TenSP"));
                sanPham.setNgayNhap(LocalDate.parse(rs.getString("NgayNhap")));
                sanPham.setNgayCapNhat(LocalDate.parse(rs.getString("NgayCapNhat")));
                sanPham.setTrangThai(rs.getBoolean("TrangThai"));
                ctsp.setMaSP(sanPham);
                ChatLieuDeGiay1 cldg = new ChatLieuDeGiay1(rs.getInt("MaCLDe"), rs.getString("TenChatLieuDe"), rs.getBoolean("TrangThai"));
                ctsp.setMaCLDe(cldg);
                Size1 size = new Size1(rs.getInt("MaSize"), rs.getInt("KichThuoc"), rs.getBoolean("TrangThai"));
                ctsp.setMaSize(size);
                MauSac1 mauSac = new MauSac1(rs.getInt("MaMS"), rs.getString("TenMau"), rs.getBoolean("TrangThai"));
                ctsp.setMaMS(mauSac);
                ChatLieuMatGiay1 clmg = new ChatLieuMatGiay1(rs.getInt("MaCL"), rs.getString("TenChatLieu"), rs.getBoolean("TrangThai"));
                ctsp.setMaCL(clmg);
                ctsp.setMoTa(rs.getString("MoTa"));
                GiamGia1 giamGia = new GiamGia1(rs.getInt("MaGG"), rs.getString("TenMaGiam"), rs.getFloat("MucGiam"), rs.getDate("NgayBatDau"), rs.getDate("NgayKetThuc"), rs.getString("GhiChu"));
                ctsp.setMaGG(giamGia);
                listCTSP.add(ctsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listCTSP;
    }

    public Integer addCTSP(ChiTietSanPham1 ctsp) {
        Integer row = null;
        String sql = "INSERT INTO CHI_TIET_SAN_PHAM\n"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, ctsp.getSoLuong());
            pstm.setFloat(2, ctsp.getDonGia());
            pstm.setInt(3, ctsp.getMaSP().getMaSP());
            pstm.setInt(4, ctsp.getMaCLDe().getMaCLDe());
            pstm.setInt(5, ctsp.getMaSize().getMaSize());
            pstm.setInt(6, ctsp.getMaMS().getMaMS());
            pstm.setInt(7, ctsp.getMaCL().getMaCL());
            pstm.setString(8, ctsp.getMoTa());
            pstm.setInt(9, ctsp.getMaGG().getMaGG());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer addCTSPMaGGNull(ChiTietSanPham1 ctsp) {
        Integer row = null;
        String sql = "INSERT INTO CHI_TIET_SAN_PHAM(SoLuong,DonGia,MaSP,MaCLDe,MaSize,MaMS,MaCL,MoTa)\n"
                + "VALUES(?,?,?,?,?,?,?,?)";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, ctsp.getSoLuong());
            pstm.setFloat(2, ctsp.getDonGia());
            pstm.setInt(3, ctsp.getMaSP().getMaSP());
            pstm.setInt(4, ctsp.getMaCLDe().getMaCLDe());
            pstm.setInt(5, ctsp.getMaSize().getMaSize());
            pstm.setInt(6, ctsp.getMaMS().getMaMS());
            pstm.setInt(7, ctsp.getMaCL().getMaCL());
            pstm.setString(8, ctsp.getMoTa());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer updateCTSP(ChiTietSanPham1 ctsp) {
        Integer row = null;
        String sql = "UPDATE CHI_TIET_SAN_PHAM\n"
                + "SET SoLuong = ?,DonGia = ?, MaSP = ?, MaCLDe = ?, MaSize = ?, MaMS = ?, MaCL = ?, MoTa = ?, MaGG = ?\n"
                + "WHERE MaCTSP = ?";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, ctsp.getSoLuong());
            pstm.setFloat(2, ctsp.getDonGia());
            pstm.setInt(3, ctsp.getMaSP().getMaSP());
            pstm.setInt(4, ctsp.getMaCLDe().getMaCLDe());
            pstm.setInt(5, ctsp.getMaSize().getMaSize());
            pstm.setInt(6, ctsp.getMaMS().getMaMS());
            pstm.setInt(7, ctsp.getMaCL().getMaCL());
            pstm.setString(8, ctsp.getMoTa());
            pstm.setInt(9, ctsp.getMaGG().getMaGG());
            pstm.setInt(10, ctsp.getMaCTSP());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer updateCTSPMaGGNull(ChiTietSanPham1 ctsp) {
        Integer row = null;
        String sql = "UPDATE CHI_TIET_SAN_PHAM\n"
                + "SET SoLuong = ?,DonGia = ?, MaSP = ?, MaCLDe = ?, MaSize = ?, MaMS = ?, MaCL = ?, MoTa = ?, MaGG = null\n"
                + "WHERE MaCTSP = ?";
        Connection cn = JdbcHelper1.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, ctsp.getSoLuong());
            pstm.setFloat(2, ctsp.getDonGia());
            pstm.setInt(3, ctsp.getMaSP().getMaSP());
            pstm.setInt(4, ctsp.getMaCLDe().getMaCLDe());
            pstm.setInt(5, ctsp.getMaSize().getMaSize());
            pstm.setInt(6, ctsp.getMaMS().getMaMS());
            pstm.setInt(7, ctsp.getMaCL().getMaCL());
            pstm.setString(8, ctsp.getMoTa());
            pstm.setInt(9, ctsp.getMaCTSP());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Integer deleteCTSP(int MaCTSP) {
        Integer row = null;
        Connection cn = JdbcHelper1.getConnection();
        String sql = "DELETE CHI_TIET_SAN_PHAM\n"
                + "WHERE MaCTSP = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, MaCTSP);
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public ArrayList<ChiTietSanPham1> getCTSPByMaSP(int MaSP) {
        ArrayList<ChiTietSanPham1> listCTSP = new ArrayList<>();
        String sql = "SELECT * FROM CHI_TIET_SAN_PHAM\n"
                + "WHERE MaSP = ?";
        Connection cn = JdbcHelper1.getConnection();
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ChiTietSanPham1 ctsp = new ChiTietSanPham1();
                ctsp.setMaCTSP(rs.getInt("MaCTSP"));
                ctsp.setSoLuong(rs.getInt("SoLuong"));
                ctsp.setDonGia(rs.getFloat("DonGia"));
                SanPham1 sp = new SanPham1(rs.getString("TenSP"));
                ctsp.setMaSP(sp);
                ChatLieuDeGiay1 cldg = new ChatLieuDeGiay1(rs.getString("TenChatLieuDe"));
                ctsp.setMaCLDe(cldg);
                Size1 size = new Size1(rs.getInt("KichThuoc"));
                ctsp.setMaSize(size);
                MauSac1 ms = new MauSac1(rs.getString("TenMau"));
                ctsp.setMaMS(ms);
                ChatLieuMatGiay1 clmg = new ChatLieuMatGiay1(rs.getString("TenChatLieu"));
                ctsp.setMaCL(clmg);
                ctsp.setMoTa(rs.getString("MoTa"));
                GiamGia1 gg = new GiamGia1(rs.getString("TenMaGiam"));
                ctsp.setMaGG(gg);
                listCTSP.add(ctsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCTSP;
    }

    public ArrayList<ChiTietSanPham1> getCTSPMaGGNull() {
        ArrayList listCTSP = new ArrayList();
        Connection cn = JdbcHelper1.getConnection();
        String sql = "SELECT * FROM CHI_TIET_SAN_PHAM\n"
                + "JOIN SAN_PHAM \n"
                + "ON CHI_TIET_SAN_PHAM.MaSP = SAN_PHAM.MaSP\n"
                + "JOIN CHAT_LIEU_DE_GIAY\n"
                + "ON CHI_TIET_SAN_PHAM.MaCLDe = CHAT_LIEU_DE_GIAY.MaCLDe\n"
                + "JOIN SIZE\n"
                + "ON CHI_TIET_SAN_PHAM.MaSize = SIZE.MaSize\n"
                + "JOIN MAU_SAC\n"
                + "ON CHI_TIET_SAN_PHAM.MaMS = MAU_SAC.MaMS\n"
                + "JOIN CHAT_LIEU\n"
                + "ON CHI_TIET_SAN_PHAM.MaCL = CHAT_LIEU.MaCL\n";
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ChiTietSanPham1 ctsp = new ChiTietSanPham1();
                ctsp.setMaCTSP(rs.getInt("MaCTSP"));
                ctsp.setSoLuong(rs.getInt("SoLuong"));
                ctsp.setDonGia(rs.getFloat("DonGia"));
                SanPham1 sp = new SanPham1(rs.getString("TenSP"));
                ctsp.setMaSP(sp);
                ChatLieuDeGiay1 cldg = new ChatLieuDeGiay1(rs.getString("TenChatLieuDe"));
                ctsp.setMaCLDe(cldg);
                Size1 size = new Size1(rs.getInt("KichThuoc"));
                ctsp.setMaSize(size);
                MauSac1 ms = new MauSac1(rs.getString("TenMau"));
                ctsp.setMaMS(ms);
                ChatLieuMatGiay1 clmg = new ChatLieuMatGiay1(rs.getString("TenChatLieu"));
                ctsp.setMaCL(clmg);
                ctsp.setMoTa(rs.getString("MoTa"));
                listCTSP.add(ctsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listCTSP;
    }

    public ArrayList<ChiTietSanPham1> getSPByMa(int MaSP) {
        ArrayList listCTSP = new ArrayList();
        Connection cn = JdbcHelper1.getConnection();
        String sql = "SELECT * FROM CHI_TIET_SAN_PHAM\n"
                + "JOIN SAN_PHAM\n"
                + "ON CHI_TIET_SAN_PHAM.MaSP = SAN_PHAM.MaSP\n"
                + "JOIN CHAT_LIEU_DE_GIAY\n"
                + "ON CHI_TIET_SAN_PHAM.MaCLDe = CHAT_LIEU_DE_GIAY.MaCLDe\n"
                + "JOIN SIZE\n"
                + "ON CHI_TIET_SAN_PHAM.MaSize = SIZE.MaSize\n"
                + "JOIN MAU_SAC\n"
                + "ON CHI_TIET_SAN_PHAM.MaMS = MAU_SAC.MaMS\n"
                + "JOIN CHAT_LIEU\n"
                + "ON CHI_TIET_SAN_PHAM.MaCL = CHAT_LIEU.MaCL\n"
                + "LEFT JOIN Giam_Gia\n"
                + "ON CHI_TIET_SAN_PHAM.MaGG = Giam_Gia.MaGG\n"
                + "WHERE SAN_PHAM.MaSP = ?";
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, MaSP);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ChiTietSanPham1 ctsp = new ChiTietSanPham1();
                ctsp.setMaCTSP(rs.getInt("MaCTSP"));
                ctsp.setSoLuong(rs.getInt("SoLuong"));
                ctsp.setDonGia(rs.getFloat("DonGia"));
                SanPham1 sanPham = new SanPham1();
                sanPham.setMaSP(rs.getInt("MaSP"));
                sanPham.setTenSP(rs.getString("TenSP"));
                sanPham.setNgayNhap(LocalDate.parse(rs.getString("NgayNhap")));
                sanPham.setNgayCapNhat(LocalDate.parse(rs.getString("NgayCapNhat")));
                sanPham.setTrangThai(rs.getBoolean("TrangThai"));
                ctsp.setMaSP(sanPham);
                ChatLieuDeGiay1 cldg = new ChatLieuDeGiay1(rs.getInt("MaCLDe"), rs.getString("TenChatLieuDe"), rs.getBoolean("TrangThai"));
                ctsp.setMaCLDe(cldg);
                Size1 size = new Size1(rs.getInt("MaSize"), rs.getInt("KichThuoc"), rs.getBoolean("TrangThai"));
                ctsp.setMaSize(size);
                MauSac1 mauSac = new MauSac1(rs.getInt("MaMS"), rs.getString("TenMau"), rs.getBoolean("TrangThai"));
                ctsp.setMaMS(mauSac);
                ChatLieuMatGiay1 clmg = new ChatLieuMatGiay1(rs.getInt("MaCL"), rs.getString("TenChatLieu"), rs.getBoolean("TrangThai"));
                ctsp.setMaCL(clmg);
                ctsp.setMoTa(rs.getString("MoTa"));
                GiamGia1 giamGia = new GiamGia1(rs.getInt("MaGG"), rs.getString("TenMaGiam"), rs.getFloat("MucGiam"), rs.getDate("NgayBatDau"), rs.getDate("NgayKetThuc"), rs.getString("GhiChu"));
                ctsp.setMaGG(giamGia);
                listCTSP.add(ctsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listCTSP;
    }

}
