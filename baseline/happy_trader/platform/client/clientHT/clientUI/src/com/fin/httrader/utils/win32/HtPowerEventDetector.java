/*
 * HtPowerEventDetector.java
 *
 
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.fin.httrader.utils.win32;

import com.fin.httrader.utils.win32.HtSysPowerEventListener;
import com.fin.httrader.utils.HtException;
import java.util.Vector;

/**
 *
 * @author Victor_Zoubok
 */
public class HtPowerEventDetector {

	private HtSysPowerEventListener listener_m = null;

	/** Creates a new instance of HtPowerEventDetector */
	public HtPowerEventDetector(HtSysPowerEventListener listener) throws Exception {
		listener_m = listener;
		//System.load(libPathName);
		boolean result = init();

		if (!result) {
			throw new HtException("HtPowerEventDetector", "HtPowerEventDetector", "Can't initialize");
		}

		if (listener_m == null) {
			throw new HtException("HtPowerEventDetector", "HtPowerEventDetector", "Listener is invalid");
		}
	}

	// -----------------------------------
	private native boolean init();

	public native void destroy();

	// -----------------------------------
	// DDE
	public native boolean connectToDDEServer(String application, Vector<DDEItem> itemList);

	public native void disconnectFromDDEServer();

	// ----------------------------------
	public void onDDEDataArrived(String topic, String item, String value) {
		DDEItem ddeitem = new DDEItem(topic, item);
		listener_m.DDEDataArrived(ddeitem, value);
	}
	// ---------------------------------
}
