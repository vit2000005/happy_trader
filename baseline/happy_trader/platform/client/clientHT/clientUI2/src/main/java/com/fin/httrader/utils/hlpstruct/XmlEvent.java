/*
 * XmlEvent.java
 *
 
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.fin.httrader.utils.hlpstruct;

import com.fin.httrader.managers.RtGlobalEventManager;
import com.fin.httrader.utils.HtException;
import com.fin.httrader.utils.HtUtils;
import com.fin.httrader.utils.Uid;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Administrator
 * This incapsulate event data that retreived from the server
 */
public class XmlEvent {

	// --------------------------------------------
	public static final int ET_Dummy = 0;
	// simple common log
	public static final int ET_CommonLog = 1; // datatype = DT_Log
	// this is event that is passed to algorithm / broker layer from Java server
	// must contain XmlParameter
	public static final int ET_AlgorithmEvent = 2; //DT_CommonXml
	// this is response on the previous event  -e.i. what these functions will actually return
	public static final int ET_AlgorithmBrokerEventResp_FromAlgorithm = 3; //datatype = DT_CommonXml
	// this is broker response event. Contains serialized XML of BrkLib::BrokerResponseBase (or derived)
	// and must be interpreted respectively
	public static final int ET_BrokerResponseEventResp = 4; // datatype = DT_CommonXml
	// Rt provider tickers, type DT_Ticker
	public static final int ET_RtProviderTicker = 5;  // datatype = DT_Ticker
	// internal import log, will insert into another log table
	public static final int ET_InternalImportResultsLog = 6; // datatype = DT_Log
	// internal export log will insert into another log table
	public static final int ET_InternalExportResultsLog = 7; // datatype = DT_Log
	// this is order generated against broker level
	public static final int ET_BrokerOrderEvent = 8; // datatype = DT_CommonXml
	// whenever broker session is initialized it sends notification to be registered in a separate table
	public static final int ET_BrokerSessionEvent = 9; // datatype = DT_CommonXml
	// this is special kind of informative events generated by custom algorithm level - alerts
	public static final int ET_AlgorithmAlert = 10;
	// this is response on the previous ET_AlgorithmEvent  -e.i. from broker layer
	public static final int ET_AlgorithmBrokerEventResp_FromBroker = 11; //datatype = DT_CommonXml
	// to pass level 2 data
	public static final int ET_Level2Data = 12; //datatype = DT_Level2Data
	// to pass level1 data
	public static final int ET_Level1Data = 13; //datatype = DT_Level1Data
	// special event to describe any kind of drawable objects (this may be put on charts)
	public static final int ET_DrawableObject = 14; // datatype = DT_DrawableObject

	// this is an arbitrary custom event
	public static final int ET_CustomEvent = 15;

	// this is issued by RT Providers to synchronize whatever (connect, disconnect, begin, end)
	public static final int ET_RtProviderSynchronizationEvent = 16;

	public static final int ET_AlertPluginSynchronizationEvent = 17;

	public static final int ET_BrokerEvent = 18; //DT_CommonXml

	// internal use only !!!
	public static final int ET_InternalGlobalEventManagerShutdown = 10001;
	// internal use only - synchronization event
	public static final int ET_SynchronizationEvent = 10002;

	// any type of notif
	public static final int ET_Notification = 19;
	
	// only within java layer
	public static final int ET_Java_Object_Common = 20;

	// ------------------------------------------
	// available events for trading server
	private static TreeSet<Integer> availableEvents_m = new TreeSet<Integer>
	(
			Arrays.asList	(	new Integer[]{ 
				ET_CommonLog,
				ET_AlgorithmEvent,
				ET_AlgorithmBrokerEventResp_FromAlgorithm,
				ET_AlgorithmBrokerEventResp_FromBroker,
				ET_BrokerResponseEventResp,
				ET_RtProviderTicker,
				ET_InternalImportResultsLog,
				ET_InternalExportResultsLog,
				ET_BrokerOrderEvent,
				ET_BrokerSessionEvent, 
				ET_AlgorithmAlert,
				ET_Level2Data, 
				ET_Level1Data, 
				ET_DrawableObject, 
				ET_CustomEvent,
				ET_RtProviderSynchronizationEvent, 
				ET_AlertPluginSynchronizationEvent, 
				ET_BrokerEvent,
				ET_Notification,
				ET_Java_Object_Common
				}
			)
	);

