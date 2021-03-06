/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectQueue;

import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Melpo
 */
public class FormManageLocation extends javax.swing.JFrame {

    ServiceLocation servicelocation;
    private DefaultTableModel model;

    public FormManageLocation() {
        initComponents();
        Database.Database();
        servicelocation = new ServiceLocation();
        showLocation();

        model = (DefaultTableModel) tbLocation.getModel();
    }

    public void getLocationInfo() {
        DefaultTableModel model = (DefaultTableModel) tbLocation.getModel();
        int rowIndex = tbLocation.getSelectedRow();
        txtLocation.setText(model.getValueAt(rowIndex, 0).toString());
        txtPrice.setText(model.getValueAt(rowIndex, 1).toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbLocation = new javax.swing.JTable();
        txtLocation = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnShowAll = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        menuHome = new javax.swing.JMenu();
        menuDriver = new javax.swing.JMenu();
        menuPassenger = new javax.swing.JMenu();
        menuLocation = new javax.swing.JMenu();
        menuQueue = new javax.swing.JMenu();
        menuSummary = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(785, 580));
        setResizable(false);
        setSize(new java.awt.Dimension(785, 580));

        tbLocation.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbLocation.setModel(new javax.swing.table.DefaultTableModel(
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
        tbLocation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLocationMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbLocation);

        txtLocation.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnAdd.setBackground(new java.awt.Color(153, 153, 255));
        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

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

        btnSearch.setBackground(new java.awt.Color(153, 153, 255));
        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Location :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Price :");

        btnShowAll.setBackground(new java.awt.Color(153, 153, 255));
        btnShowAll.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnShowAll.setText("Show All");
        btnShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        menuHome.setText("หน้าแรก");
        menuHome.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHomeMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuHome);

        menuDriver.setText("จัดการผู้ขับขี่");
        menuDriver.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuDriver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDriverMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuDriver);

        menuPassenger.setText("จัดการลูกค้า");
        menuPassenger.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuPassenger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPassengerMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuPassenger);

        menuLocation.setText("จัดการสถานที่");
        menuLocation.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuLocation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLocationMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuLocation);

        menuQueue.setText("จัดการคิว");
        menuQueue.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuQueue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuQueueMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuQueue);

        menuSummary.setText("สรุปรายได้");
        menuSummary.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuSummary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSummaryMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuSummary);

        menuLogout.setText("ออกจากระบบ");
        menuLogout.setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogoutMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuLogout);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(91, 91, 91)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(203, 203, 203)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnShowAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbLocationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLocationMouseClicked

        getLocationInfo();
    }//GEN-LAST:event_tbLocationMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        add();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        edit();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if (!isFill(txtSearch.getText())) {
            alertMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
        } else {
            search(txtSearch.getText());
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllActionPerformed
        showLocation();
        txtSearch.setText("");
    }//GEN-LAST:event_btnShowAllActionPerformed

    private void menuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHomeMouseClicked
        // TODO add your handling code here:
        new FormHome().show();
        this.dispose();
    }//GEN-LAST:event_menuHomeMouseClicked

    private void menuDriverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDriverMouseClicked
        // TODO add your handling code here:
        new FormManageDriver().show();
        this.dispose();
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
        this.show();
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
            java.util.logging.Logger.getLogger(FormManageLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManageLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManageLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManageLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormManageLocation().setVisible(true);
            }
        });
    }

    public void alertMessage(String message) {
        JLabel label = new JLabel(message);
        JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.WARNING_MESSAGE);
    }

    public boolean isFill(String field) {
        return !field.equals("");
    }

    public void showLocation() {
        List<Location> userList = servicelocation.findAllLocation();
        Iterator<Location> cursor = userList.iterator();

        String[] column = {"NameLocation", "Price"};

        DefaultTableModel model = new DefaultTableModel(column, 0);

        try {
            while (cursor.hasNext()) {
                Location lo = cursor.next();
                String nameLocation = lo.getNameLocation();
                int price = lo.getPrice();

                model.addRow(new Object[]{nameLocation, price});
            }
        } finally {

        }
        tbLocation.setModel(model);
    }

    public boolean checkEmpty() {
        return isFill(txtLocation.getText())
                && isFill(txtPrice.getText());

    }

    private void add() {
        if (checkEmpty()) {
            String nameLocation = txtLocation.getText();
            int price = Integer.valueOf(txtPrice.getText());

            Location newLocation = new Location(nameLocation, price);
            if (servicelocation.findLocationByName(nameLocation) != null) {
                alertMessage("ชื่อสถานที่ซ้ำ กรุณาใส่ชื่อสถานที่อื่น");
                clear();
            } else if (servicelocation.addLocation(newLocation)) {
                alertMessage("เพิ่มสถานที่สำเร็จ");
                showLocation();
                clear();
            }
        } else {
            alertMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
            clear();
        }
    }

    private void delete() {
        if (checkSelectedRow()) {
            int rowIndex = tbLocation.getSelectedRow();
            String nameLocation = model.getValueAt(rowIndex, 0).toString();
            if (servicelocation.deleteLocation(nameLocation)) {
                alertMessage("ลบสถานที่สำเร็จ");
                showLocation();
                clear();
            }
        } else {
            alertMessage("กรุณาเลือกสถานที่ที่ต้องการลบในตาราง");
            clear();
        }
    }

    public void clear() {
        txtLocation.setText("");
        txtPrice.setText("");

    }

    public void search(String locationSearch) {
        try {
            Location cursor = servicelocation.findLocationByName(locationSearch);

            String[] column = {"NameLocation", "Price"};

            DefaultTableModel model = new DefaultTableModel(column, 0);
            Location location = cursor;
            String nameLocation = location.getNameLocation();
            int price = location.getPrice();
            model.addRow(new Object[]{nameLocation, price});
            tbLocation.setModel(model);
        } catch (Exception e) {
            alertMessage("ไม่พบสถานที่ที่ต้องการค้นหา");
            txtSearch.setText("");
        }

    }

    private boolean checkSelectedRow() {
        int rowIndex = tbLocation.getSelectedRow();
        return rowIndex != -1;
    }

    public void edit() {
        if (checkEmpty()) {
            String nameLocation = txtLocation.getText();
            int price = Integer.valueOf(txtPrice.getText());

            Location newLocation = new Location(nameLocation, price);
            if (servicelocation.findLocationByName(nameLocation) != null) {
                if (servicelocation.updateLocation(nameLocation, newLocation)) {
                    alertMessage("แก้ไขข้อมูลสถานที่สำเร็จ");
                    showLocation();
                    clear();
                }
            }
        } else {
            alertMessage("กรุณาเลือกสถานที่ที่ต้องการแก้ไขในตาราง");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuDriver;
    private javax.swing.JMenu menuHome;
    private javax.swing.JMenu menuLocation;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JMenu menuPassenger;
    private javax.swing.JMenu menuQueue;
    private javax.swing.JMenu menuSummary;
    private javax.swing.JTable tbLocation;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}
