package utilities;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.DanhMuc1;
import model.SanPham1;
import service.impl.DanhMucServiceImpl1;
import service.impl.SanPhamServiceImpl1;
import service.DanhMucService1;
import service.SanPhamService1;

/**
 *
 * @author ADMIN
 */
public class ValidateHelper {

    public static Boolean CheckNull(JTextField txt, String mss) {
        if (txt.getText().equals("") || txt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, mss);
            txt.setText("");
            txt.requestFocus();
            return true;
        }
        return false;
    }

    public static Boolean CheckSelectedRow(int index, String mss) {
        if (index < 0) {
            JOptionPane.showMessageDialog(null, mss);
            return true;
        }
        return false;
    }

    public static Boolean confirmDelete(Integer confirm) {
        if (confirm != JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }

    public static Boolean checkRowCount(int getRowCount, String mss) {
        if (getRowCount < 1) {
            JOptionPane.showMessageDialog(null, mss);
            return true;
        }
        return false;
    }

    public static Boolean checkDanhMuc(int index, String mss) {
        if (index == -1) {
            JOptionPane.showMessageDialog(null, mss);
            return true;
        }
        return false;
    }

    public static Boolean checkTrungTen(JTextField txt, String mss) {
        SanPhamService1 sanPhamService = new SanPhamServiceImpl1();
        DanhMucService1 danhMucService = new DanhMucServiceImpl1();
        for (SanPham1 sp : sanPhamService.getAllSanPham()) {
            if (sp.getTenSP().replaceAll("\\s", "").toLowerCase().equals(txt.getText().replaceAll("\\s", "").toLowerCase())) {
                JOptionPane.showMessageDialog(null, mss);
                txt.setText("");
                txt.requestFocus();
                return true;
            }
        }
        for (DanhMuc1 dm : danhMucService.getAllDanhMuc()) {
            if (dm.getTenDanhMuc().replaceAll("\\s", "").toLowerCase().equals(txt.getText().replaceAll("\\s", "").toLowerCase())) {
                JOptionPane.showMessageDialog(null, mss);
                return true;
            }
        }
        return false;
    }

    public static Boolean checkIsInteger(JTextField txt, String mss) {
        try {
            Integer.parseInt(txt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, mss);
            txt.setText("");
            txt.requestFocus();
            return true;
        }
        return false;
    }

    public static Boolean checkIsFloat(JTextField txt, String mss) {
        try {
            Float.parseFloat(txt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, mss);
            txt.setText("");
            txt.requestFocus();
            return true;
        }
        return false;
    }

    public static Boolean checkSize(JTextField txt, String mss) {
        if (Integer.parseInt(txt.getText()) < 36 || Integer.parseInt(txt.getText()) > 46) {
            JOptionPane.showMessageDialog(null, mss);
            txt.setText("");
            txt.requestFocus();
            return true;
        }
        return false;
    }
    
}