	// ---------------------------------------------
	// data types
	public static final int DT_Dummy = 0;
	public static final int DT_Log = 1;
	public static final int DT_String = 2;
	public static final int DT_StringList = 3;
	public static final int DT_StringMap = 4;
	public static final int DT_Ticker = 5;
	public static final int DT_TickerList = 6;
	public static final int DT_CommonXml = 7;
	public static final int DT_Level2Data = 8;
	public static final int DT_Level1Data = 9;
	public static final int DT_DrawableObject = 10;
	public static final int DT_Java_Object = 11;

	// --------------------------------------------
	// notificatiosn types
	// trading server thread started
	
	public static final int ETNT_ThreadStarted = 1;
	public static final int ETNT_ThreadFinished = 2;

	// --------------------------------------------


	// helper to handle sequence number
	private static AtomicLong sequence_m = new AtomicLong(1);


	// --------------------------------------------

	/////////////////////////////////////////////
	// event type
	private int eventType_m = ET_Dummy;
	// data type
	private int dataType_m = DT_Dummy;
	// date time of log
	private long eventDate_m = -1;

	// data
	private Object data_m = null;

	// UID
	private final Uid uid_m = new Uid();

	// parent UID
	private final Uid parentUid_m = new Uid();

	// event source
	private StringBuilder eventSource_m = new StringBuilder();

	// sequence number
	private long thisSequence_m = -1;

	// --------------------------------------

	private void incSequence() {
	
		thisSequence_m = sequence_m.incrementAndGet();
		
	}
	
	private String getContext()
	{
			return XmlEvent.class.getSimpleName();
	}

	public long getSequenceNumber() {
		return thisSequence_m;
	}

	/** Creates a new instance of XmlEvent */
	public XmlEvent() {
		incSequence();
	}

	// copy ctor
	public XmlEvent(XmlEvent src) {
		create(src);
	}

	public static Set<Integer> getAllAvalableEventsForServer() {
		return availableEvents_m;
	}

	public static String getEventTypeName(int eventtype) {
		if (eventtype == ET_CommonLog) {
			return "ET_CommonLog";
		} else if (eventtype == ET_AlgorithmEvent) {
			return "ET_AlgorithmEvent";
		} else if (eventtype == ET_AlgorithmBrokerEventResp_FromAlgorithm) {
			return "ET_AlgorithmBrokerEventResp_FromAlgorithm";
		} else if (eventtype == ET_AlgorithmBrokerEventResp_FromBroker) {
			return "ET_AlgorithmBrokerEventResp_FromBroker";
		} else if (eventtype == ET_BrokerResponseEventResp) {
			return "ET_BrokerResponseEventResp";
		} else if (eventtype == ET_RtProviderTicker) {
			return "ET_RtProviderTicker";
		} else if (eventtype == ET_InternalImportResultsLog) {
			return "ET_InternalImportResultsLog";
		} else if (eventtype == ET_InternalExportResultsLog) {
			return "ET_InternalExportResultsLog";
		} else if (eventtype == ET_BrokerOrderEvent) {
			return "ET_BrokerOrderEvent";
		} else if (eventtype == ET_BrokerSessionEvent) {
			return "ET_BrokerSessionEvent";
		} else if (eventtype == ET_AlgorithmAlert) {
			return "ET_AlgorithmAlert";
		} else if (eventtype == ET_Level2Data) {
			return "ET_Level2Data";
		} else if (eventtype == ET_Level1Data) {
			return "ET_Level1Data";
		} else if (eventtype == ET_DrawableObject) {
			return "ET_DrawableObject";
		} else if (eventtype == ET_CustomEvent) {
			return "ET_CustomEvent";
		} else if (eventtype == ET_RtProviderSynchronizationEvent) {
			return "ET_RtProviderSynchronizationEvent";
		} else if (eventtype == ET_AlertPluginSynchronizationEvent) {
			return "ET_AlertPluginSynchronizationEvent";
		}	else if (eventtype == ET_BrokerEvent) {
			return "ET_BrokerEvent";
		} else if (eventtype == ET_Notification) {
			return "ET_Notification";
		}
		else if (eventtype == ET_Java_Object_Common) {
			return "ET_Java_Object_Commmon";
		}

		return "";
	}

