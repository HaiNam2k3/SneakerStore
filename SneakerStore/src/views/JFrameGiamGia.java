/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.GiamGia;
import repository.GiamGiaRepository;
import service.GiamGiaService;

/**
 *
 * @author trung
 */
public class JFrameGiamGia extends javax.swing.JFrame {

    private DefaultTableModel dtm = new DefaultTableModel();

    private GiamGiaService giamGiaService;

    private GiamGiaRepository giamGiaRepository = new GiamGiaRepository();

    private int index = -1;

    public JFrameGiamGia() throws SQLServerException {
        initComponents();
        setLocationRelativeTo(null);
        loadTable();
        this.giamGiaService = new GiamGiaService();
    }

    public void loadTable() throws SQLServerException {
        GiamGiaRepository giamGiaRepository = new GiamGiaRepository();

        dtm = (DefaultTableModel) tblGiamGia.getModel();
        dtm.setRowCount(0);

        for (GiamGia giamGia : giamGiaRepository.getAllGiamGias()) {

            Object[] rowData = new Object[]{
                giamGia.getMaGG(),
                giamGia.getTenMaGiam(),
                giamGia.getMucGiam(),
                giamGia.getNgayBatDau(),
                giamGia.getNgayKetThuc(),
                giamGia.getGhiChu()

            };
            dtm.addRow(rowData);
        }
    }

    public void mouseClick() {
        int row = tblGiamGia.getSelectedRow();
        if (row < 0) {
            return;
        }
        txtMaGG.setText(tblGiamGia.getValueAt(row, 0).toString());
        txtTenGG.setText(tblGiamGia.getValueAt(row, 1).toString());
        txtMucGiam.setText(tblGiamGia.getValueAt(row, 2).toString());
        txtNgayBatDau.setText(tblGiamGia.getValueAt(row, 3).toString());
        txtNgayKetThuc.setText(tblGiamGia.getValueAt(row, 4).toString());
        txtGhiChu.setText(tblGiamGia.getValueAt(row, 5).toString());
    }

    public void clearForm() {
        txtMaGG.setText("");
        txtTenGG.setText("");
        txtMucGiam.setText("");
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        txtGhiChu.setText("");

    }

    private GiamGia getDataGiamGia() throws ParseException {
        GiamGia giamGia = new GiamGia();

        // Lấy và kiểm tra dữ liệu từ các trường nhập liệu
        String maGGText = txtMaGG.getText().trim();
        String tenGGText = txtTenGG.getText().trim();
        String mucGiamText = txtMucGiam.getText().trim();
        String ghiChuText = txtGhiChu.getText().trim();
        String ngayBatDau = txtNgayBatDau.getText().trim();
        String ngayKetThuc = txtNgayKetThuc.getText().trim();

        SimpleDateFormat formart = new SimpleDateFormat("yyyy-MM-dd");
        // Kiểm tra xem có trường nào trống không
        // Nếu không có trường nào trống, gán giá trị cho đối tượng GiamGia
        giamGia.setMaGG(Integer.parseInt(maGGText));
        giamGia.setTenMaGiam(tenGGText);
        giamGia.setMucGiam(Float.parseFloat(mucGiamText));
        giamGia.setNgayBatDau(formart.parse(ngayBatDau));
        giamGia.setNgayKetThuc(formart.parse(ngayKetThuc));
        giamGia.setGhiChu(ghiChuText);

        if (tenGGText.isEmpty() || mucGiamText.isEmpty() || ghiChuText.isEmpty() || ngayBatDau.isEmpty() || ngayKetThuc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return giamGia;
    }

    public void addGiamGia() {
        try {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?");
            if (check != JOptionPane.YES_OPTION) {
                return;
            }
            GiamGia giamGia = getDataGiamGia();
            giamGiaService.insert(giamGia);

            loadTable();
            JOptionPane.showMessageDialog(this, "Thêm giảm giá thành công");
            clearForm();
        } catch (Exception ex) {
            Logger.getLogger(JFrameGiamGia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateGiamGia() {
        try {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật không?");
            if (check != JOptionPane.YES_OPTION) {
                return;
            }

            GiamGia giamGia = getDataGiamGia();
            int selectedRow = tblGiamGia.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Chọn một dòng để cập nhật");
                return;
            }

            int id = Integer.parseInt(tblGiamGia.getValueAt(selectedRow, 0).toString()); // Lấy mã nhân viên từ cột đầu tiên
            String index = null;
            giamGiaService.update(giamGia, index);

            loadTable();

            JOptionPane.showMessageDialog(this, "Cập nhật thành công");

            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
        }
    }

    public void deleteGiamGia() throws SQLServerException {
        int selectedRow = tblGiamGia.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một dòng để xóa");
            return;
        }

        int maGG = Integer.parseInt(tblGiamGia.getValueAt(selectedRow, 0).toString()); // Lấy mã giảm giá từ cột đầu tiên

        int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            giamGiaService.delete(maGG);

            loadTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Xóa giảm giá thành công");
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
        btnTieuDe = new javax.swing.JButton();
        btnVoucher = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnGiamGiaSanPham = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        txtNgayKetThuc = new javax.swing.JTextField();
        txtNgayBatDau = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenGG = new javax.swing.JTextField();
        jpnCRUD2 = new javax.swing.JPanel();
        btnThemNhanVien = new javax.swing.JButton();
        btnUpdateNhanVien = new javax.swing.JButton();
        btnXoaNhanVien = new javax.swing.JButton();
        btnClear2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtMucGiam = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGiamGia = new javax.swing.JTable();
        txtMaGG = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnNavigation.setBackground(new java.awt.Color(153, 153, 255));
        jpnNavigation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTieuDe.setBackground(new java.awt.Color(255, 204, 204));
        btnTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnTieuDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconSwing/Shop.png"))); // NOI18N
        btnTieuDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTieuDeActionPerformed(evt);
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

        btnHome.setBackground(new java.awt.Color(255, 255, 153));
        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
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

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 255));
        jButton1.setText("Voucher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnNavigationLayout = new javax.swing.GroupLayout(jpnNavigation);
        jpnNavigation.setLayout(jpnNavigationLayout);
        jpnNavigationLayout.setHorizontalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnNavigationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGiamGiaSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpnNavigationLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnHome)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpnNavigationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnNavigationLayout.setVerticalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addComponent(btnTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGiamGiaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHome)
                .addGap(31, 31, 31))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Quản lý giảm giá");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(25, 25, 25))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jLabel7.setText("Ngày bắt đầu : ");

