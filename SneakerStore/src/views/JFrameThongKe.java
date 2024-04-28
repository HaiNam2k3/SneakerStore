/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.raven.chart.ModelChart;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.chiTietSanPham;
import model.hoaDon;
import service.ThongKeHoaDonService;

/**
 *
 * @author trung
 */
public class JFrameThongKe extends javax.swing.JFrame {

    /**
     * Creates new form JFrameThongKe
     */
    private final ThongKeHoaDonService thongKeHoaDonService;

    public JFrameThongKe() throws SQLServerException, SQLException {
        this.thongKeHoaDonService = new ThongKeHoaDonService();
        initComponents();

//        fillDSHDCho();
//        fillTableDanhSachSP();
        spDanhMuc();

        // fillSizeSP();
        //   fillGioHang();
//        rdoDaThanhToan.setSelected(true);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(250, 250, 250));

        // Thống kê sản phẩm đã bán
        chart.addLegend("Sản phẩm đã bán", new Color(11,226,255));
        chart.addLegend("Giày đi bộ", new Color(4,251,58));
        chart.addLegend("Giày chạy", new Color(245,250,6));

        // Số lượng sản phẩm đã bán
        chart.addData(new ModelChart("Sản phẩm đã bán", new double[]{thongKeHoaDonService.tongSanPhamDaBan(), 0, 0}));

        // Số lượng sản phẩm thuộc danh mục 1
        chart.addData(new ModelChart("Giày đi bộ", new double[]{0, thongKeHoaDonService.spDanhMuc1(), 0}));

        // Số lượng sản phẩm thuộc danh mục 2
        chart.addData(new ModelChart("Giày chạy", new double[]{0, 0, thongKeHoaDonService.spDanhMuc2()}));
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        getContentPane().setBackground(new Color(250, 250, 250));

        // Thống kê sản phẩm trong cửa hàng
        chart1.addLegend("SP trong cửa hàng", new Color(11,226,255));
        chart1.addLegend("Đang kinh doanh", new Color(245,250,6));
        chart1.addLegend("Ngừng kinh doanh", new Color(92,146,253));

        
        chart1.addData(new ModelChart("SP trong cửa hàng", new double[]{thongKeHoaDonService.tongSP(), 0, 0}));

        
        chart1.addData(new ModelChart("Đang kinh doanh", new double[]{0, thongKeHoaDonService.dangKinhDoanh(), 0}));

       
        chart1.addData(new ModelChart("Ngừng kinh doanh", new double[]{0, 0, thongKeHoaDonService.ngungKinhDoanh()}));

        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        getContentPane().setBackground(new Color(250, 250, 250));

        // Thống kê hóa đơn
        chart2.addLegend("Tổng số hóa đơn", new Color(245,147,137));
        chart2.addLegend("Đã thanh toán", new Color(11,226,255));
        chart2.addLegend("Hóa đơn chờ", new Color(4,251,58));

     
        chart2.addData(new ModelChart("Tổng số hóa đơn", new double[]{thongKeHoaDonService.tongHoaDon(), 0, 0}));

     
        chart2.addData(new ModelChart("Đã thanh toán", new double[]{0, thongKeHoaDonService.daThanhToan(), 0}));

  
        chart2.addData(new ModelChart("Hóa đơn chờ", new double[]{0, 0, thongKeHoaDonService.hoaDonCho()}));



    }

