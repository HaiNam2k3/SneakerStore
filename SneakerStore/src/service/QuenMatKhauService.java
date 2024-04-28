/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
//import Repository.Getconnection;
//import Repository.XEmail;
import java.sql.*;
import repository.RandomSNN;
import repository.XEmail;
import utilities.JdbcHelper;

/**
 *
 * @author hoanh
 */
public class QuenMatKhauService {
    RandomSNN random = new RandomSNN();
    
    public boolean checkuser(String email){
        try {
            String SQL="select * from nhan_vien where Email like ?";
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement sttm = conn.prepareStatement(SQL);
            
            sttm.setObject(1, email);
            ResultSet rs = sttm.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            if(count ==1){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateMK(String email){
        int check=0;
        String SQL_doimk ="""
                      update nhan_vien set MatKhau =? where Email like ?""";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement sttm = conn.prepareStatement(SQL_doimk);
            String Password = random.soNgauNhienString(6);
            
            sttm.setObject(1, Password);
            sttm.setObject(2, email);
            
            XEmail em = new XEmail();
            em.sendMail(email, Password);
            
            check = sttm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check>0;
    }
    
    
    public boolean doiMK(String mk){
        int check =0;
        String query ="update Nhan_Vien set MatKhau =? ";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, mk);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return check>0;
    }
    
}
