//+------------------------------------------------------------------+
//|                                                 ht_data_send.mq4 |
//|                        Copyright 2013,          Victor Zoubok    |
//+------------------------------------------------------------------+
#property copyright "Copyright 2013, MetaQuotes Software Corp."
#property link      "http://www.metaquotes.net"


/** 
* These are return values of external functions
*/

int DLL_FUN_CALL_OK  = 0; // Status is OK
int DLL_FUN_CALL_FAILURE  = -1; // some failure happened


/**
* flags returned by tryLock (expected to be boolean but must comply with DLL_FUN_CALL_OK or DLL_FUN_CALL_FAILURE)
*/

int DLL_FUN_CALL_TRUE = 99999;
int DLL_FUN_CALL_FALSE = 33333;

/** 
* These are log level values. They control how much information is written into log files
* Log files are written into <MT path>\logs. 
*/


int MSG_FATAL  = 0;
int MSG_ERROR  = 1;
int MSG_WARN  = 2;
int MSG_INFO  = 3;
int MSG_DEBUG  = 4;
int MSG_DEBUG_DETAIL  = 5;

/** 
* The maximum number of working threads
*/
int MAX_WORK_THREADS=5;

/** 
* Imported functions
* 
*/
#import "htmtqueue_bck.dll"
   
   /**
   * This is just a dummy function
   */
   int sayHallo();
   
   /**
   * It initializes working environment. It executes only once when attached to any MT chart the FIRST TIME ONLY. Other calls are ignored.
   * "loglevel" � Logging level (MSG_FATAL - MSG_DEBUG_DETAIL)
   * "thread_count" � Initial number of working threads. Because each thread may spend time sending remote POST requests 
   * spawning several threads does make sense. Possible range is: 1..MAX_WORK_THREADS
   */
   int initialize(int loglevel, int thread_count);
	 
	/**
	* This is the de-initialization function. It is ignored unless EA or script removed from the LAST chart.
	*/
	int deinitialize();
	
	
	/** 
   * All functions below push data to be picked up by other thread(s) (the number of thread is passed to initialize (�) function). 
   * It IMMEDIATELY returns so main program execution flow is not delayed.
   * This function pushes data to be sent by POST request. When failure happens an attempt is repeated �repeatcount� times. 
   * Then neglecting to the result this message is removed from the queue.
   * 
   * �url� � POST request URL 
   * �data� � Data to be passed to a request as part of �multipart/form-data� request
   * �filename� � The name of the file that is passed as a part of �multipart/form-data� request
   * �repeatcount� � Repeat attempts on failure
   */

	int pushRemoveImmediately(string url, string data, string filename, int repeatcount);
   
   
   /** 
   * This function pushes data to be sent by POST request. When failure happens an attempt is repeated �repeatcount� times. 
   * Then neglecting to the result this message is pushed to the of the queue again.
   *
   * �url� � POST request URL 
   * �data� � Data to be passed to a request as part of �multipart/form-data� request
   * �filename� � The name of the file that is passed as a part of �multipart/form-data� request
   * �repeatcount� � Repeat attempts on failure
   */
   int pushNeverRemove(string url, string data, string filename,int repeatcount);
   
   
   /** 
   * This function pushes data to be sent by POST request. When failure happens an attempt is repeated �repeatcount� times. 
   * Then this message is pushed to the end of the queue again unless �failNumber� is reached or result of POST request was successfull.
   *
   * �url� � POST request URL 
   * �data� � Data to be passed to a request as part of �multipart/form-data� request
   * �filename� � The name of the file that is passed as a part of �multipart/form-data� request
   * �failNumber� The number of times the system pushes this message to the end of the queue unless POST attempt was successful. 
   * �repeatcount� � Repeat attempts on failure. It is different from �failNumber� parameter. �repeatcount� specifies the number of SEQUENTIAL POST attempts whereas  �failNumber� specifies hoe many times we will re-enqueue failing data.
   */


   int pushRemoveAfterFailsNumber(string url, string data, string filename, int failNumber, int repeatcount);
   
   /** 
   * Standard Mutex over single MT instance - locking
   */
    
   int lock();
	
	/** 
	 Standard Mutex over single MT instance - unlocking
   */
   
	int unlock();
	
	/** 
	 Standard Mutex over single MT instance - trying lock. May return DLL_FUN_CALL_FALSE - lock unsuccessfull or DLL_FUN_CALL_TRUE - lock successfull or
	 * DLL_FUN_CALL_FAILURE - some error happened
   */
   
	int tryLock();
	
#import

//+------------------------------------------------------------------+
//| script program start function                                    |
//+------------------------------------------------------------------+
int start()
{
   sayHallo();
   
   // spawing 6 threads when attached to the first chart
   int threads_count = 6;
   initialize(MSG_INFO, threads_count);
   Print("Initialized, threads number is: ", threads_count);
   
   string url = "<any URL to upload file>";
   
   // if POST call is failed repeating this operation  3 tims
   int repeat_number = 3;
   
   // if call to POST failed after repeat_number (3) attempts, enqueue our message to the end of the queue
   int fail_enqueue_number = 5;
   
   for(int i = 0; i < 4; i++) {
      
      // create file name
      string fname = "dummy_"+i+".txt";
      
      // create file data
      string data  = "...data..." + i + "... data...";
      
      // enqueue data for picking up by POST sender
      pushRemoveAfterFailsNumber(url, data, fname, fail_enqueue_number, repeat_number);
   }
   
   // this is a script so go to the loop until script is stopped
   while(!IsStopped())
   {
   
      // some delay of 0.3 secs
      lock();
      Print("lock obtained...");
      Sleep(300);
      unlock();
      Print("lock released...");
   }

   deinitialize();
   Print("Deinitialized");
   
   return(0);
}
//+------------------------------------------------------------------+