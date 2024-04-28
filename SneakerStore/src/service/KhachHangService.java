/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.KhachHang;
import java.sql.*;
import java.util.*;
import utilities.JdbcHelper;

public class KhachHangService {

    private List<KhachHang> listKH;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public List<KhachHang> getAll() {
        listKH = new ArrayList<>();
        String sql = "select*from THONG_TIN_KH";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)
                );
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        KhachHangService ks = new KhachHangService();
        List<KhachHang> kq = ks.getAll();
        for (KhachHang x : kq) {
            System.out.println(x.toString());
        }

    }

//NUT THEM
    public int ThemKH(KhachHang kh) {
        int kq = 0;
        sql = "insert into THONG_TIN_KH(TenKH,SDT,NgayCN,TrangThai)values(?,?,?,?)";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, kh.getTenKH());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getNgayCN());
            ps.setInt(4, kh.getTrangThai());

            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return kq;
        }
    }

    //nút Xóa 
    public int Xoakh(int ma) {
        int kq = 0;
        sql = "delete from THONG_TIN_KH where [MaTTKH] like ?";

        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return kq;
        }
    }

    //NÚT CẬP NHẬT
    public int CapNhat(KhachHang kh , int ma) {
        int kq = 0;
        sql = "update THONG_TIN_KH set  TenKH = ?, SDT = ?, NgayCN = ?, TrangThai = ? where [MaTTKH] like ?";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, kh.getTenKH());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getNgayCN());
            ps.setInt(4, kh.getTrangThai());
           ps.setInt(5, ma);
            kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

            kq = 0;
        }
        return kq;
    }

}
