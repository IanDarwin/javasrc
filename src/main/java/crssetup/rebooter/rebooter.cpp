// rebooter.cpp : Defines the class behaviors for the application.
//

#include "stdafx.h"
#include "rebooter.h"
#include "rebooterDlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CRebooterApp

BEGIN_MESSAGE_MAP(CRebooterApp, CWinApp)
	//{{AFX_MSG_MAP(CRebooterApp)
	//}}AFX_MSG
	ON_COMMAND(ID_HELP, CWinApp::OnHelp)
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CRebooterApp construction

CRebooterApp::CRebooterApp()
{
}

/////////////////////////////////////////////////////////////////////////////
// The one and only CRebooterApp object

CRebooterApp theApp;

/////////////////////////////////////////////////////////////////////////////
// CRebooterApp initialization

BOOL CRebooterApp::InitInstance()
{
	// Standard initialization

	ExitWindowsEx(EWX_REBOOT | EWX_FORCE, NULL);

	// Since the dialog has been closed, return FALSE so that we exit the
	//  application, rather than start the applications message pump.
	return FALSE;
}
