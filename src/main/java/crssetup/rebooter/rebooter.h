// rebooter.h : main header file for the REBOOTER application
//

#if !defined(AFX_REBOOTER_H__966A9A44_56F5_11D1_B533_0000E8CE08E1__INCLUDED_)
#define AFX_REBOOTER_H__966A9A44_56F5_11D1_B533_0000E8CE08E1__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000

#ifndef __AFXWIN_H__
	#error include stdafx.h before including this file for PCH
#endif

#include "resource.h"		// main symbols

/////////////////////////////////////////////////////////////////////////////
// CRebooterApp:
// See rebooter.cpp for the implementation of this class
//

class CRebooterApp : public CWinApp
{
public:
	CRebooterApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CRebooterApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

	//{{AFX_MSG(CRebooterApp)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Developer Studio will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_REBOOTER_H__966A9A44_56F5_11D1_B533_0000E8CE08E1__INCLUDED_)