	public void clear() {

		data_m = null;
		dataType_m = DT_Dummy;
		//eventType_m = ET_Dummy;
		eventDate_m = -1;

		uid_m.invalidate();
		parentUid_m.invalidate();
		eventSource_m.setLength(0);
		//thisSequence_m  =-1;

	}

	public void setType(int type) {
		// New type?
		//
		if (type == dataType_m) // No; bail out
		{
			return;
		}


		// Get rid of previous data
		//
		clear();


		// List new type
		//
		dataType_m = type;

		// do not negerate uid here
		//uid_m.generate();


		// Allocate data if needed
		//
		switch (dataType_m) {
			case DT_Log:

				data_m = new CommonLog();
				break;
			case DT_String:

				data_m = new StringBuilder();
				break;
			case DT_StringList:

				data_m = new Vector<String>();
				break;
			case DT_StringMap:

				data_m = new HashMap<String, String>();
				break;
			case DT_Ticker:

				data_m = new HtRtData();
				break;
			case DT_TickerList:

				data_m = new Vector<HtRtData>();
				break;
			case DT_CommonXml:

				data_m = new XmlParameter();
				break;
			case DT_Level2Data:

				data_m = new HtLevel2Data();
				break;

			case DT_Level1Data:

				data_m = new HtLevel1Data();
				break;

			case DT_DrawableObject:
				data_m = new HtDrawableObject();
				break;
		
		}
	}

	// create a deep copy
	public void create(XmlEvent src) {
		this.setType(src.getType());
		this.setUid(src.getUid());
		this.setSource(src.getSource());
		this.setParentUid(src.getParentUid());
		this.setEventDate(src.getEventDate());
		this.setEventType(src.getEventType());

		this.thisSequence_m = src.thisSequence_m;

		// copy data
		switch (dataType_m) {
			case DT_Log: {
				CommonLog data = (CommonLog) data_m;
				data.create(src.getAsLog());
				break;
			}
			case DT_String: {
				StringBuilder data = (StringBuilder) data_m;
				data.setLength(0);
				data.append(src.getAsStringBuffer());
				break;
			}
			case DT_StringList: {
				Vector<String> data = (Vector<String>) data_m;
				data.clear();
				for (int i = 0; i < src.getAsStringList().size(); i++) {
					data.add(src.getAsStringList().get(i));
				}
				break;
			}
			case DT_StringMap: {
				HashMap<String, String> data = (HashMap<String, String>) data_m;
				data.clear();
				for (Iterator<String> it = src.getAsStringMap().keySet().iterator(); it.hasNext();) {
					String key = new String(it.next());
					String value = new String(src.getAsStringMap().get(key));
					data.put(key, value);
				}
				break;
			}
			case DT_Ticker: {
				HtRtData data = (HtRtData) data_m;
				data.create(src.getAsRtData());

				break;
			}
			case DT_TickerList: {
				Vector<HtRtData> data = (Vector<HtRtData>) data_m;
				data.clear();
				for (int i = 0; i < src.getAsRtDataList().size(); i++) {
					HtRtData newRt = new HtRtData();
					newRt.create(src.getAsRtDataList().get(i));
					data.add(newRt);
				}

				break;
			}
			case DT_CommonXml: {
				XmlParameter data = (XmlParameter) data_m;
				data.create(src.getAsXmlParameter());
				break;
			}
			case DT_Level2Data: {
				HtLevel2Data data = (HtLevel2Data) data_m;
				data.create(src.getAsLevel2Data());
				break;
			}
			case DT_Level1Data: {
				HtLevel1Data data = (HtLevel1Data) data_m;
				data.create(src.getAsLevel1Data());
				break;
			}
			case DT_DrawableObject: {
				HtDrawableObject data = (HtDrawableObject) data_m;
				data.create(src.getAsDrawableObject());
				break;
			}
			case DT_Java_Object:
			{
					// just assign reference
					data_m = src.data_m;
			}
		}
	}

