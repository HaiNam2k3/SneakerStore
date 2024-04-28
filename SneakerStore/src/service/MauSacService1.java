package service;

import java.util.ArrayList;
import model.MauSac1;

/**
 *
 * @author ADMIN
 */
public interface MauSacService1 {
    ArrayList<MauSac1> getAllMauSac();
    Integer addMauSac(MauSac1 mauSac);
    Integer updateMauSac(MauSac1 mauSac);
    Integer deleteMauSac(int MaMS);
}
