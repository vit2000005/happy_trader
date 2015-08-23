#ifndef _HLPSTRUCT_XMLEVENTDATA_INCLUDED
#define _HLPSTRUCT_XMLEVENTDATA_INCLUDED


#include "hlpstructdefs.hpp"
#include "xmlparameter.hpp"
#include "xmlrtdata.hpp"
#include "xmllevel1data.hpp"
#include "xmllevel2data.hpp"
#include "xmldrawableobj.hpp"

namespace HlpStruct {
	
	// structure describing logger details
	struct HLPSTRUCT_EXP CommonLog {
		CommonLog():
			logThread_m(-1)
		{
			logDate_m = CppUtils::getTime();
			logLevel_m = LL_INFO;
		}

		CommonLog(CommonLog const& rhs):
			logDate_m(rhs.logDate_m),
			logLevel_m(rhs.logLevel_m),
			context_m(rhs.context_m),
			content_m(rhs.content_m),
			session_m(rhs.session_m),
			logThread_m(rhs.logThread_m)
		{
			
		}

		
		enum LogLevel {
			LL_FATAL =	1,
			LL_ERROR =	2,
			LL_WARN =		3,
			LL_INFO =		4,
			LL_DEBUG	=	5,
			LL_DEBUGDETAIL	=6
		};

		static CppUtils::String getLogLevelName(LogLevel const ll) {
			if (ll==LL_FATAL) return "FATAL";
			if (ll==LL_ERROR) return "ERROR";
			if (ll==LL_WARN) return "WARN";
			if (ll==LL_INFO) return "INFO";
			if (ll==LL_DEBUG) return "DEBUG";
			if (ll==LL_DEBUGDETAIL) return "DEBUGDETAIL";

			return "";
		}

		// logging level
		int logLevel_m;

		// context
		CppUtils::String context_m;

		// session (if applicable)
		CppUtils::String session_m;

		// date time
		double logDate_m;

		// main log content
		CppUtils::String content_m;

		// attributes (parameters)
		//CppUtils::StringList attributes_m;

		// log thread
		int logThread_m;

	};

	struct HLPSTRUCT_EXP EventData {
		friend HLPSTRUCT_EXP void convertEventToXmlParameter(EventData const& eventdata, XmlParameter &xmlparameter);

		friend HLPSTRUCT_EXP void convertXmlParameterToEvent(EventData &eventdata, XmlParameter const &xmlparameter);
	public:	

		// notification types
		enum ET_Notification_Types {
			ETNT_ThreadStarted=1,
			ETNT_ThreadFinished=2
		};

		// event type
		enum EType {
			ET_Dummy = 0,
			
			ET_CommonLog = 1, // datatype = DT_Log

			// this is event that is passed to algorithm / broker layer from Java server
			// must contain XmlParameter
			ET_AlgorithmEvent = 2, //DT_CommonXml
			
			// this is response on the ET_BrokerEvent event  -e.i. what these functions will actually return
			// this is return parameter from processAlgorithmRelatedEvent(...)
			ET_AlgorithmBrokerEventResp_FromAlgorithm = 3, //datatype = DT_CommonXml

			// this is broker response event. Contains serialized XML of BrkLib::BrokerResponseBase (or derived)
			// and must be interpreted respectively
			ET_BrokerResponseEventResp=4, // datatype = DT_CommonXml

			// Rt provider
			ET_RtProviderTicker=5,  // datatype = DT_Ticker

			// internal import log, will insert into another log table
			ET_InternalImportResultsLog = 6, // datatype = DT_Log
    
			// internal export log will insert into another log table
			ET_InternalExportResultsLog = 7, // datatype = DT_Log

			// this is order issued againd broker layer
			ET_BrokerOrderEvent = 8, // datatype DT_CommonXml

			// this event is generated by broker layer when it is registered
			ET_BrokerSessionEvent = 9, // datatype DT_CommonXml


			// these are alerts - special information events which are written into special table
			// alerts are generated by custom algorithm layer
			ET_AlgorithmAlert = 10, //datattype DT_CommonXml

			// this is response on the previous ET_BrokerEvent  - from broker layer
			// this is return parameter from processBrokerRelatedEvent(...)
			ET_AlgorithmBrokerEventResp_FromBroker = 11, //datatype = DT_CommonXml

			// to pass level 2 data
			ET_Level2Data = 12,

			// to pass level 1 data
			ET_Level1Data = 13,

			// any kind of drawabl objects
			ET_DrawableObject = 14,

			// any kind of custom event
			ET_CustomEvent = 15,

