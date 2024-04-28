/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.logging.Logger;

/**
 *
 * @author hoanh
 */
public class size {
    private int maSize ;
    private int kichThuoc;
    private boolean trangThai;

    public size() {
    }

    public size(int maSize, int kichThuoc, boolean trangThai) {
        this.maSize = maSize;
        this.kichThuoc = kichThuoc;
        this.trangThai = trangThai;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public int getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(int kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
   
    
    
    
}
