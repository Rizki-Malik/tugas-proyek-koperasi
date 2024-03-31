/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyek_koperasi;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rizki
 */
public class JFrameAnggota extends javax.swing.JFrame {
Connection con;
Statement stm,stat;
PreparedStatement ps;
ResultSet rs;
    /**
     * Creates new form JFrameAnggota
     */
    public JFrameAnggota() {
        initComponents();
        Koneksi();
        dataToTable();
        CBJK();
        CBJob();
        CBStatus();
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
    
    private void CBJK() {
        String[] daftarJK = {"Laki-laki", "Perempuan"};

        for (String jk : daftarJK) {
            CBJK.addItem(jk);
        }
    }
    
    private void CBJob(){
        String[] daftarPekerjaan = {"PNS", "Karyawan", "Mahasiswi", "Pengusaha", "Pegawai Swasta", "Wirausaha", "Mahasiswa", "Pelajar", "Ibu Rumah Tangga", "Pensiunan"};

        for (String pekerjaan : daftarPekerjaan) {
            CBPekerjaan.addItem(pekerjaan);
        }
    }
    
    private void CBStatus(){
        String[] pilihStatus = {"Aktif", "Tidak Aktif"};

        for (String stats : pilihStatus) {
            CBStatus.addItem(stats);
        }
    }
    
    private void dataToTable() {
        DefaultTableModel model = (DefaultTableModel) TableAnggota.getModel();
        TableAnggota.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        model.setColumnIdentifiers(new Object[]{"NIK", "Nama Anggota", "Alamat", "Nomor HP", "Email", "Tanggal Lahir", "Jenis Kelamin", "Pekerjaan", "Status Anggota"});
        String query = "SELECT * FROM anggota"; 

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nik"),
                    rs.getString("nama_anggota"),
                    rs.getString("alamat"),
                    rs.getString("no_telp"),
                    rs.getString("email"),
                    rs.getDate("tanggal_lahir"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("pekerjaan"),
                    rs.getString("status_anggota"),
                });
            }
            TableAnggota.getColumnModel().getColumn(1).setPreferredWidth(150);
            TableAnggota.getColumnModel().getColumn(2).setPreferredWidth(200);
            TableAnggota.getColumnModel().getColumn(3).setPreferredWidth(100);
            TableAnggota.getColumnModel().getColumn(4).setPreferredWidth(150);
            TableAnggota.getColumnModel().getColumn(5).setPreferredWidth(100);
            TableAnggota.getColumnModel().getColumn(6).setPreferredWidth(100);
            TableAnggota.getColumnModel().getColumn(7).setPreferredWidth(100);
        } catch (SQLException e) {
            System.err.println("Gagal menampilkan data: " + e.getMessage());
        }
    }

    private void CekDataNIK() {
        String nik = TFieldNIK.getText().trim();

        if (nik.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NIK tidak boleh kosong!");
            return;
        }

        String sqlcek = "SELECT * FROM anggota WHERE nik = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sqlcek)) {
            pstmt.setString(1, nik);
            ResultSet rscek = pstmt.executeQuery();

            if (rscek.next()) {
                TFieldNama.setText(rscek.getString("nama_anggota"));
                TAreaAlamat.setText(rscek.getString("alamat"));
                TFieldNomor.setText(rscek.getString("no_telp"));
                TFieldEmail.setText(rscek.getString("email"));
                TFieldTLahir.setText(rscek.getString("tanggal_lahir"));
                CBJK.setSelectedItem(rscek.getString("jenis_kelamin"));
                CBPekerjaan.setSelectedItem(rscek.getString("pekerjaan"));
                CBStatus.setSelectedItem(rscek.getString("status_anggota"));
                TFieldNama.requestFocus();
            } else {
                TFieldNama.setText("");
                TAreaAlamat.setText("");
                TFieldNomor.setText("");
                TFieldEmail.setText("");
                TFieldTLahir.setText("");
                TFieldNIK.requestFocus();
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
            }
        } catch (SQLException e) {
            System.err.println("Error saat cek data: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Gagal cek data. Silakan hubungi administrator!");
        }
    }
    
    private void simpanDataAnggota(String nikAnggota, String namaAnggota, String alamatAnggota, String nomorTelepon, String emailAnggota, Date tanggalLahir, String jenisKelamin, String pekerjaanAnggota, String statusAnggota) throws SQLException {
        String sqlInsert = "INSERT INTO anggota (nik, nama_anggota, alamat, no_telp, email, tanggal_lahir, jenis_kelamin, pekerjaan, status_anggota) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmtInsert = con.prepareStatement(sqlInsert)) {
            pstmtInsert.setString(1, nikAnggota);
            pstmtInsert.setString(2, namaAnggota);
            pstmtInsert.setString(3, alamatAnggota);
            pstmtInsert.setString(4, nomorTelepon);
            pstmtInsert.setString(5, emailAnggota);
            pstmtInsert.setDate(6, tanggalLahir);
            pstmtInsert.setString(7, jenisKelamin);
            pstmtInsert.setString(8, pekerjaanAnggota);
            pstmtInsert.setString(9, statusAnggota);
            pstmtInsert.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        }
    }

    private void ubahDataAnggota(String nikAnggota, String namaAnggota, String alamatAnggota, String nomorTelepon, String emailAnggota, Date tanggalLahir, String jenisKelamin, String pekerjaanAnggota, String statusAnggota) throws SQLException {
        String sqlUpdate = "UPDATE anggota SET nama_anggota = ?, alamat = ?, no_telp = ?, email = ?, tanggal_lahir = ?, jenis_kelamin = ?, pekerjaan = ?, status_anggota = ? WHERE nik = ?";
        try (PreparedStatement pstmtUpdate = con.prepareStatement(sqlUpdate)) {
            pstmtUpdate.setString(1, namaAnggota);
            pstmtUpdate.setString(2, alamatAnggota);
            pstmtUpdate.setString(3, nomorTelepon);
            pstmtUpdate.setString(4, emailAnggota);
            pstmtUpdate.setDate(5, tanggalLahir);
            pstmtUpdate.setString(6, jenisKelamin);
            pstmtUpdate.setString(7, pekerjaanAnggota);
            pstmtUpdate.setString(8, statusAnggota);
            pstmtUpdate.setString(9, nikAnggota);
            pstmtUpdate.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
        }
    }

    private boolean cekNikDatabase(String nikAnggota) throws SQLException {
        String sql = "SELECT nik FROM anggota WHERE nik = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nikAnggota);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    private boolean validasiNIK(String nik) {
        if (!nik.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "NIK harus terdiri dari angka!");
            return false;
        }

        // Cek panjang NIK
        if (nik.length() > 16) {
            JOptionPane.showMessageDialog(null, "NIK tidak boleh lebih dari 16 karakter!");
            return false;
        }

        return true;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        TFieldNIK = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TFieldNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TAreaAlamat = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        TFieldNomor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TFieldEmail = new javax.swing.JTextField();
        CBJK = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        CBPekerjaan = new javax.swing.JComboBox<>();
        CBStatus = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        TFieldTLahir = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableAnggota = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("DAFTAR ANGGOTA KOPERASI");

        TFieldNIK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFieldNIKKeyPressed(evt);
            }
        });

        jLabel2.setText("NIK               :");

        jLabel3.setText("Nama Lengkap   :");

        TAreaAlamat.setColumns(20);
        TAreaAlamat.setRows(5);
        jScrollPane1.setViewportView(TAreaAlamat);

        jLabel4.setText(" Alamat         :");

        jLabel5.setText("No Telepon        : ");

        jLabel6.setText("Email                  :");

        jLabel8.setText("Jenis Kelamin     :");

        jLabel9.setText(" Pekerjaan    :");

        jLabel10.setText("Status Anggota :");

        jLabel11.setText("Tanggal Lahir     :");

        TableAnggota.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(TableAnggota);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CBPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TFieldNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(12, 12, 12)
                                .addComponent(TFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TFieldTLahir)
                                    .addComponent(TFieldNomor)
                                    .addComponent(TFieldEmail)
                                    .addComponent(CBJK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CBStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel1)))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnSimpan)
                .addGap(18, 18, 18)
                .addComponent(btnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnKeluar)
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(TFieldNomor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(TFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFieldTLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(CBJK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(CBStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TFieldNIK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(CBPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnHapus)
                    .addComponent(btnKeluar))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TFieldNIKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFieldNIKKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            CekDataNIK();
        }
    }//GEN-LAST:event_TFieldNIKKeyPressed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String nikAnggota = TFieldNIK.getText().trim();
        String namaAnggota = TFieldNama.getText().trim();
        String alamatAnggota = TAreaAlamat.getText().trim();
        String nomorTelepon = TFieldNomor.getText().trim();
        String emailAnggota = TFieldEmail.getText().trim();
        String tanggalLahirStr = TFieldTLahir.getText().trim();
        String jenisKelamin = CBJK.getSelectedItem().toString();
        String pekerjaanAnggota = CBPekerjaan.getSelectedItem().toString();
        String statusAnggota = CBStatus.getSelectedItem().toString();

        if (nikAnggota.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NIK tidak boleh kosong!");
            TFieldNIK.requestFocus();
            return;
        }
        
        validasiNIK(nikAnggota);

        try {
            java.sql.Date tanggalLahir = java.sql.Date.valueOf(tanggalLahirStr);
            if (cekNikDatabase(nikAnggota)) {
                int pilihan = JOptionPane.showConfirmDialog(null, "NIK sudah terdaftar. Apakah Anda ingin mengubah data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (pilihan == JOptionPane.YES_OPTION) {
                    ubahDataAnggota(nikAnggota, namaAnggota, alamatAnggota, nomorTelepon, emailAnggota, tanggalLahir, jenisKelamin, pekerjaanAnggota, statusAnggota);
                    dataToTable();
                }
            } else {
                simpanDataAnggota(nikAnggota, namaAnggota, alamatAnggota, nomorTelepon, emailAnggota, tanggalLahir, jenisKelamin, pekerjaanAnggota, statusAnggota);
                dataToTable();
            }
        } catch (SQLException e) {
            try {
                String pesanError = "Gagal ";
                if (cekNikDatabase(nikAnggota)) {
                    pesanError += "mengubah data";
                } else {
                    pesanError += "menyimpan data";
                }
                pesanError += ": " + e.getMessage();
                System.err.println(pesanError);
                JOptionPane.showMessageDialog(null, pesanError + ". Silakan hubungi administrator!");
            } catch (SQLException ex) {
                Logger.getLogger(JFrameAnggota.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Format tanggal lahir tidak valid! Gunakan format YYYY-MM-DD (misalnya, 2001-10-20).");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int selectedRow = TableAnggota.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Silakan pilih baris data dari tabel yang ingin dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(null, 
                             "Apakah Anda yakin ingin menghapus data anggota ini?", 
                             "Konfirmasi Penghapusan", JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            String nik = (String) TableAnggota.getValueAt(selectedRow, 0);

            String sql = "DELETE FROM anggota WHERE nik = ?";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, nik);
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Data anggota berhasil dihapus!");
                    ((DefaultTableModel) TableAnggota.getModel()).removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
                }
            } catch (SQLException e) {
                System.err.println("Error saat menghapus data: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Gagal menghapus data. Silakan hubungi administrator!");
            } 
        } 
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
        new JFrame_MenuUtama().setVisible(true);
    }//GEN-LAST:event_btnKeluarActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameAnggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBJK;
    private javax.swing.JComboBox<String> CBPekerjaan;
    private javax.swing.JComboBox<String> CBStatus;
    private javax.swing.JTextArea TAreaAlamat;
    private javax.swing.JTextField TFieldEmail;
    private javax.swing.JTextField TFieldNIK;
    private javax.swing.JTextField TFieldNama;
    private javax.swing.JTextField TFieldNomor;
    private javax.swing.JTextField TFieldTLahir;
    private javax.swing.JTable TableAnggota;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
