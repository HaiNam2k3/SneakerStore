package service;

import java.util.ArrayList;
import model.DanhMuc1;

/**
 *
 * @author ADMIN
 */
public interface DanhMucService1 {

    public ArrayList<DanhMuc1> getAllDanhMuc();
    Integer addDanhMuc(DanhMuc1 danhMuc);
    Integer updateDanhMuc(DanhMuc1 danhMuc);
    Integer deleteDanhMuc(int MaDM);
}
