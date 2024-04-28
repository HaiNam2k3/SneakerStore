package service;

import java.util.ArrayList;
import model.ChiTietSanPham1;

/**
 *
 * @author ADMIN
 */
public interface ChiTietSPService1 {

    ArrayList<ChiTietSanPham1> getAllCTSP();

    Integer addCTSP(ChiTietSanPham1 ctsp);

    Integer updateCTSP(ChiTietSanPham1 ctsp);

    Integer deleteCTSP(int MaCTSP);

    Integer addCTSPMaGGNull(ChiTietSanPham1 ctsp);

    ArrayList<ChiTietSanPham1> getCTSPByMa(int MaSP);

    ArrayList<ChiTietSanPham1> getCTSPMaGGNull();

    Integer updateCTSPMaGGNull(ChiTietSanPham1 ctsp);

    ArrayList<ChiTietSanPham1> getSPByMa(int MaSP);
}
