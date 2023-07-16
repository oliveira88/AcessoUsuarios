package com.ufes.acessousuarios.view;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BuscarUsuariosView extends javax.swing.JInternalFrame {

    /**
     * Creates new form LoginView
     */
    public BuscarUsuariosView() {
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
        btnEnviarNotificacao = new javax.swing.JButton();
        btnVisaulizarUsuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jFechar = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnEnviarNotificacao.setText("Enviar notificação");
        btnEnviarNotificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarNotificacaoActionPerformed(evt);
            }
        });

        btnVisaulizarUsuario.setText("Visualizar usuário");

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Data do Cadastro", "Notificações enviadas", "nullNotificações lidas"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        jFechar.setText("Fechar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFechar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnVisaulizarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviarNotificacao))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jFechar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviarNotificacao)
                    .addComponent(btnVisaulizarUsuario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarNotificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarNotificacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnviarNotificacaoActionPerformed

    public JTable getjTable() {
        return jTable;
    }

    public JButton getBtnEnviarNotificacao() {
        return btnEnviarNotificacao;
    }

    public JButton getBtnVisaulizarUsuario() {
        return btnVisaulizarUsuario;
    }

    public JButton getjFechar() {
        return jFechar;
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarNotificacao;
    private javax.swing.JButton btnVisaulizarUsuario;
    private javax.swing.JButton jFechar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
