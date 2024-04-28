/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import connect.ConnectDB;
import dao.VoucherDAO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Voucherq;

/**
 *
 * @author quan
 */
public class JFrameVoucher extends javax.swing.JFrame implements MouseListener {

        public JFrameVoucher() {
            
                try {
                        ConnectDB.getInstance().connect();
                        System.out.println("Ket noi thanh cong!");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                this.voucherDAO = new VoucherDAO();

                initComponents();

                this.jTable1.addMouseListener(this);

                this.doDuLieuVaoTable();
                setLocationRelativeTo(null);
        }
         

        private void doDuLieuVaoTable() {

 ArrayList<Voucherq> vouchers = this.voucherDAO.layThongTinVoucher();
    Date currentDate = new Date(); // Ngày hiện tại

    for (Voucherq voucher : vouchers) {
        Object[] rowData = {
                voucher.getMaKM(),
                voucher.getTenKhuyenMai(),
                voucher.getNgayBD(),
                voucher.getNgayKT(),
                voucher.getMucGiam(),
                voucher.getMaGiam(),
                voucher.isDonVi() == true ? "%" : "VND",
                voucher.getNgayKT().after(currentDate) ? "Hoạt động" : "Không hoạt động" // Kiểm tra ngày kết thúc
        };
        this.modelTable.addRow(rowData);
    }
    
        }
    

        private void clear() {
                this.jTextField6.setText("");
                this.jTextField7.setText("");
                this.jTextField8.setText("");
                this.jTextField9.setText("");
                this.jDateChooser1.setDate(null);
                this.jDateChooser2.setDate(null);
                this.jRadioButton1.setSelected(false);
                this.jRadioButton2.setSelected(false);
                this.jRadioButton3.setSelected(false);
                this.jRadioButton4.setSelected(false);

                this.jTextField6.requestFocus();
        }

        private void emptyFunc() {

        }

        private void themVoucher() {

if (jTextField7.getText().isEmpty() || jDateChooser1.getDate() == null || jDateChooser2.getDate() == null || jTextField8.getText().isEmpty() || jTextField9.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
        return;
    }
    
    String TenKhuyenMai = jTextField7.getText().trim();
    Date NgayBD = jDateChooser1.getDate();
    Date NgayKT = jDateChooser2.getDate();
    String MaGiam = jTextField8.getText().trim();
    double MucGiam = Double.parseDouble(jTextField9.getText().trim());
    boolean DonVi = jRadioButton2.isSelected();
    boolean TrangThai = jRadioButton3.isSelected();

    
    if (NgayKT.before(NgayBD)) {
        JOptionPane.showMessageDialog(null, "Ngày kết thúc không được trước ngày bắt đầu!");
        return;
    }

    
    if (DonVi) { 
        if (MucGiam < 0 || MucGiam > 100) {
            JOptionPane.showMessageDialog(null, "Mức giảm phải từ 0 đến 100 khi chọn %!");
            return;
        }
    } else { 
        if (MucGiam < 0) {
            JOptionPane.showMessageDialog(null, "Mức giảm không được nhỏ hơn 0 khi chọn VND!");
            return;
        }
    }

    Voucherq voucher = new Voucherq(TenKhuyenMai, NgayBD, NgayKT, MucGiam, MaGiam, DonVi, TrangThai);

    if (this.voucherDAO.them(voucher)) {
        List<Voucherq> vouchers = this.voucherDAO.layThongTinVoucher();
        int n = vouchers.size();
        JOptionPane.showMessageDialog(null, "Thêm voucher thành công!");
        Object[] rowData = {
            vouchers.get(n - 1).getMaKM(),
            voucher.getTenKhuyenMai(),
            voucher.getNgayBD(),
            voucher.getNgayKT(),
            voucher.getMucGiam(),
            voucher.getMaGiam(),
            voucher.isDonVi() == true ? "%" : "VND",
            voucher.isTrangThai() == true ? "Hoạt động" : "Không hoạt động"
        };
        this.modelTable.addRow(rowData);
        clear();
    
                }

        }

        private void suaVoucher() {
               
int maKM = 0;
    try {
        maKM = Integer.parseInt(this.jTextField6.getText().trim());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Mã khuyến mãi phải là một số nguyên!");
        return;
    }
    
    String tenKhuyenMai = this.jTextField7.getText().trim();
    Date ngayBD = this.jDateChooser1.getDate();
    Date ngayKT = this.jDateChooser2.getDate();
    String maGiam = this.jTextField8.getText().trim();
    String mucGiamStr = this.jTextField9.getText().trim();

    // Kiểm tra xem các trường thông tin có đang trống không
    if (tenKhuyenMai.isEmpty() || ngayBD == null || ngayKT == null || maGiam.isEmpty() || mucGiamStr.isEmpty()) {
        StringBuilder errorMessage = new StringBuilder("Vui lòng nhập đầy đủ thông tin:");
        if (tenKhuyenMai.isEmpty()) errorMessage.append("\n- Tên khuyến mãi");
        if (ngayBD == null) errorMessage.append("\n- Ngày bắt đầu");
        if (ngayKT == null) errorMessage.append("\n- Ngày kết thúc");
        if (maGiam.isEmpty()) errorMessage.append("\n- Mã giảm giá");
        if (mucGiamStr.isEmpty()) errorMessage.append("\n- Mức giảm");
        JOptionPane.showMessageDialog(null, errorMessage.toString());
        return;
    }

    // Chuyển đổi dữ liệu đầu vào sang dạng số và kiểm tra
    double mucGiam = 0.0;
    try {
        mucGiam = Double.parseDouble(mucGiamStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Mức giảm phải là một số!");
        return;
    }

    // Kiểm tra xem người dùng đã chọn đúng đơn vị và mức giảm có đáp ứng điều kiện yêu cầu hay không
    boolean donVi = this.jRadioButton2.isSelected();
    if (donVi && (mucGiam <= 0 || mucGiam > 100)) {
        JOptionPane.showMessageDialog(null, "Mức giảm phải nằm trong khoảng từ 0 đến 100 khi chọn %!");
        return;
    }

    if (!donVi && mucGiam <= 0) {
        JOptionPane.showMessageDialog(null, "Mức giảm phải lớn hơn 0 khi chọn VND!");
        return;
    }

    // Tạo đối tượng voucher và thực hiện sửa vào cơ sở dữ liệu
    boolean trangThai = this.jRadioButton3.isSelected();
    Voucherq voucher = new Voucherq(maKM, tenKhuyenMai, ngayBD, ngayKT, mucGiam, maGiam, donVi, trangThai);
    
    if (this.voucherDAO.sua(voucher)) {
        JOptionPane.showMessageDialog(null, "Sửa voucher thành công!");
        int row = this.jTable1.getSelectedRow();
        Object[] rowData = {
            voucher.getMaKM(),
            voucher.getTenKhuyenMai(),
            voucher.getNgayBD(),
            voucher.getNgayKT(),
            voucher.getMucGiam(),
            voucher.getMaGiam(),
            voucher.isDonVi() ? "%" : "VND",
            voucher.isTrangThai() ? "Hoạt động" : "Không hoạt động"
        };
        this.modelTable.removeRow(row);
        this.modelTable.insertRow(row, rowData);
        clear(); // Xóa các trường nhập liệu để chuẩn bị cho việc nhập liệu tiếp theo
    } else {
        JOptionPane.showMessageDialog(null, "Sửa voucher thất bại!");
    }
        }

        private void xoaVoucher() {
                int MaKM = Integer.parseInt(this.jTextField6.getText().trim());

                int confirm = JOptionPane.showConfirmDialog(null, "Chắc chắn xóa voucher này", "Chú ý",
                                JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.NO_OPTION) {
                        return;
                }

                if (this.voucherDAO.xoa(MaKM)) {
                        JOptionPane.showMessageDialog(null, "Xóa voucher thành công!");
                        int row = this.jTable1.getSelectedRow();
                        this.modelTable.removeRow(row);
                        clear();
                }
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jpnNavigation = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnVoucher = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnNavigation.setBackground(new java.awt.Color(153, 153, 255));
        jpnNavigation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconSwing/Shop.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnVoucher.setBackground(new java.awt.Color(204, 204, 255));
        btnVoucher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVoucher.setForeground(new java.awt.Color(0, 0, 255));
        btnVoucher.setText("Quản lý Voucher");
        btnVoucher.setBorder(new javax.swing.border.MatteBorder(null));
        btnVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoucherActionPerformed(evt);
            }
        });