    void spDanhMuc() throws SQLException {
        this.lbDtAll1.setText(String.valueOf(thongKeHoaDonService.sumTotal()));
        this.lblSpdaban.setText(String.valueOf(thongKeHoaDonService.tongSanPhamDaBan()));
        this.lblDanhMuc1.setText(String.valueOf(thongKeHoaDonService.spDanhMuc1()));
        this.lblDanhMuc2.setText(String.valueOf(thongKeHoaDonService.spDanhMuc2()));
        
        this.lblTongHoaDon3.setText(String.valueOf(thongKeHoaDonService.tongHoaDon()));
        this.lblDaThanhToan.setText(String.valueOf(thongKeHoaDonService.daThanhToan()));
        this.lblHoaDonCho.setText(String.valueOf(thongKeHoaDonService.hoaDonCho()));
        this.lbDtAll.setText(String.valueOf(thongKeHoaDonService.sumTotal()));
        this.lblSpdaban1.setText(String.valueOf(thongKeHoaDonService.tongSanPhamDaBan()));
        
        this.lblTongSP.setText(String.valueOf(thongKeHoaDonService.tongSP()));
        this.lblDangKinhDoanh.setText(String.valueOf(thongKeHoaDonService.dangKinhDoanh()));
        this.lblNgungKinhDoanh.setText(String.valueOf(thongKeHoaDonService.ngungKinhDoanh()));

    }

//    void fillDSHDCho() throws SQLException {
//        System.out.println("abc");
//        int trangThai = 1;
//
//        if (rdoChuaThanhToan.isSelected()) {
//            trangThai = 0;
//
//        }
//        if (rdoDaThanhToan.isSelected()) {
//            trangThai = 1;
//
//        }
//        if (rdoTongHoaDon.isSelected()) {
//            trangThai = -1;
//        }
//
//        ArrayList<hoaDon> listHD = thongKeHoaDonService.getHoaDonCho(trangThai);
//
//        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
//        model.setRowCount(0);
//
//        for (hoaDon hd : listHD) {
//
//            Object[] data = {
//                hd.getMaHD(), hd.getMaNV(), hd.getMaTTKH(), hd.getTenNV(), hd.getNgayTao(), hd.getTrangThai() ? "Đã thanh toán" : "Chưa thanh toán", hd.getTongTien()
//            };
//            model.addRow(data);
//        }
//
//        this.lblTongHoaDon3.setText(String.valueOf(thongKeHoaDonService.tongHoaDon()));
//        this.lblDaThanhToan.setText(String.valueOf(thongKeHoaDonService.daThanhToan()));
//        this.lblHoaDonCho.setText(String.valueOf(thongKeHoaDonService.hoaDonCho()));
//        this.lbDtAll.setText(String.valueOf(thongKeHoaDonService.sumTotal()));
//        this.lblSpdaban1.setText(String.valueOf(thongKeHoaDonService.tongSanPhamDaBan()));
//        
//                this.lblTongSP.setText(String.valueOf(thongKeHoaDonService.tongSP()));
//        this.lblDangKinhDoanh.setText(String.valueOf(thongKeHoaDonService.dangKinhDoanh()));
//        this.lblNgungKinhDoanh.setText(String.valueOf(thongKeHoaDonService.ngungKinhDoanh()));
//
//    }

//    private void showDetail() {
//        int selectedRow = tblHoaDon.getSelectedRow();
//        if (selectedRow == -1) {
//            JOptionPane.showMessageDialog(this, "Chọn một dòng để xem chi tiết");
//            return;
//        }
//
//        int maHD = Integer.parseInt(tblHoaDon.getValueAt(selectedRow, 0).toString());
//        int maNV = Integer.parseInt(tblHoaDon.getValueAt(selectedRow, 1).toString());
//        int maTTKH = Integer.parseInt(tblHoaDon.getValueAt(selectedRow, 2).toString());
//        String tenNV = tblHoaDon.getValueAt(selectedRow, 3).toString();
//        String ngayTao = tblHoaDon.getValueAt(selectedRow, 4).toString();
//        String trangThai = tblHoaDon.getValueAt(selectedRow, 5).toString();
//        float tongTien = Float.parseFloat(tblHoaDon.getValueAt(selectedRow, 6).toString());
//
//        String message = "Mã hóa đơn: " + maHD + "\n"
//                + "Mã nhân viên: " + maNV + "\n"
//                + "Mã TTKH: " + maTTKH + "\n"
//                + "Tên nhân viên: " + tenNV + "\n"
//                + "Ngày tạo: " + ngayTao + "\n"
//                + "Trạng thái: " + trangThai + "\n"
//                + "Tổng tiền: " + tongTien + "\n";
//
//        JOptionPane.showMessageDialog(this, message, "Chi tiết hóa đơn", JOptionPane.PLAIN_MESSAGE);
//    }

//    public void search() throws SQLServerException, SQLException {
//        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
//        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
//        tblHoaDon.setRowSorter(obj);
//        obj.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
//        if (txtTimKiem.getText() == null) {
//            fillDSHDCho();
//        }
//    }

//    public void search1() throws SQLServerException, SQLException {
//        DefaultTableModel model = (DefaultTableModel) tblDanhSachSP.getModel();
//        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
//        tblDanhSachSP.setRowSorter(obj);
//        obj.setRowFilter(RowFilter.regexFilter(txtTimKiem1.getText()));
//        if (txtTimKiem1.getText() == null) {
//            fillTableDanhSachSP();
//        }
//    }

//    void fillTableDanhSachSP() throws SQLException {
//        List<chiTietSanPham> listCTSP = thongKeHoaDonService.listAll();
//
//        DefaultTableModel model = (DefaultTableModel) tblDanhSachSP.getModel();
//        model.setRowCount(0);
//        for (chiTietSanPham CTSP : listCTSP) {
//            Object[] data = {
//                CTSP.getMaCTSP(), CTSP.getTenSP(), CTSP.getTenSize(), CTSP.getTenMS(), CTSP.getTenCL(),
//                CTSP.getTenCLDe(), CTSP.getSoLuong(), CTSP.getDonGia(), CTSP.getMucGG()
//            };
//            model.addRow(data);
//        }
//        this.lblTongSP.setText(String.valueOf(thongKeHoaDonService.tongSP()));
//        this.lblDangKinhDoanh.setText(String.valueOf(thongKeHoaDonService.dangKinhDoanh()));
//        this.lblNgungKinhDoanh.setText(String.valueOf(thongKeHoaDonService.ngungKinhDoanh()));
//    }

//    void showDetailSanPham() {
//        int selectedRow = tblDanhSachSP.getSelectedRow();
//        if (selectedRow == -1) {
//            JOptionPane.showMessageDialog(this, "Chọn một dòng để xem chi tiết");
//            return;
//        }
//
//        int maCTSP = Integer.parseInt(tblDanhSachSP.getValueAt(selectedRow, 0).toString());
//        String tenSP = tblDanhSachSP.getValueAt(selectedRow, 1).toString();
//        String tenSize = tblDanhSachSP.getValueAt(selectedRow, 2).toString();
//        String tenMS = tblDanhSachSP.getValueAt(selectedRow, 3).toString();
//        String tenCL = tblDanhSachSP.getValueAt(selectedRow, 4).toString();
//        String tenCLDe = tblDanhSachSP.getValueAt(selectedRow, 5).toString();
//        int soLuong = Integer.parseInt(tblDanhSachSP.getValueAt(selectedRow, 6).toString());
//        float donGia = Float.parseFloat(tblDanhSachSP.getValueAt(selectedRow, 7).toString());
//        float mucGiamGia = Float.parseFloat(tblDanhSachSP.getValueAt(selectedRow, 8).toString());
//
//        String message = "Mã sản phẩm: " + maCTSP + "\n"
//                + "Tên sản phẩm: " + tenSP + "\n"
//                + "Kích thước: " + tenSize + "\n"
//                + "Màu sắc: " + tenMS + "\n"
//                + "Chất liệu: " + tenCL + "\n"
//                + "Chất liệu đế: " + tenCLDe + "\n"
//                + "Số lượng: " + soLuong + "\n"
//                + "Đơn giá: " + donGia + "\n"
//                + "Mức giảm giá: " + mucGiamGia + "\n";
//
//        JOptionPane.showMessageDialog(this, message, "Chi tiết sản phẩm", JOptionPane.PLAIN_MESSAGE);
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jpnNavigation = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        a3 = new javax.swing.JLabel();
        lblTongHoaDon3 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        h = new javax.swing.JLabel();
        lblDaThanhToan = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        s = new javax.swing.JLabel();
        lblHoaDonCho = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        lbDtAll = new javax.swing.JLabel();
        lblTongSpDaBan1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        lblSpdaban1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        chart2 = new com.raven.chart.Chart();
        jPanel8 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        lblTongSpDaBan = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        lblSpdaban = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        hh = new javax.swing.JLabel();
        lblDanhMuc1 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        lblDanhMuc2 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        lblDoanhThuSPDaban = new javax.swing.JLabel();
        lbDtAll1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        chart = new com.raven.chart.Chart();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblTongSP = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblDangKinhDoanh = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        lblNgungKinhDoanh = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        chart1 = new com.raven.chart.Chart();

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

