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
public interface GiamGiaInterface <GiamGia123> {
    void insert (GiamGia123 giamGia);
    void update (GiamGia123 giamGia, String index);
    void delete (int id);
    List<GiamGia123> findAll();
    GiamGia123 findById (String index);
}