			// RT Providers synchronizetion event
			ET_RtProviderSynchronizationEvent = 16,


			// the same but for ALERT plugins
			ET_AlertPluginSynchronizationEvent = 17,

			ET_BrokerEvent = 18,

			// this is any type of notification (DT_CommonXml)
			ET_Notification = 19

		};

		static vector<EType> availableEvents_m;

		static vector<EventData::EType> const& getAllAllowedEvents();

		static CppUtils::String getEventTypeName(EType const ll);
		

		// data type
		enum DType {
			DT_Dummy = 0,
			DT_Log	=	1,
			DT_String	=	2,
			DT_StringList	=	3,
			DT_StringMap	=	4,
			DT_Ticker	=	5,
			DT_TickerList	=	6,
			DT_CommonXml	=	7,
			DT_Level2Data = 8,
			DT_Level1Data = 9,
			DT_DrawableObject = 10
		};



	private:

		// event uid 
		CppUtils::Uid uid_m;

		// parent UID (when this event is actually response to another)
		CppUtils::Uid parentUid_m;

		// type of event
		EType eventType_m;

		// type of data
		DType dataType_m;

		// event date
		double eventDate_m;

		CppUtils::String eventSource_m;

		// different data
		union Data {
			CommonLog* commonLog_m;
			CppUtils::String* string_m;
			CppUtils::StringList* stringList_m;
			CppUtils::StringMap* stringMap_m;
			RtData* ticker_m;
			RtDataList* tickerList_m;
			XmlParameter* commonXml_m;
			RtLevel2Data* level2Data_m;
			RtLevel1Data* level1Data_m;
			RtDrawableObj* drawableObj_m;
		} data_m;

	public:

		
		/** Assignment operator. */
		EventData & operator = (	EventData const &	);

		/** Equality operator. */
		bool operator == (	EventData const &	) const;

		/** Standard ctor. */
		EventData (	);

		/** Copy ctor. */
		EventData (	EventData const &	);

		/** Destructor. Frees memory. */
		~EventData(	);

		/** Clear and frees memory */
		void clear();

		/** Accesors */
		void setType(DType const type);

		inline DType EventData::getType() const
		{
			return dataType_m;
		};

			
		inline EType const& getEventType() const
		{
			return eventType_m;
		}

		inline EType& getEventType()
		{
			return eventType_m;
		}

		// returns event UID
		inline CppUtils::Uid const& getEventUid() const
		{
			return uid_m;
		}

		inline CppUtils::Uid const& getParentEventUid() const
		{
			return parentUid_m;
		}

		void setParentUid(CppUtils::Uid const& puid);

		// source 
		void setSource(CppUtils::String const& source);

		inline CppUtils::String const& getSource() const
		{
			return eventSource_m;
		}

		

		CommonLog& getAsLog(bool setType = false);

		CommonLog const & getAsLog() const;

		CppUtils::String& getAsString(bool setType = false);

		CppUtils::String const & getAsString() const;

		CppUtils::StringList& getAsStringList(bool setType = false);

		CppUtils::StringList const & getAsStringList() const;

		CppUtils::StringMap& getAsStringMap(bool setType = false);

		CppUtils::StringMap const & getAsStringMap() const;

		RtData& getAsRtData(bool setType = false);

		RtData const & getAsRtData() const;

		RtDataList& getAsRtDataList(bool setType = false);

		RtDataList const & getAsRtDataList() const;

		XmlParameter& getAsXmlParameter(bool setType = false);
		
		XmlParameter const& getAsXmlParameter() const;

		RtLevel2Data& getAsLevel2Data(bool setType = false );

		RtLevel2Data const& getAsLevel2Data( ) const;

		//
		RtLevel1Data& getAsLevel1Data(bool setType = false );

		RtLevel1Data const& getAsLevel1Data( ) const;

		//
		RtDrawableObj& getAsDrawableObject(bool setType = false );

		RtDrawableObj const& getAsDrawableObject( ) const;


		// 
		inline void setEventDate(double const sec)
		{
			eventDate_m = sec;
		}

		inline double const& getEventDate() const
		{
			return eventDate_m;
		}
				
	};

	//typedef deque<EventData> EventDataQueue;

	// non member functions
	HLPSTRUCT_EXP void convertEventToXmlParameter(EventData const& eventdata, XmlParameter &xmlparameter);

	HLPSTRUCT_EXP void convertXmlParameterToEvent(EventData &eventdata, XmlParameter const &xmlparameter);

	
};

#endif