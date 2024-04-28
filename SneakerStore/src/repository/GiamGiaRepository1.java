package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GiamGia1;
import utilities.JdbcHelper1;

/**
 *
 * @author ADMIN
 */
public class GiamGiaRepository1 {

    public ArrayList<GiamGia1> getAllGiamGia() {
        ArrayList<GiamGia1> listGiamGia = new ArrayList<>();
        Connection cn = JdbcHelper1.getConnection();
        Statement stm;
        String sql = "SELECT * FROM Giam_Gia";
        try {
            stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                GiamGia1 giamGia = new GiamGia1(rs.getInt("MaGG"), rs.getString("TenMaGiam"), rs.getFloat("MucGiam"), rs.getDate("NgayBatDau"), rs.getDate("NgayKetThuc"), rs.getString("GhiChu"));
                listGiamGia.add(giamGia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucRepository1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGiamGia;
    }

}
