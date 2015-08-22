//#define _USE_32BIT_TIME_T

#include <tchar.h>
#include "quikddewrap.hpp"

namespace QuikConnector {


#define QUIKCONNECTORWRAP "QUIKCONNECTORWRAP" 
#define NAME_HELPER_WINDOW "dummy quik connector dde window"

HINSTANCE DDEQuikServerWrap::hInstance_m = 0;

// ------------------------------
// messages 
#define UWM_SUBSCRIBE_DDE_MSG _T("UWM_SUBSCRIBE_DDE_MSG-{74DEC763-C619-49ef-AAAD-CA2AC3BF9199}")
#define UWM_UNSUBSCRIBE_DDE_MSG _T("UWM_UNSUBSCRIBE_DDE_MSG-{C30832DF-177F-4304-AB88-FE2119E69C69}")


UINT UWM_SUBSCRIBE_DDE;

UINT UWM_UNSUBSCRIBE_DDE;

// ------------------------------

int bootThread (void *ptr)
	{
		// handle run-time exceptions
#ifdef HANDLE_NATIVE_EXCEPTIONS
			Testframe::init();  
#endif

		DDEQuikServerWrap* pThis = (DDEQuikServerWrap*)ptr; 

		try {
			pThis->threadRun();
		}
		catch(CppUtils::Exception& e) {
			LOG(MSG_ERROR, QUIKCONNECTORWRAP, "Fatal exception: " << e.message());
		}
#ifdef HANDLE_NATIVE_EXCEPTIONS
		catch(Testframe::Exception& e) {
			LOG(MSG_ERROR, QUIKCONNECTORWRAP, "Fatal MSVC exception: " << e.message());
		}
#endif
		catch(...) {
			LOG(MSG_ERROR, QUIKCONNECTORWRAP, "Unknown fatal exception");
		}

		// uninitialize DDE if any
		try {
			pThis->getDDEServer().uninitializeServer();
		}
		catch(...) {
			// ignore
		}

		return 42;

	};


// -----------------------------
//helper class
	DDEQuikServerWrap::DDEQuikServerWrap( CppUtils::DDEServerWrapListener& listener, CppUtils::String const& apps, CppUtils::DDEItemList const& itemlist ):
	threadId_m(0),
	listener_m( listener )
{
	apps_m = apps;
	itemList_m = itemlist;

	// initialize thread
	thread_m = new CppUtils::Thread(bootThread, this);

	LOG( MSG_DEBUG, QUIKCONNECTORWRAP, "DDE thread created" );

	if (!wndCreated_m.lock(5000))
		THROW(CppUtils::OperationFailed, "exc_CannotFinishInitialization", "ctx_DDEJavaWrapCtr", "");

	ddeStartedStatus_m = false;
	switchDDEOn();

	if (!ddeStartedStatus_m) {
		
		// deinitialize
		if (!stopThread()) {
			LOG( MSG_ERROR, QUIKCONNECTORWRAP, "Cannot stop thread" );
		}

		thread_m->waitForThread(5);

		// Delete thread
		delete thread_m; 
		thread_m = NULL;
		
		LOG( MSG_DEBUG, QUIKCONNECTORWRAP, "DDE thread terminated, DDE processor destroyed" );

		THROW(CppUtils::OperationFailed, "exc_CannotConnectDDE", "ctx_DDEJavaWrapCtr", "");
	}
		
}

DDEQuikServerWrap::~DDEQuikServerWrap()
{
	if (!wndCreated_m.lock(5000))
		THROW(CppUtils::OperationFailed, "exc_CannotFinishInitialization", "ctx_DDEJavaWrapDtr", "");

	switchDDEOff();

	if (!stopThread()) {
		LOG( MSG_ERROR, QUIKCONNECTORWRAP, "Cannot stop thread" );
	}

	thread_m->waitForThread(5);

	// Delete thread
	delete thread_m;
	thread_m = NULL;
	LOG( MSG_DEBUG, QUIKCONNECTORWRAP, "DDE thread terminated, DDE processor destroyed" );
}


//-------------------------------

void DDEQuikServerWrap::threadRun()
{
	hWnd_m = 0;

	// force to create message queue
	MSG Msg;
	PeekMessage(&Msg, NULL, WM_USER, WM_USER, PM_NOREMOVE);

	// get thread id
	threadId_m = ::GetCurrentThreadId();

	
	// this is a thread for DDE

	hWnd_m = CreateWindow(NAME_HELPER_WINDOW, 
                      NULL, WS_OVERLAPPEDWINDOW, 
                      CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT, 
                      CW_USEDEFAULT, NULL, NULL, hInstance_m,
											this  // create param
											);


	

	if(hWnd_m == NULL) {
		THROW(CppUtils::OperationFailed, "exc_CannotCreateWindow", "ctx_threadRun", CppUtils::getOSError());
  }

	
	LOG( MSG_DEBUG, QUIKCONNECTORWRAP, "Entered message loop" );

	
  while(GetMessage(&Msg, 0, 0, 0)) {
        TranslateMessage(&Msg);
        DispatchMessage(&Msg); 
  } 

		// destroy window
	BOOL dwin = ::DestroyWindow( hWnd_m );

	if (!dwin) {
		LOG( MSG_DEBUG, QUIKCONNECTORWRAP, "Cannot destroy window" );
	}

	LOG( MSG_DEBUG, QUIKCONNECTORWRAP, "Finished message loop" );


}

void DDEQuikServerWrap::setReadyToOperate()
{
	wndCreated_m.signalEvent();
}



void DDEQuikServerWrap::switchDDEOn()
{
	
	SendMessage(hWnd_m, UWM_SUBSCRIBE_DDE, 0, (LPARAM)this);
	
}

void DDEQuikServerWrap::switchDDEOff()
{
	
	SendMessage(hWnd_m, UWM_UNSUBSCRIBE_DDE, 0, (LPARAM)this);
}

bool DDEQuikServerWrap::stopThread()
{
		
	BOOL result = PostThreadMessage(threadId_m, WM_QUIT, 0, (LPARAM)this);

	return result;
}


// -------------------------------------

LRESULT CALLBACK DDEQuikServerWrap::WndProc(HWND hWnd, UINT Msg, WPARAM wParam, LPARAM lParam) {
		try {

			
			if(Msg == UWM_SUBSCRIBE_DDE) {
				

				
				DDEQuikServerWrap* pThis = (DDEQuikServerWrap*)lParam;
				
				pThis->getDDEServer().registerListener( pThis->getListener() );
				pThis->getDDEServer().initServer(pThis->getApps());

				// add necessary topics
				pThis->updateItemsForDDEServer(  true );
				

				pThis->setDDEConnectStatus( true );

				LOG(MSG_INFO, QUIKCONNECTORWRAP, "Performed actual initialization");
        
			}
			else if(Msg == UWM_UNSUBSCRIBE_DDE) {
				DDEQuikServerWrap* pThis = (DDEQuikServerWrap*)lParam;

				// clear listener
				pThis->getDDEServer().clearListener();

				// remove items
				pThis->updateItemsForDDEServer( false );

				// uninitialize
				pThis->getDDEServer().uninitializeServer();
				
				
				LOG(MSG_INFO, QUIKCONNECTORWRAP, "Performed actual de-initialization");
        
			}
			else if (Msg== WM_CREATE) {
				// window created - we are ready to work
				CREATESTRUCT* struct_create = (CREATESTRUCT*)lParam;
				DDEQuikServerWrap* pThis = (DDEQuikServerWrap*)struct_create->lpCreateParams;
				pThis->setReadyToOperate();

				
				
			}
			else 
				return DefWindowProc(hWnd, Msg, wParam, lParam);
			}
		catch(CppUtils::Exception& e) {
			LOG(MSG_ERROR, QUIKCONNECTORWRAP, "Fatal exception in WndProc(): " << e.message());
		}
#ifdef HANDLE_NATIVE_EXCEPTIONS
		catch(Testframe::Exception& e) {
			LOG(MSG_ERROR, QUIKCONNECTORWRAP, "Fatal MSVC exception in WndProc(): " << e.message());
		}
#endif
		catch(...) {
			LOG(MSG_ERROR, QUIKCONNECTORWRAP, "Unknown fatal exception in WndProc() ");
		}


    return 0;
}

// ------------------------------------

void DDEQuikServerWrap::registerWindowClass(HINSTANCE hinstDll)
{
	hInstance_m = hinstDll;

	UWM_SUBSCRIBE_DDE = RegisterWindowMessage(UWM_SUBSCRIBE_DDE_MSG);
	UWM_UNSUBSCRIBE_DDE = RegisterWindowMessage(UWM_UNSUBSCRIBE_DDE_MSG);

	WNDCLASSEX wcex;

	wcex.cbSize = sizeof(WNDCLASSEX); 
	wcex.style			= CS_HREDRAW | CS_VREDRAW;
	wcex.lpfnWndProc	= WndProc;
	wcex.cbClsExtra		= 0;
	wcex.cbWndExtra		= 0;
	wcex.hInstance		= hInstance_m;
	wcex.hIcon			= 0;
	wcex.hCursor		= 0;
	wcex.hbrBackground	= (HBRUSH)(COLOR_WINDOW + 1);
	wcex.lpszMenuName	= 0;
	wcex.lpszClassName	= NAME_HELPER_WINDOW;
	wcex.hIconSm		= 0;

	RegisterClassEx(&wcex);
}

// ----------------------------------

void DDEQuikServerWrap::updateItemsForDDEServer( bool add)
{
	for(int i = 0; i < itemList_m.size(); i++) {
		if (add)
			ddeServerInstance_m.addDDEItem(itemList_m[i]);
		else {
			try {
				ddeServerInstance_m.removeDDEItem(itemList_m[i]);
			}
			catch(CppUtils::OperationFailed& e)
			{
				// ignore
				//LOG(MSG_ERROR, QUIKCONNECTORWRAP, "Exception when removing DDE item: " << e.message());
			}
			catch(...)
			{
				// ignore
				//LOG(MSG_ERROR, QUIKCONNECTORWRAP, "Unknown exception when removing DDE item");
			}
		}
	}

}


};// end of namespace