	public int getType() {
		return dataType_m;
	}

	public void setEventType(int etype) {
		eventType_m = etype;
	}

	public int getEventType() {
		return eventType_m;
	}

	public long getEventDate() {
		return eventDate_m;
	}

	public void setEventDate(long edate) {
		eventDate_m = edate;
	}

	public void setUid(Uid uid) {
		uid_m.fromUid(uid);
	}

	public void setParentUid(Uid uid) {
		parentUid_m.fromUid(uid);
	}

	
	public Uid getUid() {
		return uid_m;
	}

	// this is called
	public void generateUid()
	{
		uid_m.generate();
	}


	public Uid getParentUid() {
		return parentUid_m;
	}

	public String getSource() {
		return eventSource_m.toString();
	}

	public void setSource(String source) {
		eventSource_m.setLength(0);
		eventSource_m.append(source);
	}

	// accessors
	public CommonLog getAsLog(boolean setType) {
		if (setType) {
			setType(DT_Log);
		}
		//Assert.that("dataType_m == DT_Log", dataType_m == DT_Log);
		return (CommonLog) data_m;
	}

	public StringBuilder getAsStringBuffer(boolean setType) {
		if (setType) {
			setType(DT_String);
		}
		//Assert.that("dataType_m == DT_String", dataType_m == DT_String);
		return (StringBuilder) data_m;
	}

	public Vector<String> getAsStringList(boolean setType) {
		if (setType) {
			setType(DT_StringList);
		}
		//Assert.that("dataType_m == DT_StringList", dataType_m == DT_StringList);
		return (Vector<String>) data_m;
	}

	public Map<String, String> getAsStringMap(boolean setType) {
		if (setType) {
			setType(DT_StringMap);
		}
		//Assert.that("dataType_m == DT_StringMap", dataType_m == DT_StringMap);
		return (Map<String, String>) data_m;
	}

	public HtRtData getAsRtData(boolean setType) {
		if (setType) {
			setType(DT_Ticker);
		}
		//Assert.that("dataType_m == DT_Ticker", dataType_m == DT_Ticker);
		return (HtRtData) data_m;
	}

	public Vector<HtRtData> getAsRtDataList(boolean setType) {
		if (setType) {
			setType(DT_TickerList);
		}
		//Assert.that("dataType_m == DT_TickerList", dataType_m == DT_TickerList);
		return (Vector<HtRtData>) data_m;
	}

	public XmlParameter getAsXmlParameter(boolean setType) {
		if (setType) {
			setType(DT_CommonXml);
		}
		//Assert.that("dataType_m == DT_CommonXml", dataType_m == DT_CommonXml);
		return (XmlParameter) data_m;
	}

	public HtLevel2Data getAsLevel2Data(boolean setType) {
		if (setType) {
			setType(DT_Level2Data);
		}
		//Assert.that("dataType_m == DT_Level2Data", dataType_m == DT_Level2Data);
		return (HtLevel2Data) data_m;
	}

	public HtLevel1Data getAsLevel1Data(boolean setType) {
		if (setType) {
			setType(DT_Level1Data);
		}
		//Assert.that("dataType_m == DT_Level1Data", dataType_m == DT_Level1Data);
		return (HtLevel1Data) data_m;
	}

	public HtDrawableObject getAsDrawableObject(boolean setType) {
		if (setType) {
			setType(DT_DrawableObject);
		}

		return (HtDrawableObject) data_m;
	}
	


	// constant accessors
	public CommonLog getAsLog() {

		//Assert.that(dataType_m == DT_Log);
		return (CommonLog) data_m;
	}

	public StringBuilder getAsStringBuffer() {

		//Assert.that(dataType_m == DT_String);
		return (StringBuilder) data_m;
	}

	public Vector<String> getAsStringList() {

		//Assert.that(dataType_m == DT_StringList);
		return (Vector<String>) data_m;
	}

	public Map<String, String> getAsStringMap() {

		//Assert.that(dataType_m == DT_StringMap);
		return (Map<String, String>) data_m;
	}

