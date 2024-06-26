/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.GiamGia;
import model.chiTietSanPham;
import repository.Auth;

import repository.GiamGiaRepository;
import service.GiamGiaService;
import service.ThongKeHoaDonService;

/**
 *
 * @author trung
 */
public class JFrameGiamGiaSanPham extends javax.swing.JFrame {

    /**
     * Creates new form JFrameQuanLyVoucher
     */
    private GiamGiaService giamGiaService;

    private GiamGiaRepository giamGiaRepository = new GiamGiaRepository();

    ThongKeHoaDonService thongKeHoaDonService = new ThongKeHoaDonService();

    public JFrameGiamGiaSanPham() throws SQLServerException {
        initComponents();
        loadTableGiamGia();
        fillTableDanhSachSP();
        setLocationRelativeTo(null);
        

    }

    public void loadTableGiamGia() throws SQLServerException {
        GiamGiaRepository giamGiaRepository = new GiamGiaRepository();

        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();

        model.setRowCount(0);

        for (GiamGia giamGia : giamGiaRepository.getAllGiamGias()) {

            Object[] rowData = new Object[]{
                giamGia.getMaGG(),
                giamGia.getTenMaGiam(),
                giamGia.getMucGiam(),
                giamGia.getNgayBatDau(),
                giamGia.getNgayKetThuc(),
                giamGia.getGhiChu()

            };
            model.addRow(rowData);
        }
    }

    void fillTableDanhSachSP() {
        List<chiTietSanPham> listCTSP = thongKeHoaDonService.listAll();

        DefaultTableModel model = (DefaultTableModel) tblDanhSachSP.getModel();
        model.setRowCount(0);
        for (chiTietSanPham CTSP : listCTSP) {
            Object[] data = {
                CTSP.getMaCTSP(), CTSP.getTenSP(), CTSP.getTenSize(), CTSP.getTenMS(), CTSP.getTenCL(),
                CTSP.getTenCLDe(), CTSP.getSoLuong(), CTSP.getDonGia(), CTSP.getMucGG()// Thêm trạng thái vào dòng dữ liệu
            };
            model.addRow(data);
        }
    }

