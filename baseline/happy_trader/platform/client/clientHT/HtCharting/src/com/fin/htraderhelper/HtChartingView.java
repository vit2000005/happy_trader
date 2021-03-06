/*
 * HtChartingView.java
 */
package com.fin.htraderhelper;

import com.fin.htraderhelper.utils.SymbolKey;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import javax.swing.JTable;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * The application's main frame.
 */
public class HtChartingView extends FrameView {


	private class MyTableCellRenderer extends JLabel implements TableCellRenderer {

		HtChartingView base_m = null;

		MyTableCellRenderer(HtChartingView base)
		{
			super();
			base_m = base;
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

			int id = Integer.valueOf( (String)table.getValueAt(row, 8) );
			boolean our_row = base_m.selectedRows_m.contains( id );

			if (hasFocus || isSelected) {
				// cell (and perhaps other cells) are selected
				setForeground(table.getSelectionForeground());
				setOpaque(true);
				setBackground(Color.BLACK);

			} else {
				setForeground(table.getForeground());
				//super.setBackground(table.getBackground());
				if (!our_row)
					super.setBackground(table.getBackground());
				else {
					setOpaque(true);
					super.setBackground(Color.YELLOW);
				}

				
			}

			setText(String.valueOf(value));

			
			
			return this;

		}

		public void validate() {
		}

		public void revalidate() {
		}

		protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		}

		

	};
	// --------------------------
	HtDataProvider dataProvider_m = null;

	Set<Integer> selectedRows_m = new HashSet<Integer>();

	// -------------------------------
	public HtChartingView(SingleFrameApplication app, String[] args) {
		super(app);


		initComponents();

		// status bar initialization - message timeout, idle icon and busy animation, etc

	

		progressBar.setVisible(true);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);

		/////////////////////
		

	}

	@Action
	public void showAboutBox() {
		if (aboutBox == null) {
			JFrame mainFrame = HtChartingApp.getApplication().getMainFrame();
			aboutBox = new HtChartingAboutBox(mainFrame);
			aboutBox.setLocationRelativeTo(mainFrame);
		}
		HtChartingApp.getApplication().show(aboutBox);
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
    jTable1 = new javax.swing.JTable();
    menuBar = new javax.swing.JMenuBar();
    javax.swing.JMenu fileMenu = new javax.swing.JMenu();
    jMenuItem1 = new javax.swing.JMenuItem();
    jMenuItem2 = new javax.swing.JMenuItem();
    jMenuItem3 = new javax.swing.JMenuItem();
    javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
    jMenu1 = new javax.swing.JMenu();
    jMenuItem4 = new javax.swing.JMenuItem();
    javax.swing.JMenu helpMenu = new javax.swing.JMenu();
    javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
    statusPanel = new javax.swing.JPanel();
    javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
    statusMessageLabel = new javax.swing.JLabel();
    statusAnimationLabel = new javax.swing.JLabel();
    progressBar = new javax.swing.JProgressBar();

    mainPanel.setName("mainPanel"); // NOI18N

    jScrollPane1.setName("jScrollPane1"); // NOI18N

    jTable1.setAutoCreateRowSorter(true);
    jTable1.setModel(new DefaultTableModel() {
      public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
      }
    }
  );
  jTable1.setName("jTable1"); // NOI18N
  jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent evt) {
      jTable1MouseClicked(evt);
    }
  });
  jScrollPane1.setViewportView(jTable1);

  javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
  mainPanel.setLayout(mainPanelLayout);
  mainPanelLayout.setHorizontalGroup(
    mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
  );
  mainPanelLayout.setVerticalGroup(
    mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
  );

  menuBar.setName("menuBar"); // NOI18N

  org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.fin.htraderhelper.HtChartingApp.class).getContext().getResourceMap(HtChartingView.class);
  fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
  fileMenu.setName("fileMenu"); // NOI18N

  javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.fin.htraderhelper.HtChartingApp.class).getContext().getActionMap(HtChartingView.class, this);
  jMenuItem1.setAction(actionMap.get("doBaseAction")); // NOI18N
  jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
  jMenuItem1.setName("jMenuItem1"); // NOI18N
  fileMenu.add(jMenuItem1);

  jMenuItem2.setAction(actionMap.get("doShowChart")); // NOI18N
  jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
  jMenuItem2.setName("jMenuItem2"); // NOI18N
  fileMenu.add(jMenuItem2);

  jMenuItem3.setAction(actionMap.get("onExportData")); // NOI18N
  jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
  jMenuItem3.setName("jMenuItem3"); // NOI18N
  fileMenu.add(jMenuItem3);

  exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
  exitMenuItem.setName("exitMenuItem"); // NOI18N
  fileMenu.add(exitMenuItem);

  menuBar.add(fileMenu);

  jMenu1.setText(resourceMap.getString("jToolsMenu.text")); // NOI18N
  jMenu1.setName("jToolsMenu"); // NOI18N

  jMenuItem4.setAction(actionMap.get("doTextSearch")); // NOI18N
  jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
  jMenuItem4.setName("jMenuItem4"); // NOI18N
  jMenu1.add(jMenuItem4);

  menuBar.add(jMenu1);

  helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
  helpMenu.setName("helpMenu"); // NOI18N

  aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
  aboutMenuItem.setName("aboutMenuItem"); // NOI18N
  helpMenu.add(aboutMenuItem);

  menuBar.add(helpMenu);

  statusPanel.setName("statusPanel"); // NOI18N

  statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

  statusMessageLabel.setName("statusMessageLabel"); // NOI18N

  statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

  progressBar.setName("progressBar"); // NOI18N

  javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
  statusPanel.setLayout(statusPanelLayout);
  statusPanelLayout.setHorizontalGroup(
    statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
    .addGroup(statusPanelLayout.createSequentialGroup()
      .addContainerGap()
      .addComponent(statusMessageLabel)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
      .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(statusAnimationLabel)
      .addContainerGap())
  );
  statusPanelLayout.setVerticalGroup(
    statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(statusPanelLayout.createSequentialGroup()
      .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(statusMessageLabel)
        .addComponent(statusAnimationLabel)
        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addGap(3, 3, 3))
  );

  setComponent(mainPanel);
  setMenuBar(menuBar);
  setStatusBar(statusPanel);
  }// </editor-fold>//GEN-END:initComponents

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
		// TODO add your handling code here:
		if (evt.getClickCount() == 2) {
			doShowChart();
		}
	}//GEN-LAST:event_jTable1MouseClicked

	// --------------------------
	// helpers
	private void doReadDataFile(File f, HtWorkProgressor progressor) {


		try {
			selectedRows_m.clear();
			dataProvider_m = new HtDataProvider(f.getAbsolutePath(), progressor);

			DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

			while (jTable1.getRowCount() > 0) {
				((DefaultTableModel) jTable1.getModel()).removeRow(0);
			}

			model.setColumnCount(0);

			model.addColumn("Run Name");
			model.addColumn("Name");
			model.addColumn("Provider");
			model.addColumn("Symbol");
			model.addColumn("Level 1");
			model.addColumn("Indicator");
			model.addColumn("OHCL");
			model.addColumn("Print");
			model.addColumn("Text");
			model.addColumn("ID");

			TableColumn col = jTable1.getColumnModel().getColumn(0);
			col.setCellRenderer(new MyTableCellRenderer( this ));

			//
			Set<SymbolKey> keys = dataProvider_m.getSymbolList();

			int idx = 0;
			for (Iterator<SymbolKey> it = keys.iterator(); it.hasNext();) {
				SymbolKey symbol_i = it.next();
				HtDataProvider.SymbolProperty prop_i = dataProvider_m.getSymbolProperty(symbol_i);

				model.addRow(new Object[]{
									symbol_i.getRunName(),
									symbol_i.getName(),
									symbol_i.getProvider(),
									symbol_i.getSymbol(),
									String.valueOf(prop_i.num_Leve11_m),
									String.valueOf(prop_i.num_LineIndic_m),
									String.valueOf(prop_i.num_OHCL_m),
									String.valueOf(prop_i.num_Print_m),
									String.valueOf(prop_i.num_Text_m),
									String.valueOf(idx++)});


			}

		} catch (Throwable e) {
			new MsgBox(this.getFrame(), "Exception happend: " + e.getMessage(), false);
		}


	}

	@Action
	public void doBaseAction() {


		//Create a file chooser
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.dat", "dat");

		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter);


		//In response to a button click:
		int returnVal = fc.showOpenDialog(this.getComponent());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			final File f = fc.getSelectedFile();

			// start in background
			// start task which will be constantly update


			HtWorkProgressor task = new HtWorkProgressor(this.progressBar) {

				@Override
				protected Void doInBackground() throws Exception {
					menuBar.setEnabled(false);
					doReadDataFile(f, this);
					return null;
				}

				@Override
				public void done() {
					menuBar.setEnabled(true);
				}
			};

			task.addPropertyChangeListener(task);
			task.execute();



		}

	}

	@Action
	public void doShowChart() {
		int row = jTable1.getSelectedRow();

		if (row < 0) {
			return;
		}


		SymbolKey symbol = new SymbolKey(
						(String) jTable1.getValueAt(row, 0),
						(String) jTable1.getValueAt(row, 1),
						(String) jTable1.getValueAt(row, 2),
						(String) jTable1.getValueAt(row, 3));

		if (dataProvider_m != null) {
			try {

				dataProvider_m.createChart(symbol);
			} catch (Throwable e) {
				new MsgBox(this.getFrame(), "Exception on plotting a chart: " + e.getMessage(), false);
			}
		}
	}

	@Action
	public void onExportData() {



		// export data
		try {

			//FileNameExtensionFilter filter = new FileNameExtensionFilter("*.dat", "dat");

			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			//fc.setFileFilter(filter);


			//In response to a button click:
			int returnVal = fc.showOpenDialog(this.getComponent());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				final File f = fc.getSelectedFile();

				if (dataProvider_m == null) {
					throw new Exception("No data available");
				}

				// export in thread mode

				HtWorkProgressor task = new HtWorkProgressor(this.progressBar) {

					@Override
					protected Void doInBackground() throws Exception {
						menuBar.setEnabled(false);
						dataProvider_m.exportData(f, this);
						return null;
					}

					@Override
					public void done() {
						menuBar.setEnabled(true);
					}
				};

				task.addPropertyChangeListener(task);
				task.execute();


			}

		} catch (Throwable e) {
			new MsgBox(this.getFrame(), "Exception on export data: " + e.getMessage(), false);
		}

	}

	@Action
	public void doTextSearch() {
		// performs text search

		try {
			final StringBuilder shortText = new StringBuilder();
			final StringBuilder text = new StringBuilder();


			if (dataProvider_m == null) {
				throw new Exception("No data available");
			}

			HSearchInput2 searchd  = new HSearchInput2(this.getFrame(), true, shortText, text);

			HtWorkProgressor task = new HtWorkProgressor(this.progressBar) {

					@Override
					protected Void doInBackground() throws Exception {
						menuBar.setEnabled(false);
						selectedRows_m.clear();
						List<Integer> idxes = dataProvider_m.globaltextSearch(shortText.toString(), text.toString(), this);

						selectedRows_m.addAll(idxes);

						
						return null;
					}

					@Override
					public void done() {
						menuBar.setEnabled(true);
						((AbstractTableModel)jTable1.getModel()).fireTableDataChanged();
					}
				};

				task.addPropertyChangeListener(task);
				task.execute();


			
		}
		catch (Throwable e) {
			new MsgBox(this.getFrame(), "Exception on export data: " + e.getMessage(), false);
		}

		
		

		

		
	}
	// ----------------------------------
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenuItem jMenuItem1;
  private javax.swing.JMenuItem jMenuItem2;
  private javax.swing.JMenuItem jMenuItem3;
  private javax.swing.JMenuItem jMenuItem4;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTable1;
  private javax.swing.JPanel mainPanel;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JProgressBar progressBar;
  private javax.swing.JLabel statusAnimationLabel;
  private javax.swing.JLabel statusMessageLabel;
  private javax.swing.JPanel statusPanel;
  // End of variables declaration//GEN-END:variables
	
	private JDialog aboutBox;
}
