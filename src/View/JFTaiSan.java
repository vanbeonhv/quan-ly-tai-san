package View;

import Controller.ClientController;
import Model.Event;
import Model.TaiSan;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marc
 */
public class JFTaiSan extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JFTaiSan.class.getName());
    private TaiSan ts;

    private List<TaiSan> list = new ArrayList();

    public JFTaiSan() {
        initComponents();
        this.jPanel1.setBackground(Color.GRAY);

        loadDataFromServer();
        ViewTable();

    }

    public void ViewTable() {
        DefaultTableModel model = (DefaultTableModel) this.tbTaiSan.getModel();
        model.setNumRows(0);
        for (TaiSan taiSan : list) {
            Object[] rowData = {
                taiSan.getMaTaiSan(),
                taiSan.getTenTaiSan(),
                taiSan.getLoaiTaiSan(),
                taiSan.getViTriPhong(),
                taiSan.getGiaTri()
            };
            model.addRow(rowData);
        }
    }

    public void View(int pos) {
        TaiSan ts = list.get(pos);
        this.txtMaTS.setText(ts.getMaTaiSan());
        this.txtGiaTri.setText(Double.toString(ts.getGiaTri()));
        this.txtLoaiTS.setText(ts.getLoaiTaiSan());
        this.txtTenTS.setText(ts.getTenTaiSan());
        this.txtViTriPhong.setText(ts.getViTriPhong());
    }

    public TaiSan getInput() {
        TaiSan ts = new TaiSan();
        ts.setMaTaiSan(txtMaTS.getText());
        ts.setTenTaiSan(txtTenTS.getText());
        ts.setLoaiTaiSan(txtLoaiTS.getText());
        ts.setViTriPhong(txtViTriPhong.getText());

        try {
            ts.setGiaTri(Double.parseDouble(txtGiaTri.getText()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new NumberFormatException(e.getMessage());
        }
        return ts;
    }

    public void Reset() {
        this.txtMaTS.setText("");
        this.txtGiaTri.setText("");
        this.txtLoaiTS.setText("");
        this.txtTenTS.setText("");
        this.txtViTriPhong.setText("");
    }

    private TaiSan findTaiSanById(String id) {
        for (TaiSan ts : list) {
            if (ts.getMaTaiSan().equals(id)) {
                return ts;
            }
        }
        return null;
    }

    private void sendToServer(Event e, String operation) {
        ClientController clientCtr = new ClientController();
        clientCtr.openConnection();
        clientCtr.sendData(e);
        String result = clientCtr.receiveData();
        if (result.equals("true")) {
            String successMessage = getSuccessMessage(operation);
            Message.ShowSuccessMessage(successMessage);
        } else {
            String errorMessage = getErrorMessage(operation);
            Message.ShowErrorMessage(errorMessage);
        }
        clientCtr.closeConnection();

    }

    private String getSuccessMessage(String operation) {
        switch (operation) {
            case "add":
                return "Thêm tài sản thành công!";
            case "edit":
                return "Cập nhật tài sản thành công!";
            case "delete":
                return "Xóa tài sản thành công!";
            default:
                return "Thao tác thành công!";
        }
    }

    private String getErrorMessage(String operation) {
        switch (operation) {
            case "add":
                return "Thêm tài sản thất bại!";
            case "edit":
                return "Cập nhật tài sản thất bại!";
            case "delete":
                return "Xóa tài sản thất bại!";
            default:
                return "Thao tác thất bại!";
        }
    }

    private void loadDataFromServer() {
        try {
            ClientController clientCtr = new ClientController();
            clientCtr.openConnection();
            Event event = new Event("fetch_all");
            clientCtr.sendData(event);
            list = clientCtr.receiveDataList();
            clientCtr.closeConnection();
            System.out.println("Đã tải " + list.size() + " tài sản từ server");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Không thể kết nối server, sử dụng dữ liệu mẫu");
            loadSampleData();
        }
    }

    private void loadSampleData() {
        list.clear();
        list.add(new TaiSan("1", "tivi", "high", "phong khach", 1000));
        list.add(new TaiSan("2", "Tu lanh", "high", "phong bep", 2500));
        list.add(new TaiSan("3", "May giat", "low", "phong tam", 1500));
        list.add(new TaiSan("4", "May lanh", "high", "phong ngu", 3000));
        list.add(new TaiSan("5", "Lo vi song", "low", "phong bep", 500));
        list.add(new TaiSan("6", "May hut bui", "low", "phong khach", 750));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaTS = new javax.swing.JTextField();
        txtTenTS = new javax.swing.JTextField();
        txtLoaiTS = new javax.swing.JTextField();
        txtViTriPhong = new javax.swing.JTextField();
        txtGiaTri = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTaiSan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý Tài Sản");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Quản lý tài sản");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(313, 313, 313))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Mã tài sản:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Tên tài sản:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Loại tài sản:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Vị trí phòng:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Giá trị:");

        txtMaTS.setEditable(false);
        txtMaTS.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMaTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTSActionPerformed(evt);
            }
        });

        txtTenTS.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenTSActionPerformed(evt);
            }
        });

        txtLoaiTS.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtLoaiTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoaiTSActionPerformed(evt);
            }
        });

        txtViTriPhong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtViTriPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtViTriPhongActionPerformed(evt);
            }
        });

        txtGiaTri.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtGiaTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaTriActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 153, 153));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(153, 204, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaTS))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenTS))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLoaiTS))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtViTriPhong))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(btnThem)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaTri)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnSua)
                                .addGap(27, 27, 27)
                                .addComponent(btnXoa)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnLuu)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtMaTS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtLoaiTS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtViTriPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu)
                    .addComponent(btnClear))
                .addGap(40, 40, 40))
        );

        tbTaiSan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Loại", "Vị trí", "Giá trị"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTaiSan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTaiSanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTaiSan);
        if (tbTaiSan.getColumnModel().getColumnCount() > 0) {
            tbTaiSan.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaTSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTSActionPerformed

    private void txtTenTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenTSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenTSActionPerformed

    private void txtLoaiTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoaiTSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoaiTSActionPerformed

    private void txtViTriPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtViTriPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtViTriPhongActionPerformed

    private void txtGiaTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaTriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTriActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int pos = this.tbTaiSan.getSelectedRow();
        if (pos == -1) {
            Message.ShowErrorMessage("Hãy chọn tài sản muốn sửa trước");
            return;
        }
        View(pos);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int pos = this.tbTaiSan.getSelectedRow();
        if (pos == -1) {
            Message.ShowErrorMessage("Hãy chọn tài sản muốn xóa trước");
            return;
        }
        
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this, 
            "Bạn có chắc chắn muốn xóa tài sản này?", 
            "Xác nhận xóa", 
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            TaiSan selectedTaiSan = list.get(pos);
            Event event = new Event("delete", selectedTaiSan);
            sendToServer(event, "delete");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        
            loadDataFromServer();
            ViewTable();
            Reset();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tbTaiSanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTaiSanMouseClicked
        // TODO add your handling code here:
//        int pos = this.tbTaiSan.getSelectedRow();
//        View(pos);
    }//GEN-LAST:event_tbTaiSanMouseClicked

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        Reset();
        loadDataFromServer();
        ViewTable();
        Message.ShowInfoMessage("Đã làm mới dữ liệu từ server");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        try {
            TaiSan taisan = getInput();
            Event event = null;
            String operation = null;
            if (taisan.getMaTaiSan().isEmpty()) {
                event = new Event("add", taisan);
                operation = "add";
            } else {
                event = new Event("edit", taisan);
                operation = "edit";
            }
            sendToServer(event, operation);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            loadDataFromServer();
            ViewTable();
            Reset();
        } catch (Exception e) {
            Message.ShowErrorMessage("Giá trị nhập phải là số");
        }
    }//GEN-LAST:event_btnLuuActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new JFTaiSan().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbTaiSan;
    private javax.swing.JTextField txtGiaTri;
    private javax.swing.JTextField txtLoaiTS;
    private javax.swing.JTextField txtMaTS;
    private javax.swing.JTextField txtTenTS;
    private javax.swing.JTextField txtViTriPhong;
    // End of variables declaration//GEN-END:variables
}
