/*
 * HtVolumeAnalizerView.java
 */
package com.fin.httraderdevelop;

import com.fin.httrader.eventplugins.volanalizer.HtVolumeAnalizer;
import com.fin.httrader.eventplugins.volanalizer.MainWindowListener;
import com.fin.httrader.eventplugins.volanalizer.MsgBox;
import com.fin.httrader.utils.HtMathUtils;
import com.fin.httrader.utils.HtSystem;
import com.fin.httrader.utils.HtUtils;
import com.fin.httraderdevelop.utils.HtEventReceiver;
import com.fin.httraderdevelop.utils.HtFrameLogger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The application's main frame.
 */
public class HtVolumeAnalizerView extends FrameView implements MainWindowListener {

	private HtVolumeAnalizer plugin_m = null;
	private HtEventReceiver eventreceiver_m = null;
	private static HtVolumeAnalizerView instance_m = null;
	private HtWorkInProgressDlg progressDlg_m = null;
	private String hashedPassword_m = null;

	// -------------------------------
	public HtVolumeAnalizerView(SingleFrameApplication app) {
		super(app);

		initComponents();
		progressDlg_m = new HtWorkInProgressDlg(this.getFrame(), true, this);


		

		// some data returned from jnlp
		String user_host =System.getProperty("user_host");
		String user_port_s =System.getProperty("user_port");
		String user_name = System.getProperty("user_name");
		String hashed_password = System.getProperty("hashed_password");

		if (user_host != null)
			jHostTxt.setText( user_host );

		if (user_port_s != null)
			jPortTxt.setText( user_port_s );

		if (user_name != null)
			jUserTxt.setText(user_name);

		// initial
		hashedPassword_m = hashed_password;
		jPasswordTxt.setText(hashedPassword_m);

		// if begin to edit remove all
		jPasswordTxt.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent de) {
					hashedPassword_m = null;
			}

			public void insertUpdate(DocumentEvent de) {
				hashedPassword_m = null;
			}

