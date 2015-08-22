/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HtWorkInProgressDlg.java
 *
 * Created on 23.07.2010, 13:24:45
 */
package htrangebreaker.utils.gui;

import htrangebreaker.utils.ProgressDialogInterface;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.SwingUtilities;
import org.jdesktop.application.Action;

/**
 *
 * @author DanilinS
 */
public class HtWorkInProgressDlg extends javax.swing.JDialog {

	private ProgressDialogInterface pdint_m = null;

	/** Creates new form HtWorkInProgressDlg */
	public HtWorkInProgressDlg(java.awt.Frame parent, boolean modal, ProgressDialogInterface pdint ) {
		super(parent, modal);
		pdint_m = pdint;
		initComponents();
	}

	

	public void showDialogWindow(final String message)
	{
		final HtWorkInProgressDlg pthis = this;
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				if (message != null) {
						jLabel2.setText(message);
						pthis.repaint();
				}
				
				pthis.centerParent();
				pthis.pack();
				pthis.setVisible(true);
				pthis.requestFocus();
					
			}
		});
	
	}

	public void hideDialogWindow()
	{
		final HtWorkInProgressDlg pthis = this;
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				pthis.setVisible(false);
			}
		});
	}

	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jCancelBtn = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(htrangebreaker.HtRangeBreakerApp.class).getContext().getResourceMap(HtWorkInProgressDlg.class);
    setBackground(resourceMap.getColor("Form.background")); // NOI18N
    setBounds(new java.awt.Rectangle(0, 0, 0, 0));
    setForeground(resourceMap.getColor("Form.foreground")); // NOI18N
    setName("Form"); // NOI18N
    setResizable(false);
    setUndecorated(true);

    jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jPanel1.setName("jPanel1"); // NOI18N

    jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
    jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
    jLabel1.setName("jLabel1"); // NOI18N

    jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
    jLabel2.setName("jLabel2"); // NOI18N

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(htrangebreaker.HtRangeBreakerApp.class).getContext().getActionMap(HtWorkInProgressDlg.class, this);
    jCancelBtn.setAction(actionMap.get("onCancel")); // NOI18N
    jCancelBtn.setText(resourceMap.getString("jCancelBtn.text")); // NOI18N
    jCancelBtn.setName("jCancelBtn"); // NOI18N

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addContainerGap())
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addComponent(jCancelBtn)
            .addGap(91, 91, 91))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addGap(111, 111, 111))))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
        .addComponent(jLabel1)
        .addGap(18, 18, 18)
        .addComponent(jCancelBtn)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

	

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jCancelBtn;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  // End of variables declaration//GEN-END:variables
	// centers the dialog within the parent container [1.1]
//  (put that in the Dialog class)
	private void centerParent() {
		Dimension d = getToolkit().getScreenSize();
		setLocation(d.width / 3, d.height / 3);
		
	}

	@Action
	public void onCancel() {
		if (pdint_m != null)
			pdint_m.onCancel();
	}

	
}