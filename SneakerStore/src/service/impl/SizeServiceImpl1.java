package service.impl;

import java.util.ArrayList;
import model.Size1;
import repository.SizeRepository1;
import service.SizeService1;

/**
 *
 * @author ADMIN
 */
public class SizeServiceImpl1 implements SizeService1{
    
    private final SizeRepository1 sizeRepository = new SizeRepository1();

    @Override
    public ArrayList<Size1> getAllSize() {
        return sizeRepository.getAllSize();
    }

    @Override
    public Integer addSize(Size1 size) {
        return sizeRepository.addSize(size);
    }

    @Override
    public Integer updateSize(Size1 size) {
        return sizeRepository.updateSize(size);
    }

    @Override
    public Integer deleteSize(int MaSize) {
        return sizeRepository.deleteSize(MaSize);
    }
    
    
    
}
