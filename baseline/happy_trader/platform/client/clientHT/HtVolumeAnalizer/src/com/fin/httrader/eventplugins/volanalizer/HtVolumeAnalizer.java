/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HtVolumeAnalizer.java
 *
 * Created on 14.06.2010, 8:24:03
 */
package com.fin.httrader.eventplugins.volanalizer;

import com.fin.httrader.interfaces.HtDataSelection;
import com.fin.httrader.interfaces.HtEventPlugin;
import com.fin.httrader.managers.RtAlertPluginManager;

import com.fin.httrader.managers.RtRealTimeProviderManager;
import com.fin.httrader.model.HtCommandProcessor;
import com.fin.httrader.utils.HtDateTimeUtils;
import com.fin.httrader.utils.HtException;
import com.fin.httrader.utils.HtLog;

import com.fin.httrader.utils.HtUtils;
import com.fin.httrader.utils.hlpstruct.HtPair;
import com.fin.httrader.utils.hlpstruct.HtRtData;
import com.fin.httrader.utils.hlpstruct.XmlEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;
import org.jdesktop.application.Action;

/**
 *
 * @author DanilinS
 */
public class HtVolumeAnalizer extends javax.swing.JFrame implements HtEventPlugin {

	// ----------------------------------------
	// const
	private static final int MAIN_TAB_SYMBOL_IDX = 0;
	private static final int MAIN_TAB_TIME_IDX = 1;
	private static final int MAIN_TAB_APER_IDX = 2;
	private static final int MAIN_TAB_OPEN_IDX = 3;
	private static final int MAIN_TAB_HIGH_IDX = 4;
	private static final int MAIN_TAB_LOW_IDX = 5;
	private static final int MAIN_TAB_CLOSE_IDX = 6;
	private static final int MAIN_TAB_QTY_IDX = 7;
	// tick tab
	private static final int TICK_TAB_TIME_IDX = 0;
	private static final int TICK_TAB_SYMBOL_IDX = 1;
	private static final int TICK_TAB_PRICE_IDX = 2;
	private static final int TICK_TAB_QTY_IDX = 3;
	// ----------------------------------------
	private StringBuilder name_m = new StringBuilder();
	private HashSet<String> availableSymbols_m = new HashSet<String>();
	private List<TransactionEntry> transactions_m = new ArrayList<TransactionEntry>();
	private HashMap<String, Map<String, Map<Double, AggregatedStructure>>> aggregateds_m = new HashMap<String, Map<String, Map<Double, AggregatedStructure>>>();
	private DecimalFormat volumeFormat_m = new DecimalFormat("###,###,###,###.00");
	private DecimalFormat priceFormat_m = new DecimalFormat("###,###.00");
	private Object mtx_m = new Object();
	private HashMap<String, Long> lastTimeStamps_m = new HashMap<String, Long>();
	private HashMap<String, HtDetailForm> detailedForms_m = new HashMap<String, HtDetailForm>();
	private long beginHistory_m = -1;
	
	private String profileName_m = "";
	private MainWindowListener mainWindowListener_m = null;
	private Map<String, Integer> aggregatedRows_m = new HashMap<String, Integer>();

	private String getContext()
	{
		return this.getClass().getSimpleName();
	}

	public Object getBaseMutex() {
		return mtx_m;
	}

