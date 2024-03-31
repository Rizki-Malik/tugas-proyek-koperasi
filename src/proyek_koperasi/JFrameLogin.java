/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyek_koperasi;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
   //penambahan library
import java.awt.event.KeyEvent;//untuk enter
import javax.swing.table.TableColumn;//merapihkan kolom table
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author rizki
 */
public class JFrameLogin extends javax.swing.JFrame {
Connection con;
Statement stm,stat;
ResultSet rs;
    /**
     * Creates new form JFrameLogin
     */
    public JFrameLogin() {
        initComponents();
        Koneksi();
    }
    
    private void Koneksi(){
         try {
            String url ="jdbc:mysql://localhost/db_koperasi";
            String user="root";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pass);
            stm = con.createStatement();
            System.out.println("koneksi berhasil;");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("koneksi gagal" +e.getMessage());
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TField_username = new javax.swing.JTextField();
        Button_Login = new javax.swing.JButton();
        Button_Keluar = new javax.swing.JButton();
        Check_Password = new javax.swing.JCheckBox();
        TField_password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("LOGIN KOPERASI");

        jLabel2.setText("Username");

        jLabel3.setText("Password");

        TField_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TField_usernameKeyPressed(evt);
            }
        });

        Button_Login.setText("Login");
        Button_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_LoginActionPerformed(evt);
            }
        });

        Button_Keluar.setText("Keluar");
        Button_Keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_KeluarActionPerformed(evt);
            }
        });

        Check_Password.setText("Cek Password");
        Check_Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Check_PasswordActionPerformed(evt);
            }
        });

        TField_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TField_passwordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(28, 28, 28)
                                .addComponent(TField_username))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Check_Password)
                                    .addComponent(TField_password, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(Button_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Button_Keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TField_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TField_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Check_Password)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_Keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_KeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_KeluarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_Button_KeluarActionPerformed

    private void TField_usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TField_usernameKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_ENTER){
            TField_password.requestFocus();
        }
    }//GEN-LAST:event_TField_usernameKeyPressed

    private void Button_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_LoginActionPerformed
        // TODO add your handling code here:
        String user=TField_username.getText();
        String pass=TField_password.getText();

        if (user.isEmpty() && pass.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "User dan Password Tidak Boleh Kosong");
            TField_username.requestFocus();
        }else
        {
            try{

                String sql="select * from user where username='"+ TField_username.getText() +"' && password='"+TField_password.getText() +"'";
                stm=con.createStatement();
                rs=stm.executeQuery(sql);

            if (rs.next())
            {
                JFrame_MenuUtama menu=new JFrame_MenuUtama();
                menu.setLocationRelativeTo(null);
                menu.setVisible(true);
                dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "User atau pass ada yang salah");
                TField_username.requestFocus();
            }
            }catch(SQLException err){
                JOptionPane.showMessageDialog(this, "Koneksi Gagal\n"+err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Button_LoginActionPerformed

    private void Check_PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Check_PasswordActionPerformed
        // TODO add your handling code here:
        if(Check_Password.isSelected()){
           TField_password.setEchoChar((char)0); 
        }else{
            TField_password.setEchoChar('*');
        }
    }//GEN-LAST:event_Check_PasswordActionPerformed

    private void TField_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TField_passwordKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_ENTER){
            Button_LoginActionPerformed(null);
        }
    }//GEN-LAST:event_TField_passwordKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Keluar;
    private javax.swing.JButton Button_Login;
    private javax.swing.JCheckBox Check_Password;
    private javax.swing.JPasswordField TField_password;
    private javax.swing.JTextField TField_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
