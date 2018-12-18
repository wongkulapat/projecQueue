package ProjectQueue;

import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ProjectQueue.Database;
import ProjectQueue.Drivers;
import ProjectQueue.ServiceManageDriver;

public class FormManageDriver extends javax.swing.JFrame {

    ServiceManageDriver service;
    private DefaultTableModel model;

    public FormManageDriver() {
        initComponents();
        Database.Database();
        service = new ServiceManageDriver();
        showDriver();
        model = (DefaultTableModel) tableDriver.getModel();

    }

    public void showDriver() {
        List<Drivers> driverList = service.findAllDriver();
        Iterator<Drivers> cursor = driverList.iterator();

        String[] column = {"Number", "FirstName", "LastName", "LicensePlate", "License", "Tel", "Count", "Income"};

        DefaultTableModel model = new DefaultTableModel(column, 0);

        try {
            while (cursor.hasNext()) {
                Drivers driver = cursor.next();

                String FirstName = driver.getDriverFirName();
                String LastName = driver.getDriverLastName();
                String Number = driver.getDriverNumber();
                String LicensePlate = driver.getDriverLicPlat();
                String License = driver.getDriverLic();
                String Tel = driver.getDriverTel();
                String Count = driver.getDriverCount();
                String Income = driver.getDriverIncome();

                model.addRow(new Object[]{Number, FirstName, LastName, LicensePlate, License, Tel, Count, Income});
            }
        } finally {

        }
        tableDriver.setModel(model);
    }

