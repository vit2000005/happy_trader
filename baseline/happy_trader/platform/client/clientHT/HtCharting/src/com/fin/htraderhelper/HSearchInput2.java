/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HSearchInput2.java
 *
 * Created on 01.03.2010, 14:40:07
 */

package com.fin.htraderhelper;

import org.jdesktop.application.Action;

/**
 *
 * @author DanilinS
 */
public class HSearchInput2 extends javax.swing.JDialog {

		private StringBuilder shortText_m = null;

		private StringBuilder longText_m = null;


    /** Creates new form HSearchInput2 */
    public HSearchInput2(java.awt.Frame parent, boolean modal, StringBuilder shortText, StringBuilder longText) {
        super(parent, modal);
        initComponents();

				shortText_m = shortText;
				longText_m = longText;

				shortText.setLength(0);
				longText.setLength(0);
				
				this.setVisible(true);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jCheckBox1 = new javax.swing.JCheckBox();
    jCheckBox2 = new javax.swing.JCheckBox();
    jText = new javax.swing.JTextField();
    jShortText = new javax.swing.JTextField();
    jButton2 = new javax.swing.JButton();
    jButton1 = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setName("Form"); // NOI18N

    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.fin.htraderhelper.HtChartingApp.class).getContext().getResourceMap(HSearchInput2.class);
    jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
    jCheckBox1.setName("jCheckBox1"); // NOI18N

    jCheckBox2.setSelected(true);
    jCheckBox2.setText(resourceMap.getString("jCheckBox2.text")); // NOI18N
    jCheckBox2.setName("jCheckBox2"); // NOI18N

    jText.setName("jText"); // NOI18N

    jShortText.setName("jShortText"); // NOI18N

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.fin.htraderhelper.HtChartingApp.class).getContext().getActionMap(HSearchInput2.class, this);
    jButton2.setAction(actionMap.get("doCancel")); // NOI18N
    jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
    jButton2.setName("jButton2"); // NOI18N

    jButton1.setAction(actionMap.get("doOk")); // NOI18N
    jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
    jButton1.setName("jButton1"); // NOI18N

    jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
    jLabel1.setName("jLabel1"); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 399, Short.MAX_VALUE)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jCheckBox1)
              .addComponent(jCheckBox2))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jText, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
              .addComponent(jShortText, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(jLabel1))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 158, Short.MAX_VALUE)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jCheckBox1)
          .addComponent(jShortText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jCheckBox2)
          .addComponent(jText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButton2)
          .addComponent(jButton1))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

	@Action
	public void doOk() {
		// pass params
		if (jCheckBox1.isSelected())
			shortText_m.append( jShortText.getText() );

		if (jCheckBox2.isSelected())
			longText_m.append(jText.getText());

		this.hide();
	}

	@Action
	public void doCancel() {
		// do nothing
		this.hide();
	}
    

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JCheckBox jCheckBox1;
  private javax.swing.JCheckBox jCheckBox2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JTextField jShortText;
  private javax.swing.JTextField jText;
  // End of variables declaration//GEN-END:variables

}
