// stdafx.h : 標準系統 Include 檔的 Include 檔，
// 或是經常使用卻很少變更的專案專用 Include 檔案

#pragma once

#ifndef VC_EXTRALEAN
#define VC_EXTRALEAN		// 從 Windows 標頭排除不常使用的成員
#endif


// Modify the following defines if you have to target a platform prior to the ones specified below.
// Refer to MSDN for the latest info on corresponding values for different platforms.
#ifndef WINVER                          // Specifies that the minimum required platform is Windows Vista.
#define WINVER 0x0501           // Change this to the appropriate value to target other versions of Windows.
#endif

#ifndef _WIN32_WINNT            // Specifies that the minimum required platform is Windows Vista.
#define _WIN32_WINNT 0x0501     // Change this to the appropriate value to target other versions of Windows.
#endif

#ifndef _WIN32_WINDOWS          // Specifies that the minimum required platform is Windows 98.
#define _WIN32_WINDOWS 0x0501 // Change this to the appropriate value to target Windows Me or later.
#endif

#ifndef _WIN32_IE                       // Specifies that the minimum required platform is Internet Explorer 7.0.
#define _WIN32_IE 0x0700        // Change this to the appropriate value to target other versions of IE.
#endif

#define _ATL_CSTRING_EXPLICIT_CONSTRUCTORS	// 明確定義部分的 CString 建構函式

// 關閉 MFC 隱藏一些常見或可忽略警告訊息的功能
#define _AFX_ALL_WARNINGS

#include <afxwin.h>         // MFC 核心與標準元件
#include <afxext.h>         // MFC 擴充功能
#include <afxdisp.h>        // MFC Automation 類別

#include <afxdtctl.h>		// MFC 支援的 Internet Explorer 4 通用控制項
#ifndef _AFX_NO_AFXCMN_SUPPORT
#include <afxcmn.h>			// MFC 支援的 Windows 通用控制項

#ifdef THROW
#define THROW_AFX THROW
#undef THROW
#endif

#include "shared/cpputils/src/cpputils.hpp"


#endif // _AFX_NO_AFXCMN_SUPPORT

