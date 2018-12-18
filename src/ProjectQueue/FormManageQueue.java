/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sunisa Liangtrakool
 */
public class FormManageQueue extends javax.swing.JFrame {

    ServiceQueue service;
    private DefaultTableModel model;

    /**
     * Creates new form FormInQueue
     */
    public FormManageQueue() {
        initComponents();
        Database.Database();
        service = new ServiceQueue();
        showTime();
        showDate();
        showQueue();

    }

    public void checkNumber() {

        List<Drivers> driList = service.findAllDriver();
        Iterator<Drivers> cursor = driList.iterator();
       

        try {
            while (cursor.hasNext()) {
                Drivers driver = cursor.next();
                System.out.println(driver.getDriverNumber());
                if (driver.getDriverNumber().equals(txtNumber.getText())) {
                    txtName.setText(driver.getDriverFirName());
                    
                }
            }

        } finally {

        }
    }

    public void searchPassenger() {

        List<Passenger> passList = service.findAllPassenger();
        Iterator<Passenger> cu = passList.iterator();
        int count = 0;
        try {
            while (cu.hasNext()) {
                Passenger passenger = cu.next();
                System.out.println(passenger.getPassengerFN());
                if (passenger.getPassengerID().equals(txtIdPassenger.getText())) {
                    txtNamePassenger.setText(passenger.getPassengerFN());
                    count ++;
                }
            }if(count==0){
                 alertMessage("ไม่มี ID ลูกค้านี้ในระบบ");
            }

        } finally {

        }
    }
   

    public void searchLocation() {

        List<Location> locationList = service.findAllLocation();
        Iterator<Location> cur = locationList.iterator();
        int count = 0;
        try {
            while (cur.hasNext()) {
                Location location = cur.next();
                System.out.println(location.getNameLocation());
                if (location.getNameLocation().equals(txtLocation.getText())) {
                    txtPrice.setText(String.valueOf(location.getPrice()));
                     count++ ;

                }
            }if(count==0){
                    alertMessage("สถานที่นี้ไม่มีในระบบ");
            }

        } finally {

        }
    }

    public void showQueue() {
        List<Queue> queueList = service.findAllQueue();
        Iterator<Queue> cursor = queueList.iterator();

        String[] column = {"First Time", "Number"};

        DefaultTableModel model = new DefaultTableModel(column, 0);

        try {
            while (cursor.hasNext()) {
                Queue queue = cursor.next();

                String firstTime = queue.getFirstTime();
                String userNumber = queue.getDriverNumber();

                model.addRow(new Object[]{firstTime, userNumber});
            }
        } finally {

        }
        TableQueue.setModel(model);
    }

