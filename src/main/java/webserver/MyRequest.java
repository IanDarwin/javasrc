package webserver;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

public class MyRequest implements HttpServletRequest {
	/* Construct a dummy MyRequest */
	public MyRequest() {
	}
	/** Construct a MyRequest for the given input */
	public MyRequest(InputStream s) {
	}

	public String getAuthType() { return null; }
	public String getContextPath() { return null; }
	public javax.servlet.http.Cookie[] getCookies() { return null; }
	public long getDateHeader(String name) { return 0; }
	public String getHeader(String name) { return null; }
	public java.util.Enumeration getHeaderNames() { return null; }
	public java.util.Enumeration getHeaders(String name) { return null; }
	public int getIntHeader(String name) {
		return Integer.parseInt(getHeader(name));
	}
	public String getMethod() { return null; }
	public String getPathInfo() { return null; }
	public String getPathTranslated() { return null; }
	public String getQueryString() { return null; }
	public String getRemoteUser() { return null; }
	public StringBuffer getRequestURL() { 
		return null;
	}
	public String getRequestURI() { 
		return getRequestURL().toString();
	}
	public String getRequestedSessionId() { return null; }
	public String getServletPath() { return null; }
	public javax.servlet.http.HttpSession getSession() { return null; }
	public javax.servlet.http.HttpSession getSession(boolean create) { return null; }
	public java.security.Principal getUserPrincipal() { return null; }
	public boolean isRequestedSessionIdFromCookie() { return false; }
	public boolean isRequestedSessionIdFromURL() { return false; }
	public boolean isRequestedSessionIdFromUrl() { return false; }
	public boolean isRequestedSessionIdValid() { return false; }
	public boolean isUserInRole(String user) { return false; }
	protected String enc = "iso-8859-1";
	public String getCharacterEncoding() { return enc; }
	public void setCharacterEncoding(String nenc) { enc = nenc; }
	public int getContentLength() { return 0; }
	public String getContentType() { return "text/html"; }
	public javax.servlet.ServletInputStream getInputStream() { return null; }
	public java.util.Locale getLocale() { return null; }
	public java.util.Enumeration getLocales() { return null; }
	public String getParameter(String s1) { return null; }
	public java.util.Enumeration getParameterNames() { return null; }
	public java.util.Map getParameterMap() { return null; }
	public String[] getParameterValues(String s1) { return null; }
	public String getProtocol() { return "http"; }
	public java.io.BufferedReader getReader() { return null; }
	public String getRealPath(String s1) { return null; }
	public String getRemoteAddr() { return null; }
	public String getRemoteHost() { return null; }
	public javax.servlet.RequestDispatcher getRequestDispatcher(String req) {
		return null;
	}
	public String getScheme() { return "http"; }
	public String getServerName() { return "localhost"; }
	public int getServerPort() { return 80; }
	public boolean isSecure() { return false; }

	public void setAttribute(String s1, Object s2) { }
	public void removeAttribute(String s1) { }
	public Object getAttribute(String s1) { return null; }
	public java.util.Enumeration getAttributeNames() { return null; }
}