	/** Creates new form HtVolumeAnalizer */
	public HtVolumeAnalizer() {
		initComponents();


		// init table
		DefaultTableModel mod = (DefaultTableModel) jAggrTab.getModel();
		mod.addColumn("Symbol");
		mod.addColumn("Time");
		mod.addColumn("Aggregation Period");
		mod.addColumn("Open");
		mod.addColumn("High");
		mod.addColumn("Low");
		mod.addColumn("Close");
		mod.addColumn("Qty");

		// pack
		Utils.packAllColumns(jAggrTab);


		GreenReadRowRenderer.signupForTable(jAggrTab, priceFormat_m, HtVolumeAnalizer.MAIN_TAB_OPEN_IDX, HtVolumeAnalizer.MAIN_TAB_CLOSE_IDX);





		// add selection listener
		jAggrTab.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return; // if you don't want to handle intermediate selections
				}
				ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
				int selectedIndex = rowSM.getMinSelectionIndex();
				if (selectedIndex >= 0) {
					String cur_symbol = (String) jAggrTab.getValueAt(selectedIndex, MAIN_TAB_SYMBOL_IDX);
					

					DefaultComboBoxModel model = (DefaultComboBoxModel)JSymbolsCB.getModel();
					model.setSelectedItem(cur_symbol);

					// aggregation
					String aggrPerS = (String) jAggrTab.getValueAt(selectedIndex, MAIN_TAB_APER_IDX);
					jAggrPerCB.getModel().setSelectedItem(aggrPerS);

				}

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

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jAddAggrBtn = new javax.swing.JButton();
    jRemoveAggrBtn = new javax.swing.JButton();
    jAggrPerCB = new javax.swing.JComboBox();
    jopenDetailedBtn = new javax.swing.JButton();
    JSymbolsCB = new javax.swing.JComboBox();
    jScrollPane1 = new javax.swing.JScrollPane();
    jAggrTab = new javax.swing.JTable();
    jCloseBtn = new javax.swing.JButton();
    jSeparator1 = new javax.swing.JSeparator();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    jLabel1.setText("Symbol");

    jLabel2.setText("Aggregation Periods");

