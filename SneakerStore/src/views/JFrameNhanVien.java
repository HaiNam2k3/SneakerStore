/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.NhanVien;
import repository.Auth;
import repository.MsgBox;
import repository.NhanVienRepository;
import service.NhanVienService;

/**
 *
 * @author trung
 */
public class JFrameNhanVien extends javax.swing.JFrame {

    private DefaultTableModel dtm = new DefaultTableModel();

    private NhanVienService nhanVienService;

    private NhanVienRepository nhanVienRepository;

    public JFrameNhanVien() throws SQLServerException {
        initComponents();
        setLocationRelativeTo(null);
        loadTable();
        this.nhanVienService = new NhanVienService();
        this.nhanVienRepository = new NhanVienRepository();
        setLocationRelativeTo(null);
    }

    public void loadTable() throws SQLServerException {
        NhanVienRepository nhanVienRepository = new NhanVienRepository();

        dtm = (DefaultTableModel) tblNhanVien.getModel();
        dtm.setRowCount(0);

        for (NhanVien nhanVien : nhanVienRepository.getAllNhanViens()) {

            Object[] rowData = new Object[]{
                nhanVien.getMaNv(),
                nhanVien.getTenDangNhap(),
                nhanVien.getMatKhau(),
                nhanVien.getHoTen(),
                nhanVien.getGioiTinh() == 1 ? "Nam" : "Nữ",
                nhanVien.getDienThoai(),
                nhanVien.getEmail(),
                nhanVien.getNgayTao(),
                nhanVien.getChucVu() == 1 ? "Nhân Viên" : "Admin",
                nhanVien.getTrangThai() == 1 ? "Nghỉ làm" : "Đi làm"
            };
            dtm.addRow(rowData);
        }
    }

