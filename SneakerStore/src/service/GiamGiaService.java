/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Interface.GiamGiaInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.List;
import model.GiamGia;
import repository.GiamGiaRepository;

/**
 *
 * @author trung
 */
public class GiamGiaService implements GiamGiaInterface<GiamGia>{
    private GiamGiaRepository giamGiaRepository;

    public GiamGiaService() throws SQLServerException {
        this.giamGiaRepository = new GiamGiaRepository();
    }

    @Override
    public void insert(GiamGia giamGia) {
        this.giamGiaRepository.addGiamGia(giamGia);
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override 
    public void update(GiamGia giamGia, String index) {
        this.giamGiaRepository.update(giamGia,index);
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int maGG) {
        this.giamGiaRepository.deleteGiamGia(maGG);
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GiamGia> findAll() {
        return this.giamGiaRepository.getAllGiamGias();
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GiamGia findById(String index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