    jAddAggrBtn.setText("Add Symbol");
    jAddAggrBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jAddAggrBtnActionPerformed(evt);
      }
    });

    jRemoveAggrBtn.setText("Remove Symbol");
    jRemoveAggrBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRemoveAggrBtnActionPerformed(evt);
      }
    });

    jAggrPerCB.setModel(new DefaultComboBoxModel(VolumeAnalizerConsts.AGGR_PERIODS) );

    jopenDetailedBtn.setText("Open Detailed");
    jopenDetailedBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jopenDetailedBtnActionPerformed(evt);
      }
    });

    JSymbolsCB.setModel(new DefaultComboBoxModel());

    jAggrTab.setAutoCreateRowSorter(true);
    jAggrTab.setModel(new DefaultTableModel() {
      public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
      }
    });
    jAggrTab.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jAggrTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane1.setViewportView(jAggrTab);

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.fin.httraderdevelop.HtVolumeAnalizerApp.class).getContext().getActionMap(HtVolumeAnalizer.class, this);
    jCloseBtn.setAction(actionMap.get("onClose")); // NOI18N
    jCloseBtn.setText("Close");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel2)
              .addComponent(jLabel1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
              .addComponent(JSymbolsCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(jAggrPerCB, javax.swing.GroupLayout.Alignment.LEADING, 0, 133, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jAddAggrBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jRemoveAggrBtn)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jopenDetailedBtn)
            .addGap(12, 12, 12)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(550, Short.MAX_VALUE)
        .addComponent(jCloseBtn)
        .addContainerGap())
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jAddAggrBtn, jRemoveAggrBtn, jopenDetailedBtn});

    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel1)
              .addComponent(JSymbolsCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jAggrPerCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel2)))
          .addGroup(layout.createSequentialGroup()
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jAddAggrBtn)
              .addComponent(jRemoveAggrBtn)
              .addComponent(jopenDetailedBtn))))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jCloseBtn)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

	private void jAddAggrBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddAggrBtnActionPerformed
		// TODO add your handling code here:
		try {

			synchronized (mtx_m) {
				total_addNewAggregationEntry((String)JSymbolsCB.getSelectedItem(), (String) jAggrPerCB.getModel().getSelectedItem());

				// pack
				Utils.packAllColumns(jAggrTab);
				
			}

		} catch (Throwable e) {
			new MsgBox(this, "Exception: " + e.getMessage());
		}
	}//GEN-LAST:event_jAddAggrBtnActionPerformed

	private void jRemoveAggrBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRemoveAggrBtnActionPerformed
		// TODO add your handling code here:
		try {
			synchronized (mtx_m) {
				int idx = jAggrTab.getSelectedRow();
				if (idx >= 0) {

					total_removeAggregationEntry((String) jAggrTab.getValueAt(idx, MAIN_TAB_SYMBOL_IDX), (String) jAggrTab.getValueAt(idx, this.MAIN_TAB_APER_IDX));
				}
			}
		} catch (Throwable e) {
			new MsgBox(this, "Exception: " + e.getMessage());
		}
	}//GEN-LAST:event_jRemoveAggrBtnActionPerformed

	private void jopenDetailedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jopenDetailedBtnActionPerformed
		// TODO add your handling code here:
		try {
			synchronized (mtx_m) {
				int idx = jAggrTab.getSelectedRow();
				if (idx >= 0) {

					String symbol = (String) jAggrTab.getValueAt(idx, MAIN_TAB_SYMBOL_IDX);
					String aperS = (String) jAggrTab.getValueAt(idx, MAIN_TAB_APER_IDX);
					String key = symbol + aperS;

					if (!detailedForms_m.containsKey(key)) {
						total_openDetailedWindow(symbol, aperS);
					} else {
						new MsgBox(this, "This detailed window already exists");
					}

				}
			}
		} catch (Throwable e) {
			new MsgBox(this, "Exception: " + e.getMessage());
		}
	}//GEN-LAST:event_jopenDetailedBtnActionPerformed

	private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
		// TODO add your handling code here:
		if (mainWindowListener_m != null) {
			mainWindowListener_m.onClose();
		}
	}//GEN-LAST:event_formWindowClosing

	public Integer[] returnSubscribtionEventTypes() {
		return new Integer[]{
							XmlEvent.ET_RtProviderTicker
						};
	}

	// the functions below to manage the stuff the other app
	public void setUp_external(MainWindowListener mainWindowListener) {
		mainWindowListener_m = mainWindowListener;
	}



	public void initialize(Map<String, String> initdata) throws Exception {
		setVisible(true);


		int history_depth = HtUtils.parseInt(HtUtils.getParameterWithoutException(initdata, "HISTORY_DEPTH_DAYS"));
		beginHistory_m = HtDateTimeUtils.getCurGmtTime_DayBegin() - (long) history_depth * 24 * 60 * 60 * 1000;

		//HtLog.log(Level.INFO, getContext(), "initialize", "Will read history from: " + HtUtils.time2SimpleString_Gmt(beginHistory_m));
		profileName_m = HtUtils.getParameterWithException(initdata, "HISTORY_PROFILE");

	}

	public void deinitialize() throws Exception {
		setVisible(false);
		dispose();
	}

	public String getEventPluginId() {
		return name_m.toString();
	}

	public void setEventPluginId(String pluginId) {
		name_m.setLength(0);
		name_m.append(pluginId);
	}

	public void registerDetailedForm(String symbol, String aggrPeriod, HtDetailForm dform) {
		synchronized (mtx_m) {
			detailedForms_m.put(symbol + aggrPeriod, dform);
		}
	}

	public void removeDetailForm(String symbol, String aggrPeriod) {
		synchronized (mtx_m) {
			detailedForms_m.remove(symbol + aggrPeriod);
		}
	}

	public void disableTickTab()
	{
			/*
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				
				jTicksTab.setEnabled(false);
				jTicksTab.setVisible(false);
			}
		});
		*/
	}

	public void enableTickTab()
	{
		/*
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				
				jTicksTab.setEnabled(true);
				jTicksTab.setVisible(true);

			}
		});

		 */
		
	}
	

	public void execute(XmlEvent alertData) throws Exception {

			// only if history read
		HtRtData param_i = alertData.getAsRtData();
		pushEventHelper(param_i);
	}

	private void pushEventHelper(HtRtData param_i) throws Exception {
		String symbol = param_i.getSymbol();
		TransactionEntry entry_i = new TransactionEntry(
						symbol,
						(int) param_i.volume_m,
						param_i.bid_m,
						param_i.time_m,
						param_i.operation_m);


		//HtLog.log(Level.INFO, getContext(), "execute", symbol + " - " + HtUtils.time2String_Gmt((long)tdate));
		synchronized (mtx_m) {

			// if we already have ticks just clear averything

			Long last_time = lastTimeStamps_m.get(symbol);
			if (last_time != null) {


				if (param_i.time_m <= last_time.longValue()) {

					return;
				}
			}


			if (!availableSymbols_m.contains(symbol)) {
				availableSymbols_m.add(symbol);
				gui_addNewSymbol(symbol);
			}


		


			lastTimeStamps_m.put(symbol, param_i.time_m);
			transactions_m.add(entry_i);

			processAgregationStructureOnTickArrived(entry_i, null);
		}

	}
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox JSymbolsCB;
  private javax.swing.JButton jAddAggrBtn;
  private javax.swing.JComboBox jAggrPerCB;
  private javax.swing.JTable jAggrTab;
  private javax.swing.JButton jCloseBtn;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JButton jRemoveAggrBtn;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JButton jopenDetailedBtn;
  // End of variables declaration//GEN-END:variables

	/*
	 * helpers
	 */
	// just fill aggragegation structure at teh beginning
	// add to aggregation structures on ticks arrival
	private void processAgregationStructureOnTickArrived(TransactionEntry entry, String aggrPeriodStrOnly) throws Exception {

		Map<String, Map<Double, AggregatedStructure>> lst = aggregateds_m.get(entry.symbol_m);

		if (lst != null) {
			// symbol is registered. iterate
			for (Iterator<String> it = lst.keySet().iterator(); it.hasNext();) {
				String aggrPeriodStr_i = it.next();

				if (aggrPeriodStrOnly != null && (!aggrPeriodStr_i.equalsIgnoreCase(aggrPeriodStrOnly))) {
					continue;
				}

				TreeMap<Double, AggregatedStructure> lst2 = (TreeMap<Double, AggregatedStructure>) lst.get(aggrPeriodStr_i);



				// private static final String [] AGGR_PERIODS = {"10 sec", "20 sec", "1 min", "5 min", "15 min", "30 min", "1 hour", "Day"};
				// we have aggregation period (string version and based on this )

				double rounded = -1;
				if (aggrPeriodStr_i.equalsIgnoreCase("10 sec")) {
					rounded = (10000 * Math.floor(entry.time_m / 10000));

				} else if (aggrPeriodStr_i.equalsIgnoreCase("20 sec")) {
					rounded = (20000 * Math.floor(entry.time_m / 20000));
				} else if (aggrPeriodStr_i.equalsIgnoreCase("1 min")) {
					rounded = (60000 * Math.floor(entry.time_m / 60000));
				} else if (aggrPeriodStr_i.equalsIgnoreCase("5 min")) {
					rounded = (5 * 60 * 1000 * Math.floor(entry.time_m / (5 * 60 * 1000)));
				} else if (aggrPeriodStr_i.equalsIgnoreCase("15 min")) {
					rounded = (15 * 60 * 1000 * Math.floor(entry.time_m / (15 * 60 * 1000)));
				} else if (aggrPeriodStr_i.equalsIgnoreCase("30 min")) {
					rounded = (30 * 60 * 1000 * Math.floor(entry.time_m / (30 * 60 * 1000)));
				} else if (aggrPeriodStr_i.equalsIgnoreCase("1 hour")) {
					rounded = (60 * 60 * 1000 * Math.floor(entry.time_m / (60 * 60 * 1000)));
				} else if (aggrPeriodStr_i.equalsIgnoreCase("Day")) {
					rounded = (24 * 60 * 60 * 1000 * Math.floor(entry.time_m / (24 * 60 * 60 * 1000)));

				}



				// last tick time


				boolean insert = false;
				AggregatedStructure struct_i = lst2.get(rounded);
				if (struct_i == null) {
					// new
					struct_i = new AggregatedStructure(entry.symbol_m, rounded, entry.qty_m, entry.price_m);

					lst2.put(rounded, struct_i);

					insert = true;
				} else {
					struct_i.aggregate(entry.price_m, entry.qty_m);
				}



				// add histogram entry

				HistogramEntry histEttry = struct_i.histogram_m.get(entry.price_m);

				if (histEttry == null) {
					histEttry = new HistogramEntry();
					struct_i.histogram_m.put(entry.price_m, histEttry);

				}



				histEttry.qty_m += entry.qty_m;

				//int idx = gui_findRowInTable(entry.symbol_m, aggrPeriodStr_i);
				int idx = findAggregatedRow(entry.symbol_m, aggrPeriodStr_i);
				if (idx >= 0) {
					//HtLog.log(Level.INFO, getContext(), "processAgregationStructureOnTickArrived", struct_i.toString());
					gui_updateRow(idx, struct_i);

				}

				String key_i = entry.symbol_m + aggrPeriodStr_i;
				HtDetailForm frm_i = detailedForms_m.get(key_i);

				if (frm_i != null) {
					if (insert == true) {
						frm_i.addEntry(struct_i, lst2);

					} else {
						frm_i.updateLastEntry(struct_i, lst2);

					}
				}

			} // end loop

		}
	}

	private void initVolumeAggregation(String symbol, String aggrPeriod) throws Exception {

		Map<String, Map<Double, AggregatedStructure>> lst = null;
		if (!aggregateds_m.containsKey(symbol)) {
			lst = new TreeMap<String, Map<Double, AggregatedStructure>>();
			aggregateds_m.put(symbol, lst);


		} else {
			lst = aggregateds_m.get(symbol);

		}

		lst.put(aggrPeriod, new TreeMap<Double, AggregatedStructure>());

		// add existing from existing transactions
		for (int i = 0; i < transactions_m.size(); i++) {

			TransactionEntry trans_i = transactions_m.get(i);

			processAgregationStructureOnTickArrived(trans_i, aggrPeriod);

		}

	}

	private void removeVolumeAggregation(String symbol, String aggrPeriod) throws Exception {

		//private HashMap<String, Map<String, Map<Double, AggregatedStructure>>> aggregateds_m = new HashMap<String, Map<String, Map<Double, AggregatedStructure>>>();

		if (aggregateds_m.containsKey(symbol)) {
			Map<String, Map<Double, AggregatedStructure>> lst = aggregateds_m.get(symbol);

			lst.remove(aggrPeriod);

			if (lst.isEmpty()) {
				aggregateds_m.remove(symbol);
			}

		}

	}

		

	// ---------------
	private void total_openDetailedWindow(String symbol, String aggrPeriod) throws Exception {
		if (symbol == null || symbol.length() <= 0) {
			throw new HtException(getContext(), "addNewAggregationEntry", "Invalid symbol");
		}

		if (aggrPeriod == null || aggrPeriod.length() <= 0) {
			throw new HtException(getContext(), "addNewAggregationEntry", "Invalid aggregation period");
		}



		synchronized (mtx_m) {
			HtDetailForm frm = new HtDetailForm(symbol, aggrPeriod, this);
			frm.setVisible(true);

			// iterate through existing
			Map<String, Map<Double, AggregatedStructure>> lst = aggregateds_m.get(symbol);

			if (lst != null) {
				TreeMap<Double, AggregatedStructure> lst2 = (TreeMap<Double, AggregatedStructure>) lst.get(aggrPeriod);

				if (lst2 != null) {
					for (Iterator<Double> it = lst2.keySet().iterator(); it.hasNext();) {
						AggregatedStructure aggr_i = lst2.get(it.next());

						frm.addEntry(aggr_i, lst2);


					}
				}
			}
		}


	}

	private void total_addNewAggregationEntry(String symbol, String aggrPeriod) throws Exception {
		if (symbol == null || symbol.length() <= 0) {
			throw new HtException(getContext(), "addNewAggregationEntry", "Invalid symbol");
		}

		if (aggrPeriod == null || aggrPeriod.length() <= 0) {
			throw new HtException(getContext(), "addNewAggregationEntry", "Invalid aggregation period");
		}

		if (findAggregatedRow(symbol, aggrPeriod) < 0) {
		//if (gui_findRowInTable(symbol, aggrPeriod) < 0) {

			int idx = gui_insertRow_guith(symbol, aggrPeriod);
			insertAggregatedRow(symbol, aggrPeriod, idx);
			initVolumeAggregation(symbol, aggrPeriod);
		}

	}

	private void total_removeAggregationEntry(String symbol, String aggrPeriod) throws Exception {
		if (symbol == null || symbol.length() <= 0) {
			throw new HtException(getContext(), "addNewAggregationEntry", "Invalid symbol");
		}

		if (aggrPeriod == null || aggrPeriod.length() <= 0) {
			throw new HtException(getContext(), "addNewAggregationEntry", "Invalid aggregation period");
		}

		//int idx = gui_findRowInTable(symbol, aggrPeriod);
		int idx = findAggregatedRow(symbol, aggrPeriod);
		if (idx >= 0) {
			gui_removeRow_guith(idx);
			removeAggregatedRow(symbol, aggrPeriod, idx);

			removeVolumeAggregation(symbol, aggrPeriod);
		}
	}

	

	private int findAggregatedRow(String symbol, String aggrPeriod)
	{

		String key = symbol + aggrPeriod;
		Integer idx = aggregatedRows_m.get(key);
		if (idx == null)
			return -1;
		else
			return idx.intValue();

	}

	private void insertAggregatedRow(String symbol, String aggrPeriod, int idx)
	{
		String key = symbol + aggrPeriod;
		aggregatedRows_m.put(key, idx);
	}

	private void removeAggregatedRow(String symbol, String aggrPeriod, int idx)
	{
		String key = symbol + aggrPeriod;
		aggregatedRows_m.remove(key);

		for(Iterator<String> it = aggregatedRows_m.keySet().iterator(); it.hasNext(); ) {
			String key_i = it.next();
			Integer val_i = aggregatedRows_m.get(key_i);

			if (val_i > idx) {
				int newidx = val_i.intValue() - 1;
				aggregatedRows_m.put(key_i, newidx);
			}
		}

	
	}

	
	private void gui_removeRow_guith(final int idx) {

		final DefaultTableModel tmodel = (DefaultTableModel) jAggrTab.getModel();
		tmodel.removeRow(idx);

	}

	private int gui_insertRow_guith(final String symbol, final String aggrPeriodS) {

		DefaultTableModel tmodel = (DefaultTableModel) jAggrTab.getModel();
		tmodel.addRow(new Object[]{symbol, "", aggrPeriodS, "", "", "", "", ""});
		int idx = jAggrTab.getRowCount() - 1;

		return idx;
	}

	private void gui_updateRow(
					final int idx,
					final AggregatedStructure struct_i) {

		final DefaultTableModel tmodel = (DefaultTableModel) jAggrTab.getModel();
		final HtVolumeAnalizer pthis = this;

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				tmodel.setValueAt(HtDateTimeUtils.time2SimpleString_Gmt(struct_i.time_m), idx, pthis.MAIN_TAB_TIME_IDX);
				tmodel.setValueAt(String.valueOf(struct_i.qty_m), idx, pthis.MAIN_TAB_QTY_IDX);

				tmodel.setValueAt(priceFormat_m.format(struct_i.open_m), idx, pthis.MAIN_TAB_OPEN_IDX);
				tmodel.setValueAt(priceFormat_m.format(struct_i.high_m), idx, pthis.MAIN_TAB_HIGH_IDX);
				tmodel.setValueAt(priceFormat_m.format(struct_i.low_m), idx, pthis.MAIN_TAB_LOW_IDX);
				tmodel.setValueAt(priceFormat_m.format(struct_i.close_m), idx, pthis.MAIN_TAB_CLOSE_IDX);

			}
		});

	}


	private void gui_addNewSymbol(String symbol)
	{
		final DefaultComboBoxModel model = (DefaultComboBoxModel)JSymbolsCB.getModel();
		final String symbols = new String(symbol);

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				model.addElement(symbols);
			}
		});
	}

	@Action
	public void onClose() {
		if (mainWindowListener_m != null) {
			mainWindowListener_m.onClose();
		}
		
		this.setVisible(false);
		this.dispose();
	}

	

	//
	
}
