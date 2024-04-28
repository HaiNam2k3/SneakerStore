package service;

import java.util.ArrayList;
import model.SanPham1;

/**
 *
 * @author ADMIN
 */
public interface SanPhamService1 {
    ArrayList<SanPham1> getAllSanPham();
    Integer deleteSanPham(int MaSP);
    Integer addSanPham(SanPham1 sanPham);
    Integer updateSanPham(SanPham1 sanPham);
}