    public void mouseClick() {
        NhanVien nv = new NhanVien();
        int row = tblNhanVien.getSelectedRow();
        if (row < 0) {
            return;
        }
        txtMaNV.setText(tblNhanVien.getValueAt(row, 0).toString());
        txtTenDangNhap.setText(tblNhanVien.getValueAt(row, 1).toString());
        txtMatKhau.setText(tblNhanVien.getValueAt(row, 2).toString());
        txtHoTen.setText(tblNhanVien.getValueAt(row, 3).toString());
        if (tblNhanVien.getValueAt(row, 4).toString() == "Nam") {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtSDT.setText(tblNhanVien.getValueAt(row, 5).toString());
        txtEmail.setText(tblNhanVien.getValueAt(row, 6).toString());
        txtNgayTao.setText(tblNhanVien.getValueAt(row, 7).toString());
        cboChucVu.setSelectedItem(tblNhanVien.getValueAt(row, 8).toString());
        cboTrangThaiNV.setSelectedItem(tblNhanVien.getValueAt(row, 9).toString());
    }
    
    void ShowPass(){
        int row = tblNhanVien.getSelectedRow();
        if (row < 0) {
            return;
        }
        
        
    }
    

    public void clearForm() {
        txtMaNV.setText("");
        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
        txtHoTen.setText("");
        rdoNam.setSelected(true);
        txtSDT.setText("");
        txtEmail.setText("");
        txtNgayTao.setText("");
        cboChucVu.setSelectedIndex(0);
        cboTrangThaiNV.setSelectedIndex(0);

    }

    public NhanVien getDataNhanVien() throws ParseException {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNv(Integer.parseInt(txtMaNV.getText().trim()));
        String tenDangNhap = txtTenDangNhap.getText().trim();
        String matKhau = txtMatKhau.getText().trim();
        String hoTen = txtHoTen.getText().trim();
        String sdt = txtSDT.getText().trim();
        String email = txtEmail.getText().trim();
        String ngayTao = txtNgayTao.getText().trim();

        if (tenDangNhap.isEmpty() || matKhau.isEmpty() || hoTen.isEmpty() || sdt.isEmpty() || email.isEmpty() || ngayTao.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String usernameRegex = "^[a-zA-Z0-9]+$";

        if (!tenDangNhap.matches(usernameRegex)) {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập chỉ được chứa chữ và số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String passwordRegex = "^[a-zA-Z0-9]+$";
        if (!matKhau.matches(passwordRegex)) {
            JOptionPane.showMessageDialog(null, "Mật khẩu chỉ được chứa chữ và số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

//         String nameRegex = "^[a-zA-Z\\s]+$";  
//        if (!hoTen.matches(nameRegex)) {
//        JOptionPane.showMessageDialog(null, "Họ và tên không được chứa ký tự đặc biệt hoặc số", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        return null;
//    }
        String nameRegex = "^[\\p{L}\\s'`?,.]+$";
        if (!hoTen.matches(nameRegex)) {
            JOptionPane.showMessageDialog(null, "Họ và tên không được chứa ký tự đặc biệt hoặc số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String phoneRegex = "^\\d{10}$";
        if (!sdt.matches(phoneRegex)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        SimpleDateFormat formart = new SimpleDateFormat("yyyy-MM-dd");
        nhanVien.setTenDangNhap(tenDangNhap);
        nhanVien.setMatKhau(matKhau);
        nhanVien.setHoTen(hoTen);
        nhanVien.setGioiTinh(rdoNam.isSelected() ? 1 : 0);
        nhanVien.setDienThoai(sdt);
        nhanVien.setEmail(email);
        nhanVien.setNgayTao(formart.parse(ngayTao));

        // Lấy giá trị chức vụ từ ComboBox
        int chucVu = cboChucVu.getSelectedIndex();
        nhanVien.setChucVu(chucVu);

        int trangThai = cboTrangThaiNV.getSelectedIndex();
        nhanVien.setTrangThai(trangThai);

        return nhanVien;
    }

    public void addNhanVien() {

        try {

            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không");
            if (check != JOptionPane.YES_OPTION) {
                return;
            }
            NhanVien nhanVien = getDataNhanVien();
            nhanVienService.insert(nhanVien);

            loadTable();

            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }

    }

    public void updateNhanVien() {
        try {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật không?");
            if (check != JOptionPane.YES_OPTION) {
                return;
            }

            NhanVien nhanVien = getDataNhanVien();
            int selectedRow = tblNhanVien.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Chọn một dòng để cập nhật");
                return;
            }

            int id = Integer.parseInt(tblNhanVien.getValueAt(selectedRow, 0).toString()); // Lấy mã nhân viên từ cột đầu tiên
            String index = null;
            nhanVienService.update(nhanVien, index);

            loadTable();

            JOptionPane.showMessageDialog(this, "Cập nhật thành công");

            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
        }
    }

    public void updateTrangThaiNhanVien() throws SQLServerException {
        int banGhiChon = this.tblNhanVien.getSelectedRow();
        if (banGhiChon == -1) {
            JOptionPane.showMessageDialog(this, "Chọn 1 dòng để Cập nhật trạng thái");
            return;
        }

        int maNV = Integer.parseInt(txtMaNV.getText());

        int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật trạng thái không?", "Xác nhận cập nhật", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            int trangThaiMoi = 1;

            nhanVienRepository.updateTrangThaiNhanVien(maNV, trangThaiMoi);

            loadTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Cập nhật trạng thái nhân viên thành công");
        }
    }

    public void deleteNhanVien() throws SQLServerException {
        int banGhiChon = this.tblNhanVien.getSelectedRow();
        if (banGhiChon == -1) {
            JOptionPane.showMessageDialog(this, "Chọn 1 dòng để Xóa");
            return;
        }

        int maNV = Integer.parseInt(txtMaNV.getText()); // Chuyển đổi thành số nguyên

        int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            nhanVienService.delete(maNV);

            loadTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jpnNavigation = new javax.swing.JPanel();
        btnTieuDe = new javax.swing.JButton();
        btnQuanLyNhanVien = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cboTrangThaiNV = new javax.swing.JComboBox<>();
        txtNgayTao = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jpnCRUD2 = new javax.swing.JPanel();
        btnThemNhanVien = new javax.swing.JButton();
        btnUpdateNhanVien = new javax.swing.JButton();
        btnXoaNhanVien = new javax.swing.JButton();
        btnClearForm = new javax.swing.JButton();
        btnUpdateNhanVien1 = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        cboChucVu = new javax.swing.JComboBox<>();

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

        btnQuanLyNhanVien.setBackground(new java.awt.Color(204, 204, 255));
        btnQuanLyNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuanLyNhanVien.setForeground(new java.awt.Color(0, 0, 255));
        btnQuanLyNhanVien.setText("Quản lý nhân viên");
        btnQuanLyNhanVien.setBorder(new javax.swing.border.MatteBorder(null));
        btnQuanLyNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyNhanVienActionPerformed(evt);
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
            .addComponent(btnTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnNavigationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnQuanLyNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnNavigationLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnHome)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnNavigationLayout.setVerticalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addComponent(btnTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuanLyNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHome)
                .addGap(31, 31, 31))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Quản lý nhân viên");

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

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên đăng nhập", "Mật khẩu", "Họ Tên", "Giới tính", "Điện thoại", "Email", "Ngày tạo", "Chức vụ", "Trạng thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jLabel1.setText("Mã nhân viên : ");

        txtMaNV.setEnabled(false);

        jLabel4.setText("Tên đăng nhập :");

        jLabel5.setText("Mật khẩu :");

        jLabel6.setText("Họ tên : ");

        jLabel8.setText("Giới tính : ");

        rdoNam.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel7.setText("Số điện thoại : ");

        jLabel9.setText("Email : ");

        jLabel10.setText("Ngày tạo : ");

        jLabel11.setText("Trạng thái : ");

        cboTrangThaiNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đi làm", "Nghỉ làm" }));

        jLabel12.setText("Chức vụ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Tìm kiếm nhân viên :");

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

        btnClearForm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClearForm.setText("Clear");
        btnClearForm.setBorder(new javax.swing.border.MatteBorder(null));
        btnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFormActionPerformed(evt);
            }
        });

        btnUpdateNhanVien1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdateNhanVien1.setForeground(new java.awt.Color(0, 204, 0));
        btnUpdateNhanVien1.setText("Cập nhật TT");
        btnUpdateNhanVien1.setBorder(new javax.swing.border.MatteBorder(null));
        btnUpdateNhanVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateNhanVien1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnCRUD2Layout = new javax.swing.GroupLayout(jpnCRUD2);
        jpnCRUD2.setLayout(jpnCRUD2Layout);
        jpnCRUD2Layout.setHorizontalGroup(
            jpnCRUD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCRUD2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jpnCRUD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdateNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Nhân Viên" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnTimKiem)
                .addGap(61, 61, 61))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(41, 41, 41)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(111, 111, 111)
                                            .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTrangThaiNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jpnCRUD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
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
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)
                            .addComponent(jLabel11)
                            .addComponent(cboTrangThaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(66, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jpnCRUD2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
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
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jpnNavigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTieuDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTieuDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTieuDeActionPerformed

    private void btnQuanLyNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyNhanVienActionPerformed
        setVisible(false);
        new JFrameMain().setVisible(true);
    }//GEN-LAST:event_btnQuanLyNhanVienActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setVisible(false);
        new JFrameMain().setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        int us= Auth.isManager();
        if(us == 0){
             addNhanVien();
        }else{
            MsgBox.alert(this, "Bạn không phải admin");
        }
       
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void btnUpdateNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNhanVienActionPerformed
        int us= Auth.isManager();
        if(us == 0){
             updateNhanVien();
        }else{
            MsgBox.alert(this, "Bạn không phải admin");
        }
    }//GEN-LAST:event_btnUpdateNhanVienActionPerformed

    private void btnXoaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVienActionPerformed
        try {
            deleteNhanVien();
        } catch (SQLServerException ex) {
            Logger.getLogger(JFrameNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXoaNhanVienActionPerformed

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearFormActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        mouseClick();
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tblNhanVien.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
        if (txtSearch.getText() == null) {
            try {
                loadTable();
            } catch (SQLServerException ex) {
                Logger.getLogger(JFrameNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnUpdateNhanVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNhanVien1ActionPerformed
        try {
            updateTrangThaiNhanVien();
        } catch (SQLServerException ex) {
            Logger.getLogger(JFrameNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateNhanVien1ActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                    new JFrameNhanVien().setVisible(true);
                } catch (SQLServerException ex) {
                    Logger.getLogger(JFrameNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnQuanLyNhanVien;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnTieuDe;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnUpdateNhanVien;
    private javax.swing.JButton btnUpdateNhanVien1;
    private javax.swing.JButton btnXoaNhanVien;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboTrangThaiNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpnCRUD2;
    private javax.swing.JPanel jpnNavigation;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables



    private String encryptPassword(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(new String(pass).getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