    public void updateGiamGia() {
        try {
            

            int selectedRow = tblDanhSachSP.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Chọn một dòng để cập nhật");
                return;
            }

            int maCTSP = Integer.parseInt(tblDanhSachSP.getValueAt(selectedRow, 0).toString());

            String maGGString = JOptionPane.showInputDialog(this, "Nhập mã giảm giá mới:");

            if (maGGString == null || maGGString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã giảm giá");
                return;
            }

            int maGG = Integer.parseInt(maGGString);

            thongKeHoaDonService.updateMaGG(maCTSP, maGG);

            fillTableDanhSachSP();

            JOptionPane.showMessageDialog(this, "Add mã giảm giá thành công");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Add mã giảm giá thất bại");
        }
    }


    public void updateGiamGiaNull() {
        try {
            // Kiểm tra xem có dòng nào được chọn trong bảng hay không
            int selectedRow = tblDanhSachSP.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Chọn một dòng để xóa");
                return;
            }

            // Lấy mã chi tiết sản phẩm từ dòng được chọn trong bảng
            int maCTSP = Integer.parseInt(tblDanhSachSP.getValueAt(selectedRow, 0).toString());

            // Gọi phương thức để cập nhật mã giảm giá thành null
            thongKeHoaDonService.updateMaGGToNullByMaCTSP(maCTSP);

            // Làm mới bảng hiển thị sản phẩm
            fillTableDanhSachSP();
            // Hiển thị thông báo xóa thành công
            JOptionPane.showMessageDialog(this, "Xóa giảm giá thành công");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã giảm giá phải là một số nguyên");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xóa giảm giá thất bại");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnNavigation = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnVoucher = new javax.swing.JButton();
        btnGiamGiaSanPham = new javax.swing.JButton();
        btnvoucher = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachSP = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtTimKiem1 = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnTimKiem1 = new javax.swing.JButton();
        btnAddMaGG = new javax.swing.JButton();
        btnXoaGiamGia = new javax.swing.JButton();

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

        btnHome.setBackground(new java.awt.Color(255, 255, 153));
        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnVoucher.setBackground(new java.awt.Color(204, 204, 255));
        btnVoucher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVoucher.setForeground(new java.awt.Color(0, 0, 255));
        btnVoucher.setText("Quản lý giảm giá");
        btnVoucher.setBorder(new javax.swing.border.MatteBorder(null));
        btnVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoucherActionPerformed(evt);
            }
        });

        btnGiamGiaSanPham.setBackground(new java.awt.Color(204, 204, 255));
        btnGiamGiaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGiamGiaSanPham.setForeground(new java.awt.Color(0, 0, 255));
        btnGiamGiaSanPham.setText("Áp dụng giảm giá");
        btnGiamGiaSanPham.setBorder(new javax.swing.border.MatteBorder(null));
        btnGiamGiaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiamGiaSanPhamActionPerformed(evt);
            }
        });

        btnvoucher.setBackground(new java.awt.Color(204, 204, 255));
        btnvoucher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnvoucher.setForeground(new java.awt.Color(0, 0, 255));
        btnvoucher.setText("Voucher");
        btnvoucher.setBorder(new javax.swing.border.MatteBorder(null));
        btnvoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvoucherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnNavigationLayout = new javax.swing.GroupLayout(jpnNavigation);
        jpnNavigation.setLayout(jpnNavigationLayout);
        jpnNavigationLayout.setHorizontalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnHome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGiamGiaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnvoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnNavigationLayout.setVerticalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGiamGiaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnvoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(btnHome)
                .addGap(24, 24, 24))
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
                .addContainerGap(438, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(25, 25, 25))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã GG", "Tên GG", "Mức giảm", "Ngày bắt đầu", "Ngày kết thúc", "Ghi chú"
            }
        ));
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhuyenMai);

        tblDanhSachSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SPCT", "Tên SP", "Size", "Màu", "Chất liệu", "Chất liệu đế", " Số lượng còn", "Đơn giá", "Mức giảm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachSPMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDanhSachSPMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblDanhSachSP);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Danh sách khuyến mại");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Danh sách sản phẩm");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        txtTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiem1ActionPerformed(evt);
            }
        });

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem1.setText("Tìm kiếm");
        btnTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem1ActionPerformed(evt);
            }
        });

        btnAddMaGG.setText("Add GG");
        btnAddMaGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMaGGActionPerformed(evt);
            }
        });

        btnXoaGiamGia.setText("Delete GG");
        btnXoaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGiamGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(btnTimKiem)
                                            .addGap(40, 40, 40)
                                            .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btnAddMaGG)
                                    .addGap(51, 51, 51)
                                    .addComponent(btnXoaGiamGia)
                                    .addGap(48, 48, 48)
                                    .addComponent(btnTimKiem1)
                                    .addGap(39, 39, 39)
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel4))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem1)
                    .addComponent(btnAddMaGG)
                    .addComponent(btnXoaGiamGia))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 949, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(39, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setVisible(false);
        new JFrameMain().setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoucherActionPerformed
        setVisible(false);
        try {
            new JFrameGiamGia().setVisible(true);
        } catch (SQLServerException ex) {
            Logger.getLogger(JFrameGiamGiaSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVoucherActionPerformed

    private void btnGiamGiaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiamGiaSanPhamActionPerformed
        setVisible(false);
        try {
            new JFrameGiamGiaSanPham().setVisible(true);
        } catch (SQLServerException ex) {
            Logger.getLogger(JFrameGiamGiaSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGiamGiaSanPhamActionPerformed

    private void tblDanhSachSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSPMouseClicked
      //  Auth us=  
       

    }//GEN-LAST:event_tblDanhSachSPMouseClicked

    private void tblDanhSachSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSPMousePressed

    }//GEN-LAST:event_tblDanhSachSPMousePressed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked

    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void txtTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiem1ActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tblKhuyenMai.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtTimKiem1.getText()));
        if (txtTimKiem1.getText() == null) {
            try {
                loadTableGiamGia();
            } catch (SQLServerException ex) {
                Logger.getLogger(JFrameNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblDanhSachSP.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tblDanhSachSP.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
        if (txtTimKiem.getText() == null) {
            fillTableDanhSachSP();
        }
    }//GEN-LAST:event_btnTimKiem1ActionPerformed

    private void btnvoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvoucherActionPerformed
        // TODO add your handling code here:
         setVisible(false);
        new JFrameVoucher().setVisible(true);
    }//GEN-LAST:event_btnvoucherActionPerformed

    private void btnAddMaGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMaGGActionPerformed
        // TODO add your handling code here:
        updateGiamGia();
    }//GEN-LAST:event_btnAddMaGGActionPerformed

    private void btnXoaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGiamGiaActionPerformed
        updateGiamGiaNull();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaGiamGiaActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameGiamGiaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameGiamGiaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameGiamGiaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameGiamGiaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFrameGiamGiaSanPham().setVisible(true);
                } catch (SQLServerException ex) {
                    Logger.getLogger(JFrameGiamGiaSanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMaGG;
    private javax.swing.JButton btnGiamGiaSanPham;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiem1;
    private javax.swing.JButton btnVoucher;
    private javax.swing.JButton btnXoaGiamGia;
    private javax.swing.JButton btnvoucher;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jpnNavigation;
    private javax.swing.JTable tblDanhSachSP;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiem1;
    // End of variables declaration//GEN-END:variables
}
