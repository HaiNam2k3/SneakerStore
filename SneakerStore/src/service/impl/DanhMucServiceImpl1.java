package service.impl;

import java.util.ArrayList;
import model.DanhMuc1;
import repository.DanhMucRepository1;
import service.DanhMucService1;

/**
 *
 * @author ADMIN
 */
public class DanhMucServiceImpl1 implements DanhMucService1 {

    private final DanhMucRepository1 danhMucRepository = new DanhMucRepository1();

    public ArrayList<DanhMuc1> getAllDanhMuc() {
        return danhMucRepository.getAllDanhMuc();
    }

    @Override
    public Integer addDanhMuc(DanhMuc1 danhMuc) {
        return danhMucRepository.addDanhMuc(danhMuc);
    }

    @Override
    public Integer updateDanhMuc(DanhMuc1 danhMuc) {
        return danhMucRepository.updateDanhMuc(danhMuc);
    }

    @Override
    public Integer deleteDanhMuc(int MaDM) {
        return danhMucRepository.deleteDanhMuc(MaDM);
    }
    
    
}