	public HtRtData getAsRtData() {

		//Assert.that(dataType_m == DT_Ticker);
		return (HtRtData) data_m;
	}

	public Vector<HtRtData> getAsRtDataList() {

		//Assert.that(dataType_m == DT_TickerList);
		return (Vector<HtRtData>) data_m;
	}

	public XmlParameter getAsXmlParameter() {

		//Assert.that(dataType_m == DT_CommonXml);
		return (XmlParameter) data_m;
	}

	public HtLevel2Data getAsLevel2Data() {
		//Assert.that("dataType_m == DT_Level2Data", dataType_m == DT_Level2Data);
		return (HtLevel2Data) data_m;
	}

	public HtLevel1Data getAsLevel1Data() {
		//Assert.that("dataType_m == DT_Level1Data", dataType_m == DT_Level1Data);
		return (HtLevel1Data) data_m;
	}

	public HtDrawableObject getAsDrawableObject() {
		return (HtDrawableObject) data_m;
	}
	
	// for java objects stored by ref shoukd be another mean
	public Object getJavaObject()
	{
		if (this.getType() != XmlEvent.DT_Java_Object )
				return null;
		return (Object) data_m;
	}
	
	public void setJavaObject(Object data) throws Exception
	{
		if (this.getType() != XmlEvent.DT_Java_Object )
				throw new HtException(getContext(), "getJavaObject", "Data type must be DT_Java_Object" );
		data_m = data;
	}

	// conversion functions
	public static void convertEventToXmlParameter(XmlEvent eventdata, XmlParameter xmlparameter) throws Exception {
		xmlparameter.clear();
		xmlparameter.setCommandName("event_packet");

		xmlparameter.setString("source", eventdata.getSource());
		xmlparameter.setUid("uid", eventdata.getUid());
		xmlparameter.setUid("puid", eventdata.getParentUid());

		xmlparameter.setInt("etype", eventdata.getEventType());
		xmlparameter.setInt("dtype", eventdata.getType());
		xmlparameter.setDate("etime", eventdata.getEventDate());

		int dtype = eventdata.getType();
		if (dtype== XmlEvent.DT_Log) {
			XmlParameter logParam = new XmlParameter();
			logParam.setDate("time", eventdata.getAsLog().getLogDate());
			logParam.setInt("level", eventdata.getAsLog().getLogLevel());
			logParam.setInt("thread", eventdata.getAsLog().getLogThread());
			logParam.setString("context", eventdata.getAsLog().getContext());
			logParam.setString("content", HtUtils.wrapToCDataTags(eventdata.getAsLog().getContent()));
			logParam.setString("session", eventdata.getAsLog().getSession());

			

			//logParam.setStringList("attrib", attribs);
			xmlparameter.setXmlParameter("log", logParam);
		} else if (dtype == XmlEvent.DT_CommonXml) {
			xmlparameter.setXmlParameter("commonxml", eventdata.getAsXmlParameter());
		} else if (dtype == XmlEvent.DT_String) {
			xmlparameter.setString("str", eventdata.getAsStringBuffer().toString());

		} else if (dtype == XmlEvent.DT_StringList) {
			xmlparameter.setStringList("strlist", eventdata.getAsStringList());
		} else if (dtype == XmlEvent.DT_StringMap) {
			XmlParameter strMapParam = new XmlParameter();

			Map<String, String> strmap = eventdata.getAsStringMap();
			for (Iterator<String> it = strmap.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				String value = strmap.get(key);
				strMapParam.setString(key, value);
			}

			xmlparameter.setXmlParameter("strmap", strMapParam);
		} else if (dtype == XmlEvent.DT_Ticker) {
			XmlParameter xmlparam = new XmlParameter();
			HtRtData.rtDataToParameter(eventdata.getAsRtData(), xmlparam);
			xmlparameter.setXmlParameter("ticker", xmlparam);

		} else if (dtype == XmlEvent.DT_TickerList) {
			XmlParameter tickerParam = new XmlParameter();
			Vector<HtRtData> rtlist = eventdata.getAsRtDataList();
			Vector<XmlParameter> xmlParameterList = new Vector<XmlParameter>();
			for (int i = 0; i < rtlist.size(); i++) {
				HtRtData.rtDataToParameter(rtlist.get(i), tickerParam);
				xmlParameterList.add(tickerParam);
			}
			xmlparameter.setXmlParameterList("tickerlist", xmlParameterList);

		} else if (dtype == XmlEvent.DT_Level2Data) {
			XmlParameter xmlparam = new XmlParameter();
			HtLevel2Data.level2DataToParameter(eventdata.getAsLevel2Data(), xmlparam);
			xmlparameter.setXmlParameter("level2data", xmlparam);

		} else if (dtype == XmlEvent.DT_Level1Data) {
			XmlParameter xmlparam = new XmlParameter();
			HtLevel1Data.level1DataToParameter(eventdata.getAsLevel1Data(), xmlparam);
			xmlparameter.setXmlParameter("level1data", xmlparam);

		} else if (dtype == XmlEvent.DT_DrawableObject) {
			XmlParameter xmlparam = new XmlParameter();
			HtDrawableObject.drawableObjectToParameter(eventdata.getAsDrawableObject(), xmlparam);
			xmlparameter.setXmlParameter("drawobj", xmlparam);

		}
		else 
			throw new HtException("convertEventToXmlParameter", "convertEventToXmlParameter", "Invalid data type: " +dtype);
		

	}

