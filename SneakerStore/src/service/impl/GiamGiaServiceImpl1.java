package service.impl;

import java.util.ArrayList;
import model.GiamGia1;
import repository.GiamGiaRepository1;
import service.GiamGiaService1;

/**
 *
 * @author ADMIN
 */
public class GiamGiaServiceImpl1 implements GiamGiaService1{
    private final GiamGiaRepository1 giamGiaRepository = new GiamGiaRepository1();

    @Override
    public ArrayList<GiamGia1> getAllGiamGia() {
        return giamGiaRepository.getAllGiamGia();
    }
    
}
