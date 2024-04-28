/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import service.KhachHangService;

/**
 *
 * @author hoanh
 */
public class ThemKHNhanh extends javax.swing.JDialog {

    /**
     * Creates new form ThemKHNhanh
     */
    public ThemKHNhanh(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        fillTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblkh = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtSDTKH = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        rdoNewKH = new javax.swing.JRadioButton();
        rdoOldKH = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblkh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Tên KH", "SDT", "Ngày tạo", "Trạng thái"
            }
        ));
        jScrollPane1.setViewportView(tblkh);

        jButton1.setText("Thêm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên");

        jLabel2.setText("SDT");

        jLabel3.setText("Ngày tạo");

        jLabel4.setText("Trạng thái");

        buttonGroup1.add(rdoNewKH);
        rdoNewKH.setText("Khách hàng mới");

        buttonGroup1.add(rdoOldKH);
        rdoOldKH.setText("Khách hàng thân thiết");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdoNewKH)
                        .addGap(70, 70, 70)
                        .addComponent(rdoOldKH)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenKH)
                            .addComponent(txtSDTKH)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdoNewKH)
                    .addComponent(rdoOldKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insert();     
        fillTable();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemKHNhanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemKHNhanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemKHNhanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemKHNhanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemKHNhanh dialog = new ThemKHNhanh(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNewKH;
    private javax.swing.JRadioButton rdoOldKH;
    private javax.swing.JTable tblkh;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
    List<KhachHang> list = new ArrayList<>();
    KhachHangService service = new KhachHangService();
    
    void fillTable(){
        list = service.getAll();
        DefaultTableModel model = (DefaultTableModel) tblkh.getModel();
        model.setRowCount(0);
        for (KhachHang kh : list) {
            Object[] row={
            kh.getMaKH(), kh.getTenKH(),kh.getSdt(),kh.getNgayCN(),kh.getTrangThai()==1?"Khách hàng thân thiết":"Khách hàng mới"
            };
            model.addRow(row);
        }
    }
    
    boolean check() {
        String tenKH;
        String soDienThoai;

        tenKH = txtTenKH.getText();

        if (tenKH.isEmpty() || tenKH.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên khách hàng");
            return false;
        }
         soDienThoai = txtSDTKH.getText();
         String regex = "^(0|\\+84)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))[0-9]{7}$";

        // Tạo Pattern từ regex
       // Pattern pattern = Pattern.compile(regex);
        if (!(soDienThoai.matches(regex))) {
            JOptionPane.showMessageDialog(this, "Số điện thoại chỉ chứa số");
            return false;
        }
       
        if (soDienThoai.isEmpty() || soDienThoai.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại, Sai dinh dang sdt");
            return false;
        }
   
        if (!(soDienThoai.length() == 10)) {
            JOptionPane.showMessageDialog(this, "số điện thoại phải đủ 10 số");
            return false;
        }

        return true;
    }

    public KhachHang getKhachHang() {
        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = (DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ldt));
        Date now = new Date();

        KhachHang kh = new KhachHang();
        kh.setTenKH(txtTenKH.getText());
        kh.setSdt(txtSDTKH.getText());
        kh.setNgayCN(dateNow);
        int tt = 0;
        if (rdoOldKH.isSelected()) {
            tt = 1;
        } else {
            tt = 0;
        }
        kh.setTrangThai(tt);

        return kh;
    }

    void insert() {
        boolean check = check();
        if (check) {
            KhachHang kh = getKhachHang();
            service.ThemKH(kh);
            clear();
            return;
        } 

    }
    void clear(){
       
        txtTenKH.setText("");
        txtSDTKH.setText("");
        txtNgayTao.setText("");
        buttonGroup1.clearSelection();
    }
   
}