	public static void convertXmlParameterToEvent(XmlEvent eventdata, XmlParameter xmlparameter) throws Exception {
		eventdata.clear();


		int etype = (int) xmlparameter.getInt("etype");
		int dtype = (int) xmlparameter.getInt("dtype");
		long etime = xmlparameter.getDate("etime");

		//Assert.that( xmlparameter.getCommandName().equalsIgnoreCase("event_packet" ) );



		if (dtype == XmlEvent.DT_Log) {
			XmlParameter logParam = xmlparameter.getXmlParameter("log");
			eventdata.getAsLog(true).setContent(logParam.getString("content"));
			eventdata.getAsLog().setContext(logParam.getString("context"));
			eventdata.getAsLog().setLogLevel((int) logParam.getInt("level"));
			eventdata.getAsLog().setLogDate(logParam.getDate("time"));
			eventdata.getAsLog().setSession(logParam.getString("session"));
			eventdata.getAsLog().setLogThread((int) logParam.getInt("thread"));

			

		} else if (dtype == XmlEvent.DT_CommonXml) {
			eventdata.getAsXmlParameter(true).create(xmlparameter.getXmlParameter("commonxml"));
		} else if (dtype == XmlEvent.DT_String) {
			// always copied
			eventdata.getAsStringBuffer(true).append(xmlparameter.getString("str"));
		} else if (dtype == XmlEvent.DT_StringList) {

			List<String> strlist = xmlparameter.getStringList("strlist");
			for (int i = 0; i < strlist.size(); i++) {
				eventdata.getAsStringList(true).add(new String(strlist.get(i)));
			}

		} else if (dtype == XmlEvent.DT_StringMap) {
			XmlParameter strMapParam = xmlparameter.getXmlParameter("strmap");
			for (Iterator<String> it = strMapParam.getKeys().iterator(); it.hasNext();) {
				String key = it.next();
				String value = xmlparameter.getString(key);

				eventdata.getAsStringMap(true).put(key, value);
			}
		} else if (dtype == XmlEvent.DT_Ticker) {
			HtRtData.rtDataFromParameter(xmlparameter.getXmlParameter("ticker"), eventdata.getAsRtData(true));
		} else if (dtype == XmlEvent.DT_TickerList) {
			List<XmlParameter> tickerList = xmlparameter.getXmlParameterList("tickerlist");


			for (int i = 0; i < tickerList.size(); i++) {
				XmlParameter param_i = tickerList.get(i);

				HtRtData rtdata_i = new HtRtData();
				HtRtData.rtDataFromParameter(param_i, rtdata_i);
				eventdata.getAsRtDataList(true).add(rtdata_i);
			}

		} else if (dtype == XmlEvent.DT_Level2Data) {
			HtLevel2Data.level2DataFromParameter(xmlparameter.getXmlParameter("level2data"), eventdata.getAsLevel2Data(true));
		} else if (dtype == XmlEvent.DT_Level1Data) {
			HtLevel1Data.level1DataFromParameter(xmlparameter.getXmlParameter("level1data"), eventdata.getAsLevel1Data(true));
		} else if (dtype == XmlEvent.DT_DrawableObject) {
			HtDrawableObject.drawableObjectFromParameter(xmlparameter.getXmlParameter("drawobj"), eventdata.getAsDrawableObject(true));
		} else 
			throw new HtException("convertXmlParameterToEvent", "convertXmlParameterToEvent", "Invalid data type: " +dtype);
		
		

		eventdata.setUid(xmlparameter.getUid("uid"));
		eventdata.setParentUid(xmlparameter.getUid("puid"));
		eventdata.setEventType(etype);
		eventdata.setEventDate(etime);
		eventdata.setSource(xmlparameter.getString("source"));
	}