    public void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        txtDate.setText(s.format(d));

    }

    public void showTime() {
        new javax.swing.Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss ");
                txtTime.setText(s.format(d));
            }

        }
        ).start();
    }

    public void editIncomeAndCountDriver(String driNumber) {
        List<Drivers> userList = service.findOneDriver(driNumber);
        Iterator<Drivers> cursor = userList.iterator();

        try {
            while (cursor.hasNext()) {
                Drivers driver = cursor.next();
                int income = Integer.valueOf(driver.getDriverIncome());
                int count = Integer.valueOf(driver.getDriverCount()) + 1;
                int price = Integer.valueOf(txtPrice.getText());
                String sum = String.valueOf(income + price);
                String sumcount = String.valueOf(count);
                service.updateIncomeAndCountDriver(driNumber, sum, sumcount);
            }

        } finally {

        }
    }

    public void editCountPassenger(String passenID) {
        List<Passenger> passList = service.findOnePassenger(passenID);
        Iterator<Passenger> cursor = passList.iterator();

        try {
            while (cursor.hasNext()) {
                Passenger passenger = cursor.next();
                int count = Integer.valueOf(passenger.getPassengerCount()) + 1;

                String sum = String.valueOf(count);
                service.updateCountPassenger(passenID, sum);
            }

        } finally {

        }
    }

    public void getDriverInfo() {
        DefaultTableModel model = (DefaultTableModel) TableQueue.getModel();
        int rowIndex = TableQueue.getSelectedRow();

        txtNumber.setText(model.getValueAt(rowIndex, 1).toString());
    }

    public void alertMessage(String message) {
        JLabel label = new JLabel(message);
        JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.WARNING_MESSAGE);
    }

    public void clear() {
        txtNumber.setText("");
        txtName.setText("");
        txtIdPassenger.setText("");
        txtNamePassenger.setText("");
        txtLocation.setText("");
        txtPrice.setText("");
    }

    public boolean isFill(String field) {
        return !field.equals("");
    }

    public boolean checkEmpty() {
        return isFill(txtLocation.getText())
                && isFill(txtPrice.getText())
                && isFill(txtIdPassenger.getText())
                && isFill(txtNamePassenger.getText());
    }

    public boolean checkSelectedRow() {
        int rowIndex = TableQueue.getSelectedRow();
        return rowIndex != -1;
    }

    public void outQueue() {
        try {
            if (!checkEmpty()) {
                alertMessage("กรุณากรอกข้อมูลให้ครบ");
            } else if (checkSelectedRow()) {
                int rowIndex = TableQueue.getSelectedRow();
                String driverNumber = TableQueue.getValueAt(rowIndex, 1).toString();
                String passengerID = txtIdPassenger.getText();
                if (service.deleteQueue(driverNumber)) {
                    alertMessage("Number " + txtNumber.getText() + " ออกคิวสำเร็จ");
                    showQueue();
                    editIncomeAndCountDriver(driverNumber);
                    editCountPassenger(passengerID);
                    clear();
                }
            } else {
                alertMessage("กรุณาเลือกคิวที่จะออกส่งลูกค้า");
            }
        } catch (Exception e) {
            alertMessage("เกิดข้อผิดพลาด กรุณาลองใหม่อีกครั้ง");
            System.out.print(e);

        }
    }

    public void EnterQueue() {
        String firstTime = txtTime.getText();
        String driverNumber = txtNumber.getText();

        Queue queue = new Queue(firstTime, driverNumber);

        if (!txtNumber.getText().equals("") && !txtName.getText().equals("")) {
            if (service.addQueue(queue)) {
                alertMessage("เข้าคิวสำเร็จ");
                showQueue();
                clear();
            }
        } else {
            alertMessage("กรุณาใส่ข้อมูลให้ครบถ้วน");

        }
    }

    public void checkDriver() {
        try {
            if (service.findDriverByNumber(txtNumber.getText()) != null) {
                checkNumber();
                System.out.println("เจอ");
            } else {
                 alertMessage("ไม่มีเลขหลังเสื้อนี้ในระบบ");
                txtNumber.setText("");
            }
        } catch (Exception e) {
            alertMessage("ไม่สามารถเชื่อมต่อข้อมูลในฐานได้");
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

        txtDate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableQueue = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtNumber = new javax.swing.JTextField();
        btnSearchNumber = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnEnter = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtIdPassenger = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLocation = new javax.swing.JTextField();
        btnSearchLocation = new javax.swing.JButton();
        txtPrice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnOut = new javax.swing.JButton();
        txtNamePassenger = new javax.swing.JTextField();
        btnSearchID = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnShowLocation = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        menuHome = new javax.swing.JMenu();
        menuDriver = new javax.swing.JMenu();
        menuPassenger = new javax.swing.JMenu();
        menuLocation = new javax.swing.JMenu();
        menuQueue = new javax.swing.JMenu();
        menuSummary = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("TH SarabunPSK", 1, 24)); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(785, 580));

        txtDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Date :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Time :");

        txtTime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TableQueue.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        TableQueue.setModel(new javax.swing.table.DefaultTableModel(
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
        TableQueue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableQueueMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableQueue);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Number : ");

        txtNumber.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumberActionPerformed(evt);
            }
        });

        btnSearchNumber.setBackground(new java.awt.Color(153, 153, 255));
        btnSearchNumber.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSearchNumber.setText("Search");
        btnSearchNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchNumberActionPerformed(evt);
            }
        });

        txtName.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtName.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Name :");

        btnEnter.setBackground(new java.awt.Color(153, 153, 255));
        btnEnter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEnter.setText("Enter");
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("ID Passenger :");

        txtIdPassenger.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtIdPassenger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPassengerActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Location :");

        txtLocation.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnSearchLocation.setBackground(new java.awt.Color(153, 153, 255));
        btnSearchLocation.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSearchLocation.setText("Search");
        btnSearchLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchLocationActionPerformed(evt);
            }
        });

        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtPrice.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Price :");

        btnOut.setBackground(new java.awt.Color(153, 153, 255));
        btnOut.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOut.setText("Out");
        btnOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutActionPerformed(evt);
            }
        });

        txtNamePassenger.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtNamePassenger.setEnabled(false);

        btnSearchID.setBackground(new java.awt.Color(153, 153, 255));
        btnSearchID.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSearchID.setText("Search");
        btnSearchID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchIDActionPerformed(evt);
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

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Name :");

        btnShowLocation.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnShowLocation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/showLocation.png"))); // NOI18N
        btnShowLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowLocationActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(26, 26, 26)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearchNumber))
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(314, 314, 314)
                        .addComponent(btnSearchID))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnOut, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnShowLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtIdPassenger, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearchLocation))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNamePassenger, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTime, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 472, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtIdPassenger, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSearchID))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNamePassenger, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSearchLocation)
                                    .addComponent(btnShowLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSearchNumber))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnOut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(31, 31, 31))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchNumberActionPerformed
        checkDriver();

    }//GEN-LAST:event_btnSearchNumberActionPerformed

    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed
        EnterQueue();

    }//GEN-LAST:event_btnEnterActionPerformed

    private void btnSearchIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchIDActionPerformed
      searchPassenger();
    }//GEN-LAST:event_btnSearchIDActionPerformed

    private void btnSearchLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchLocationActionPerformed
        searchLocation();
    }//GEN-LAST:event_btnSearchLocationActionPerformed

    private void btnOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutActionPerformed
        // TODO add your handling code here:
        outQueue();

    }//GEN-LAST:event_btnOutActionPerformed

    private void txtNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumberActionPerformed

    private void TableQueueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableQueueMouseClicked
        // TODO add your handling code here:
        getDriverInfo();
    }//GEN-LAST:event_TableQueueMouseClicked

    private void txtIdPassengerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPassengerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdPassengerActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

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
        this.show();
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

    private void btnShowLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowLocationActionPerformed
        // TODO add your handling code here:
        new FormShowLocation().show();

    }//GEN-LAST:event_btnShowLocationActionPerformed

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
            java.util.logging.Logger.getLogger(FormManageQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManageQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManageQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManageQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormManageQueue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableQueue;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnEnter;
    private javax.swing.JButton btnOut;
    private javax.swing.JButton btnSearchID;
    private javax.swing.JButton btnSearchLocation;
    private javax.swing.JButton btnSearchNumber;
    private javax.swing.JButton btnShowLocation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuDriver;
    private javax.swing.JMenu menuHome;
    private javax.swing.JMenu menuLocation;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JMenu menuPassenger;
    private javax.swing.JMenu menuQueue;
    private javax.swing.JMenu menuSummary;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtIdPassenger;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNamePassenger;
    private javax.swing.JTextField txtNumber;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
