/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HtSettings.java
 *
 * Created on 03.08.2010, 19:03:25
 */
package htrangebreaker;

import com.fin.httrader.utils.HtMathUtils;
import com.fin.httrader.utils.HtUtils;
import htrangebreaker.utils.HtSettingsStruct;
import htrangebreaker.utils.gui.MsgBox;
import java.awt.Color;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdesktop.application.Action;

/**
 *
 * @author DanilinS
 */
public class HtSettings extends javax.swing.JDialog {


	// helper structure holding all settings
	
	// ---------------------------------------
	HtSettingsStruct settings_m = new HtSettingsStruct();
	private Color oldColor_m = null;
	private static HtSettings instance_m = null;
	

	public static HtSettings getInstance()
	{
		return instance_m;
	}

	public static void setInstance(HtSettings instance)
	{
		instance_m = instance;
	}

	/** Creates new form HtSettings */
	public HtSettings(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();

		instance_m = this;

		// initialize settings by default
		settings_m.host = System.getProperty("user_host");
		settings_m.port = HtUtils.parseInt(System.getProperty("user_port"));
		settings_m.user = System.getProperty("user_name");
		settings_m.hashed_password = System.getProperty("hashed_password");
		oldColor_m = jPasswordTxt.getBackground();

		if (settings_m.isValid()) {
			jHostTxt.setText(settings_m.host);
			jUserTxt.setText(settings_m.user);
			jPortTxt.setText(String.valueOf(settings_m.port));
			jPasswordTxt.setText("dummy"); // dummy as already received

			jPasswordTxt.setBackground(Color.GREEN);
			jSignalLengthTxt.setText(String.valueOf( settings_m.alertSignalLengthSec_m));
		}



		// password
		// if begin to edit remove all
		jPasswordTxt.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent de) {
			
				jPasswordTxt.setBackground(oldColor_m);
			}

			public void insertUpdate(DocumentEvent de) {
			
				jPasswordTxt.setBackground(oldColor_m);
			}

			public void removeUpdate(DocumentEvent de) {
				jPasswordTxt.setBackground(oldColor_m);
			}
		});

		// update settings
		updateSettings();

	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jtCancel = new javax.swing.JButton();
    jtOk = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    jHostTxt = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    jUserTxt = new javax.swing.JTextField();
    jLabel5 = new javax.swing.JLabel();
    jPasswordTxt = new javax.swing.JPasswordField();
    jPortTxt = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    JDebugServletCB = new javax.swing.JCheckBox();
    jLabel1 = new javax.swing.JLabel();
    jSignalLengthTxt = new javax.swing.JTextField();

    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(htrangebreaker.HtRangeBreakerApp.class).getContext().getResourceMap(HtSettings.class);
    setTitle(resourceMap.getString("Form.title")); // NOI18N
    setName("Form"); // NOI18N
    setResizable(false);

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(htrangebreaker.HtRangeBreakerApp.class).getContext().getActionMap(HtSettings.class, this);
    jtCancel.setAction(actionMap.get("doCancel")); // NOI18N
    jtCancel.setText(resourceMap.getString("jtCancel.text")); // NOI18N
    jtCancel.setName("jtCancel"); // NOI18N

    jtOk.setAction(actionMap.get("doClose")); // NOI18N
    jtOk.setText(resourceMap.getString("jtOk.text")); // NOI18N
    jtOk.setName("jtOk"); // NOI18N

    jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
    jLabel2.setName("jLabel2"); // NOI18N

    jHostTxt.setText(resourceMap.getString("jHostTxt.text")); // NOI18N
    jHostTxt.setName("jHostTxt"); // NOI18N

    jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
    jLabel4.setName("jLabel4"); // NOI18N

    jUserTxt.setText(resourceMap.getString("jUserTxt.text")); // NOI18N
    jUserTxt.setName("jUserTxt"); // NOI18N

    jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
    jLabel5.setName("jLabel5"); // NOI18N

    jPasswordTxt.setText(resourceMap.getString("jPasswordTxt.text")); // NOI18N
    jPasswordTxt.setName("jPasswordTxt"); // NOI18N

    jPortTxt.setText(resourceMap.getString("jPortTxt.text")); // NOI18N
    jPortTxt.setName("jPortTxt"); // NOI18N

    jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
    jLabel3.setName("jLabel3"); // NOI18N

    JDebugServletCB.setText(resourceMap.getString("JDebugServletCB.text")); // NOI18N
    JDebugServletCB.setName("JDebugServletCB"); // NOI18N

    jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
    jLabel1.setName("jLabel1"); // NOI18N

    jSignalLengthTxt.setText(resourceMap.getString("jSignalLengthTxt.text")); // NOI18N
    jSignalLengthTxt.setName("jSignalLengthTxt"); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
              .addContainerGap()
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                  .addComponent(jLabel4)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(jUserTxt))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                  .addComponent(jLabel2)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(jHostTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(JDebugServletCB, javax.swing.GroupLayout.Alignment.LEADING))
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel5)
                .addComponent(jLabel3))
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPortTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
              .addContainerGap()
              .addComponent(jtCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(jtOk, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSignalLengthTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(jHostTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel3)
          .addComponent(jPortTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(jUserTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel5)
          .addComponent(jPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(JDebugServletCB)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(jSignalLengthTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jtOk)
          .addComponent(jtCancel))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

	@Action
	public void doClose() {
		updateSettings();
		this.setVisible(false);
	}

	@Action
	public void doCancel() {
		this.setVisible(false);
	}

	public HtSettingsStruct getSettings() {
		return settings_m;
	}

	/*
	 * helpers
	 */

	private void updateSettings()
	{
			

		settings_m.port = HtUtils.parseInt(jPortTxt.getText());
		settings_m.host = jHostTxt.getText();
		settings_m.user = jUserTxt.getText();
		settings_m.isServletDebug_m = JDebugServletCB.isSelected();

		if (jPasswordTxt.getBackground().equals(oldColor_m)) {
			// use thif field
			settings_m.hashed_password = HtMathUtils.md5HashString( jPasswordTxt.getText() );
		}

		int slength = HtUtils.parseInt(jSignalLengthTxt.getText());
		if (settings_m.alertSignalLengthSec_m <=0) {
			MsgBox.showMessageBox("Invalid alert signal length");
		}

		settings_m.alertSignalLengthSec_m = slength;


		// MsgBox.showMessageBox(settings_m.toString());
	}
	
	
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox JDebugServletCB;
  private javax.swing.JTextField jHostTxt;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JPasswordField jPasswordTxt;
  private javax.swing.JTextField jPortTxt;
  private javax.swing.JTextField jSignalLengthTxt;
  private javax.swing.JTextField jUserTxt;
  private javax.swing.JButton jtCancel;
  private javax.swing.JButton jtOk;
  // End of variables declaration//GEN-END:variables
}