/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ptda_swing;

/**
 *
 * @author diogo
 */
public class Bilhetes extends javax.swing.JPanel {
    public static void main(String[] args) {
        // TODO code application logic here
     
    }

    /**
     * Creates new form Bilhetes
     */
    public Bilhetes() {
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

        pagamentos = new javax.swing.JPanel();
        Multibanco = new javax.swing.JRadioButton();
        MBWAY = new javax.swing.JRadioButton();
        Balcao = new javax.swing.JRadioButton();
        Visa = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        n_bilhetes = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Normal = new javax.swing.JRadioButton();
        Estudante = new javax.swing.JRadioButton();
        VIP = new javax.swing.JRadioButton();
        DEF = new javax.swing.JRadioButton();
        Texto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        preco_bilhete = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        pagamentos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Método de pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        Multibanco.setText("Multibanco");
        Multibanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MultibancoActionPerformed(evt);
            }
        });

        MBWAY.setText("MBWAY");

        Balcao.setText("Balcão");

        Visa.setText("Visa");

        jButton1.setText("Comprar bilhetes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pagamentosLayout = new javax.swing.GroupLayout(pagamentos);
        pagamentos.setLayout(pagamentosLayout);
        pagamentosLayout.setHorizontalGroup(
            pagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Multibanco)
                    .addComponent(MBWAY)
                    .addComponent(Balcao)
                    .addComponent(Visa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagamentosLayout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        pagamentosLayout.setVerticalGroup(
            pagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagamentosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(Multibanco)
                .addGap(18, 18, 18)
                .addComponent(MBWAY)
                .addGap(18, 18, 18)
                .addComponent(Balcao)
                .addGap(18, 18, 18)
                .addComponent(Visa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jLabel1.setText("Número de Bilhetes -> ");

        n_bilhetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n_bilhetesActionPerformed(evt);
            }
        });

        jLabel2.setText("Preço por bilhete -> ");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Bilhetes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        Normal.setText("Bilhete Normal");
        Normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NormalActionPerformed(evt);
            }
        });

        Estudante.setText("Bilhete Estudante");
        Estudante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstudanteActionPerformed(evt);
            }
        });

        VIP.setText("Bilhete VIP");
        VIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VIPActionPerformed(evt);
            }
        });

        DEF.setText("Bilhete DEF");
        DEF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DEFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Normal)
                    .addComponent(Estudante)
                    .addComponent(VIP)
                    .addComponent(DEF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(Normal)
                .addGap(18, 18, 18)
                .addComponent(Estudante)
                .addGap(18, 18, 18)
                .addComponent(VIP)
                .addGap(18, 18, 18)
                .addComponent(DEF)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        preco_bilhete.setText("jLabel3");

        jButton2.setText("jButton2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(preco_bilhete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(n_bilhetes, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(Texto, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(pagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(preco_bilhete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(n_bilhetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addComponent(Texto, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void n_bilhetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n_bilhetesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_n_bilhetesActionPerformed

    private void NormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NormalActionPerformed
        // TODO add your handling code here:
        if(Normal.isSelected() == true){
            preco_bilhete.setText("7€");
        }
    }//GEN-LAST:event_NormalActionPerformed

    private void MultibancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MultibancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MultibancoActionPerformed

    private void EstudanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstudanteActionPerformed
        // TODO add your handling code here:
        if(Estudante.isSelected() == true){
            preco_bilhete.setText("5€");
        }
    }//GEN-LAST:event_EstudanteActionPerformed

    private void VIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VIPActionPerformed
        // TODO add your handling code here:
        if(VIP.isSelected() == true){
            preco_bilhete.setText("14€");
        }
    }//GEN-LAST:event_VIPActionPerformed

    private void DEFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DEFActionPerformed
        // TODO add your handling code here:
        if(Normal.isSelected() == true){
            preco_bilhete.setText("6€");
        }
    }//GEN-LAST:event_DEFActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int price = Integer.parseInt(preco_bilhete.getText());
        int bilhetes = Integer.parseInt(n_bilhetes.getText());
        int precoTotal = price*bilhetes;
        
        if(Multibanco.isSelected() == true){
            Texto.setText("Total -> " + precoTotal + "€");
            Texto.setText("Método de Pagamento -> Multibanco");
        }else{
            if(Balcao.isSelected() == true){
                Texto.setText("Total -> " + precoTotal + "€");
                Texto.setText("Terá de se deslocar ao balcão");
            }else{
                if(Visa.isSelected() == true){
                    Texto.setText("Total -> " + precoTotal + "€");
                    Texto.setText("Método de Pagamento -> Visa Card");
                }else{
                    if(MBWAY.isSelected() == true){
                        Texto.setText("Total -> " + precoTotal + "€");
                        Texto.setText("Método de Pagamento -> MBWAY");
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Balcao;
    private javax.swing.JRadioButton DEF;
    private javax.swing.JRadioButton Estudante;
    private javax.swing.JRadioButton MBWAY;
    private javax.swing.JRadioButton Multibanco;
    private javax.swing.JRadioButton Normal;
    private javax.swing.JLabel Texto;
    private javax.swing.JRadioButton VIP;
    private javax.swing.JRadioButton Visa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField n_bilhetes;
    private javax.swing.JPanel pagamentos;
    private javax.swing.JLabel preco_bilhete;
    // End of variables declaration//GEN-END:variables
}