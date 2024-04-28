package service.impl;

import java.util.ArrayList;
import model.ChiTietSanPham1;
import repository.ChiTietSPRepository1;
import service.ChiTietSPService1;

/**
 *
 * @author ADMIN
 */
public class ChiTietSPServiceImpl1 implements ChiTietSPService1 {

    private final ChiTietSPRepository1 chiTietSPRepository = new ChiTietSPRepository1();

    @Override
    public ArrayList<ChiTietSanPham1> getAllCTSP() {
        return chiTietSPRepository.getAllCTSP();
    }

    @Override
    public Integer addCTSP(ChiTietSanPham1 ctsp) {
        return chiTietSPRepository.addCTSP(ctsp);
    }

    @Override
    public Integer updateCTSP(ChiTietSanPham1 ctsp) {
        return chiTietSPRepository.updateCTSP(ctsp);
    }

    @Override
    public Integer deleteCTSP(int MaCTSP) {
        return chiTietSPRepository.deleteCTSP(MaCTSP);
    }

    @Override
    public Integer addCTSPMaGGNull(ChiTietSanPham1 ctsp) {
        return chiTietSPRepository.addCTSPMaGGNull(ctsp);
    }


    @Override
    public ArrayList<ChiTietSanPham1> getCTSPByMa(int MaSP) {
        return chiTietSPRepository.getCTSPByMaSP(MaSP);
    }

    @Override
    public ArrayList<ChiTietSanPham1> getCTSPMaGGNull() {
        return chiTietSPRepository.getCTSPMaGGNull();
    }

    @Override
    public Integer updateCTSPMaGGNull(ChiTietSanPham1 ctsp) {
        return chiTietSPRepository.updateCTSPMaGGNull(ctsp);
    }

    @Override
    public ArrayList<ChiTietSanPham1> getSPByMa(int MaSP) {
        return chiTietSPRepository.getSPByMa(MaSP);
    }
    
    

}
