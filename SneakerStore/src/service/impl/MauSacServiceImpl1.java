package service.impl;

import java.util.ArrayList;
import model.MauSac1;
import repository.MauSacRepository1;
import service.MauSacService1;

/**
 *
 * @author ADMIN
 */
public class MauSacServiceImpl1 implements MauSacService1{
    private final MauSacRepository1 mauSacRepository = new MauSacRepository1();

    @Override
    public ArrayList<MauSac1> getAllMauSac() {
        return mauSacRepository.getAllMauSac();
    }

    @Override
    public Integer addMauSac(MauSac1 mauSac) {
        return mauSacRepository.addMauSac(mauSac);
    }

    @Override
    public Integer updateMauSac(MauSac1 mauSac) {
        return mauSacRepository.updateMauSac(mauSac);
    }    

    @Override
    public Integer deleteMauSac(int MaMS) {
        return mauSacRepository.deleteMauSac(MaMS);
    }
    
    
}