			public void removeUpdate(DocumentEvent de) {

			}
		});


	}

	@Action
	public void showAboutBox() {
		if (aboutBox == null) {
			JFrame mainFrame = HtVolumeAnalizerApp.getApplication().getMainFrame();
			aboutBox = new HtVolumeAnalizerAboutBox(mainFrame);
			aboutBox.setLocationRelativeTo(mainFrame);
		}
		HtVolumeAnalizerApp.getApplication().show(aboutBox);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    mainPanel = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jLogTxt = new javax.swing.JTextArea();
    jPanel1 = new javax.swing.JPanel();
    jLabel5 = new javax.swing.JLabel();
    jUserTxt = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    jPortTxt = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    jHostTxt = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    jLabel1 = new javax.swing.JLabel();
    jProfileTxt = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    jHistDepthTxt = new javax.swing.JTextField();
    jPasswordTxt = new javax.swing.JPasswordField();
    jStatusTxt = new javax.swing.JTextField();
    menuBar = new javax.swing.JMenuBar();
    javax.swing.JMenu fileMenu = new javax.swing.JMenu();
    javax.swing.JMenuItem startMenuItem = new javax.swing.JMenuItem();
    javax.swing.JMenuItem exitMenuItem1 = new javax.swing.JMenuItem();
    javax.swing.JMenu helpMenu = new javax.swing.JMenu();
    javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

    mainPanel.setName("mainPanel"); // NOI18N

    jScrollPane1.setName("jScrollPane1"); // NOI18N

    jLogTxt.setColumns(20);
    jLogTxt.setEditable(false);
    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.fin.httraderdevelop.HtVolumeAnalizerApp.class).getContext().getResourceMap(HtVolumeAnalizerView.class);
    jLogTxt.setFont(resourceMap.getFont("jLogTxt.font")); // NOI18N
    jLogTxt.setRows(5);
    jLogTxt.setName("jLogTxt"); // NOI18N
    jScrollPane1.setViewportView(jLogTxt);

    jPanel1.setName("jPanel1"); // NOI18N

    jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
    jLabel5.setName("jLabel5"); // NOI18N

    jUserTxt.setText(resourceMap.getString("jUserTxt.text")); // NOI18N
    jUserTxt.setName("jUserTxt"); // NOI18N

    jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
    jLabel4.setName("jLabel4"); // NOI18N

    jPortTxt.setText(resourceMap.getString("jPortTxt.text")); // NOI18N
    jPortTxt.setName("jPortTxt"); // NOI18N

    jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
    jLabel3.setName("jLabel3"); // NOI18N

    jHostTxt.setText(resourceMap.getString("jHostTxt.text")); // NOI18N
    jHostTxt.setName("jHostTxt"); // NOI18N

    jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
    jLabel2.setName("jLabel2"); // NOI18N

    jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
    jLabel1.setName("jLabel1"); // NOI18N

    jProfileTxt.setText(resourceMap.getString("jProfileTxt.text")); // NOI18N
    jProfileTxt.setName("jProfileTxt"); // NOI18N

    jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
    jLabel6.setName("jLabel6"); // NOI18N

    jHistDepthTxt.setText(resourceMap.getString("jHistDepthTxt.text")); // NOI18N
    jHistDepthTxt.setName("jHistDepthTxt"); // NOI18N

    jPasswordTxt.setText(resourceMap.getString("jPasswordTxt.text")); // NOI18N
    jPasswordTxt.setName("jPasswordTxt"); // NOI18N

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel4)
          .addComponent(jLabel2))
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jUserTxt)
          .addComponent(jHostTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(jLabel5)
          .addComponent(jLabel3))
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jPortTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jProfileTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jHistDepthTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(jPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(86, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(8, 8, 8)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel2)
            .addComponent(jHostTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPortTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel1)
            .addComponent(jProfileTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel6)
            .addComponent(jHistDepthTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(jLabel3))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(jUserTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel5)
          .addComponent(jPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jStatusTxt.setEditable(false);
    jStatusTxt.setText(resourceMap.getString("jStatusTxt.text")); // NOI18N
    jStatusTxt.setName("jStatusTxt"); // NOI18N

    javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
    mainPanel.setLayout(mainPanelLayout);
    mainPanelLayout.setHorizontalGroup(
      mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
      .addComponent(jStatusTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
      .addGroup(mainPanelLayout.createSequentialGroup()
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    mainPanelLayout.setVerticalGroup(
      mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(mainPanelLayout.createSequentialGroup()
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jStatusTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    menuBar.setName("menuBar"); // NOI18N

    fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
    fileMenu.setName("fileMenu"); // NOI18N

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.fin.httraderdevelop.HtVolumeAnalizerApp.class).getContext().getActionMap(HtVolumeAnalizerView.class, this);
    startMenuItem.setAction(actionMap.get("startPlugin")); // NOI18N
    startMenuItem.setText(resourceMap.getString("startMenuItem.text")); // NOI18N
    startMenuItem.setName("startMenuItem"); // NOI18N
    fileMenu.add(startMenuItem);

    exitMenuItem1.setAction(actionMap.get("quit")); // NOI18N
    exitMenuItem1.setName("exitMenuItem1"); // NOI18N
    fileMenu.add(exitMenuItem1);

    menuBar.add(fileMenu);

    helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
    helpMenu.setName("helpMenu"); // NOI18N

    aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
    aboutMenuItem.setName("aboutMenuItem"); // NOI18N
    helpMenu.add(aboutMenuItem);

    menuBar.add(helpMenu);

    setComponent(mainPanel);
    setMenuBar(menuBar);
  }// </editor-fold>//GEN-END:initComponents

	@Action
	public void startPlugin() {
		try {
			if (plugin_m == null) {

				//
			
				jLogTxt.setText("");
				plugin_m = new HtVolumeAnalizer();
				instance_m = this;
				plugin_m.setUp_external(this);

				HashMap<String, String> initdata = new HashMap<String, String>();
				initdata.put("HISTORY_PROFILE", jProfileTxt.getText());
				initdata.put("HISTORY_DEPTH_DAYS", jHistDepthTxt.getText());
				plugin_m.initialize(initdata);

				int history_load_days = Integer.valueOf(jHistDepthTxt.getText());

				String baseUrl = "http://" + jHostTxt.getText() + ":" + jPortTxt.getText() + "/htHTTPEventPropagator.jsp";


				String valid_hash_pass = null;
				if (hashedPassword_m != null)
					valid_hash_pass = hashedPassword_m;
				else
					valid_hash_pass = HtMathUtils.md5HashString(jPasswordTxt.getText());

				eventreceiver_m = new HtEventReceiver(
								this,
								baseUrl,
								null,
								plugin_m,
								jUserTxt.getText(),
								valid_hash_pass,
								history_load_days,
								jProfileTxt.getText());

				notifyLoaded_guith();

			}
		} catch (Throwable e) {
			new MsgBox(HtVolumeAnalizerApp.getApplication().getMainFrame(), "Exception: " + e.getMessage());
		}
	}

	public void addLoggingEntry(final String entry) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				jLogTxt.append(entry);
				jLogTxt.append("\n");  
			}
		});
	}

	public void startCommunicationChannel() {

		
	}

	public void stopCommunicationChannel() {
		//
		stopReadingHistory();
	}

	public void startReadingHistory() {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				
				progressDlg_m.centerParent();
			}
		});


	}

	public void stopReadingHistory() {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				progressDlg_m.setVisible(false);
			}
		});
	}

	public static HtVolumeAnalizerView getInstance() {
		return instance_m;
	}
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextField jHistDepthTxt;
  private javax.swing.JTextField jHostTxt;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JTextArea jLogTxt;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPasswordField jPasswordTxt;
  private javax.swing.JTextField jPortTxt;
  private javax.swing.JTextField jProfileTxt;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextField jStatusTxt;
  private javax.swing.JTextField jUserTxt;
  private javax.swing.JPanel mainPanel;
  private javax.swing.JMenuBar menuBar;
  // End of variables declaration//GEN-END:variables
	private JDialog aboutBox;

	public void onClose() {
		deinitializePlugin();
	}

	private void deinitializePlugin() {

		if (plugin_m != null) {

			if (eventreceiver_m != null) {
				eventreceiver_m.shutdown();
				eventreceiver_m = null;
			}

			try {
				plugin_m.deinitialize();
			} catch (Throwable e) {
			}

			plugin_m = null;
			notifyUnloaded_guith();

		}
	}

	private void notifyLoaded_guith() {
		jStatusTxt.setText("Volume analizer loaded");
		jHostTxt.setEnabled(false);
		jPortTxt.setEnabled(false);
		jProfileTxt.setEnabled(false);
		jHistDepthTxt.setEnabled(false);
		jUserTxt.setEnabled(false);
		jPasswordTxt.setEnabled(false);
	}

	private void notifyUnloaded_guith() {
		jStatusTxt.setText("Volume analizer unloaded");
		jHostTxt.setEnabled(true);
		jPortTxt.setEnabled(true);
		jProfileTxt.setEnabled(true);
		jHistDepthTxt.setEnabled(true);
		jUserTxt.setEnabled(true);
		jPasswordTxt.setEnabled(true);
	}
}