    public void alertMessage(String message) {
        JLabel label = new JLabel(message);
        JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.WARNING_MESSAGE);
    }

    public boolean isFill(String field) {
        return !field.equals("");
    }

    public boolean checkEmpty() {
        return isFill(txtFirstName.getText())
                && isFill(txtLastName.getText())
                && isFill(txtNumber.getText())
                && isFill(txtLicPlate.getText())
                && isFill(txtLicense.getText())
                && isFill(txtTel.getText())
                && isFill(txtCount.getText())
                && isFill(txtIncome.getText());

    }

    private void add() {
        if (checkEmpty()) {
            String Number = txtNumber.getText();
            String FirstName = txtFirstName.getText();
            String LastName = txtLastName.getText();
            String LicPlate = txtLicPlate.getText();
            String Lic = txtLicense.getText();
            String Tel = txtTel.getText();
            String Count = txtCount.getText();
            String Income = txtIncome.getText();

            Drivers driver = new Drivers(Number, FirstName, LastName, LicPlate, Lic, Tel, Count, Income);
            if (service.findDriverByNumber(Number) != null) {
                alertMessage("มีเลขหลังเสื้อนี้อยู่ในระบบแล้ว");
                txtNumber.setText("");
                clear();
            } else if (service.addDriver(driver)) {
                alertMessage("เพิ่มผู้ขับขี่สำเร็จ");
                showDriver();
                clear();
            }
        } else {
            alertMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
            clear();
        }
    }

    private void delete() {
        if (checkSelectedRow()) {
            int rowIndex = tableDriver.getSelectedRow();
            String driNumber = model.getValueAt(rowIndex, 0).toString();
            if (service.deleteDriver(driNumber)) {
                alertMessage("ลบผู้ขับขี่สำเร็จ");
                showDriver();
                clear();
            } else {
                alertMessage("เกิดข้อผิดพลาด กรุณาลองใหม่อีกครั้ง");
                clear();
            }
        } else {
            alertMessage("กรุณาเลือกข้อมูลผู้ขับขี่ที่ต้องการลบในตาราง");
            clear();
        }
    }

    public void clear() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtNumber.setText("");
        txtLicPlate.setText("");
        txtLicense.setText("");
        txtTel.setText("");
        txtCount.setText("");
        txtIncome.setText("");

    }

    private boolean checkSelectedRow() {
        int rowIndex = tableDriver.getSelectedRow();
        return rowIndex != -1;
    }

    public void edit() {
        if (checkEmpty()) {
            String Number = txtNumber.getText();
            String FirstName = txtFirstName.getText();
            String LastName = txtLastName.getText();
            String LicPlate = txtLicPlate.getText();
            String Lic = txtLicense.getText();
            String Tel = txtTel.getText();
            String Count = txtCount.getText();
            String Income = txtIncome.getText();

            Drivers newDriver = new Drivers(Number, FirstName, LastName, LicPlate, Lic, Tel, Count, Income);
            if (service.findDriverByNumber(Number) != null) {
                if (service.updateDriver(Number, newDriver)) {
                    alertMessage("แก้ไขข้อมูลผู้ขับขี่สำเร็จ");
                    showDriver();
                    clear();
                } else {
                    alertMessage("เกิดข้อผิดพลาด กรุณาลองใหม่อีกครั้ง");
                    clear();
                }
            } else {
                alertMessage("ไม่มีเลขหลังเสื้อนี้ในระบบ");
            }
        } else {
            alertMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
        }
    }

    public void getDriverInfo() {

        DefaultTableModel model = (DefaultTableModel) tableDriver.getModel();
        int rowIndex = tableDriver.getSelectedRow();

        txtNumber.setText(model.getValueAt(rowIndex, 0).toString());
        txtFirstName.setText(model.getValueAt(rowIndex, 1).toString());
        txtLastName.setText(model.getValueAt(rowIndex, 2).toString());
        txtLicPlate.setText(model.getValueAt(rowIndex, 3).toString());
        txtLicense.setText(model.getValueAt(rowIndex, 4).toString());
        txtTel.setText(model.getValueAt(rowIndex, 5).toString());
        txtCount.setText(model.getValueAt(rowIndex, 6).toString());
        txtIncome.setText(model.getValueAt(rowIndex, 7).toString());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInsert = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDriver = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        labelLastname = new javax.swing.JLabel();
        labelLicensePlate = new javax.swing.JLabel();
        labelNumber = new javax.swing.JLabel();
        labelFirstname = new javax.swing.JLabel();
        labelLicense = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtNumber = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtLicPlate = new javax.swing.JTextField();
        txtLicense = new javax.swing.JTextField();
        labelTel = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        labelCount = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCount = new javax.swing.JTextField();
        txtIncome = new javax.swing.JTextField();
        menuBar = new javax.swing.JMenuBar();
        menuHome = new javax.swing.JMenu();
        menuDriver = new javax.swing.JMenu();
        menuPassenger = new javax.swing.JMenu();
        menuLocation = new javax.swing.JMenu();
        menuQueue = new javax.swing.JMenu();
        menuSummary = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(785, 580));

        btnInsert.setBackground(new java.awt.Color(153, 153, 255));
        btnInsert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInsert.setText("Add");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        tableDriver.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableDriver.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableDriver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDriverMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDriver);

        btnEdit.setBackground(new java.awt.Color(153, 153, 255));
        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(153, 153, 255));
        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(153, 153, 255));
        btnClear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        labelLastname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelLastname.setText("Last Name :");

        labelLicensePlate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelLicensePlate.setText("License Plate :");

        labelNumber.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelNumber.setText("Number :");

        labelFirstname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelFirstname.setText("First Name :");

        labelLicense.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelLicense.setText("License :");

        txtFirstName.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtNumber.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtLastName.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtLicPlate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtLicense.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        labelTel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelTel.setText("Tel :");

        txtTel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        labelCount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelCount.setText("Count :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("Income :");

        txtCount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtIncome.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        menuHome.setText("หน้าแรก");
        menuHome.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHomeMouseClicked(evt);
            }
        });
        menuBar.add(menuHome);

        menuDriver.setText("จัดการผู้ขับขี่");
        menuDriver.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuDriver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDriverMouseClicked(evt);
            }
        });
        menuBar.add(menuDriver);

        menuPassenger.setText("จัดการลูกค้า");
        menuPassenger.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuPassenger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPassengerMouseClicked(evt);
            }
        });
        menuBar.add(menuPassenger);

        menuLocation.setText("จัดการสถานที่");
        menuLocation.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuLocation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLocationMouseClicked(evt);
            }
        });
        menuBar.add(menuLocation);

        menuQueue.setText("จัดการคิว");
        menuQueue.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuQueue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuQueueMouseClicked(evt);
            }
        });
        menuBar.add(menuQueue);

        menuSummary.setText("สรุปรายได้");
        menuSummary.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuSummary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSummaryMouseClicked(evt);
            }
        });
        menuBar.add(menuSummary);

        menuLogout.setText("ออกจากระบบ");
        menuLogout.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogoutMouseClicked(evt);
            }
        });
        menuBar.add(menuLogout);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelNumber)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelLastname)
                                    .addComponent(labelFirstname)
                                    .addComponent(labelLicensePlate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLicPlate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelLicense)
                            .addComponent(labelTel)
                            .addComponent(jLabel11)
                            .addComponent(labelCount))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLicense)
                            .addComponent(txtTel)
                            .addComponent(txtCount)
                            .addComponent(txtIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(115, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLicense, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLicense))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTel)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCount)
                            .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIncome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNumber))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelFirstname))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelLastname)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLicPlate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLicensePlate))))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        add();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        edit();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tableDriverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDriverMouseClicked
        getDriverInfo();


    }//GEN-LAST:event_tableDriverMouseClicked

    private void menuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHomeMouseClicked
        // TODO add your handling code here:
        new FormHome().show();
        this.dispose();
    }//GEN-LAST:event_menuHomeMouseClicked

    private void menuDriverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDriverMouseClicked
        // TODO add your handling code here:
        this.show();
    }//GEN-LAST:event_menuDriverMouseClicked

    private void menuPassengerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPassengerMouseClicked
        // TODO add your handling code here:
        new FormManagePassenger().show();
        this.dispose();
    }//GEN-LAST:event_menuPassengerMouseClicked

    private void menuQueueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuQueueMouseClicked
        // TODO add your handling code here:
        new FormManageQueue().show();
        this.dispose();
    }//GEN-LAST:event_menuQueueMouseClicked

    private void menuSummaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSummaryMouseClicked
        // TODO add your handling code here:
        new FormSummary().show();
        this.dispose();
    }//GEN-LAST:event_menuSummaryMouseClicked

    private void menuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_menuLogoutMouseClicked

    private void menuLocationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLocationMouseClicked
        // TODO add your handling code here:
        new FormManageLocation().show();
        this.dispose();
    }//GEN-LAST:event_menuLocationMouseClicked

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
            java.util.logging.Logger.getLogger(FormManageDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManageDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManageDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManageDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormManageDriver().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnInsert;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCount;
    private javax.swing.JLabel labelFirstname;
    private javax.swing.JLabel labelLastname;
    private javax.swing.JLabel labelLicense;
    private javax.swing.JLabel labelLicensePlate;
    private javax.swing.JLabel labelNumber;
    private javax.swing.JLabel labelTel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuDriver;
    private javax.swing.JMenu menuHome;
    private javax.swing.JMenu menuLocation;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JMenu menuPassenger;
    private javax.swing.JMenu menuQueue;
    private javax.swing.JMenu menuSummary;
    private javax.swing.JTable tableDriver;
    private javax.swing.JTextField txtCount;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtIncome;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtLicPlate;
    private javax.swing.JTextField txtLicense;
    private javax.swing.JTextField txtNumber;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
