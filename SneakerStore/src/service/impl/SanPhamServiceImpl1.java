/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import model.SanPham1;
import repository.SanPhamRopository1;
import service.SanPhamService1;

/**
 *
 * @author ADMIN
 */
public class SanPhamServiceImpl1 implements SanPhamService1 {

    public final SanPhamRopository1 sanPhamRopository = new SanPhamRopository1();

    @Override
    public ArrayList<SanPham1> getAllSanPham() {
        return sanPhamRopository.getAllSanPham();
    }

    @Override
    public Integer deleteSanPham(int MaSP) {
        return sanPhamRopository.deleteSanPham(MaSP);
    }

    @Override
    public Integer addSanPham(SanPham1 sanPham) {
        return sanPhamRopository.addSanPham(sanPham);
    }

    @Override
    public Integer updateSanPham(SanPham1 sanPham) {
        return sanPhamRopository.updateSanPham(sanPham);
    }

    
    
}
