/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import java.util.List;


/**
 *
 * @author trung
 */
public interface NhanVienInterface <NhanVien123> {
    
    void insert (NhanVien123 nhanvien);
    void update (NhanVien123 nhanvien, String index);
    void delete (int id);
    List<NhanVien123> findAll();
    NhanVien123 findById (String index);

}
