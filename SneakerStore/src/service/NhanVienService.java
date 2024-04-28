/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Interface.NhanVienInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.List;
import model.NhanVien;
import repository.NhanVienRepository;

/**
 *
 * @author trung
 */
public class NhanVienService implements NhanVienInterface<NhanVien> {

    private NhanVienRepository nhanVienRepository;

    public NhanVienService() throws SQLServerException {
        this.nhanVienRepository = new NhanVienRepository();
    }

    @Override
    public void insert(NhanVien nhanvien) {
        this.nhanVienRepository.addNhanVien(nhanvien);
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(NhanVien nhanvien, String index) {
        this.nhanVienRepository.update(nhanvien, index);
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int maNV) {
        this.nhanVienRepository.delete(maNV);
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NhanVien> findAll() {
        return this.nhanVienRepository.getAllNhanViens();
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhanVien findById(String index) {
        try {
            return this.nhanVienRepository.getNhanVienByTenDangNhap(index);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public NhanVien selectByTenDangNhap(String tenDangNhap) {
        return this.nhanVienRepository.getNhanVienByTenDangNhap(tenDangNhap);
    }

}
