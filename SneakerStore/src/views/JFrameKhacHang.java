/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.KhachHang;
import repository.Auth;
import repository.MsgBox;
import service.KhachHangService;

public class JFrameKhacHang extends javax.swing.JFrame {

    private KhachHangService ks = new KhachHangService();
    private DefaultTableModel model = new DefaultTableModel();
    private int index = -1;
    private ArrayList<KhachHang> list = new ArrayList<>();

    public JFrameKhacHang() {
        initComponents();
        this.fillTable(ks.getAll());
        setLocationRelativeTo(null);
    }

    // TẠO FILLTABLE
    public void fillTable(List<KhachHang> list) {
        list = ks.getAll();
        model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);// XÓA SẠCH BẢNG CŨ
        for (KhachHang x : list) {
            Object[] row = {
                x.getMaKH(), x.getTenKH(), x.getSdt(), x.getNgayCN(), x.getTrangThai() == 1 ? "Khách hàng thân thiết" : "Khách hàng mới"
            };
            model.addRow(row);
        }
    }

    public boolean validates() {

        if (txtTenKhachHang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng không được để trống!");
            return false;
        }
        if (txtSdt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng không được để trống!");
            return false;
        }
        if (txtNgayCapNhat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày cập nhật khách hàng không được để trống!");
            return false;
        }

        return true;
    }
    // Check dữ liệu nhập

    public boolean isValidName(String ten) {
        String regex = "[a-zA-Z]+";// phải đúng là chuỗi [a-zA-Z]
        return ten.matches(regex);
    }

    public boolean isValidDate(String ngayCapNhat) {
        String regex = "\\d+";  //  \\d+ chuỗi 0-9;+ có thể lặp lại một số nhiều lần
        return ngayCapNhat.matches(regex);
    }

    public boolean isValidNumberPhone(String sdt) {
        String regex = "\\d+";
        return sdt.matches(regex);
    }

    //TẠO READFROM
    KhachHang readForm() {
        int ma;
        String ten, sdt, ngayCN;
        int trangthai;

        ten = txtTenKhachHang.getText().trim();
        sdt = txtSdt.getText().trim();
        ngayCN = txtNgayCapNhat.getText().trim();

        if (rdKhachHangMoi.isSelected()) {
            trangthai = 0;
        } else {
            trangthai = 1;
        }

        return new KhachHang(ten, sdt, ngayCN, trangthai);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jpnNavigation = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNgayCapNhat = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        rdKhachHangMoi = new javax.swing.JRadioButton();
        jpnCRUD3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtTimKhachHang = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        rdKhachHangThanThiet = new javax.swing.JRadioButton();

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

        btnKhachHang.setBackground(new java.awt.Color(204, 204, 255));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKhachHang.setForeground(new java.awt.Color(0, 0, 255));
        btnKhachHang.setText("Quản lý khách hàng");
        btnKhachHang.setBorder(new javax.swing.border.MatteBorder(null));
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
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

        javax.swing.GroupLayout jpnNavigationLayout = new javax.swing.GroupLayout(jpnNavigation);
        jpnNavigation.setLayout(jpnNavigationLayout);
        jpnNavigationLayout.setHorizontalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btnHome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnNavigationLayout.setVerticalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 503, Short.MAX_VALUE)
                .addComponent(btnHome)
                .addGap(40, 40, 40))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Quản lý khách hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã thông tin khách hàng", "Tên khách hàng", "Số điện thoại", "Ngày cập nhật", "Trạng thái"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);

        jLabel3.setText("Mã thông tin khách hàng");

        txtMaKhachHang.setEnabled(false);

        jLabel14.setText("Tên khách hàng");

        jLabel15.setText("Số điện thoại");

        jLabel16.setText("Ngày cập nhật");

        jLabel17.setText("Trạng thái");

        rdKhachHangMoi.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdKhachHangMoi);
        rdKhachHangMoi.setText("Khách hàng mới");

        jpnCRUD3.setBackground(new java.awt.Color(255, 204, 204));
        jpnCRUD3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 51))); // NOI18N

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 0, 255));
        btnThem.setText("Thêm");
        btnThem.setBorder(new javax.swing.border.MatteBorder(null));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(0, 204, 0));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBorder(new javax.swing.border.MatteBorder(null));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 0, 0));
        btnXoa.setText("Xoá");
        btnXoa.setBorder(new javax.swing.border.MatteBorder(null));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear.setText("Clear");
        btnClear.setBorder(new javax.swing.border.MatteBorder(null));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnCRUD3Layout = new javax.swing.GroupLayout(jpnCRUD3);
        jpnCRUD3.setLayout(jpnCRUD3Layout);
        jpnCRUD3Layout.setHorizontalGroup(
            jpnCRUD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCRUD3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jpnCRUD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jpnCRUD3Layout.setVerticalGroup(
            jpnCRUD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCRUD3Layout.createSequentialGroup()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 49, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Tìm kiếm khách hàng :");

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdKhachHangThanThiet);
        rdKhachHangThanThiet.setText("Khách hàng thân thiết");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem)
                .addGap(101, 101, 101))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNgayCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rdKhachHangThanThiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdKhachHangMoi)
                                .addGap(145, 145, 145)))))
                .addGap(57, 57, 57)
                .addComponent(jpnCRUD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTimKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtNgayCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(rdKhachHangMoi)
                            .addComponent(rdKhachHangThanThiet)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jpnCRUD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        setVisible(false);
        new JFrameKhacHang().setVisible(true);
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setVisible(false);
        new JFrameMain().setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        int row = tblKhachHang.getSelectedRow();
        if (row == -1) {
            return;
        }

        txtMaKhachHang.setText(tblKhachHang.getValueAt(row, 0).toString());
        txtTenKhachHang.setText(tblKhachHang.getValueAt(row, 1).toString());
        txtSdt.setText(tblKhachHang.getValueAt(row, 2).toString());
        txtNgayCapNhat.setText(tblKhachHang.getValueAt(row, 3).toString());
       
        if ( tblKhachHang.getValueAt(row, 4).toString().endsWith("Khách hàng thân thiết")) {
          
              rdKhachHangThanThiet.setSelected(true);
        } else {
            rdKhachHangMoi.setSelected(true);
        }

    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        //  KhachHang kh = readForm();
//        if (validates()) {
//            if (ks.ThemKH(kh) > 0) {
//                JOptionPane.showMessageDialog(this, "Thêm thành công");
//                this.fillTable(ks.getAll());
//            } else {
//                JOptionPane.showMessageDialog(this, "Thêm thất bại");
//            }
//        }
//        boolean isValid = true;
//        String ten = kh.getTenKH();
//        String sdt = kh.getSdt();
//        String ngayCapNhat = kh.getNgayCN();
//        if (!isValidName(ten)) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên cho hợp lệ(TÊN CHỈ CHỨA CÁC CHỮ CÁI TỪ A-Z)");
//            isValid = false;
//            return;
//        } else if (!isValidName(ngayCapNhat)) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày cập nhật cho hợp lệ(NGÀY CẬP NHẬT CHỈ CHỨA CÁC CHỮ SỐ TỪ 0-9");
//            isValid = false;
//            return;
//        } else if (!isValidName(sdt)) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại cho hợp lệ(SỐ ĐIỆN THOẠI CHỈ CHỨA CÁC SỐ TỪ 0-9)");
//            isValid = false;
//            return;
//        } else {
//            this.fillTable(ks.getAll());
//        }
        insert();
        fillTable(list);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        index = tblKhachHang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào!");
        } else {
            int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn Cập nhật không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                KhachHang kh = readForm();
                int ma = (int) tblKhachHang.getValueAt(index, 0);
                if (ks.CapNhat(kh, ma) > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    this.fillTable(ks.getAll());
                    clear();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            }
        }


    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int admin = Auth.isManager();
        if (admin == 0) {
            index = tblKhachHang.getSelectedRow();

            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng xóa");
            } else {
                int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    int ma = (int) tblKhachHang.getValueAt(index, 0);
                    if (ks.Xoakh(ma) > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công");
                        fillTable(ks.getAll());
                        clear();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại");
                    }
                }
            }

        } else {
            MsgBox.alert(this, "Bạn không được phép xóa");
        }


    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtSdt.setText("");
        txtNgayCapNhat.setText("");
        buttonGroup1.clearSelection();

    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tblKhachHang.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtTimKhachHang.getText()));
        if (txtTimKhachHang.getText() == null) {
            fillTable(list);
        }

    }//GEN-LAST:event_btnTimKiemActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameKhacHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameKhacHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameKhacHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameKhacHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameKhacHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpnCRUD3;
    private javax.swing.JPanel jpnNavigation;
    private javax.swing.JRadioButton rdKhachHangMoi;
    private javax.swing.JRadioButton rdKhachHangThanThiet;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtNgayCapNhat;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTimKhachHang;
    // End of variables declaration//GEN-END:variables

    boolean check() {
        String tenKH;
        String soDienThoai;

        tenKH = txtTenKhachHang.getText();

        if (tenKH.isEmpty() || tenKH.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên khách hàng");
            return false;
        }
         soDienThoai = txtSdt.getText();
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
        kh.setTenKH(txtTenKhachHang.getText());
        kh.setSdt(txtSdt.getText());
        kh.setNgayCN(dateNow);
        int tt = 0;
        if (rdKhachHangThanThiet.isSelected()) {
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
            ks.ThemKH(kh);
            clear();
            return;
        } 

    }
    
    void clear(){
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtSdt.setText("");
        txtNgayCapNhat.setText("");
        buttonGroup1.clearSelection();
    }
}
