/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.chiTietSanPham;
import model.sanPham;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChatLieuDeGiay1;
import model.ChatLieuMatGiay1;
import model.ChiTietSanPham1;
import model.GiamGia1;
import model.Voucher;
import model.hinhThucThanhToan;
import model.hoaDon;
import model.hoaDonChiTiet;
import model.KhachHang;
import model.MauSac1;
import model.SanPham1;
import model.Size1;
import model.size;
import repository.ChiTietSPRepository1;
import utilities.JdbcHelper;
import utilities.JdbcHelper1;

/**
 *
 * @author hoanh
 */
public class banHangService {

    JdbcHelper helper = new JdbcHelper();

    //lấy all chi tiet sản phẩm
    public List<chiTietSanPham> listAll() {
        List<chiTietSanPham> listAll = new ArrayList<>();
        String query = """
                     select CHI_TIET_SAN_PHAM.MaCTSP, SAN_PHAM.TenSP, CHAT_LIEU_DE_GIAY.TenChatLieuDe, 
                                          SIZE.KichThuoc, MAU_SAC.TenMau, CHAT_LIEU.TenChatLieu,  
                                          CHI_TIET_SAN_PHAM.DonGia,CHI_TIET_SAN_PHAM.SoLuong, Giam_Gia.MucGiam,CHI_TIET_SAN_PHAM.mota 
                                          from CHI_TIET_SAN_PHAM 
                                          
                     					 join SAN_PHAM on SAN_PHAM.MaSP =  CHI_TIET_SAN_PHAM.MaSP 
                     					 join CHAT_LIEU_DE_GIAY on CHAT_LIEU_DE_GIAY.MaCLDe  =  CHI_TIET_SAN_PHAM.MaCLDe
                                          join SIZE on SIZE.MaSize =  CHI_TIET_SAN_PHAM.MaSize
                                          join MAU_SAC on MAU_SAC.MaMS =  CHI_TIET_SAN_PHAM.MaMS
                                        Left join Giam_Gia on Giam_Gia.MaGG= CHI_TIET_SAN_PHAM.MaGG
                                          join CHAT_LIEU on CHAT_LIEU.MaCL =  CHI_TIET_SAN_PHAM.MaCL
                                           where SAN_PHAM.TrangThai=1
                       """;

        Connection conn = null;
        try {
            conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                chiTietSanPham CTSP = new chiTietSanPham();
                CTSP.setMaCTSP(rs.getInt("MaCTSP"));
                CTSP.setTenSP(rs.getString("TenSP"));
                CTSP.setTenCLDe(rs.getString("TenChatLieuDe"));
                CTSP.setTenSize(rs.getInt("KichThuoc"));
                CTSP.setTenMS(rs.getString("TenMau"));
                CTSP.setTenCL(rs.getString("TenChatLieu"));
                CTSP.setDonGia(rs.getFloat("DonGia"));
                CTSP.setSoLuong(rs.getInt("SoLuong"));
                CTSP.setMucGG(rs.getFloat("MucGiam"));
                CTSP.setMoTa(rs.getString("mota"));
                listAll.add(CTSP);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAll;
    }

    // fill Size
    public ArrayList getSize() {
        ArrayList listSize = new ArrayList<>();
        String query = "  select * from SIZE ";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("TrangThai") == 1) {
                    int kichThuoc = rs.getInt("kichThuoc");
                    listSize.add(kichThuoc);
                }
            }
            return listSize;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList getHTTT() {
        String query = "select * from Thanh_Toan";
        ArrayList listTT = new ArrayList<>();
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("MaTT");
                String hinThuc = rs.getString("HinhThucThanhToan");
                // hinhThucThanhToan httt = new hinhThucThanhToan(id, hinThuc);
                listTT.add(hinThuc);
            }
            return listTT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<hinhThucThanhToan> getALLHTTT() {
        String query = "select * from Thanh_Toan";
        ArrayList<hinhThucThanhToan> listTT = new ArrayList<>();
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("MaTT");
                String hinThuc = rs.getString("HinhThucThanhToan");
                hinhThucThanhToan httt = new hinhThucThanhToan(id, hinThuc);
                listTT.add(httt);
            }
            return listTT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //fill danh mục
    public ArrayList getChatLieuDe() {

        ArrayList listMaDM = new ArrayList<>();

        String query = "select * from Chat_Lieu_De_Giay";

        try {
            Connection conn = JdbcHelper.getConnection();
            Statement sttm = conn.createStatement();
            ResultSet rs = sttm.executeQuery(query);
            while (rs.next()) {
                if (rs.getInt("TrangThai") == 1) {
                    String tenChatLieuDe = rs.getString("tenChatLieuDe");
                    listMaDM.add(tenChatLieuDe);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMaDM;
    }

    //fill danh sach hoa don
    public ArrayList<hoaDon> getAllHD() {
        String query = """
                     select HOA_DON.MaHD,NHAN_VIEN.HoTen, HOA_DON.NgayTao, HOA_DON.TrangThai from HOA_DON 
                                                                                                join THONG_TIN_KH on HOA_DON.MaTTKH= THONG_TIN_KH.MaTTKH
                                                                                                join NHAN_VIEN on HOA_DON.MaNV= NHAN_VIEN.MaNV
                                                                                                where HOA_DON.TrangThai = ?""";
        ArrayList<hoaDon> listAll = new ArrayList<>();
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                hoaDon hd = new hoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setTenNV(rs.getString("HoTen"));
                hd.setNgayTao(rs.getString("ngayTao"));
                hd.setTrangThai(rs.getBoolean("trangThai"));
                listAll.add(hd);
            }

            return listAll;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean isertHoaDonCho(int maNV, String ngayTao, int trangThai) {
        String query = """
                       INSERT INTO HOA_DON
                                  (MaNV
                                   ,NgayTao
                                  ,TrangThai
                                  )
                            VALUES
                                  (?
                                  ,?
                                  ,?)""";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maNV);
            stmt.setString(2, ngayTao);
            stmt.setInt(3, trangThai);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<hoaDon> getHoaDonCho(int trangThai) {
        ArrayList<hoaDon> listHDCho = new ArrayList<>();
        String query = """
                       select HOA_DON.MaHD,NHAN_VIEN.HoTen, HOA_DON.NgayTao,
                       HOA_DON.TrangThai from HOA_DON 
                                         
                                         join NHAN_VIEN on HOA_DON.MaNV= NHAN_VIEN.MaNV
                                                      where HOA_DON.TrangThai like ?
                                                      order by MaHD desc """;

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + trangThai + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                hoaDon hd = new hoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setTenNV(rs.getString("HoTen"));
                hd.setNgayTao(rs.getString("ngayTao"));
                hd.setTrangThai(rs.getBoolean("trangThai"));
                listHDCho.add(hd);
            }
            System.out.println("getdata");
            return listHDCho;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
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

    public ArrayList<hoaDonChiTiet> getHDCT(int maHD) {
        String query = """
                      SELECT MaHDCT, MaHD, CHI_TIET_SAN_PHAM.MaCTSP,
                                            SAN_PHAM.TenSP,HOA_DON_CHI_TIET.SoLuong,
                                            HOA_DON_CHI_TIET.DonGia,Giam_Gia.MucGiam
                      
                      FROM HOA_DON_CHI_TIET join CHI_TIET_SAN_PHAM on HOA_DON_CHI_TIET.MaCTSP = CHI_TIET_SAN_PHAM.MaCTSP
                                        join SAN_PHAM on SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP
                                        join Giam_Gia on Giam_Gia.maGG= CHI_TIET_SAN_PHAM.maGG
                                        where MaHD =?""";
        ArrayList<hoaDonChiTiet> listHDCT = new ArrayList<>();
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maHD);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                hoaDonChiTiet hdct = new hoaDonChiTiet();

                hdct.setMaHDCT(rs.getInt("MaHDCT"));
                hdct.setMaHD(rs.getInt("MaHD"));
                hdct.setMaCTSP(rs.getInt("MaCTSP"));
                hdct.setTenSP(rs.getString("TenSP"));
                hdct.setSoLuong(rs.getInt("SoLuong"));
                hdct.setDonGia(rs.getFloat("DonGia"));
                hdct.setMucGiam(rs.getInt("MucGiam"));
                listHDCT.add(hdct);
            }
            return listHDCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateHDCT(int maHD, int maCTSP, float donGia, int soLuong) {
        String query = """
                      update HOA_DON_CHI_TIET
                                             set SoLuong = ?, DonGia = ? where MaHD = ? and MaCTSP = ?""";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, soLuong);
            stmt.setFloat(2, donGia);
            stmt.setInt(3, maHD);
            stmt.setInt(4, maCTSP);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCTSP(int maCTSP, int soLuong) {
        String query = "update CHI_TIET_SAN_PHAM\n"
                + "                       set SoLuong = ? WHERE MaCTSP = ?";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, soLuong);
            pstmt.setInt(2, maCTSP);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean insertHDCT(int maCTSP, int maHD, int soLuong, float donGia) {
        String query = "insert into HOA_DON_CHI_TIET(MaCTSP, MaHD, SoLuong, DonGia)\n"
                + "                       values(?,?,?,?)";
        System.out.println("aaaaa");
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maCTSP);
            pstmt.setInt(2, maHD);
            pstmt.setInt(3, soLuong);
            pstmt.setFloat(4, donGia);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean insertHDCT2(int maCTSP, int maHD, int soLuong, float donGia, int maGG) {
        String query = "insert into HOA_DON_CHI_TIET(MaCTSP, MaHD, SoLuong, DonGia,maGG)\n"
                + "                       values(?,?,?,?,?)";
        System.out.println("aaaaa");
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maCTSP);
            pstmt.setInt(2, maHD);
            pstmt.setInt(3, soLuong);
            pstmt.setFloat(4, donGia);
            pstmt.setInt(5, maGG);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public int getSoLuongCT(int maCTSP) {
        int soLuong = 0;

        String query = "select soLuong from chi_tiet_san_pham where maCTSP =?";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maCTSP);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt("soLuong");
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public boolean deleteHDCT(int maHDCT) {
        String query = "delete Hoa_Don_Chi_Tiet where maHDCT =?";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maHDCT);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getSoLuongCTSP(int maCTSP) {
        int soLuong = 0;
        String query = "select soluong from chi_tiet_san_pham where maCTSP =?";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maCTSP);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt("soluong");
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList searchSPCT(String tenChatLieuDe) {
        String query = """
                        select SAN_PHAM.MaSP,SAN_PHAM.TenSP,CHI_TIET_SAN_PHAM.MaCTSP, SoLuong, DonGia, TenMau, KichThuoc, TenChatLieu, TenChatLieuDe 
                                        from CHI_TIET_SAN_PHAM  join SAN_PHAM ON SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP
                                    join chat_Lieu on chat_lieu.macl = chi_tiet_san_pham.maCL
                                    join mau_sac on mau_sac.maMS = chi_tiet_san_pham.maCL
                        join SIZE on SIZE.MaSize = CHI_TIET_SAN_PHAM.MaSize
                         join CHAT_LIEU_DE_GIAY on CHAT_LIEU_DE_GIAY.MaCLDe = CHI_TIET_SAN_PHAM.MaCLDe
                                             where   TenChatLieuDe like ?;""";

        ArrayList<chiTietSanPham> list = new ArrayList<>();
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, "%" + tenChatLieuDe + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                chiTietSanPham CTSP = new chiTietSanPham();
                CTSP.setMaSP(rs.getInt("MaSP"));
                CTSP.setMaCTSP(rs.getInt("MaCTSP"));
                CTSP.setTenSP(rs.getString("TenSP"));
                CTSP.setSoLuong(rs.getInt("SoLuong"));
                CTSP.setDonGia(rs.getFloat("DonGia"));
                CTSP.setTenMS(rs.getString("TenMau"));
                CTSP.setTenSize(rs.getInt("KichThuoc"));
                CTSP.setTenCL(rs.getString("TenChatLieu"));
                CTSP.setTenCLDe(rs.getString("TenChatLieuDe"));
                list.add(CTSP);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean huyHoaDon(int maDH) {
        String query = "delete from hoa_don where maHD =?";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maDH);
            stmt.execute();
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    public ArrayList getVoucher() {
        ArrayList<Voucher> listVoucher = new ArrayList<>();
        String query = "select * from KHUYEN_MAI";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Voucher kM = new Voucher();
                kM.setMaKM(rs.getInt("MaKM"));
                kM.setTenKhuyenMai(rs.getString("TenKhuyenMai"));
                kM.setNgayBD(rs.getDate("NgayBD"));
                kM.setNgayKT(rs.getDate("NgayKT"));
                kM.setMucGiam(rs.getFloat("MucGiam"));
                kM.setMaGiam(rs.getString("MaGiam"));
//                if (rs.getInt("TrangThai") == 0) {
//                    kM.setTrangThai(" Không hoạt động");
//                } else {
//                    kM.setTrangThai("Đang hoạt động");
//                }

                kM.setTrangThai(rs.getInt("TrangThai") == 0 ? "Không hoạt động" : "Đang hoạt động");

//                if (rs.getInt("DonVi") == 0) {
//                    kM.setDonVi("VNĐ");
//                } else {
//                    kM.setDonVi("%");
//                }
                kM.setDonVi(rs.getInt("DonVi") == 0 ? "VNĐ" : "%");
                listVoucher.add(kM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }
//    public void getGiamGia(int maCTSP){
//        chiTietSanPham CTSP = new chiTietSanPham();
//        try {
//            Connection conn = JdbcHelper.getConnection();
//            String query = "select ";
//        } catch (Exception e) {
//        }
//    }

    // public boolean 
    public ArrayList getKhachHang() {
        ArrayList<KhachHang> list = new ArrayList<>();
        String query = "SELECT MaTTKH,TenKH,SDT,NgayCN,TrangThai FROM THONG_TIN_KH";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt("MaTTKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setSdt(rs.getString("SDT"));

                kh.setNgayCN(rs.getString("NgayCN"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean updateHoaDon(int maHD, String ngayTao, boolean trangThai, float tongTien, int maKH) {
        String query = "update HOA_DON\n"
                + "set NgayTao = ?, TrangThai = ?, TongTien = ?,maTTKH =?\n"
                + "where MaHD = ?";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, ngayTao);
            pstmt.setBoolean(2, trangThai);
            pstmt.setFloat(3, tongTien);
            pstmt.setInt(4, maKH);
            pstmt.setInt(5, maHD);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateHDCT(int maTT, int maKM, int maHD) {
        System.out.println("abcdfd");
        String query = "update Hoa_Don_Chi_Tiet  set maTT =?, maKM=? where maHD =?";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maTT);
            pstmt.setInt(2, maKM);
            pstmt.setInt(3, maHD);
            pstmt.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateHoaDon2(int maHD, String ngayTao, boolean trangThai, float tongTien) {
        String query = "update Hoa_Don set ngayTao =?, trangThai= ?, tongTien = ? where maHD =?";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, ngayTao);
            pstmt.setBoolean(2, trangThai);
            pstmt.setFloat(3, tongTien);
            pstmt.setInt(4, maHD);
            pstmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertThanhToan(int maHD, int maTKKH, String hinhThucThanhToan) {
        String query = "insert into THANH_TOAN (HinhThucThanhToan, MaTTKH, MaHD)\n"
                + "values(?,?,?)";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, hinhThucThanhToan);
            pstmt.setInt(2, maTKKH);
            pstmt.setInt(3, maHD);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean insertThanhToan2(int maHD, String hinhThucThanhToan) {
        String query = "insert into THANH_TOAN (HinhThucThanhToan, MaHD)\n"
                + "values(?,?)";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, hinhThucThanhToan);
            pstmt.setInt(2, maHD);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateKhuyenMai(int maKM, String tenKhuyenMai, Date ngayBD, Date ngayKT, float mucGiam, String maGiam, int trangThai, int donVi) {
        String query = "Update KHUYEN_MAI\n"
                + "set TenKhuyenMai = ?, NgayBD = ?, NgayKT= ?, MucGiam = ?,MaGiam = ?, TrangThai = ?, DonVi = ?\n"
                + "where MaKM = ?";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenKhuyenMai);
            pstmt.setDate(2, ngayBD);
            pstmt.setDate(3, ngayKT);
            pstmt.setFloat(4, mucGiam);
            pstmt.setString(5, maGiam);
            pstmt.setInt(6, trangThai);
            pstmt.setInt(7, donVi);
            pstmt.setInt(8, maKM);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean insertHDKM(int maHD, int maKM, float soTienConLai) {
        String query = "INSERT INTO HOA_DON_KHUYEN_MAI (MaHD, MaKM, SoTienConlai)\n"
                + "VALUES(?,?,?)";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maHD);
            pstmt.setInt(2, maKM);
            pstmt.setFloat(3, soTienConLai);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public List<chiTietSanPham> listAll2() {
        List<chiTietSanPham> listAll = new ArrayList<>();
        String query = """
                     select CHI_TIET_SAN_PHAM.MaCTSP, SAN_PHAM.TenSP, CHAT_LIEU_DE_GIAY.TenChatLieuDe, 
                                        SIZE.KichThuoc, MAU_SAC.TenMau, CHAT_LIEU.TenChatLieu,  
                                        CHI_TIET_SAN_PHAM.DonGia,CHI_TIET_SAN_PHAM.SoLuong, Giam_Gia.MucGiam,CHI_TIET_SAN_PHAM.mota 
                                        from CHI_TIET_SAN_PHAM 
                                          
                     			join SAN_PHAM on SAN_PHAM.MaSP =  CHI_TIET_SAN_PHAM.MaSP 
                                        join CHAT_LIEU_DE_GIAY on CHAT_LIEU_DE_GIAY.MaCLDe  =  CHI_TIET_SAN_PHAM.MaCLDe
                                        join SIZE on SIZE.MaSize =  CHI_TIET_SAN_PHAM.MaSize
                                        join MAU_SAC on MAU_SAC.MaMS =  CHI_TIET_SAN_PHAM.MaMS
                                        Left join Giam_Gia on Giam_Gia.MaGG= CHI_TIET_SAN_PHAM.MaGG
                                        join CHAT_LIEU on CHAT_LIEU.MaCL =  CHI_TIET_SAN_PHAM.MaCL""";

        Connection conn = null;
        try {
            conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                chiTietSanPham CTSP = new chiTietSanPham();
                CTSP.setMaCTSP(rs.getInt("MaCTSP"));
                CTSP.setTenSP(rs.getString("TenSP"));
                CTSP.setTenCLDe(rs.getString("TenChatLieuDe"));
                CTSP.setTenSize(rs.getInt("KichThuoc"));
                CTSP.setTenMS(rs.getString("TenMau"));
                CTSP.setTenCL(rs.getString("TenChatLieu"));
                CTSP.setDonGia(rs.getFloat("DonGia"));
                CTSP.setSoLuong(rs.getInt("SoLuong"));
                CTSP.setMucGG(rs.getFloat("MucGiam"));
                CTSP.setMoTa(rs.getString("mota"));

                listAll.add(CTSP);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAll;
    }

    public List<hoaDon> ThongKeTheoKhoangThoiGian(String ngBD, String ngKT) {
        List<hoaDon> listh= new ArrayList<>();
        String SQL = """
                     select HOA_DON.MaHD , COUNT(HOA_DON_CHI_TIET.MaCTSP)as 'TongHangHoa',HOA_DON.TongTien
                      from HOA_DON_CHI_TIET join HOA_DON on HOA_DON_CHI_TIET.MaHD= HOA_DON.MaHD
                            where HOA_DON.NgayTao BETWEEN ? AND ?
                        GROUP BY HOA_DON.MaHD, HOA_DON.TongTien""";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1,  ngBD);
            ps.setString(2,  ngKT);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hoaDon hd = new hoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setTongHH(rs.getInt("TongHangHoa"));
                hd.setTongTien(rs.getDouble("TongTien"));
                listh.add(hd);
            }
                return listh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