        jLabel9.setText("Ngày kết thúc :");

        jLabel10.setText("Ghi chú");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Tìm kiếm mã giảm giá :");

        jLabel1.setText("Mã GG :");

        jLabel4.setText("Tên mã giảm :");

        jpnCRUD2.setBackground(new java.awt.Color(255, 204, 204));
        jpnCRUD2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 51))); // NOI18N

        btnThemNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemNhanVien.setForeground(new java.awt.Color(0, 0, 255));
        btnThemNhanVien.setText("Thêm");
        btnThemNhanVien.setBorder(new javax.swing.border.MatteBorder(null));
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        btnUpdateNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdateNhanVien.setForeground(new java.awt.Color(0, 204, 0));
        btnUpdateNhanVien.setText("Cập nhật");
        btnUpdateNhanVien.setBorder(new javax.swing.border.MatteBorder(null));
        btnUpdateNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateNhanVienActionPerformed(evt);
            }
        });

        btnXoaNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaNhanVien.setForeground(new java.awt.Color(255, 0, 0));
        btnXoaNhanVien.setText("Xoá");
        btnXoaNhanVien.setBorder(new javax.swing.border.MatteBorder(null));
        btnXoaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanVienActionPerformed(evt);
            }
        });

        btnClear2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear2.setText("Clear");
        btnClear2.setBorder(new javax.swing.border.MatteBorder(null));
        btnClear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnCRUD2Layout = new javax.swing.GroupLayout(jpnCRUD2);
        jpnCRUD2.setLayout(jpnCRUD2Layout);
        jpnCRUD2Layout.setHorizontalGroup(
            jpnCRUD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCRUD2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jpnCRUD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jpnCRUD2Layout.setVerticalGroup(
            jpnCRUD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCRUD2Layout.createSequentialGroup()
                .addComponent(btnThemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdateNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClear2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jLabel5.setText("Mức giảm :");

        tblGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã GG", "Tên mã giảm", "Mức giảm", "Ngày bắt đầu", "Ngày kết thúc", "Ghi chú"
            }
        ));
        tblGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGiamGia);

        txtMaGG.setEnabled(false);

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnTimKiem)
                .addGap(61, 61, 61))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTenGG, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaGG)))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(jpnCRUD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTenGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jpnCRUD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(51, Short.MAX_VALUE))))
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

    private void btnTieuDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTieuDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTieuDeActionPerformed

    private void btnVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoucherActionPerformed
        setVisible(false);
        try {
            new JFrameGiamGia().setVisible(true);
        } catch (SQLServerException ex) {
            Logger.getLogger(JFrameGiamGia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVoucherActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setVisible(false);
        new JFrameMain().setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnClear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear2ActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClear2ActionPerformed

    private void btnXoaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVienActionPerformed
        try {
            deleteGiamGia();
        } catch (SQLServerException ex) {
            Logger.getLogger(JFrameGiamGia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXoaNhanVienActionPerformed

    private void btnUpdateNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNhanVienActionPerformed
        updateGiamGia();
    }//GEN-LAST:event_btnUpdateNhanVienActionPerformed

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        addGiamGia();
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void tblGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamGiaMouseClicked
        mouseClick();
    }//GEN-LAST:event_tblGiamGiaMouseClicked

    private void btnGiamGiaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiamGiaSanPhamActionPerformed
        setVisible(false);
        try {
            new JFrameGiamGiaSanPham().setVisible(true);
        } catch (SQLServerException ex) {
            Logger.getLogger(JFrameGiamGia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGiamGiaSanPhamActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

        DefaultTableModel model = (DefaultTableModel) tblGiamGia.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tblGiamGia.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
        if (txtTimKiem.getText() == null) {
            try {
                loadTable();
            } catch (SQLServerException ex) {
                Logger.getLogger(JFrameNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new JFrameVoucher().setVisible(true);
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
            java.util.logging.Logger.getLogger(JFrameGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                    new JFrameGiamGia().setVisible(true);
                } catch (SQLServerException ex) {
                    Logger.getLogger(JFrameGiamGia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear2;
    private javax.swing.JButton btnGiamGiaSanPham;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnTieuDe;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnUpdateNhanVien;
    private javax.swing.JButton btnVoucher;
    private javax.swing.JButton btnXoaNhanVien;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpnCRUD2;
    private javax.swing.JPanel jpnNavigation;
    private javax.swing.JTable tblGiamGia;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaGG;
    private javax.swing.JTextField txtMucGiam;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtTenGG;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
