/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyek_koperasi;

/**
 *
 * @author rizki
 */
public class JFrame_MenuUtama extends javax.swing.JFrame {

    /**
     * Creates new form JFrame_MenuUtama
     */
    public JFrame_MenuUtama() {
        initComponents();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        MAnggotaKoperasi = new javax.swing.JMenuItem();
        MSimpananAnggota = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        MUser = new javax.swing.JMenuItem();
        MRole = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\koperasi.jpg")); // NOI18N

        jMenu1.setText("File");

        jMenuItem3.setText("Keluar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Data Master");

        jMenu3.setText("Koperasi");

        MAnggotaKoperasi.setText("Anggota Koperasi");
        MAnggotaKoperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MAnggotaKoperasiActionPerformed(evt);
            }
        });
        jMenu3.add(MAnggotaKoperasi);

        MSimpananAnggota.setText("Simpanan Anggota");
        jMenu3.add(MSimpananAnggota);

        jMenuItem1.setText("Angsuran Pinjaman Anggota");
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Pinjaman Anggota");
        jMenu3.add(jMenuItem2);

        jMenuItem4.setText("Transaksi Keuangan Koperasi");
        jMenu3.add(jMenuItem4);

        jMenu2.add(jMenu3);

        jMenu4.setText("User");

        MUser.setText("Maintenance User");
        MUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MUserActionPerformed(evt);
            }
        });
        MUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MUserKeyPressed(evt);
            }
        });
        jMenu4.add(MUser);

        MRole.setText("Role User");
        MRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRoleActionPerformed(evt);
            }
        });
        jMenu4.add(MRole);

        jMenu2.add(jMenu4);

        jMenuItem5.setText("Laporan");
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        dispose();
        new JFrameLogin().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void MRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRoleActionPerformed
        // TODO add your handling code here:
        dispose();
        new JFrameRole().setVisible(true);
    }//GEN-LAST:event_MRoleActionPerformed

    private void MUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MUserKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MUserKeyPressed

    private void MUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MUserActionPerformed
        // TODO add your handling code here:
        dispose();
        new JFrameUser().setVisible(true);
    }//GEN-LAST:event_MUserActionPerformed

    private void MAnggotaKoperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MAnggotaKoperasiActionPerformed
        // TODO add your handling code here:
        dispose();
        new JFrameAnggota().setVisible(true);
    }//GEN-LAST:event_MAnggotaKoperasiActionPerformed

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
            java.util.logging.Logger.getLogger(JFrame_MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MAnggotaKoperasi;
    private javax.swing.JMenuItem MRole;
    private javax.swing.JMenuItem MSimpananAnggota;
    private javax.swing.JMenuItem MUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables
}
