/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoanh
 */
public class hinhThucThanhToan {
    private int maTT;
    private String hinhThucThanhToan;

    public hinhThucThanhToan() {
    }

    public hinhThucThanhToan(int maTT, String hinhThucThanhToan) {
        this.maTT = maTT;
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }
    
}