        btnHome.setBackground(new java.awt.Color(255, 255, 153));
        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(204, 204, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 255));
        jButton5.setText("Quản lý giảm giá");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(204, 204, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 255));
        jButton6.setText("Áp mã giảm giá");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnNavigationLayout = new javax.swing.GroupLayout(jpnNavigation);
        jpnNavigation.setLayout(jpnNavigationLayout);
        jpnNavigationLayout.setHorizontalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnNavigationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnNavigationLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnHome)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpnNavigationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnNavigationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnNavigationLayout.setVerticalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHome)
                .addGap(31, 31, 31))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Quản lý voucher");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel2)
                .addContainerGap(476, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(25, 25, 25))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        String[] columnNames = { "Mã KM", "Tên khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Mức giảm",
        "Mã giảm",
        "Đơn vị", "Trạng thái" };

this.modelTable = new DefaultTableModel(columnNames, 0);
this.jTable1.setModel(modelTable);
        jScrollPane1.setViewportView(jTable1);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("VND");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("%");

        jRadioButton3.setText("Hoạt động");

        jRadioButton4.setText("Ngưng hoạt động");

        jLabel1.setText("Mã khuyến mại:");

        jLabel3.setText("Tên khuyến mại:");

        jLabel4.setText("Mã giảm:");

        jLabel5.setText("Mức giảm:");

        jLabel6.setText("Trạng thái:");

        jLabel7.setText("Đơn vị:");

        jLabel8.setText("Ngày bắt đầu:");

        jLabel9.setText("Ngày kết thúc:");

        jButton2.setText("Thêm");

        jButton3.setText("Sửa");

        jButton4.setText("Xóa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 957, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(99, 99, 99)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8))
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jLabel6))
                .addGap(0, 79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnNavigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnNavigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
            try {
                // TODO add your handling code here:
                setVisible(false);
                new JFrameGiamGia().setVisible(true);
            } catch (SQLServerException ex) {
                Logger.getLogger(JFrameVoucher.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
            try {
                new JFrameGiamGiaSanPham().setVisible(true);
            } catch (SQLServerException ex) {
                Logger.getLogger(JFrameVoucher.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton6ActionPerformed

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jButton1ActionPerformed

        private void btnVoucherActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnVoucherActionPerformed
                setVisible(false);
                new JFrameVoucher().setVisible(true);
        }// GEN-LAST:event_btnVoucherActionPerformed

        private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnHomeActionPerformed
                setVisible(false);
                new JFrameMain().setVisible(true);
        }// GEN-LAST:event_btnHomeActionPerformed

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                /* Set the Nimbus look and feel */
                // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
                // (optional) ">
                /*
                 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
                 * look and feel.
                 * For details see
                 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
                 */
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(JFrameVoucher.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(JFrameVoucher.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(JFrameVoucher.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(JFrameVoucher.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                }
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new JFrameVoucher().setVisible(true);
                        }
                });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnVoucher;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel jpnNavigation;
    private DefaultTableModel modelTable;
    private VoucherDAO voucherDAO;
    // End of variables declaration//GEN-END:variables
        @Override
        public void mouseClicked(MouseEvent e) {
                int row = this.jTable1.getSelectedRow();
                this.jTextField6.setText(this.jTable1.getValueAt(row, 0).toString());
                this.jTextField7.setText(this.jTable1.getValueAt(row, 1).toString());
                this.jTextField8.setText(this.jTable1.getValueAt(row, 5).toString());
                this.jTextField9.setText(this.jTable1.getValueAt(row, 4).toString());

                try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = dateFormat.parse(this.jTable1.getValueAt(row, 2).toString());
                        Date date2 = dateFormat.parse(this.jTable1.getValueAt(row, 3).toString());

                        this.jDateChooser1.setDate(date1);
                        this.jDateChooser2.setDate(date2);
                } catch (Exception ex) {
                        ex.printStackTrace();
                }
                if (this.jTable1.getValueAt(row,
                                7).toString().equals("Hoạt động")) {
                        this.jRadioButton3.setSelected(true);
                } else {
                        this.jRadioButton4.setSelected(true);
                }

                if (this.jTable1.getValueAt(row,
                                6).toString().equals("%")) {
                        this.jRadioButton2.setSelected(true);
                } else {
                        this.jRadioButton1.setSelected(true);
                }

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
}
