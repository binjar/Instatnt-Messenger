/**
 * v1.2
 * @author arif
 */

import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.*;

public class ClientGUI extends javax.swing.JFrame {

    private boolean connected;
    private Client client;
    private int port;
    private String defaultHost;
    private SimpleDateFormat sdf;
    private DefaultListModel onln_model;
 
    /**
     * Creates new form ClientGUI
     */
    public ClientGUI(String host, int defPort) {
        initComponents();
        
        port = defPort;
        defaultHost = host;
        
        tfServer.setText(host);
        tfPort.setText("" + port);
        u_name.requestFocus();
        sdf = new SimpleDateFormat("HH:mm:ss");
    }
    
    
    void append(String str) {
        ta.append(str + "\n");
    }
    
    void showOnln(String[] str){
        onln_model = new DefaultListModel();
        onln_model.removeAllElements();
        for (String str1 : str) {
            onln_model.addElement(str1); 
        }
        online.setModel(onln_model);
    }
    
    void clearList(){
        onln_model = new DefaultListModel();
        onln_model.removeAllElements();
        online.setModel(onln_model);
    }
    
    void connectionFailed() {
        //tfServer.setText(defaultHost);
        tfServer.setEnabled(true);
        
        //tfPort.setText("" + port);
        tfPort.setEnabled(true);
        
        login_logout.setText("Log in");
        
        u_name.setText("");
        u_name.setEditable(true);
        
        onlineBtn.setEnabled(false);
        
        ta.setText("");
        this.clearList();
        
        to.setText("");
        to.setEnabled(false);
        
        msg.setText("");
        msg.setEnabled(false);
        msg_send_btn.setEnabled(false);
        connected = false;
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
        tfServer = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfPort = new javax.swing.JTextField();
        login_logout = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        u_name = new javax.swing.JTextField();
        onlineBtn = new javax.swing.JButton();
        msg_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        to = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        msg = new javax.swing.JTextArea();
        msg_send_btn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        online = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client App");
        setPreferredSize(new java.awt.Dimension(878, 596));
        setResizable(false);

        jLabel1.setText("Server Address : ");

        jLabel2.setText("Port Number : ");

        login_logout.setText("Log in");
        login_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        login_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_logoutActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Username: ");

        u_name.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        onlineBtn.setText("Online");
        onlineBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        onlineBtn.setEnabled(false);
        onlineBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onlineBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfServer, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(u_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(onlineBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(login_logout))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfServer)
                    .addComponent(jLabel2)
                    .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(u_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(onlineBtn)
                    .addComponent(login_logout))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        msg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("To: ");
        msg_panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, -1, -1));

        to.setEnabled(false);
        msg_panel.add(to, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 11, 200, -1));

        jLabel5.setText("Message: ");
        msg_panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 14, -1, -1));

        msg.setColumns(20);
        msg.setLineWrap(true);
        msg.setRows(5);
        msg.setEnabled(false);
        jScrollPane2.setViewportView(msg);

        msg_panel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 11, 460, 40));

        msg_send_btn.setText("send");
        msg_send_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        msg_send_btn.setEnabled(false);
        msg_send_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_send_btnActionPerformed(evt);
            }
        });
        msg_panel.add(msg_send_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, -1, -1));

        ta.setEditable(false);
        ta.setColumns(20);
        ta.setLineWrap(true);
        ta.setRows(5);
        ta.setFocusable(false);
        jScrollPane1.setViewportView(ta);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        online.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        online.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onlineMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(online);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(msg_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msg_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void login_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_logoutActionPerformed
        if(connected){
            client.sendMessage(new ChatMessage(ChatMessage.LOGOUT));
            client.disconnect();
            return;
        }
                        
        String username = u_name.getText().trim();
            
        if(username.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter an username.");
            return;
        }

        String server = tfServer.getText().trim();
        if(server.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter server address.");
            return;
        }

        String portNumber = tfPort.getText().trim();
        if(portNumber.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter port number.");
            return;
        }

        int port = 0;
        try {
            port = Integer.parseInt(portNumber);
        }
        catch(NumberFormatException e) {
            append(e.getMessage());
            return;
        }

        // creating a new Client
        client = new Client(server, port, username, this);

        if(!client.start()) 
            return;
        
        tfServer.setEnabled(false);
        tfPort.setEnabled(false);
        login_logout.setText("Log out");
        u_name.setEditable(false);
        onlineBtn.setEnabled(true);
        to.setEnabled(true);
        msg.setEnabled(true);
        msg_send_btn.setEnabled(true);
        connected = true;
    }//GEN-LAST:event_login_logoutActionPerformed

    private void onlineBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onlineBtnActionPerformed
        client.sendMessage(new ChatMessage(ChatMessage.ONLINE, null));
    }//GEN-LAST:event_onlineBtnActionPerformed

    private void onlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onlineMouseClicked
        // TODO add your handling code here:
        // double click?
        if (evt.getClickCount() == 2) {
            int pos = online.locationToIndex(evt.getPoint());
            online.setSelectedIndex(pos);
            
            String selectedUser = (String) online.getSelectedValue();
            String getAllRecvr = to.getText();

            if(!"".equals(getAllRecvr)){
                if(!getAllRecvr.contains(selectedUser));
                    to.setText(getAllRecvr + ";" + selectedUser);
            } else {
                to.setText(selectedUser);
            }
        } else if (evt.getClickCount() == 1) {
            // single click?
            online.clearSelection() ;
        }
    }//GEN-LAST:event_onlineMouseClicked

    private void msg_send_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_send_btnActionPerformed
        // TODO add your handling code here:
        if(!msg.getText().isEmpty()) {
            client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, msg.getText(), to.getText()));
            msg.setText("");
        }
    }//GEN-LAST:event_msg_send_btnActionPerformed

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
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI("localhost", 3434).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton login_logout;
    private javax.swing.JTextArea msg;
    private javax.swing.JPanel msg_panel;
    private javax.swing.JButton msg_send_btn;
    private javax.swing.JList online;
    private javax.swing.JButton onlineBtn;
    private javax.swing.JTextArea ta;
    private javax.swing.JTextField tfPort;
    private javax.swing.JTextField tfServer;
    private javax.swing.JTextField to;
    private javax.swing.JTextField u_name;
    // End of variables declaration//GEN-END:variables
}