// stdafx.h : include file for standard system include files,
//  or project specific include files that are used frequently, but
//      are changed infrequently
//

#if !defined(AFX_STDAFX_H__4E5301A2_5D95_46C5_99B0_4C0B98AD6238__INCLUDED_)
#define AFX_STDAFX_H__4E5301A2_5D95_46C5_99B0_4C0B98AD6238__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#define WIN32_LEAN_AND_MEAN		// Exclude rarely-used stuff from Windows headers

#pragma warning(disable:4786)

#include <winsock2.h>
#include <windows.h>

#ifdef _DEBUG
#include "../KomodiaInfraLib/DebugHeap.h"
#endif

// TODO: reference additional headers your program requires here

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_STDAFX_H__4E5301A2_5D95_46C5_99B0_4C0B98AD6238__INCLUDED_)