	@Override
	public String toString()
	{
		StringBuilder out = new StringBuilder();

		out.append("XmlEvent object - ");
		out.append(" type: [").append(getEventTypeName(getEventType()));
		out.append("], uid: [").append(this.getUid());
		out.append("], sequence ID: [").append(this.getSequenceNumber()).append("]");

		return out.toString();
	}

	/**
	 * Helpers to create simple log entries
	 */
	public static void createSimpleLog(
					String session,
					String context,
					int logLevel,
					String message) {
		XmlEvent event = new XmlEvent();
		event.setEventType(XmlEvent.ET_CommonLog);

		event.getAsLog(true).setSession(session != null ? session : "N/A");

		event.getAsLog().setContext(context != null ? context : "N/A");
		event.getAsLog().setLogThread(Thread.currentThread().getId());
		event.getAsLog().setLogLevel(logLevel);
		event.getAsLog().setLogDate(Calendar.getInstance().getTimeInMillis());
		event.getAsLog().setContent(message);

		// push data to event manager
		RtGlobalEventManager.instance().pushCommonEvent(event);
	}

	public static void createSimpleAttributedLog_1(
					String session,
					String context,
					int logLevel,
					String message,
					String attribute1) {
		XmlEvent event = new XmlEvent();
		event.setEventType(XmlEvent.ET_CommonLog);

		event.getAsLog(true).setSession(session != null ? session : "N/A");

		event.getAsLog().setContext(context != null ? context : "N/A");
		event.getAsLog().setLogThread(Thread.currentThread().getId());
		event.getAsLog().setLogLevel(logLevel);
		event.getAsLog().setLogDate(Calendar.getInstance().getTimeInMillis());
		event.getAsLog().setContent(message);
		//event.getAsLog().getAttributeList().add(attribute1);
		event.getAsLog().addContentedAttribute(attribute1);

		// push data to event manager
		RtGlobalEventManager.instance().pushCommonEvent(event);
	}

	public static void createSimpleAttributedLog_2(
					String session,
					String context,
					int logLevel,
					String message,
					String attribute1,
					String attribute2) {
		XmlEvent event = new XmlEvent();
		event.setEventType(XmlEvent.ET_CommonLog);

		event.getAsLog(true).setSession(session != null ? session : "N/A");

		event.getAsLog().setContext(context != null ? context : "N/A");
		event.getAsLog().setLogThread(Thread.currentThread().getId());
		event.getAsLog().setLogLevel(logLevel);
		event.getAsLog().setLogDate(Calendar.getInstance().getTimeInMillis());
		event.getAsLog().setContent(message);
		//event.getAsLog().getAttributeList().add(attribute1);
		//event.getAsLog().getAttributeList().add(attribute2);

		event.getAsLog().addContentedAttribute(attribute1);
		event.getAsLog().addContentedAttribute(attribute2);


		// push data to event manager
		RtGlobalEventManager.instance().pushCommonEvent(event);
	}

	// if event is of notification type returns notification type
	public static int getNotificationType(XmlEvent notifEvent)
	{
		if (notifEvent.getEventType() == XmlEvent.ET_Notification) {
			return (int)notifEvent.getAsXmlParameter().getInt("notification_type");
		}

		return -1;
	}
	
};
