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
import javax.swing.table.DefaultTableModel;
import ProjectQueue.Database;
import ProjectQueue.Passenger;
import ProjectQueue.ServiceManagePassenger;

public class FormManagePassenger extends javax.swing.JFrame {

    ServiceManagePassenger service;
    private DefaultTableModel model;

    public FormManagePassenger() {
        initComponents();
        Database.Database();
        service = new ServiceManagePassenger();
        ShowPassenger();
        model = (DefaultTableModel) tablePassenger.getModel();
    }

    public void ShowPassenger() {
        List<Passenger> userList = service.findAllPassenger();
        Iterator<Passenger> cursor = userList.iterator();

        String[] column = {"PassengerID", "FirstName", "LastName", "Tel", "Count"};

        DefaultTableModel model = new DefaultTableModel(column, 0);

        try {
            while (cursor.hasNext()) {
                Passenger pas = cursor.next();
                String PassenID = pas.getPassengerID();
                String FirstName = pas.getPassengerFN();
                String LastName = pas.getPassengerLN();
                String Tel = pas.getPassengerTel();
                String Count = pas.getPassengerCount();

                model.addRow(new Object[]{PassenID, FirstName, LastName, Tel, Count});
            }
        } finally {

        }
        tablePassenger.setModel(model);
    }

    public void alertMessage(String message) {
        JLabel label = new JLabel(message);
        JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.WARNING_MESSAGE);
    }

    public boolean isFill(String field) {
        return !field.equals("");
    }

    public boolean checkEmpty() {
        return isFill(txtFirstname.getText())
                && isFill(txtLastname.getText())
                && isFill(txtId.getText())
                && isFill(txtTel.getText())
                && isFill(txtCount.getText());

    }

    private void add() {
        if (checkEmpty()) {
            String passenID = txtId.getText();
            String FirstName = txtFirstname.getText();
            String LastName = txtLastname.getText();
            String Tel = txtTel.getText();
            String Count = txtCount.getText();

            Passenger pas = new Passenger(passenID, FirstName, LastName, Tel, Count);
            if (service.findPassengerByID(passenID) != null) {
                alertMessage("มี PassengerID อยู่ในระบบแล้ว");
                clear();
            } else if (service.addPassenger(pas)) {
                alertMessage("เพิ่มลูกค้าสำเร็จ");
                ShowPassenger();
                clear();
            }
        } else {
            alertMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
            clear();
        }
    }

    private void delete() {
        if (checkSelectedRow()) {
            int rowIndex = tablePassenger.getSelectedRow();
            String passenID = model.getValueAt(rowIndex, 0).toString();
            if (service.deletePassenger(passenID)) {
                alertMessage("ลบข้อมูลสำเร็จ");
                ShowPassenger();
                clear();
            } else {
                alertMessage("เกิดข้อผิดพลาด กรุณาลองใหม่อีกครั้ง");
                clear();
            }
        } else {
            alertMessage("กรุณาเลือกข้อมูลลูกค้าที่ต้องการลบในตาราง");
            clear();
        }
    }

    public void edit() {
        if (checkEmpty()) {
            String passengerID = txtId.getText();
            String FirstName = txtFirstname.getText();
            String LastName = txtLastname.getText();
            String Tel = txtTel.getText();
            String Count = txtCount.getText();

            Passenger newPassen = new Passenger(passengerID, FirstName, LastName, Tel, Count);
            if (service.findPassengerByID(passengerID) != null) {
                if (service.updatePassengerByID(passengerID, newPassen)) {
                    alertMessage("แก้ไขข้อมูลลูกค้าสำเร็จ");
                    ShowPassenger();
                    clear();
                } else {
                    alertMessage("เกิดข้อผิดพลาด กรุณาลองใหม่อีกครั้ง");
                    clear();
                }
            } else {
                alertMessage("ไม่มี PassengerID ในระบบ");
            }
        } else {
            alertMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
        }
    }

    public void clear() {
        txtId.setText("");
        txtFirstname.setText("");
        txtLastname.setText("");
        txtTel.setText("");
        txtCount.setText("");
    }

    private boolean checkSelectedRow() {
        int rowIndex = tablePassenger.getSelectedRow();
        return rowIndex != -1;
    }

    public void getPassengerInfo() {
        DefaultTableModel model = (DefaultTableModel) tablePassenger.getModel();
        int rowIndex = tablePassenger.getSelectedRow();

        txtId.setText(model.getValueAt(rowIndex, 0).toString());
        txtFirstname.setText(model.getValueAt(rowIndex, 1).toString());
        txtLastname.setText(model.getValueAt(rowIndex, 2).toString());
        txtTel.setText(model.getValueAt(rowIndex, 3).toString());
        txtCount.setText(model.getValueAt(rowIndex, 4).toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePassenger = new javax.swing.JTable();
        labelId = new javax.swing.JLabel();
        labelFirstname = new javax.swing.JLabel();
        labelLastname = new javax.swing.JLabel();
        labelTel = new javax.swing.JLabel();
        labelCount = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtFirstname = new javax.swing.JTextField();
        txtLastname = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();
        txtCount = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        menuHome = new javax.swing.JMenu();
        menuDriver = new javax.swing.JMenu();
        menuPassenger = new javax.swing.JMenu();
        menuLocation = new javax.swing.JMenu();
        menuQueue = new javax.swing.JMenu();
        menuSummary = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenu();

        jButton4.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(785, 580));

        tablePassenger.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tablePassenger.setModel(new javax.swing.table.DefaultTableModel(
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
        tablePassenger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePassengerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePassenger);

        labelId.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelId.setText("PassengerID :");

        labelFirstname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelFirstname.setText("First Name :");

        labelLastname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelLastname.setText("Last Name :");

        labelTel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelTel.setText("Tel :");

        labelCount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelCount.setText("Count :");

        txtId.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtFirstname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtLastname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtTel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtCount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtCount.setEnabled(false);

        btnClear.setBackground(new java.awt.Color(153, 153, 255));
        btnClear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
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

        btnEdit.setBackground(new java.awt.Color(153, 153, 255));
        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnInsert.setBackground(new java.awt.Color(153, 153, 255));
        btnInsert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInsert.setText("Add");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelId)
                            .addComponent(labelFirstname, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelLastname, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(txtFirstname)
                            .addComponent(txtLastname))
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelCount, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCount)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelId)
                    .addComponent(labelTel)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFirstname)
                    .addComponent(labelCount)
                    .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLastname)
                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
        // TODO add your handling code here:

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        edit();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        add();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tablePassengerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePassengerMouseClicked
        getPassengerInfo();
    }//GEN-LAST:event_tablePassengerMouseClicked

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
        this.show();
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
            java.util.logging.Logger.getLogger(FormManagePassenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManagePassenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManagePassenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManagePassenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormManagePassenger().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCount;
    private javax.swing.JLabel labelFirstname;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelLastname;
    private javax.swing.JLabel labelTel;
    private javax.swing.JMenu menuDriver;
    private javax.swing.JMenu menuHome;
    private javax.swing.JMenu menuLocation;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JMenu menuPassenger;
    private javax.swing.JMenu menuQueue;
    private javax.swing.JMenu menuSummary;
    private javax.swing.JTable tablePassenger;
    private javax.swing.JTextField txtCount;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