        btnThongKe.setBackground(new java.awt.Color(204, 204, 255));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(0, 0, 255));
        btnThongKe.setText("Thống kê");
        btnThongKe.setBorder(new javax.swing.border.MatteBorder(null));
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
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
                .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnHome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnNavigationLayout.setVerticalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHome)
                .addGap(51, 51, 51))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Quản lý thống kê");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(342, 342, 342)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(24, 24, 24))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jTabbedPane4.setBackground(new java.awt.Color(222, 231, 227));
        jTabbedPane4.setPreferredSize(new java.awt.Dimension(928, 520));

        jPanel7.setBackground(new java.awt.Color(222, 231, 227));

        jPanel23.setBackground(new java.awt.Color(222, 231, 227));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Thống kê"));
        jPanel23.setLayout(new java.awt.GridLayout(1, 0));

        jPanel28.setBackground(new java.awt.Color(245, 147, 137));
        jPanel28.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel28.setForeground(new java.awt.Color(250, 12, 12));

        a3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        a3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a3.setText("Tổng số  hóa đơn");

        lblTongHoaDon3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongHoaDon3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongHoaDon3.setText("0");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(a3, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblTongHoaDon3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(lblTongHoaDon3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(a3)
                .addContainerGap())
        );

        jPanel23.add(jPanel28);

        jPanel30.setBackground(new java.awt.Color(11, 226, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        h.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        h.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h.setText("Đã thanh toán");

        lblDaThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblDaThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDaThanhToan.setText("0");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(h, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblDaThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(h)
                .addContainerGap())
            .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel30Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(lblDaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE)))
        );

        jPanel23.add(jPanel30);

        jPanel31.setBackground(new java.awt.Color(4, 251, 58));
        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        s.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        s.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        s.setText("Đang chờ");

        lblHoaDonCho.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblHoaDonCho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoaDonCho.setText("0");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(s, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblHoaDonCho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(s)
                .addContainerGap())
            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel31Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(lblHoaDonCho, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE)))
        );

        jPanel23.add(jPanel31);

        jPanel19.setBackground(new java.awt.Color(92, 146, 253));
        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Tổng doanh thu ");

        lbDtAll.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbDtAll.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDtAll.setText("0");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lbDtAll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(lbDtAll, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addContainerGap())
        );

        jPanel23.add(jPanel19);

        lblTongSpDaBan1.setBackground(new java.awt.Color(11, 226, 255));
        lblTongSpDaBan1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Tổng số lượng SP đã bán");

        lblSpdaban1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSpdaban1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSpdaban1.setText("0");

        javax.swing.GroupLayout lblTongSpDaBan1Layout = new javax.swing.GroupLayout(lblTongSpDaBan1);
        lblTongSpDaBan1.setLayout(lblTongSpDaBan1Layout);
        lblTongSpDaBan1Layout.setHorizontalGroup(
            lblTongSpDaBan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblTongSpDaBan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblSpdaban1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lblTongSpDaBan1Layout.setVerticalGroup(
            lblTongSpDaBan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblTongSpDaBan1Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(lblSpdaban1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addContainerGap())
        );

        jPanel23.add(lblTongSpDaBan1);

        jPanel10.setBackground(new java.awt.Color(222, 231, 227));

        jPanel15.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart2, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1022, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Thống kê hoá đơn", jPanel7);

        jPanel8.setBackground(new java.awt.Color(222, 231, 227));

        jPanel14.setBackground(new java.awt.Color(222, 231, 227));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Thống kê"));
        jPanel14.setLayout(new java.awt.GridLayout(1, 4));

        lblTongSpDaBan.setBackground(new java.awt.Color(11, 226, 255));
        lblTongSpDaBan.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Tổng số lượng SP đã bán");

        lblSpdaban.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSpdaban.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSpdaban.setText("0");

        javax.swing.GroupLayout lblTongSpDaBanLayout = new javax.swing.GroupLayout(lblTongSpDaBan);
        lblTongSpDaBan.setLayout(lblTongSpDaBanLayout);
        lblTongSpDaBanLayout.setHorizontalGroup(
            lblTongSpDaBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblTongSpDaBanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblSpdaban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lblTongSpDaBanLayout.setVerticalGroup(
            lblTongSpDaBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblTongSpDaBanLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(lblSpdaban, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addContainerGap())
        );

        jPanel14.add(lblTongSpDaBan);

        jPanel16.setBackground(new java.awt.Color(4, 251, 58));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        hh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        hh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hh.setText("Giày đi bộ");

        lblDanhMuc1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblDanhMuc1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDanhMuc1.setText("0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hh, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblDanhMuc1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(lblDanhMuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hh)
                .addContainerGap())
        );

        jPanel14.add(jPanel16);

        jPanel17.setBackground(new java.awt.Color(245, 250, 6));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Giày chạy");

        lblDanhMuc2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblDanhMuc2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDanhMuc2.setText("0");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblDanhMuc2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(lblDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addContainerGap())
        );

        jPanel14.add(jPanel17);

        jPanel18.setBackground(new java.awt.Color(92, 146, 253));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDoanhThuSPDaban.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDoanhThuSPDaban.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhThuSPDaban.setText("Tổng doanh thu ");

        lbDtAll1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbDtAll1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDtAll1.setText("0");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoanhThuSPDaban, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lbDtAll1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(lbDtAll1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDoanhThuSPDaban)
                .addContainerGap())
        );

        jPanel14.add(jPanel18);

        jPanel11.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Thống kê doanh thu", jPanel8);

        jPanel5.setBackground(new java.awt.Color(222, 231, 227));

        jPanel2.setBackground(new java.awt.Color(222, 231, 227));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thống kê"));
        jPanel2.setLayout(new java.awt.GridLayout(1, 4));

        jPanel4.setBackground(new java.awt.Color(11, 226, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Sản phẩm trong cửa hàng");

        lblTongSP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongSP.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblTongSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(lblTongSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        jPanel2.add(jPanel4);

        jPanel9.setBackground(new java.awt.Color(245, 250, 6));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Đang Kinh Doanh");

        lblDangKinhDoanh.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblDangKinhDoanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDangKinhDoanh.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblDangKinhDoanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(lblDangKinhDoanh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap())
        );

        jPanel2.add(jPanel9);

        jPanel13.setBackground(new java.awt.Color(92, 146, 253));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Ngừng kinh doanh");

        lblNgungKinhDoanh.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNgungKinhDoanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgungKinhDoanh.setText("0");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblNgungKinhDoanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(lblNgungKinhDoanh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        jPanel2.add(jPanel13);

        jPanel12.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        jTabbedPane4.addTab("Thống kê sản phẩm", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
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

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        setVisible(false);
        try {
            new JFrameThongKe().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(JFrameThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setVisible(false);
        new JFrameMain().setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFrameThongKe().setVisible(true);
                } catch (SQLServerException ex) {
                    Logger.getLogger(JFrameThongKe.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(JFrameThongKe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a3;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnThongKe;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private com.raven.chart.Chart chart;
    private com.raven.chart.Chart chart1;
    private com.raven.chart.Chart chart2;
    private javax.swing.JLabel h;
    private javax.swing.JLabel hh;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JPanel jpnNavigation;
    private javax.swing.JLabel lbDtAll;
    private javax.swing.JLabel lbDtAll1;
    private javax.swing.JLabel lblDaThanhToan;
    private javax.swing.JLabel lblDangKinhDoanh;
    private javax.swing.JLabel lblDanhMuc1;
    private javax.swing.JLabel lblDanhMuc2;
    private javax.swing.JLabel lblDoanhThuSPDaban;
    private javax.swing.JLabel lblHoaDonCho;
    private javax.swing.JLabel lblNgungKinhDoanh;
    private javax.swing.JLabel lblSpdaban;
    private javax.swing.JLabel lblSpdaban1;
    private javax.swing.JLabel lblTongHoaDon3;
    private javax.swing.JLabel lblTongSP;
    private javax.swing.JPanel lblTongSpDaBan;
    private javax.swing.JPanel lblTongSpDaBan1;
    private javax.swing.JLabel s;
    // End of variables declaration//GEN-END:variables
}
