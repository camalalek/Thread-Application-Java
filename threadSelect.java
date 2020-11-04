package ChatWebServer;

//Importing all used modules
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class threadSelect extends javax.swing.JFrame {
    //Declaring login object class with array
    public static loginElements lE;
    public static ArrayList<loginElements> userArray = new ArrayList<loginElements>();
    public static final String PATH = "users.txt";
    public String user;

    public threadSelect() {
        initComponents();
    }

    //Setting username and JTable on JFrame start
    threadSelect(String username) {
        initComponents();
        try {
            loadTable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        user = username;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        createNewBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setTitle("Westminster Threads");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Westminster Threads");

        createNewBtn.setText("Create New");
        createNewBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateDisplay(evt);
            }
        });
        createNewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewBtnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thread Name", "Created By", "Creation Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateDisplay(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editThreadBtn(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(createNewBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createNewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createNewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewBtnActionPerformed
        //opeing Dialog box for new thread name | Setting a time stamp
        String threadName = JOptionPane.showInputDialog(null, "Enter Thread Name", "Create New Thread", JOptionPane.QUESTION_MESSAGE);
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        //Getting current table model for row addition
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{threadName, user, timeStamp});
        jTable1.setModel(model);
        try {
            //Saving table data (Thread)
            saveTable();
        } catch (Exception ex) {
            Logger.getLogger(threadSelect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_createNewBtnActionPerformed

    private void editThreadBtn(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editThreadBtn
        //Setting varible from row selected
        String name = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        //Passing user and thread name when creating new jframe instance
        threadEdit tE = new threadEdit(name, user);
        tE.setVisible(true);
    }//GEN-LAST:event_editThreadBtn

    private void updateDisplay(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateDisplay
        try {
            loadTable();
        } catch (Exception ex) {
            Logger.getLogger(threadSelect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateDisplay

   //Loading userdata file for login form
    private static void loadFile(String path) throws IOException {
        //Setting file path
        RandomAccessFile raf = new RandomAccessFile(path, "r");
        int i = 0;
        String line = raf.readLine();
        while (line != null) {
            //Splitting the data file line and inserting into object for array insertion
            String[] data = line.split(" ");
            loginElements lE = new loginElements();
            lE.setLoginid(data[0]);
            lE.setNickname(data[1]);
            lE.setPassword(data[2]);
            userArray.add(lE);
            line = raf.readLine();
            i++;
        }
        System.out.println("[*] File Loaded");
    }

    //Saving userdata file for login form
    private static void saveFile(String path) throws IOException {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(path));
            for (int i = 0; i < userArray.size(); i++) {
                //Printing line onto text file with all object variables appeneded
                out.println(userArray.get(i).getLoginid() + " " + userArray.get(i).getNickname() + " " + userArray.get(i).getPassword());
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("[*] File Saved");
    }

    //Getters and Setters for user array list when Signin on
    public static ArrayList<loginElements> getArray() {
        return userArray;
    }

    public static void setArray(ArrayList<loginElements> tmpArray) throws IOException {
        userArray = tmpArray;
        saveFile(PATH);
    }

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
            java.util.logging.Logger.getLogger(threadSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(threadSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(threadSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(threadSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    loadFile(PATH);
                } catch (IOException ex) {
                    Logger.getLogger(threadSelect.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Loading the login page first
                new loginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createNewBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    //Grabbing table data for csv file
    public void saveTable() throws Exception {
        BufferedWriter bfw = new BufferedWriter(new FileWriter("threads.csv"));
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            for (int j = 0; j < jTable1.getColumnCount(); j++) {
                bfw.write((String) (jTable1.getValueAt(i, j)));
                bfw.write(",");
            }
            bfw.newLine();
        }
        bfw.close();
    }

    //loading csbv file for table model
    public void loadTable() throws Exception {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        String col1;
        String col2;
        String col3;
        Scanner sc = new Scanner(new File("threads.csv"));
        while (sc.hasNextLine()) {
            sc.useDelimiter(",");
            col1 = sc.next();
            col2 = sc.next();
            col3 = sc.next();
            model.addRow(new Object[]{col1, col2, col3});
            sc.nextLine();
        }
        jTable1.setModel(model);

    }

}
